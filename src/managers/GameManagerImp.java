package managers;

import exceptions.WordLoadException;
import interfaces.*;

import render.HangmanDraw;
import resources.sound.SoundResources;

import java.util.ArrayList;
import java.util.List;

public class GameManagerImp implements GameManager {
    private static final int MAX_ATTEMPTS = HangmanDraw.values().length;

    private int attemptNumber = 0;
    private String hiddenWord;
    private final List<Character> hiddenWordState = new ArrayList<>();

    private final WordManager wordManager;
    private final HangmanRenderer hangmanRenderer;
    private final InputReader inputReader;
    private final SoundPlayer soundPlayer;

    public GameManagerImp(
            WordManager wordManager,
            HangmanRenderer hangmanRenderer,
            InputReader inputReader,
            SoundPlayer soundPlayer
    ) {
        this.wordManager = wordManager;
        this.hangmanRenderer = hangmanRenderer;
        this.inputReader = inputReader;
        this.soundPlayer = soundPlayer;
    }

    public void start() {
        boolean playAgain = true;
        while (playAgain) {
            printWelcome();
            playRound();
            playAgain = askPlayAgain();
            if (playAgain) reset();
        }
    }

    private void reset() {
        hiddenWord = "";
        hiddenWordState.clear();
        attemptNumber = 0;
        System.out.print("\033\143");
        System.out.flush();
    }

    private void printWelcome() {
        System.out.println("***************");
        System.out.println("    WELCOME    ");
        System.out.println("***************");
    }

    private void playRound() {
        try {
            setupHiddenWord();

            boolean isWon = false;

            while(attemptNumber < (MAX_ATTEMPTS - 1)) {
                System.out.print("Hidden word:");
                for(Character c : hiddenWordState) {
                    System.out.print(" " + c);
                }

                char userGuess = getUserInput();

                if(hiddenWord.indexOf(userGuess) >= 0) {
                    System.out.println("You are right!");
                    System.out.println();
                    for(int i = 0; i <= hiddenWord.length() - 1; i++) {
                        if(hiddenWord.charAt(i) == userGuess) {
                            hiddenWordState.set(i, userGuess);
                        }
                    }
                    if (!hiddenWordState.contains('_')) {
                        isWon = true;
                        break;
                    }
                } else {
                    attemptNumber++;
                    System.out.println("You are wrong!");
                    System.out.println(hangmanRenderer.render(attemptNumber));
                }
            }

            if (isWon) {
                System.out.println("Congratulations! The hidden word is: " + hiddenWord);
                soundPlayer.play(SoundResources.VICTORY.getPath());
            } else {
                System.out.println("Sorry, you are lost");
                soundPlayer.play(SoundResources.LOSE.getPath());
            }
        } catch (WordLoadException e) {
            System.out.println("error: " + e);
        }
    }

    private boolean askPlayAgain() {
        System.out.print("Do you want to try again?(Y Or N)");
        char userInput = ' ';
        while (userInput != 'Y' && userInput != 'N') {
            userInput = getUserInput();
            userInput = Character.toUpperCase(userInput);
            if (userInput != 'Y' && userInput != 'N') {
                System.out.print("Sorry, wrong letter!");
            }
        }
        switch (userInput) {
            case 'Y' -> {
                System.out.println("You choose to continue");
                return true;
            }
            case 'N' -> {
                System.out.println("You choose not to continue");
                return false;
            }
            default -> {
                System.out.println("Something went wrong!");
                return false;
            }
        }
    }

    private void setupHiddenWord() throws WordLoadException {
        hiddenWord = wordManager.getRandomWord();

        for (int i = 0; i < hiddenWord.length(); i++) {
            hiddenWordState.add('_');
        }
    }

    private char getUserInput() {
        return inputReader.readLetter();
    }
}
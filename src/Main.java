import interfaces.*;
import managers.*;

import render.HangmanRendererImp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final FileLoaderManager fileLoaderManager = new FileLoaderManagerImp();
            final WordManager wordManager = new WordManagerImp(fileLoaderManager);
            final HangmanRenderer hangmanRenderer = new HangmanRendererImp();
            final InputReader inputReader = new InputReaderImp(scanner);
            final SoundPlayer soundPlayer = new WavSoundPlayer();


            GameManager gameManager = new GameManagerImp(wordManager, hangmanRenderer, inputReader, soundPlayer);
            gameManager.start();
        }
    }
}

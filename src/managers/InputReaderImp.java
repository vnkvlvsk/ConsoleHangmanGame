package managers;

import interfaces.InputReader;
import java.util.Scanner;

public class InputReaderImp implements InputReader {

    private final Scanner scanner;

    public InputReaderImp(Scanner scanner) {
        this.scanner = scanner;
    }

    public char readLetter() {
        while (true) {
            System.out.print("\nYour letter: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Please enter a letter.");
                continue;
            }

            char letter = input.toUpperCase().charAt(0);

            if (!Character.isLetter(letter)) {
                System.out.println("Please enter a valid letter.");
                continue;
            }

            return letter;
        }
    }
}
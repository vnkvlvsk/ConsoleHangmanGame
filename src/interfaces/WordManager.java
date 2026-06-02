package interfaces;

import exceptions.WordLoadException;

public interface WordManager {
    String getRandomWord() throws WordLoadException;
}


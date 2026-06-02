package managers;

import exceptions.FileLoadException;
import exceptions.WordLoadException;
import interfaces.WordManager;
import interfaces.FileLoaderManager;
import resources.text.TextResources;

import java.util.List;
import java.util.Random;

public class WordManagerImp implements WordManager {

    private final FileLoaderManager fileLoaderManager;
    private final Random random;

    public WordManagerImp(FileLoaderManager fileLoaderManager) {
        this.fileLoaderManager = fileLoaderManager;
        this.random = new Random();
    }

    public String getRandomWord() throws WordLoadException {
        try {
            List<String> words = fileLoaderManager.loadLines(TextResources.WORDS.getPath());
            return words.get(random.nextInt(words.size()));
        } catch (FileLoadException e) {
            throw new WordLoadException("Failed to load words", e);
        }
    }
}

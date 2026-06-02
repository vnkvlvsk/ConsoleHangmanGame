package managers;
import exceptions.FileLoadException;
import interfaces.FileLoaderManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class FileLoaderManagerImp implements FileLoaderManager {

    public List<String> loadLines(String path) throws FileLoadException {
        InputStream is = getClass().getResourceAsStream(path);
        if (is == null) {
            throw new FileLoadException("Resource not found: " + path);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileLoadException("Failed to read resource: " + path, e);
        }
    }
}


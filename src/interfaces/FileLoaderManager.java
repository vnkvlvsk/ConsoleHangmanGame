package interfaces;

import exceptions.FileLoadException;

import java.util.List;

public interface FileLoaderManager {
    List<String> loadLines(String path) throws FileLoadException;
}

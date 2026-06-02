package resources.text;

public enum TextResources {
    WORDS("/resources/text/Words.txt");

    private final String path;

    TextResources(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
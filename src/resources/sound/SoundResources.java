package resources.sound;

public enum SoundResources {
    LOSE("/resources/sound/lose_sound.wav"),
    VICTORY("/resources/sound/victory_sound.wav");

    private final String path;

    SoundResources(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
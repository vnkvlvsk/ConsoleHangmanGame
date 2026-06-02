package managers;

import interfaces.SoundPlayer;
import resources.sound.SoundResources;

import javax.sound.sampled.*;
import java.io.InputStream;

public class WavSoundPlayer implements SoundPlayer {

    @Override
    public void play(String path) {
        try {
            InputStream is = SoundPlayer.class.getResourceAsStream(path);
            if (is == null) return;

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(is);

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            clip.start();

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
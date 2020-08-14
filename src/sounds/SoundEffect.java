package sounds;

import utils.Util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {

    private Clip clip;
    private File audioFile;
    private AudioInputStream inputStream;

    public SoundEffect(String path) {

    }

    public void setFile(String soundFile) {
        try {
            audioFile = new File(soundFile);
            inputStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            Util.error(e, e.getLocalizedMessage());
            Util.exit(-1);
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }
}

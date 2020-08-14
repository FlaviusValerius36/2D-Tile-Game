package sounds;

import utils.Util;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundClip {

    private AudioInputStream sample;
    private Clip clip;
    private boolean looping = false;
    private int repeat = 0;
    private String filename;

    public SoundClip() {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            Util.error(e, e.getLocalizedMessage());
            Util.exit(-1);
        }
    }

    public SoundClip(String file) {
        this();
        load(file);
    }

    public boolean load(String file) {
        try {
            setFilename(file);
            sample = AudioSystem.getAudioInputStream(getURL(file));
            clip.open(sample);
            return true;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            Util.error(e, e.getLocalizedMessage());
            Util.exit(-1);
            return false;
        }
    }

    public void play() {
        if (!isLoaded()) {
            return;
        }
        clip.setFramePosition(0);

        if (looping) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.loop(repeat);
        }
    }

    public void stop() {
        clip.stop();
    }

    private URL getURL(String file) {
        URL url = null;
        try {
            url = this.getClass().getResource(file);
        } catch (Exception e) {
            Util.error(e, e.getLocalizedMessage());
            Util.exit(-1);
        }
        return url;
    }

    public boolean isLoaded() {
        return (sample != null);
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public boolean isLooping() {
        return looping;
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

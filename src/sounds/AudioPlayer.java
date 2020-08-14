package sounds;

import utils.Util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    private boolean paused = false;
    private boolean playing = false;
    private Clip audioClip;
    private String status;
    private long timePosition;
    private String audioPath;
    private File audioFile;
    private AudioInputStream audioInput;

    public AudioPlayer(String audioPath) {
        this.audioPath = audioPath;
        audioFile = new File(audioPath);
        try {
            audioClip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            Util.error(e, e.getLocalizedMessage());
            Util.exit(-1);
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void stop() {
        if (audioClip.isRunning()) {
            if (playing || paused) {
                paused = false;
                playing = false;
                audioClip.stop();
                audioClip.close();
                setStatus(1);
            }
        }

    }

    public void pause() {
        if (audioClip.isRunning()) {
            if (!paused && playing) {
                paused = true;
                playing = false;
                timePosition = audioClip.getMicrosecondPosition();
                audioClip.stop();
                setStatus(2);
            }
        }
    }

    public void setStatus(int stat) {
        switch (stat) {
            case 0 -> {
                status = "Playing";
            }
            case 1 -> {
                status = "Stopping";
            }
            case 2 -> {
                status = "Pausing";
            }
            case 3 -> {
                status = "Ready";
            }
        }
    }

    public void setFile() {
        if (audioFile.exists()) {
            try {
                audioInput = AudioSystem.getAudioInputStream(audioFile);
                audioClip = AudioSystem.getClip();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                Util.error(e, e.getLocalizedMessage());
                Util.exit(-1);
            }
        } else {
            System.err.println("File doesn't exist!");
            Util.exit(-1);
        }
    }

    public void play() {
        if (!audioClip.isOpen()) {
            try {
                audioClip.open(audioInput);
                setStatus(3);
            } catch (LineUnavailableException | IOException e) {
                Util.error(e, e.getLocalizedMessage());
                Util.exit(-1);
            }
        }
        if (!audioClip.isRunning()) {
            playing = true;
            paused = false;
            setStatus(0);
            audioClip.setFramePosition(0);
            audioClip.start();
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void resume() {
        if (!audioClip.isRunning() && audioClip.isOpen()) {
            if (paused && !playing) {
                paused = false;
                playing = true;
                audioClip.setMicrosecondPosition(timePosition);
                play();
            }

        }
    }

    public void newTrack(String audioFile) {
        try {
            audioInput = AudioSystem.getAudioInputStream(new File(audioFile).getAbsoluteFile());
            audioClip.open(audioInput);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Util.error(ex, "");
            Util.exit(-1);
        }
    }

    public String showStatus() {
        return String.format("Audio is %s", status);
    }
}

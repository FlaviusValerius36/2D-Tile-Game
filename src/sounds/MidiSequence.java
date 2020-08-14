package sounds;

import utils.Util;

import javax.sound.midi.*;
import java.io.IOException;
import java.net.URL;

public class MidiSequence {

    //Primary midi sequencer object
    private Sequencer sequencer;

    //Provide Sequence as a read-only property
    private Sequence song;

    //Filename property is read-only
    private String filename;

    //Looping property for looping continuously
    private boolean looping = false;

    //Repeat property for looping a fixed number of times
    private int repeat = 0;

    public MidiSequence() {
        try {
            // Loading sequencer
            sequencer = MidiSystem.getSequencer();
        } catch (MidiUnavailableException e) {
            Util.error(e, e.getLocalizedMessage());
            Util.exit(-1);
        }
    }

    public MidiSequence(String file) {
        this();
        load(file);
    }

    public boolean load(String file) {
        //Load the midi file into the sequencer
        this.filename = file;
        try {
            song = MidiSystem.getSequence(getURL(file));
            sequencer.setSequence(song);
            sequencer.open();
            return true;
        } catch (InvalidMidiDataException | IOException | MidiUnavailableException e) {
            Util.error(e, e.getLocalizedMessage());
            Util.exit(-1);
            return false;
        }
    }

    public void play() {
        if (!sequencer.isOpen()) {
            return;
        }
        if (looping) {
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        } else {
            sequencer.setLoopCount(repeat);
        }
        sequencer.start();
    }

    public void stop() {
        sequencer.stop();
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
        return (sequencer.isOpen());
    }

    public Sequencer getSequencer() {
        return sequencer;
    }

    public void setSequencer(Sequencer sequencer) {
        this.sequencer = sequencer;
    }

    public Sequence getSong() {
        return song;
    }

    public void setSong(Sequence song) {
        this.song = song;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
}

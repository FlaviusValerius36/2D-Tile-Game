package sounds;

import utils.Util;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;

public class SoundAPI {

    private AudioFormat format;
    private DataLine.Info sourceInfo, targetInfo;
    private SourceDataLine sourceLine;
    private TargetDataLine targetLine;
    private Thread sourceThread, targetThread;
    private ByteArrayOutputStream outputStream;
    private byte[] data;

    public SoundAPI() {
        try {
            format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);

            sourceInfo = new DataLine.Info(SourceDataLine.class, format);
            sourceLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);
            sourceLine.open();

            targetInfo = new DataLine.Info(TargetDataLine.class, format);
            targetLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
            targetLine.open();

            outputStream = new ByteArrayOutputStream();
        } catch (LineUnavailableException e) {
            Util.error(e, e.getLocalizedMessage());
            Util.exit(-1);
        }
    }

    public void record() {
        // Microphone Input?
        targetThread = new Thread() {
            public void run() {
                targetLine.start();
                data = new byte[targetLine.getBufferSize() / 5];
                int readBytes;
                while (true) {
                    // Returns the number of bytes in the buffer
                    readBytes = targetLine.read(data, 0, data.length);
                    outputStream.write(data, 0, readBytes);
                }
            }
        };
        targetThread.start();
        System.out.println("Started Recording...");
    }

    public void stopRecording() {
        Util.SleepThread(5000);
        targetLine.stop();
        targetLine.close();
        System.out.println("Ended Recording");
    }

    public void playback() {
        sourceThread = new Thread() {
            public void run() {
                sourceLine.start();

                while (true) {
                    sourceLine.write(outputStream.toByteArray(), 0, outputStream.size());
                }
            }
        };
        System.out.println("Starting Playback...");
        sourceThread.start();
    }

    public void stopPlayback() {
        Util.SleepThread(5000);
        sourceLine.stop();
        sourceLine.close();
        System.out.println("Ended Playback");
    }
}

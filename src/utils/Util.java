package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Util {


    public static void exit(int exitCode) {
        System.out.close();
        System.exit(exitCode);
    }

    public static void error(Exception e, String message) {
        System.err.println(Arrays.deepToString(e.getStackTrace()));
        System.err.println(message);
    }

    public static void SleepThread(long nanoSec) {
        try {
            Thread.sleep(nanoSec);
        } catch (Exception e) {
            error(e, e.getLocalizedMessage());
            exit(-1);
        }
    }

    public static String loadFileAsString(String file) {
        StringBuilder source = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                source.append(line).append("\n");
            }
            reader.close();
        } catch (IOException ex) {
            error(ex, ex.getLocalizedMessage());
            exit(-1);
        }
        return source.toString();
    }

    public static int parseInt(String number) {
        try {
            if(number instanceof String) {
                return Integer.parseInt(number);
            } else {
                return 0;
            }
        } catch (NumberFormatException ex) {
            error(ex, "Error with String variable number!");
            exit(-1);
            return 0;
        }
    }

    public static BufferedImage loadTexture(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
            return image;
        } catch (IOException e) {
            Util.error(e, "Util method loadTexture() contains error!");
            return null;
        } finally {
            if (image != null) {
                image.flush();
            }
        }
    }

    /**
     * This method is used for closing the window.
     */
    public static void closingWindow() {
        int exit = JOptionPane.showConfirmDialog(null, "Are you sure?", "EXIT", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if(exit == JOptionPane.YES_OPTION) {
            Util.exit(0);
        } else if(exit == JOptionPane.NO_OPTION) {
            return;
        }
    }

    public static Icon setIcon(String filename) {
        try {
            BufferedImage image = ImageIO.read(new File("resources/textures/" + filename));
            return new ImageIcon(image);
        } catch (IOException e) {
            error(e, "Util method setIcon() contains error!");
            exit(-1);
            return null;
        }
    }
}

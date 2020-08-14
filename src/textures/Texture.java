package textures;

import utils.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Texture {
    private static BufferedImage image;
    private static int[] pixels;
    private static int textureHeight;
    // The width of the image
    private int width;
    // The height of the image
    private int height;

    public Texture(String path) {
        FileInputStream in;

        try {
            in = new FileInputStream(new File(path));
            image = ImageIO.read(in);
            setImageWidth(image.getWidth());
            setImageHeight(image.getHeight());
            pixels = image.getRGB(0, 0, getImageWidth(), getImageHeight(), null, 0, getImageWidth());
            image.flush();
            in.close();
        } catch (IOException e) {
            Util.error(e, "Error from method loadTexture in class TextureLoader");
            Util.exit(-1);
        }
    }

    public Image getScaledTexture() {
        return image.getScaledInstance(getImageWidth(), getImageHeight(), BufferedImage.SCALE_SMOOTH);
    }

    public Image crop(int x, int y) {
        return image.getSubimage(x * getImageWidth(), y * getImageHeight(), getImageWidth(), getImageHeight());
    }

    public int[] getPixels() {
        return pixels;
    }

    public BufferedImage getTexture() {
        return image;
    }

    public int getImageWidth() {
        return width;
    }

    public void setImageWidth(int width) {
        if (width > 0) {
            this.width = width;
        }
    }

    public int getImageHeight() {
        return height;
    }

    public void setImageHeight(int height) {
        if (height > 0) {
            this.height = height;
        }
    }
}

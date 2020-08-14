package fonts;

import math3D.vectors.Vector2i;
import textures.Texture;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Font {

    private final BufferedImage FONT_SHEET;
    private final int TILE_SIZE = 64;

    private int width;
    private int height;
    private int letterWidth;
    private int letterHeight;

    public Font(String file) {
        FONT_SHEET = new Texture("resources/fonts/" + file).getTexture();
        width = TILE_SIZE;
        height = TILE_SIZE;
        letterWidth = Objects.requireNonNull(FONT_SHEET).getWidth(null) / width;
        letterHeight = FONT_SHEET.getHeight(null) / height;
    }

    public Font(String file, int width, int height) {
        FONT_SHEET = new Texture("resources/fonts/" + file).getTexture();
        this.width = width;
        this.height = height;
        letterWidth = Objects.requireNonNull(FONT_SHEET).getWidth(null) / width;
        letterHeight = FONT_SHEET.getHeight(null) / height;
    }

    public void render(Graphics2D g2d, String phrase, Vector2i position, int width, int height,
                       int xOffset, int yOffset) {
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) != 32) {
                g2d.drawImage(getLetter(phrase.charAt(i)), position.x, position.y, width, height, null);
            }
            position.x += xOffset;
            position.y += yOffset;
        }
    }

    public BufferedImage getLetter(int x, int y) {
        return FONT_SHEET.getSubimage(x * width, y * height, width, height);
    }

    public BufferedImage getLetter(char letter) {
        int value = letter - 65;
        int x = value % letterWidth;
        int y = value / letterWidth;
        return getLetter(x, y);
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        letterWidth = FONT_SHEET.getWidth() / width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        letterHeight = FONT_SHEET.getHeight() / height;
    }

    public int getLetterWidth() {
        return letterWidth;
    }

    public int getLetterHeight() {
        return letterHeight;
    }

    public BufferedImage getFONT_SHEET() {
        return FONT_SHEET;
    }

    public int getTILE_SIZE() {
        return TILE_SIZE;
    }
}

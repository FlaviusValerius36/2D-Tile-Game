package graphics;

import core.Core;
import entities.Creature;
import inputs.Keyboard;
import inputs.Mouse;
import textures.Texture;
import utils.Handler;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class ImageEntity extends Creature {

    private Image image;
    private AffineTransform at;
    private JFrame frame;
    private Graphics2D g2d;
    private Core loop;

    public ImageEntity(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_ENTITY_WIDTH, DEFAULT_ENTITY_HEIGHT);
        this.g2d = handler.getGraphics2D();
        this.frame = handler.getJFrame();
        setAlive(true);
        image = null;
        at = new AffineTransform();
    }

    public void transform() {
        at.setToIdentity();
        at.translate(x + width() / 2, y + height() / 2);
        at.rotate(Math.toRadians(getFaceAngle()));
        at.translate(-width() / 2, -height() / 2);
    }

    public Image load(String filename) {
        image = new Texture(filename).getTexture();

        while (getImage().getWidth(frame) <= 0) {
            float x = handler.getWidth() / 2 - width() / 2;
            float y = handler.getHeight() / 2 - height() / 2;
            at = AffineTransform.getTranslateInstance(x, y);
        }
        return image;
    }

    public void input(Keyboard key, Mouse mouse) {
        x += velX;
        y += velY;
    }

    public void update() {

    }

    @Override
    public void setStats(float health, float defense, float attack) {
        this.health = health;
        this.defensiveStrength = defense;
        this.attackPower = attack;
    }

    public void render(Graphics2D g2d) {
        if (this.g2d == null) {
            this.g2d = g2d;
        }
        g2d.drawImage(getImage(), at, frame);
    }

    /**
     * This method handles all the code needed for
     * managing memory.
     */
    @Override
    public void cleanUp() {

    }

    public float getCenterX() {
        return x + width() / 2;
    }

    public float getCenterY() {
        return y + height() / 2;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width(), height());
    }

    public int width() {
        if (image != null) {
            return image.getWidth(frame);
        } else {
            return 0;
        }
    }

    public int height() {
        if (image != null) {
            return image.getHeight(frame);
        } else {
            return 0;
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        float x = handler.getWidth() / 2 - width() / 2;
        float y = handler.getHeight() / 2 - height() / 2;
        at = AffineTransform.getTranslateInstance(x, y);
    }

    public AffineTransform getAt() {
        return at;
    }

    public void setAt(AffineTransform at) {
        this.at = at;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

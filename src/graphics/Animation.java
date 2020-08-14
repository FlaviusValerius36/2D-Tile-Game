package graphics;
/**
 * @Author Ian James Fannon
 * @E-mail flaviusvalerius36@gmail.com
 * @Github FlaviusValerius36
 */

import utils.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 *
 */
public class Animation {

    private Handler handler;
    /**
     * The collection of frames
     */
    private BufferedImage[] images;
    /**
     * How fast the animation will go.
     */
    private int frameSpeed;
    /**
     * The total amount of frames
     */
    private int totalFrames;
    /**
     * The current frame
     */
    private int frameIndex = 0;
    /**
     *
     */
    private int frameCount = 0;
    /**
     *
     */
    private long lastTime, timer;


    /**
     * The Animation class constructor which takes a speed and frames
     * array variables
     */
    public Animation(Handler handler, int frameSpeed, BufferedImage[] images) {
        this.handler = handler;
        this.frameSpeed = frameSpeed;
        this.images = new BufferedImage[images.length];

        for(int i = 0; i < images.length; i++) {
            this.images[i] = images[i];
        }
        this.totalFrames = images.length;
        frameIndex = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void update() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > frameSpeed) {
            frameIndex++;
            timer = 0;

            if(frameIndex >= totalFrames) {
                frameIndex = 0;
            }
        }
    }

    public void render(Graphics2D g2d, int x, int y, int width, int height) {
        g2d.drawImage(getCurrentFrame(), x, y, width, height, handler.getJFrame());
    }

    public BufferedImage getCurrentFrame() {
        return images[frameIndex];
    }
}

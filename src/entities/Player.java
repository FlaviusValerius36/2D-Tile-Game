package entities;

import graphics.Animation;
import inputs.Keyboard;
import inputs.Mouse;
import textures.Assets;
import utils.Handler;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Creature {


    private Animation animUp, animDown, animLeft, animRight;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_ENTITY_WIDTH, DEFAULT_ENTITY_HEIGHT);
        setEntityBounds(22, 30, 20, 30);

        animUp = new Animation(handler, 500, Assets.vladWalking_up);
        animDown = new Animation(handler, 500, Assets.vladWalking_down);
        animLeft = new Animation(handler, 500, Assets.vladWalking_left);
        animRight = new Animation(handler, 500, Assets.vladWalking_right);
    }

    /**
     * This method handles all the keyboard and mouse
     * input device code being passed into it.
     * @param key is the Keyboard
     * @param mouse is the Mouse
     */
    @Override
    public void input(Keyboard key, Mouse mouse) {
        key.mapToKey(w_up, KeyEvent.VK_W);
        key.mapToKey(dir_up, KeyEvent.VK_UP);
        key.mapToKey(s_down, KeyEvent.VK_S);
        key.mapToKey(dir_down, KeyEvent.VK_DOWN);
        key.mapToKey(a_left, KeyEvent.VK_A);
        key.mapToKey(dir_left, KeyEvent.VK_LEFT);
        key.mapToKey(d_right, KeyEvent.VK_D);
        key.mapToKey(dir_right, KeyEvent.VK_RIGHT);

        if (w_up.isPressed() || dir_up.isPressed()) {
            up = true;
        } else {
            up = false;
        }
        if (s_down.isPressed() || dir_down.isPressed()) {
            down = true;
        } else {
            down = false;
        }
        if (a_left.isPressed() || dir_left.isPressed()) {
            left = true;
        } else {
            left = false;
        }
        if (d_right.isPressed() || dir_right.isPressed()) {
            right = true;
        } else {
            right = false;
        }
        move();
    }

    /**
     * This method handles all the code used for
     * updating variables from that class.
     */
    @Override
    public void update() {
        animUp.update();
        animDown.update();
        animLeft.update();
        animRight.update();
        updatePosition();
        handler.getCamera().center(this);
    }

    /**
     * This method handles all the graphical code
     * that draws images to the window.
     * @param g is the Graphics object
     */
    @Override
    public void render(Graphics2D g) {
        g.drawImage(getCurrentFrameAnimation(), (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), 128, 128, handler.getJFrame());
        //g.setColor(Color.RED);
        //g.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset()), (int)(y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
    }

    private BufferedImage getCurrentFrameAnimation() {
        if(velX < 0) {
            return animLeft.getCurrentFrame();
        } else if(velX > 0) {
            return animRight.getCurrentFrame();
        } else if(velY < 0) {
            return animUp.getCurrentFrame();
        } else if(velY > 0) {
            return animDown.getCurrentFrame();
        } else {
            return animDown.getCurrentFrame();
        }
    }

    /**
     * This method handles all the code needed for
     * managing memory.
     */
    @Override
    public void cleanUp() {

    }
}

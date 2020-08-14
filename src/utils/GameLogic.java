package utils;

import inputs.Keyboard;
import inputs.Mouse;

import java.awt.*;
import java.io.Serializable;

public interface GameLogic extends Serializable {

    /**
     * The minimum size of the window
     */
    Dimension MIN = new Dimension(800, 600);
    /**
     * The maximum size of the window
     */
    Dimension MAX = new Dimension(1280, 720);
    StringBuilder TITLE = new StringBuilder("2D Tile Based Game");

    /**
     * Used for updating the game.
     *
     * @param elapsedTime
     */
    void update();

    /**
     * Any keyboard and mouse input is performed
     * in this method.
     *
     * @param key   is the class that
     * @param mouse handles all input.
     */
    void input(Keyboard key, Mouse mouse);

    /**
     * Any graphical rendering should be performed
     * in this method using the Graphics2D class.
     *
     * @param g2d is the graphics object
     */
    void render(Graphics2D g2d);

    /**
     * Any memory management should be performed
     * is this method.
     */
    void cleanUp();
}

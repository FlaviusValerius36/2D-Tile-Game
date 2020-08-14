package states;

import inputs.Keyboard;
import inputs.Mouse;
import math3D.vectors.Vector2i;
import utils.Handler;
import fonts.Font;
import java.awt.*;

public class InventoryState extends State {

    private final Font font;

    public InventoryState(Handler handler, StateManager state) {
        super(handler, state, 7);
        font = new Font("ZeldaFont.png", 16, 16);
    }


    /**
     * Used for updating the game.
     *
     * @param elapsedTime
     */
    @Override
    public void update() {

    }

    /**
     * Any keyboard and mouse input is performed
     * in this method.
     *
     * @param key   is the keyboard input
     * @param mouse is the mouse input
     */
    @Override
    public void input(Keyboard key, Mouse mouse) {


    }

    /**
     * Any graphical rendering should be performed
     * in this method using the Graphics2D class.
     *
     * @param g2d is the graphics object
     */
    @Override
    public void render(Graphics2D g2d) {
        font.render(g2d, "Inventory Screen", new Vector2i(MAX.width / 2 - 300, MAX.height / 2 - 150), 80, 80, 50, 0);
    }

    /**
     * Any memory management should be performed
     * is this method.
     */
    @Override
    public void cleanUp() {

    }
}

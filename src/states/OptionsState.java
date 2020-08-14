package states;

import inputs.Keyboard;
import inputs.Mouse;
import utils.Handler;

import java.awt.*;

public class OptionsState extends State {


    public OptionsState(Handler handler, StateManager state) {
        super(handler, state, 2);
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
     * @param mouse
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

    }

    /**
     * Any memory management should be performed
     * is this method.
     */
    @Override
    public void cleanUp() {

    }
}

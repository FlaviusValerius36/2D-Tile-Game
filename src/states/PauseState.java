package states;

import core.Core;
import fonts.Font;
import inputs.GameAction;
import inputs.Keyboard;
import inputs.Mouse;
import math3D.vectors.Vector2i;
import utils.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;


public class PauseState extends State {

    private final GameAction pause = new GameAction("Pause", GameAction.NORMAL);
    public final GameAction menu = new GameAction("Menu", GameAction.NORMAL);
    private final Font font;

    public PauseState(Handler handler, StateManager state) {
        super(handler, state, 4);
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
        key.mapToKey(pause, KeyEvent.VK_P);
        key.mapToKey(menu, KeyEvent.VK_M);

        if ((state.pause == StateManager.getCurrentState()) && pause.isPressed() && Core.isPaused()) {
            Core.setPaused(false);
            state.push(StateManager.PLAY);
        }
        if ((state.play == StateManager.getCurrentState()) && menu.isPressed() && !Core.isMenuUp()) {
            Core.setMenuUp(true);
            state.push(StateManager.MENU);
        }
    }

    /**
     * Any graphical rendering should be performed
     * in this method using the Graphics2D class.
     *
     * @param g2d is the graphics object
     */
    @Override
    public void render(Graphics2D g2d) {
        font.render(g2d, "Game Paused", new Vector2i(handler.getWidth() / 2 - 300, handler.getHeight() / 2 - 150), 96, 96, 50, 0);
    }

    /**
     * Any memory management should be performed
     * is this method.
     */
    @Override
    public void cleanUp() {

    }
}

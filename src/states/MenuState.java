package states;

import core.Core;
import inputs.GameAction;
import inputs.Keyboard;
import inputs.Mouse;
import sounds.AudioPlayer;
import utils.Handler;
import utils.Util;

import java.awt.*;
import java.awt.event.KeyEvent;

import static textures.Assets.menuScreen;

public class MenuState extends State {

    private final transient GameAction menu = new GameAction("Menu", GameAction.NORMAL);
    private final transient GameAction up = new GameAction("Move Up", GameAction.NORMAL);
    private final transient GameAction down = new GameAction("Move Down", GameAction.NORMAL);
    private final transient GameAction select = new GameAction("Select", GameAction.NORMAL);

    private final Rectangle play = new Rectangle(MAX.width / 2 - (menuScreen.getWidth() / 2) + 16,(MAX.height / 2 - menuScreen.getHeight() / 2 - 35) + 11, 171, 38);
    private final Rectangle settings = new Rectangle(MAX.width / 2 - (menuScreen.getWidth() / 2) + 16, (MAX.height / 2 - menuScreen.getHeight() / 2 - 35) + 68, 171, 38);
    private final Rectangle restart = new Rectangle(MAX.width / 2 - (menuScreen.getWidth() / 2) + 16,  (MAX.height / 2 - menuScreen.getHeight() / 2 - 35) + 122, 171, 38);
    private final Rectangle exit = new Rectangle(MAX.width / 2 - (menuScreen.getWidth() / 2) + 16, (MAX.height / 2 - menuScreen.getHeight() / 2 - 35) + 178, 171, 38);
    private final Rectangle back = new Rectangle(MAX.width / 2 - (menuScreen.getWidth() / 2) + 16, (MAX.height / 2 - menuScreen.getHeight() / 2 - 35) + 233, 171, 38);

    private final Rectangle[] optionBoxes = new Rectangle[]{play, settings, restart, exit, back};
    private final Font font;
    private int currentSelection = -1;

    public MenuState(Handler handler, StateManager state) {
        super(handler, state, 6);
        font = new Font("ZeldaFont.png", 16, 16);
    }

    /**
     * Used for updating the game.
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
        key.mapToKey(menu, KeyEvent.VK_M);
        key.mapToKey(up, KeyEvent.VK_UP);
        key.mapToKey(down, KeyEvent.VK_DOWN);
        key.mapToKey(select, KeyEvent.VK_ENTER);

        if ((state.menu == StateManager.getCurrentState()) && menu.isPressed() && Core.isMenuUp()) {
            Core.setMenuUp(false);
            state.push(StateManager.PLAY);
        }

        if (down.isPressed()) {
            currentSelection++;

            if (currentSelection >= optionBoxes.length) {
                currentSelection = 0;
            }
        }
        if (up.isPressed()) {
            currentSelection--;

            if (currentSelection < 0) {
                currentSelection = optionBoxes.length - 1;
            }
        }
        if (select.isPressed()) {
            switch (currentSelection) {
                // Play New Game
                case 0:
                    state.push(StateManager.PLAY);
                    break;
                // Settings
                case 1:
                    state.push(StateManager.SETTINGS);
                    break;
                // Takes to the Start Screen
                case 2:
                    state.push(StateManager.START_STATE);
                    break;
                // Exit Game
                case 3:
                    Util.closingWindow();
                    break;
                // Back to existing game
                case 4:
                    state.push(StateManager.getPreviousState());
                    break;
            }
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
        g2d.setColor(new Color(201, 158, 56, 255));
        g2d.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        g2d.drawImage(menuScreen, (handler.getWidth() / 2 - menuScreen.getWidth() / 2),
                (handler.getHeight() / 2 - menuScreen.getHeight() / 2 - 35),
                menuScreen.getWidth() + 25, menuScreen.getHeight() + 7, null);

        for (int i = 0; i < optionBoxes.length; i++) {
            if (i == currentSelection) {
                g2d.setColor(Color.BLUE);
            } else {
                g2d.setColor(Color.YELLOW);
            }
            g2d.drawRect(optionBoxes[i].x, optionBoxes[i].y, optionBoxes[i].width, optionBoxes[i].height);
        }
    }

    /**
     * Any memory management should be performed
     * is this method.
     */
    @Override
    public void cleanUp() {

    }
}

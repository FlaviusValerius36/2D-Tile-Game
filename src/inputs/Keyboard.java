/*

 */
package inputs;

import core.Core;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Ian Fannon
 */
public class Keyboard implements KeyListener {

    private static final int NUM_KEY_CODES = 600;
    private static final Logger logger = Logger.getLogger(Keyboard.class.getName());

    private final GameAction[] keyActions = new GameAction[NUM_KEY_CODES];
    private final boolean[] keys = new boolean[NUM_KEY_CODES];
    private int absorbs = 0, presses = 0;
    private int keyCode = -1;
    private final Core core;

    /**
     *
     */
    public Keyboard(Core core) {
        this.core = core;
        logger.fine("Input System initialization complete.");
        Arrays.fill(keys, false);
    }

    /**
     * Gets the name of a key code.
     */
    public static String getKeyName(int keyCode) {
        return KeyEvent.getKeyText(keyCode);
    }

    /**
     * @return the numKeyCodes
     */
    public static int getNumKeyCodes() {
        return NUM_KEY_CODES;
    }

    /**
     * Maps a GameAction to a specific key. The key codes are defined in
     * java.awt.KeyEvent. If the key already has a GameAction mapped to it, the new
     * GameAction overwrites it.
     */
    public void mapToKey(GameAction gameAction, int keyCode) {
        if (gameAction.isPressed()) {
            presses++;
        } else {
            absorbs++;
        }
        keyActions[keyCode] = gameAction;
    }

    /**
     * Clears all mapped keys and mouse actions to this GameAction.
     */
    public void clearMap(GameAction gameAction) {
        for (int i = 0; i < keyActions.length; i++) {
            if (keyActions[i] == gameAction) {
                keyActions[i] = null;
            }
        }
        gameAction.reset();
    }

    /**
     * Gets a List of names of the keys actions mapped to this GameAction.
     * Each entry in the List is a String.
     */
    public List<String> getMaps(GameAction gameCode) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < keyActions.length; i++) {
            if (keyActions[i] == gameCode) {
                list.add(getKeyName(i));
            }
        }
        return list;
    }

    /**
     * Resets all GameActions so they appear like they haven't been pressed.
     */
    public void resetAllGameActions() {
        for (GameAction keyAction : keyActions) {
            if (keyAction != null) {
                keyAction.reset();
                presses = 0;
                absorbs = 0;
            }
        }
    }

    private GameAction getKeyAction(KeyEvent e) {
        keyCode = e.getKeyCode();

        if (keyCode < keyActions.length) {
            return keyActions[keyCode];
        } else {
            return null;
        }
    }

    public void cleanUp() {
        for (GameAction keyAction : keyActions) {
            clearMap(keyAction);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        GameAction gameAction = getKeyAction(e);
        System.out.println("Key Pressed - Code: " + keyCode + " | Name: " + getKeyName(keyCode));
        keys[keyCode] = true;

        if(e.getSource() == core.getWindow()) {
            if (gameAction != null) {
                gameAction.press();
                presses = gameAction.getAmount();
            } else {
                presses = 0;
            }
        }
        e.consume();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        GameAction gameAction = getKeyAction(e);
        System.out.println("Key Released - Code: " + keyCode + " | Name: " + getKeyName(keyCode));
        keys[keyCode] = false;

        if(e.getSource() == core.getWindow()) {
            if (gameAction != null) {
                gameAction.release();
                absorbs++;
            } else {
                absorbs = 0;
            }
        }
        e.consume();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    /**
     * @return the absorbs
     */
    public int getAbsorbs() {
        return absorbs;
    }

    /**
     * @param absorbs the absorbs to set
     */
    public void setAbsorbs(int absorbs) {
        this.absorbs = absorbs;
    }

    /**
     * @return the keyCode
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * @param keyCode the keyCode to set
     */
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * @return the keyActions
     */
    public GameAction[] getKeyActions() {
        return keyActions;
    }

    public int getPresses() {
        return presses;
    }
}

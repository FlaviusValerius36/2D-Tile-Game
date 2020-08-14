package inputs;

/**
 * The GameAction class is an abstract to a user-initiated action, like jumping
 * or moving. GameActions can be mapped to keys or the mouse with the
 * InputManager.
 */
public class GameAction {

    /**
     * Normal behavior. The isPressed() method returns true as long as the key is
     * held down.
     */
    public static final int NORMAL = 0;

    /**
     * Initial press behavior. The isPressed() method returns true only after the
     * key is first pressed, and not again until the key is released and pressed
     * again.
     */
    public static final int DETECT_INITIAL_PRESS_ONLY = 1;

    public static final int STATE_RELEASED = 0;
    public static final int STATE_PRESSED = 1;
    public static final int STATE_WAITING_FOR_RELEASE = 2;

    private final boolean recentering = false;
    private String name;
    private int behavior;
    private int amount;
    private int state;

    /**
     * Create a new GameAction with the NORMAL behavior.
     */
    public GameAction(String name) {
        this(name, NORMAL);
    }

    /**
     * Create a new GameAction with the specified behavior.
     */
    public GameAction(String name, int behavior) {
        this.name = name;
        this.behavior = behavior;
        reset();
    }

    /**
     * Gets the name of this GameAction.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Resets this GameAction so that it appears like it hasn't been pressed.
     */
    public void reset() {
        state = STATE_RELEASED;
        amount = 0;
    }

    /**
     * Taps this GameAction. Same as calling press() followed by release().
     */
    public synchronized void tap() {
        press();
        release();
    }

    /**
     * Signals that the key was pressed.
     */
    public synchronized void press() {
        press(1);
    }

    /**
     * Signals that the key was pressed a specified number of times, or that the
     * mouse move a specified distance.
     */
    public synchronized void press(int amount) {
        if (state != STATE_WAITING_FOR_RELEASE) {
            this.amount += amount;
            state = STATE_PRESSED;
        }

    }

    /**
     * Signals that the key was released
     */
    public synchronized void release() {
        state = STATE_RELEASED;
    }

    /**
     * Returns whether the key was pressed or not since last checked.
     */
    public synchronized boolean isPressed() {
        return (getAmount() != 0);
    }

    /**
     * For keys, this is the number of times the key was pressed since it was last
     * checked. For mouse movement, this is the distance moved.
     */
    public synchronized int getAmount() {
        int retVal = amount;
        if (retVal != 0) {
            if (state == STATE_RELEASED) {
                amount = 0;
            } else if (behavior == DETECT_INITIAL_PRESS_ONLY) {
                state = STATE_WAITING_FOR_RELEASE;
                amount = 0;
            }
        }
        return retVal;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the behavior
     */
    public int getBehavior() {
        return behavior;
    }

    /**
     * @param behavior the behavior to set
     */
    public void setBehavior(int behavior) {
        this.behavior = behavior;
    }

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state) {
        this.state = state;
    }
}

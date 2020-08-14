package states;

import inputs.Keyboard;
import inputs.Mouse;
import utils.GameLogic;
import utils.Handler;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StateManager implements GameLogic {

    // Each state of the game is given an integer numeral as its code
    public static final int START_STATE = 1, OPTIONS = 2, PLAY = 3, PAUSE = 4, SETTINGS = 5,
                            MENU = 6,  INVENTORY = 7, BATTLE = 8, GAME_OVER = 9;
    // Each state of the game is given an integer numeral as its code
    private static final Map<Integer, State> states = new HashMap<>();
    private static State currentState = null;
    private static State lastState = null;
    private static int presentState = -1;
    private static int previousState = -1;
    protected final PlayState play;
    protected final MenuState menu;
    protected final PauseState pause;
    protected final GameOverState gameOver;
    protected final BattleState battle;
    protected final InventoryState inventory;
    protected final StartState start;
    protected final SettingsState settings;
    protected final OptionsState options;

    public StateManager(Handler handler) {
        start = new StartState(handler, this);
        options = new OptionsState(handler, this);
        play = new PlayState(handler, this);
        pause = new PauseState(handler, this);
        settings = new SettingsState(handler, this);
        menu = new MenuState(handler, this);
        inventory = new InventoryState(handler, this);
        battle = new BattleState(handler, this);
        gameOver = new GameOverState(handler, this);
    }

    public void push(int state) {
        if (state < 10 && state > 0) {
            previousState = presentState;
            presentState = state;

            switch(state) {
                case 1:
                    if(lastState != null) {
                        remove(lastState.id, lastState);
                    }
                    setState(start);
                    break;
                case 2:
                    if(lastState != null) {
                        remove(lastState.id, lastState);
                    }
                    setState(options);
                    break;
                case 3:
                    if(lastState != null) {
                        remove(lastState.id, lastState);
                    }
                    setState(play);
                    break;
                case 4:
                    if(lastState != null) {
                        remove(lastState.id, lastState);
                    }
                    setState(pause);
                    break;
                case 5:
                    if(lastState != null) {
                        remove(lastState.id, lastState);
                    }
                    setState(settings);
                    break;
                case 6:
                    if(lastState != null) {
                        remove(lastState.id, lastState);
                    }
                    setState(menu);
                    break;
                case 7:
                    if(lastState != null) {
                        remove(lastState.id, lastState);
                    }
                    setState(inventory);
                    break;
                case 8:
                    if(lastState != null) {
                        remove(lastState.id, lastState);
                    }
                    setState(battle);
                    break;
                case 9:
                    if(lastState != null) {
                        remove(lastState.id, lastState);
                    }
                    setState(gameOver);
                    break;
            }
        }
    }

    public void remove(int key, State state) {
        state.setActive(false);
        states.remove(key, state);
    }

    /**
     * Used for updating the game.
     */
    @Override
    public void update() {
        if (currentState != null) {
            currentState.update();
        }
    }

    @Override
    public void input(Keyboard key, Mouse mouse) {
        if (currentState != null) {
            currentState.input(key, mouse);
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
        if (currentState != null) {
            currentState.render(g2d);
        }
    }

    /**
     * Any memory management should be performed
     * is this method.
     */
    @Override
    public void cleanUp() {
        states.clear();
    }

    public Map<Integer, State> getStates() {
        return states;
    }

    public void setNowAndThen(int presentState, int previousState) {
        StateManager.previousState = previousState;
        StateManager.presentState = presentState;
    }

    public static int getPresentState() {
        return presentState;
    }

    public static void setPresentState(int presentState) {
        StateManager.presentState = presentState;
    }

    public static int getPreviousState() {
        return previousState;
    }

    public static void setPreviousState(int previousState) {
        StateManager.previousState = previousState;
    }

    public static void setState(State state) {
        lastState = currentState;

        if(lastState != null) {
            lastState.setActive(false);
        }
        currentState = state;
        states.put(currentState.id, currentState);
        currentState.setActive(true);
    }

    public static State getCurrentState() {
        return currentState;
    }

    public static State getLastState() {
        return lastState;
    }

    public PlayState getPlay() {
        return play;
    }

    public MenuState getMenu() {
        return menu;
    }

    public PauseState getPause() {
        return pause;
    }

    public GameOverState getGameOver() {
        return gameOver;
    }

    public BattleState getBattle() {
        return battle;
    }

    public InventoryState getInventory() {
        return inventory;
    }

    public StartState getStart() {
        return start;
    }

    public SettingsState getSettings() {
        return settings;
    }

    public OptionsState getOptions() {
        return options;
    }
}

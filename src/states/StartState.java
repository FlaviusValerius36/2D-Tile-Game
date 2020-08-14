package states;

import inputs.GameAction;
import inputs.Keyboard;
import inputs.Mouse;
import sounds.AudioPlayer;
import textures.Assets;
import utils.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class StartState extends State {

    private final GameAction left = new GameAction("Move Left", GameAction.NORMAL);
    private final GameAction right = new GameAction("Move Right", GameAction.NORMAL);
    private final GameAction select = new GameAction("Select", GameAction.NORMAL);

    private final Rectangle[] optionBoxes = new Rectangle[]{new Rectangle((int) (MAX.width / 4.0f) + 50, (int) (MAX.height / 4.0f), 185, 64),
            new Rectangle((int) (MAX.width / 2.0f) + 70, (int) (MAX.height / 4.0f), 185, 64)};
    private int currentSelection = -1;
    private AudioPlayer startMusic = startMusic = new AudioPlayer("resources/sounds/01_-_Dragon_Warrior_-_NES_-_Overture_March.wav");;

    public StartState(Handler handler, StateManager state) {
        super(handler, state, 1);
        Assets.startMusic.setFile();
        Assets.startMusic.play();
    }

    /**
     * Used for updating the game.
     */
    @Override
    public void update() {
        if (!startMusic.isPlaying() && startMusic.isPaused()) {
            startMusic.resume();
        }
    }

    @Override
    public void input(Keyboard key, Mouse mouse) {
        key.mapToKey(left, KeyEvent.VK_LEFT);
        key.mapToKey(right, KeyEvent.VK_RIGHT);
        key.mapToKey(select, KeyEvent.VK_ENTER);

        // Navigates the selections
        if (right.isPressed()) {
            currentSelection++;

            if (currentSelection >= optionBoxes.length) {
                currentSelection = 0;
            }
        }
        if (left.isPressed()) {
            currentSelection--;

            if (currentSelection < 0) {
                currentSelection = optionBoxes.length - 1;
            }
        }
        // When enter is pressed over a selection
        if (select.isPressed()) {
            switch (currentSelection) {
                case 0:
                    startMusic.stop();
                    state.push(StateManager.PLAY);
                    break;
                case 1:
                    startMusic.stop();
                    state.push(StateManager.OPTIONS);
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
        g2d.drawImage(Assets.startBtn, (int) (MAX.width / 4.0f) + 50, (int) (MAX.height / 4.0f), 185, 64, handler.getJFrame());
        g2d.drawImage(Assets.optionsBtn, (int) (MAX.width / 2.0f) + 70, (int) (MAX.height / 4.0f), 185, 64, handler.getJFrame());
        g2d.drawImage(Assets.celticDragon, (int) (MAX.width / 3.0f) + 50, (int) (MAX.height / 2.7f), 300, 221, handler.getJFrame());

        for (int i = 0; i < optionBoxes.length; i++) {
            if (i == currentSelection) {
                g2d.setColor(Color.ORANGE);
            } else {
                g2d.setColor(Color.GREEN);
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

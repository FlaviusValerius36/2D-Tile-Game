package states;

import core.Core;
import fonts.Font;
import graphics.Camera;
import inputs.GameAction;
import inputs.Keyboard;
import inputs.Mouse;
import math3D.vectors.Vector2i;
import sounds.AudioPlayer;
import textures.Assets;
import tiles.World;
import utils.Handler;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayState extends State {

    public final GameAction pause = new GameAction("Pause", GameAction.NORMAL);
    public final GameAction menu = new GameAction("Menu", GameAction.NORMAL);
    public final GameAction escape = new GameAction("Escape", GameAction.NORMAL);
    public final GameAction select = new GameAction("Enter", GameAction.NORMAL);

    private final Font font;
    private final World map;
    private final Handler handler;
    private final AudioPlayer playMusic = new AudioPlayer("resources/sounds/02_-_Dragon_Warrior_Chateau_Ladutorm.wav");

    public PlayState(Handler handler, StateManager state) {
        super(handler, state, 3);
        this.handler = handler;
        font = new Font("ZeldaFont.png", 16, 16);
        map = new World(handler, "map3.txt");
        handler.setWorld(map);
        handler.getCamera().moveCamera(0, 0);
    }

    /**
     * Used for updating the game.
     */
    @Override
    public void update() {
        if (!playMusic.isPlaying() && playMusic.isPaused()) {
            playMusic.resume();
        }
        map.getPlayer().update();
        handler.getCamera().moveCamera(1, 1);
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
        key.mapToKey(escape, KeyEvent.VK_ESCAPE);
        key.mapToKey(select, KeyEvent.VK_ENTER);

        if(pause.isPressed() || menu.isPressed()) {
            playMusic.pause();
        }
        if ((state.play == StateManager.getCurrentState()) && pause.isPressed() && !Core.isPaused()) {
            Core.setPaused(true);
            state.push(StateManager.PAUSE);
        }
        if ((state.play == StateManager.getCurrentState()) && menu.isPressed() && !Core.isMenuUp()) {
            Core.setMenuUp(true);
            state.push(StateManager.MENU);
        }
        map.getPlayer().input(key, mouse);
    }

    /**
     * Any graphical rendering should be performed
     * in this method using the Graphics2D class.
     *
     * @param g2d is the graphics object
     */
    @Override
    public void render(Graphics2D g2d) {
        font.render(g2d, "Java Game Programming", new Vector2i(100, 100), 32, 32, 16, 0);
        map.render(g2d);
    }

  /** Any memory management should be performed is this method. */
  @Override
  public void cleanUp() {
    map.getPlayer().cleanUp();
  }

    public Font getFont() {
        return font;
    }

    public World getMap() {
        return map;
    }

    public Camera getCamera() {
        return handler.getCamera();
    }

    public Window getWindow() {
        return handler.getWindow();
    }
}

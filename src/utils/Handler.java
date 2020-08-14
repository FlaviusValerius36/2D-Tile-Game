package utils;

import core.Core;
import core.Window;
import entities.Player;
import graphics.Camera;
import inputs.Keyboard;
import inputs.Mouse;
import states.StateManager;
import tiles.World;

import javax.swing.*;
import java.awt.*;

public class Handler {

    private Core core;
    private World world;

    public Handler(Core core) {
        this.core = core;
    }

    public Core getCore() {
        return core;
    }

    public int getWidth() {
        return core.MAX.width;
    }

    public int getHeight() {
        return core.MAX.height;
    }

    public Camera getCamera() {
        return core.getCamera();
    }

    public Window getWindow() {
        return core.getWindow();
    }

    public JFrame getJFrame() {
        return core.getWindow();
    }

    public Graphics2D getGraphics2D() {
        return core.getG2d();
    }

    public Keyboard getKeyboard() {
        return core.getKey();
    }

    public Mouse getMouse() {
        return core.getMouse();
    }

    public StateManager getGameState() {
        return core.getState();
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Player getPlayer() {
        return world.getPlayer();
    }
}

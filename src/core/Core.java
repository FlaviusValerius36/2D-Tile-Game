package core;

import graphics.Camera;
import inputs.Keyboard;
import inputs.Mouse;
import states.StateManager;
import textures.Assets;
import utils.GameLogic;
import utils.Handler;
import utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Core implements GameLogic, Runnable {

    @SuppressWarnings("compatibility:-8336639853390141927")
    private static final long serialVersionUID = 1L;
    /**
     * Conditional variable to tell whether game loop
     * is active or not.
     */
    private static volatile boolean running = false;
    /**
     * Conditional variable to tell whether game play
     * has been temporally halted or not.
     */
    public static volatile boolean paused = false;
    /**
     * Conditional variable to tell whether the player
     * has ended the game or not.
     */
    public static volatile boolean gameOver = false;
    /**
     * Conditional variable to tell whether the menu
     * state has been called up or not.
     */
    public static volatile boolean menuUp = false;
    /**
     * Returns the elapsedTime from the game loop
     */
    public static long delta;
    /**
     * Tracks the amount of time in the pause state.
     */
    public static int pauseTimer = 0;

    private final Window window;
    private final BufferedImage image;
    private final Graphics2D g2d;
    private final Rectangle rect;

    private transient Handler handler;
    private transient Camera camera;
    /**
     * The Keyboard input class
     */
    private transient Keyboard key;
    private transient Mouse mouse;
    private transient Thread thread;
    private transient StateManager state;

    public Core() {
        window = new Window(MIN, MAX, TITLE.toString());
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        rect = new Rectangle(0, 0, getWidth(), getHeight());
    }

    public static void main(String[] args) {
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels= UIManager.getInstalledLookAndFeels();
            for (int idx = 0; idx < installedLookAndFeels.length; idx++) {
                if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
                    UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;
                }
            }
            Core core = new Core();
            core.start();
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
            Util.error(ex, ex.getLocalizedMessage());
            Util.exit(-1);
        }
    }

    /**
     * This method is for starting the game loop by
     * controlling the running variable.
     */
    protected synchronized void start() {
        if(!running) {
            running = true;
            thread = new Thread(this, TITLE.toString());
            System.out.println("Thread Starting..." + thread.getName());

            if(!thread.isAlive()) {
                window.addNotify();
                thread.setDaemon(true);
                thread.start();
            }
        }
    }

    /**
     * This method is for stopping the game loop by
     * controlling the running variable.
     */
    protected synchronized void stop() {
        if(running) {
            running = false;
            try {
                if(thread.isAlive()) {
                    Thread.yield();
                    thread.join(5);
                }
            } catch (InterruptedException e) {
                Util.error(e, e.getLocalizedMessage());
                Util.exit(-1);
            }
        }
    }

    private void init() {
        window.initFrame();
        key = new Keyboard(this);
        mouse = new Mouse(this);
        window.addKeyListener(key);
        window.addMouseListener(mouse);
        window.addMouseWheelListener(mouse);
        window.addMouseMotionListener(mouse);
        window.addFocusListener(mouse);
        window.setDropTarget(new DropTarget(window.getPanel(), mouse));
        window.setFocusTraversalKeysEnabled(false);
        Assets.initCursor();
        Assets.initEntities();
        Assets.initItems();
        Assets.initObjects();
        Assets.initTiles();
        Assets.initUIObjects();
        Assets.initAudio();
        handler = new Handler(this);
        camera = new Camera(handler,0, 0);
        state = new StateManager(handler);
        state.push(StateManager.PLAY);
    }

    public void updatePauseTimer() {
        if (paused) {
            pauseTimer++;

        } else {
            pauseTimer = 0;
        }
    }

    /**
     * This method handles all the keyboard and mouse
     * input device code being passed into it.
     * @param key   is the Keyboard
     * @param mouse is the Mouse
     */
    @Override
    public void input(Keyboard key, Mouse mouse) {
        window.input(key, mouse);

        if (StateManager.getCurrentState() != null) {
            state.input(key, mouse);
        }
    }

    /**
     * This method handles all the code used for
     * updating variables from that class.
     */
    @Override
    public void update() {
        if (StateManager.getCurrentState() != null) {
            state.update();
        }
    }

    /**
     * This method handles all the graphical code
     * that draws images to the window.
     * @param g2d is the Graphics object
     */
    @Override
    public void render(Graphics2D g2d) {
        BufferStrategy bs = window.getBufferStrategy();

        if(bs == null) {
            window.createBufferStrategy(3);
            return;
        }
        do {
            if (g2d != null) {
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                g2d.clearRect(0, 0, getWidth(), getHeight());

                if (StateManager.getCurrentState() != null) {
                    state.render(g2d);
                }
            }
            Graphics graphics = bs.getDrawGraphics();
            graphics.clearRect(0, 0,getWidth(), getHeight());
            // Draws an image that will hold any graphics that the Graphics2D object draws
            graphics.drawImage(image, 0, 0, image.getWidth(window), image.getHeight(window), window);
            bs.show();
            graphics.dispose();
        } while(bs.contentsLost());
    }

    @Override
    public void cleanUp() {

        if (StateManager.getCurrentState() != null) {
            state.cleanUp();
        }
    }

    @Override
    public void run() {
        init();
        final double GAME_HERTZ = 60.0;
        final double TIME_BEFORE_UPDATE = 1_000_000_000 / GAME_HERTZ;
        final int MAX_UPDATE_BEFORE_RENDER = 5;
        long lastUpdateTime = System.nanoTime();
        long lastRenderTime;
        final double TARGET_FPS = 60;
        final double TARGET_TIME_BEFORE_RENDER = 1_000_000_000 / TARGET_FPS;
        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1_000_000_000);
        int lastFrameCount = 0;

        while (running) {
            long now = System.nanoTime();
            int updateCount = 0;
            input(key, mouse);

            while (((now - lastUpdateTime) > TIME_BEFORE_UPDATE) && (updateCount < MAX_UPDATE_BEFORE_RENDER)) {
                delta = now - lastUpdateTime;
                update();
                lastUpdateTime += TIME_BEFORE_UPDATE;
                updateCount++;
            }
            if (now - lastUpdateTime > TIME_BEFORE_UPDATE) {
                lastUpdateTime = now - (long) TIME_BEFORE_UPDATE;
            }
            render(g2d);
            lastRenderTime = now;
            frameCount++;
            int thisSecond = (int) (lastUpdateTime / 1_000_000_000);

            if (thisSecond > lastSecondTime) {
                if (frameCount != lastFrameCount) {
                    System.out.println("NEW SECOND: " + thisSecond + " | FPS: " + frameCount);
                    lastFrameCount = frameCount;
                }
                System.out.println("OLD SECOND: " + lastSecondTime + " | FPS: " + frameCount);
                frameCount = 0;
                lastSecondTime = thisSecond;
            }
            while (now - lastRenderTime < TARGET_TIME_BEFORE_RENDER && now - lastUpdateTime < TIME_BEFORE_UPDATE) {
                Thread.yield();
                Util.SleepThread(5);
                now = System.nanoTime();
            }
        }
        cleanUp();
        stop();
    }

    public static boolean isMenuUp() {
        return menuUp;
    }

    public static void setMenuUp(boolean menuUp) {
        Core.menuUp = menuUp;
    }

    public static boolean isPaused() {
        return paused;
    }

    public static void setPaused(boolean paused) {
        Core.paused = paused;
    }

    /**
     * Conditional variable to tell whether game loop
     * is active or not.
     */
    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        Core.running = running;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        Core.gameOver = gameOver;
    }

    public int getWidth() {
        return MAX.width;
    }

    public int getHeight() {
        return MAX.height;
    }

    public Window getWindow() {
        return window;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Camera getCamera() {
        return camera;
    }

    public Handler getHandler() {
        return handler;
    }

    public Keyboard getKey() {
        return key;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public StateManager getState() {
        return state;
    }
}

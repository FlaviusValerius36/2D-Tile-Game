/*

 */
package inputs;

import core.Core;
import math3D.vectors.Vector2i;
import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Ian Fannon
 */
public class Mouse implements MouseMotionListener, MouseListener, MouseWheelListener, FocusListener, DropTargetListener {

    public static final int NUM_MOUSE_CODES = 9;
    public static final GameAction[] mouseActions = new GameAction[NUM_MOUSE_CODES];
    private static final Logger logger = Logger.getLogger(Mouse.class.getName());
    /**
     * An invisible cursor.
     */
    public static final Cursor INVISIBLE_CURSOR = Toolkit.getDefaultToolkit()
            .createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), new Point(0, 0), "invisible");
    // Public Access Global Variables
    public static final int MOUSE_MOVE_LEFT = 0;
    public static final int MOUSE_MOVE_RIGHT = 1;
    public static final int MOUSE_MOVE_UP = 2;
    public static final int MOUSE_MOVE_DOWN = 3;
    public static final int MOUSE_WHEEL_UP = 4;
    public static final int MOUSE_WHEEL_DOWN = 5;
    public static final int MOUSE_BUTTON_1 = 6;
    public static final int MOUSE_BUTTON_2 = 7;
    public static final int MOUSE_BUTTON_3 = 8;
    // Encapsulated Private Access Global Variables
    private final Vector2i mouseLoc = new Vector2i(-1, -1);
    private final Vector2i mouseEnter = new Vector2i(-1, -1);
    private final Vector2i mouseExit = new Vector2i(-1, -1);
    private final Vector2i mouseClick = new Vector2i(-1, -1);
    private final Vector2i mousePress = new Vector2i(-1, -1);
    private final Vector2i mouseRelease = new Vector2i(-1, -1);
    private final Vector2i mouseDrag = new Vector2i(-1, -1);
    private final Vector2i mouseMove = new Vector2i(-1, -1);
    private final Vector2i centerLoc = new Vector2i(-1, -1);
    private final Vector2i[] mousePos = new Vector2i[]{mouseLoc, mouseEnter, mouseExit, mouseClick, mousePress,
            mouseRelease, mouseDrag, mouseMove, centerLoc};
    private boolean recentering = false;
    private int presses = 0, absorbs = 0;
    private int button = -1;
    private Robot robot;
    private final Core core;

    /**
     *
     */
    public Mouse(Core core) {
        this.core = core;
        logger.fine("Input System initialization complete.");
    }

    /**
     * Gets the mouse code for the button specified in this MouseEvent.
     */
    public static int getMouseButtonCode(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                return MOUSE_BUTTON_1;
            case MouseEvent.BUTTON2:
                return MOUSE_BUTTON_2;
            case MouseEvent.BUTTON3:
                return MOUSE_BUTTON_3;
            default:
                return -1;
        }
    }

    /**
     * Gets the name of a mouse code.
     */
    public static String getMouseName(int mouseCode) {
        switch (mouseCode) {
            case MOUSE_MOVE_LEFT:
                return "Mouse Left";
            case MOUSE_MOVE_RIGHT:
                return "Mouse Right";
            case MOUSE_MOVE_UP:
                return "Mouse Up";
            case MOUSE_MOVE_DOWN:
                return "Mouse Down";
            case MOUSE_WHEEL_UP:
                return "Mouse Wheel Up";
            case MOUSE_WHEEL_DOWN:
                return "Mouse Wheel Down";
            case MOUSE_BUTTON_1:
                return "Mouse Button 1";
            case MOUSE_BUTTON_2:
                return "Mouse Button 2";
            case MOUSE_BUTTON_3:
                return "Mouse Button 3";
            default:
                return "Unknown mouse code " + mouseCode;
        }
    }

    /**
     * Returns whether or not relative mouse mode is on.
     */
    public boolean isRelativeMouseMode() {
        return (robot != null);
    }

    /**
     * Sets whether relative mouse mode is on or not. For relative mouse mode, the
     * mouse is "locked" in the center of the screen, and only the changed in mouse
     * movement is measured. In normal mode, the mouse is free to move about the
     * screen.
     */
    public void setRelativeMouseMode(boolean mode) {
        if (mode == isRelativeMouseMode()) {
            return;
        }
        if (mode) {
            try {
                robot = new Robot();
                mouseLoc.x = core.getWindow().getWidth() / 2;
                mouseLoc.y = core.getWindow().getHeight() / 2;
                recenterMouse();
            } catch (AWTException ex) {
                // couldn't create robot!
                robot = null;
            }
        } else {
            robot = null;
        }
    }

    public void clearMap(GameAction gameAction) {
        for (int i = 0; i < mouseActions.length; i++) {
            if (mouseActions[i] == gameAction) {
                mouseActions[i] = null;
            }
        }
        gameAction.reset();
    }

    public void mapToMouse(GameAction gameAction, int button) {
        if (gameAction.isPressed()) {
            presses++;
        } else {
            absorbs++;
        }
        mouseActions[button] = gameAction;
    }

    /**
     * Resets all GameActions so they appear like they haven't
     * been pressed.
     */
    public void resetAllGameActions() {
        for(GameAction mouseAction : mouseActions) {
            if(mouseAction != null) {
                mouseAction.reset();
            }
        }
    }

    public void update() {
        resetAllGameActions();
    }

    private GameAction getMouseButtonAction(MouseEvent e) {
        int mouseCode = getMouseButtonCode(e);

        if (mouseCode != -1) {
            return mouseActions[mouseCode];
        } else {
            return null;
        }
    }

    /**
     * Uses the Robot class to try to position the mouse in the center of the screen.
     * Note that use of the Robot class may not be available on all platforms.
     */
    private synchronized void recenterMouse() {
        if (robot != null && core.getWindow().isShowing()) {
            centerLoc.x = core.getWindow().getWidth() / 2;
            centerLoc.y = core.getWindow().getHeight() / 2;
            SwingUtilities.convertPointToScreen(new Point(centerLoc.x, centerLoc.y), core.getWindow());
            recentering = true;
            robot.mouseMove(centerLoc.x, centerLoc.y);
        }
    }

    public void cleanUp() {
        for (GameAction mouseAction : mouseActions) {
            clearMap(mouseAction);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked - [X: " + e.getXOnScreen() + "]|[Y: " + e.getYOnScreen() + "]");
        mouseClick.set(e.getX(), e.getY());
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseEnter.set(e.getX(), e.getY());

        if (e.getSource() == core.getWindow()) {
            System.out.println("Mouse Entered - [X: " + e.getXOnScreen() + "]|[Y: " + e.getYOnScreen() + "]");
        }
        e.consume();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseExit.set(e.getX(), e.getY());

        if (e.getSource() == core.getWindow()) {
            System.out.println("Mouse Exited - [X: " + e.getXOnScreen() + "]|[Y: " + e.getYOnScreen() + "]");
        }
        e.consume();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        button = getMouseButtonCode(e);
        mouseLoc.set(e.getX(), e.getY());
        mousePress.set(e.getX(), e.getY());
        GameAction gameAction = getMouseButtonAction(e);

        if (gameAction != null) {
            gameAction.press(button);
            presses = gameAction.getAmount();
        } else {
            presses = 0;
        }
        System.out.println("Mouse Pressed - [X: " + e.getXOnScreen() + "]|[Y: " + e.getYOnScreen() + "]");
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        button = getMouseButtonCode(e);
        mouseRelease.set(e.getX(), e.getY());
        GameAction gameAction = getMouseButtonAction(e);

        if (gameAction != null) {
            gameAction.release();
            absorbs++;
        } else {
            absorbs = 0;
        }
        System.out.println("Mouse Released - [X: " + e.getXOnScreen() + "]|[Y: " + e.getYOnScreen() + "]");
        e.consume();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseLoc.set(e.getX(), e.getY());
        mouseDrag.set(e.getX(), e.getY());
        System.out.println("Mouse Dragged - [X: " + e.getXOnScreen() + "]|[Y: " + e.getYOnScreen() + "]");
        e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseMove.set(e.getX(), e.getY());

        if (recentering && centerLoc.x == e.getX() && centerLoc.y == e.getY()) {
            recentering = false;
        } else {
            int directionX = e.getX() - mouseLoc.x;
            int directionY = e.getY() - mouseLoc.y;
            mouseHelper(MOUSE_MOVE_LEFT, MOUSE_MOVE_RIGHT, directionX);
            mouseHelper(MOUSE_MOVE_UP, MOUSE_MOVE_DOWN, directionY);

            if (isRelativeMouseMode()) {
                recenterMouse();
            }
        }
        mouseLoc.x = e.getX();
        mouseLoc.y = e.getY();
        e.consume();
    }

    /**
     * Invoked when a component gains the keyboard focus.
     *
     * @param e
     */
    @Override
    public void focusGained(FocusEvent e) {

    }

    /**
     * Invoked when a component loses the keyboard focus.
     *
     * @param e
     */
    @Override
    public void focusLost(FocusEvent e) {

    }

    /**
     * Called while a drag operation is ongoing, when the mouse pointer enters
     * the operable part of the drop site for the <code>DropTarget</code>
     * registered with this listener.
     *
     * @param dtde the <code>DropTargetDragEvent</code>
     */
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

    }

    /**
     * Called when a drag operation is ongoing, while the mouse pointer is still
     * over the operable part of the drop site for the <code>DropTarget</code>
     * registered with this listener.
     *
     * @param dtde the <code>DropTargetDragEvent</code>
     */
    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    /**
     * Called if the user has modified
     * the current drop gesture.
     * <p>
     *
     * @param dtde the <code>DropTargetDragEvent</code>
     */
    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    /**
     * Called while a drag operation is ongoing, when the mouse pointer has
     * exited the operable part of the drop site for the
     * <code>DropTarget</code> registered with this listener.
     *
     * @param dte the <code>DropTargetEvent</code>
     */
    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    /**
     * Called when the drag operation has terminated with a drop on
     * the operable part of the drop site for the <code>DropTarget</code>
     * registered with this listener.
     * <p>
     * This method is responsible for undertaking
     * the transfer of the data associated with the
     * gesture. The <code>DropTargetDropEvent</code>
     * provides a means to obtain a <code>Transferable</code>
     * object that represents the data object(s) to
     * be transferred.<P>
     * From this method, the <code>DropTargetListener</code>
     * shall accept or reject the drop via the
     * acceptDrop(int dropAction) or rejectDrop() methods of the
     * <code>DropTargetDropEvent</code> parameter.
     * <p>
     * Subsequent to acceptDrop(), but not before,
     * <code>DropTargetDropEvent</code>'s getTransferable()
     * method may be invoked, and data transfer may be
     * performed via the returned <code>Transferable</code>'s
     * getTransferData() method.
     * <p>
     * At the completion of a drop, an implementation
     * of this method is required to signal the success/failure
     * of the drop by passing an appropriate
     * <code>boolean</code> to the <code>DropTargetDropEvent</code>'s
     * dropComplete(boolean success) method.
     * <p>
     * Note: The data transfer should be completed before the call  to the
     * <code>DropTargetDropEvent</code>'s dropComplete(boolean success) method.
     * After that, a call to the getTransferData() method of the
     * <code>Transferable</code> returned by
     * <code>DropTargetDropEvent.getTransferable()</code> is guaranteed to
     * succeed only if the data transfer is local; that is, only if
     * <code>DropTargetDropEvent.isLocalTransfer()</code> returns
     * <code>true</code>. Otherwise, the behavior of the call is
     * implementation-dependent.
     * <p>
     *
     * @param dtde the <code>DropTargetDropEvent</code>
     */
    @Override
    public void drop(DropTargetDropEvent dtde) {

    }

    public void setCursor(Cursor cursor) {
        core.getWindow().setCursor(cursor);
    }

    public java.util.List<String> getMaps(GameAction gameCode) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < mouseActions.length; i++) {
            if (mouseActions[i] == gameCode) {
                list.add(getMouseName(i));
            }
        }
        return list;
    }

    // from the MouseWheelListener interface
    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseHelper(MOUSE_WHEEL_UP, MOUSE_WHEEL_DOWN, e.getWheelRotation());
    }

    private void mouseHelper(int codeNeg, int codePos, int amount) {
        GameAction gameAction;

        if (amount < 0) {
            gameAction = mouseActions[codeNeg];
        } else {
            gameAction = mouseActions[codePos];
        }
        if (gameAction != null) {
            gameAction.press(Math.abs(amount));
            gameAction.release();
        }
    }

    /**
     * Gets the x position of the mouse.
     */
    public float getMouseX() {
        return mouseLoc.x;
    }

    /**
     * Gets the y position of the mouse.
     */
    public float getMouseY() {
        return mouseLoc.y;
    }

    /**
     * Gets the mouse button that is causing an event
     */
    public int getButton() {
        return button;
    }

    public int getButtonPresses() {
        return presses;
    }

    public int getButtonAbsorbs() {
        return absorbs;
    }
}

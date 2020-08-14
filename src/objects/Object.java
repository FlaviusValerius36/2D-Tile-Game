package objects;

import math3D.vectors.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {

    /**
     * Represents a GameObject that is idle. If the object is
     * idle, it's Transform3D is not updated. By default,
     * GameObjects are initially idle and are changed to the
     * active state when they are initially visible. This
     * behavior can be changed by overriding the notifyVisible()
     * method.
     */
    protected static final int STATE_IDLE = 0;

    /**
     * Represents a GameObject that is active.
     * should no longer be updated or drawn.
     * Once in the STATE_DESTROYED state, the GameObjectManager
     * should remove this object from the list of GameObject
     * it manages.
     */
    protected static final int STATE_ACTIVE = 1;

    /**
     * Represents a GameObject that has been destroyed, and
     * should no longer be updated or drawn.
     * Once in the STATE_DESTROYED state, the GameObjectManager
     * should remove this object from the list of GameObject
     * it manages.
     */
    protected static final int STATE_FOUND = 2;

    private Rectangle bounds;
    private int state;
    private boolean taken;
    private float floorHeight;
    private float ceilHeight;
    private Vector2f position;
    private Dimension size;

    public Object(BufferedImage object, float x, float y, int width, int height) {
        bounds = new Rectangle((int) x, (int) y, width, height);
        position = new Vector2f(x, y);
        size = new Dimension(width, height);
        state = STATE_IDLE;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds.getBounds();
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    /**
     * Gets the floor height set in the setFloorHeight method.
     */
    public float getFloorHeight() {
        return floorHeight;
    }

    /**
     * Method to record the height of the floor that this
     * GameObject is on.
     */
    public void setFloorHeight(float floorHeight) {
        this.floorHeight = floorHeight;
    }

    /**
     * Gets the ceiling height set in the setCeilHeight method.
     */
    public float getCeilHeight() {
        return ceilHeight;
    }

    /**
     * Method to record the height of the ceiling that this
     * GameObject is under.
     */
    public void setCeilHeight(float ceilHeight) {
        this.ceilHeight = ceilHeight;
    }

    /**
     * Sets the state of this object. Should be either
     * STATE_IDLE, STATE_ACTIVE, or STATE_DESTROYED.
     */
    protected void setState(int state) {
        this.state = state;
    }

    /**
     * Sets the state of the specified object. This allows
     * any GameObject to set the state of any other GameObject.
     * The state should be either STATE_IDLE, STATE_ACTIVE, or
     * STATE_DESTROYED.
     */
    protected void setState(Object object, int state) {
        object.setState(state);
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public int getWidth() {
        return size.width;
    }

    public int getHeight() {
        return size.height;
    }

    /**
     * Returns true if this GameObject is idle.
     */
    public boolean isIdle() {
        return (state == STATE_IDLE);
    }

    /**
     * Returns true if this GameObject is active.
     */
    public boolean isActive() {
        return (state == STATE_ACTIVE);
    }

    /**
     * Returns true if this GameObject is destroyed.
     */
    public boolean isFound() {
        return (state == STATE_FOUND);
    }

    /**
     * Notifies this GameObject whether it was visible or not
     * on the last update. By default, if this GameObject is
     * idle and notified as visible, it changes to the active
     * state.
     */
    public void notifyVisible(boolean visible) {
        if (visible && isIdle()) {
            state = STATE_ACTIVE;
        }
    }

    /**
     * Notifies this GameObject that when it moved, it collided
     * with the specified object.  Does nothing by default.
     */
    public void notifyObjectCollision(Object otherObject) {
        // do nothing
    }

    /**
     * Notifies this GameObject that when it moved, it collided
     * with a floor. Does nothing by default.
     */
    public void notifyFloorCollision() {
        // do nothing
    }

    /**
     * Notifies this GameObject that when it moved, it collided
     * with a ceiling. Does nothing by default.
     */
    public void notifyCeilingCollision() {
        // do nothing
    }

    /**
     * Notifies this GameObject that when it moved, it collided
     * with a wall. Does nothing by default.
     */
    public void notifyWallCollision() {
        // do nothing
    }
}

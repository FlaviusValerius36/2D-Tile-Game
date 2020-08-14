package entities;

import core.Core;
import inputs.Keyboard;
import inputs.Mouse;
import math3D.vectors.Vector2f;
import utils.Handler;

import java.awt.*;

public abstract class BaseEntity implements Attributes {

    protected Handler handler;
    /**
     * The position along the X-axis of the
     * coordinate plane
     */
    protected float x;
    /**
     * The position along the Y-axis of the
     * coordinate plane
     */
    protected float y;
    /**
     * The directional movement along the X-axis
     */
    protected float velX;
    /**
     * The directional movement along the Y-axis
     */
    protected float velY;
    /**
     * Move Angle indicates direction entity is moving
     */
    protected float moveAngle;
    /**
     * Face Angle indicates which direction entity is facing
     */
    protected float faceAngle;
    /**
     * The total size of the entity along the x-axis
     */
    protected int width;
    /**
     * The total size of the entity along the y-axis
     */
    protected int height;
    protected Rectangle bounds;
    /**
     * The alive field will tell whether a entity is active or
     * has been killed which is inactive
     */
    protected boolean alive = false;
    protected boolean attacking = false;
    protected boolean defending = false;
    protected boolean running = false;
    protected boolean jumping = false;
    protected boolean hit = false;
    protected boolean up = false;
    protected boolean down = false;
    protected boolean left = false;
    protected boolean right = false;

    public BaseEntity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(0, 0, width, height);
        this.velX = 0.0f;
        this.velY = 0.0f;
    }

    public void move() {
        setVelocity(0, 0);
        if (isUp()) {
            setVelY(getVelY() - ACCELERATION);
            if (getVelY() < -MAX_SPEED) {
                setVelY(-MAX_SPEED);
            }
        } else {
            if (getVelY() < 0) {
                setVelY(getVelY() + DECELERATION);
                if (getVelY() > 0) {
                    setVelY(0);
                }
            }
        }
        if (isDown()) {
            setVelY(getVelY() + ACCELERATION);
            if (getVelY() > MAX_SPEED) {
                setVelY(MAX_SPEED);
            }
        } else {
            if (getVelY() > 0) {
                setVelY(getVelY() - DECELERATION);
                if (getVelY() < 0) {
                    setVelY(0);
                }
            }
        }
        if (isLeft()) {
            setVelX(getVelX() - ACCELERATION);
            if (getVelX() < -MAX_SPEED) {
                setVelX(-MAX_SPEED);
            }
        } else {
            if (getVelX() < 0) {
                setVelX(getVelX() + DECELERATION);
                if (getVelX() > 0) {
                    setVelX(0);
                }
            }
        }
        if (isRight()) {
            setVelX(getVelX() + ACCELERATION);
            if (getVelX() > MAX_SPEED) {
                setVelX(MAX_SPEED);
            }
        } else {
            if (getVelX() > 0) {
                setVelX(getVelX() - DECELERATION);
                if (getVelX() < 0) {
                    setVelX(0);
                }
            }
        }
    }

    /**
     * This method handles all the keyboard and mouse
     * input device code being passed into it.
     * @param key is the Keyboard
     * @param mouse is the Mouse
     */
    public abstract void input(Keyboard key, Mouse mouse);

    /**
     * This method handles all the code used for
     * updating variables from that class.
     */
    public abstract void update();

    /**
     * This method handles all the graphical code
     * that draws images to the window.
     * @param g is the Graphics object
     */
    public abstract void render(Graphics2D g);

    /**
     * This method handles all the code needed for
     * managing memory.
     */
    public abstract void cleanUp();

    public Rectangle getBounds() {
        return bounds;
    }

    public void setEntityBounds(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public void setBounds(Rectangle bounds) {
        this.bounds.setBounds(bounds);
    }

    /**
     * The position along the X-axis of the
     * coordinate plane
     */
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    /**
     * The position along the Y-axis of the
     * coordinate plane
     */
    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    /**
     * Move Angle indicates direction entity is moving
     */
    public float getMoveAngle() {
        return moveAngle;
    }

    public void setMoveAngle(float moveAngle) {
        this.moveAngle = moveAngle;
    }

    /**
     * Face Angle indicates which direction entity is facing
     */
    public float getFaceAngle() {
        return faceAngle;
    }

    public void setFaceAngle(float faceAngle) {
        this.faceAngle = faceAngle;
    }

    /**
     * The alive field will tell whether a entity is active or
     * has been killed which is inactive
     */
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setMain(Handler handler) {
        this.handler = handler;
    }

    /**
     * The directional movement along the X-axis
     */
    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    /**
     * The directional movement along the Y-axis
     */
    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public void setVelocity(float x, float y) {
        setVelX(x);
        setVelY(y);
    }

    /**
     * The total size of the entity along the x-axis
     */
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * The total size of the entity along the y-axis
     */
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public boolean isAttacking() {
        setDefending(false);
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isDefending() {
        setAttacking(false);
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public boolean isRunning() {
        setJumping(false);
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isJumping() {
        setRunning(false);
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}

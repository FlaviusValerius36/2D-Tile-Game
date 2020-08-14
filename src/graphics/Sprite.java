package graphics;

import core.Core;
import math3D.vectors.Vector2f;
import utils.Handler;

import javax.swing.*;
import java.awt.*;

public class Sprite extends Object {

    protected ImageEntity entity;
    protected Vector2f position;
    protected Vector2f velocity;
    protected float rotateRate;
    protected int currentState;
    protected JFrame frame;
    protected Graphics2D g2d;

    public Sprite(Handler handler) {
        this.frame = handler.getJFrame();
        this.g2d = handler.getGraphics2D();
        position = new Vector2f();
        velocity = new Vector2f();
        entity = new ImageEntity(handler, position.x, position.y);
        entity.setAlive(false);
        rotateRate = 0.0f;
        currentState = 0;
    }

    public void load(String filename) {
        entity.load(filename);
    }

    public void transform() {
        entity.setPosition(position.x, position.y);
        entity.transform();
    }

    public void updatePosition() {
        position.x += velocity.x;
        position.y += velocity.y;
    }

    public void updateRotation() {
        setFaceAngle(getFaceAngle() + rotateRate);

        if (getFaceAngle() < 0) {
            setFaceAngle(360 - rotateRate);
        } else if (getFaceAngle() > 360) {
            setFaceAngle(rotateRate);
        }
    }

    public Vector2f center() {
        return new Vector2f(entity.getCenterX(), entity.getCenterY());
    }

    public boolean collidesWith(Rectangle rect) {
        return (rect.intersects(getBounds()));
    }

    public boolean collidesWith(Sprite sprite) {
        return (getBounds().intersects(sprite.getBounds()));
    }

    public boolean collidesWith(Vector2f position) {
        return (getBounds().contains((int) position.x, (int) position.y));
    }

    public ImageEntity getEntity() {
        return entity;
    }

    public void setEntity(ImageEntity entity) {
        this.entity = entity;
    }

    public Image getImage() {
        return entity.getImage();
    }

    public void setImage(Image image) {
        entity.setImage(image);
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public float getRotateRate() {
        return rotateRate;
    }

    public void setRotateRate(float rotateRate) {
        this.rotateRate = rotateRate;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public float getFaceAngle() {
        return entity.getFaceAngle();
    }

    public void setFaceAngle(float angle) {
        entity.setFaceAngle(angle);
    }

    public float getMoveAngle() {
        return entity.getMoveAngle();
    }

    public void setMoveAngle(float angle) {
        entity.setMoveAngle(angle);
    }

    public int imageWidth() {
        return entity.width();
    }

    public int imageHeight() {
        return entity.height();
    }

    public Rectangle getBounds() {
        return entity.getBounds();
    }

    public boolean isAlive() {
        return entity.isAlive();
    }

    public void setAlive(boolean alive) {
        entity.setAlive(alive);
    }
}

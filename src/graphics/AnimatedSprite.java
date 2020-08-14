package graphics;

import math3D.vectors.Vector2f;
import textures.Texture;
import utils.Handler;

import javax.swing.*;
import java.awt.*;

public class AnimatedSprite {

    private Image image;
    private boolean alive;
    private Vector2f position;
    private Vector2f velocity;
    private float rotationRate;
    private int currentState;
    private int currentFrame, totalFrames;
    private int animationDirection;
    private int frameCount, frameDelay;
    private int frameWidth, frameHeight, columns;
    private float moveAngle, faceAngle;
    private JFrame frame;
    private Graphics2D g2d;

    public AnimatedSprite(Handler handler) {
        this.frame = handler.getJFrame();
        this.g2d = handler.getGraphics2D();
        image = null;
        alive = true;
        position = new Vector2f();
        velocity = new Vector2f();
        rotationRate = 0.0f;
        currentState = 0;
        currentFrame = 0;
        totalFrames = 1;
        animationDirection = 1;
        frameCount = 0;
        frameDelay = 0;
        frameWidth = 0;
        frameHeight = 0;
        columns = 1;
        moveAngle = 0.0f;
        faceAngle = 0.0f;
    }

    public void load(String filename, int columns, int totalFrames, int width, int height) {
        image = new Texture(filename).getTexture();

        while(image.getWidth(frame) <= 0);
        this.columns = columns;
        this.totalFrames = totalFrames;
        this.frameWidth = width;
        this.frameHeight = height;
    }

    public void update() {
        // Update Position
        position.x += velocity.x;
        position.y += velocity.y;

        // Update Rotation
        if (rotationRate > 0.0) {
            faceAngle += rotationRate;

            if (faceAngle < 0) {
                faceAngle = 360 - rotationRate;
            } else if (faceAngle > 360) {
                faceAngle = rotationRate;
            }
        }
        // Update Animation
        if (totalFrames > 1) {
            frameCount++;

            if (frameCount > frameDelay) {
                frameCount = 0;
                currentFrame += animationDirection;

                if (currentFrame > totalFrames - 1) {
                    currentFrame = 0;
                } else if (currentFrame < 0) {
                    currentFrame = totalFrames - 1;
                }
            }
        }
    }

    /**
     * Draw bounding rectangle around sprite
     *
     * @param color
     */
    public void drawBounds(Color color) {
        g2d.setColor(color);
        g2d.draw(getBounds());
    }

    public void render(Graphics2D g2d) {
        update();
        // Get the current frame
        int frameX = (currentFrame % columns) * frameWidth;
        int frameY = (currentFrame / columns) * frameHeight;
        // Draw the frame
        g2d.drawImage(image, (int) position.x, (int) position.y, (int) (position.x + frameWidth), (int) (position.y +
                frameHeight), frameX, frameY, (frameX + frameWidth), (frameY + frameHeight), getJFrame());
    }

    public boolean collidesWith(Rectangle rect) {
        return (rect.intersects(getBounds()));
    }

    public boolean collidesWith(AnimatedSprite sprite) {
        return (getBounds().intersects(sprite.getBounds()));
    }

    public boolean collidesWith(Vector2f point) {
        return (getBounds().contains(point.x, point.y));
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getWidth() {
        if (image != null) {
            return image.getWidth(frame);
        } else {
            return 0;
        }
    }

    public int getHeight() {
        if (image != null) {
            return image.getHeight(frame);
        } else {
            return 0;
        }
    }

    public float getCenterX() {
        return position.x + getWidth() / 2;
    }

    public float getCenterY() {
        return position.y + getHeight() / 2;
    }

    public Vector2f getCenter() {
        return new Vector2f(getCenterX(), getCenterY());
    }

    public Rectangle getBounds() {
        return (new Rectangle((int) position.x, (int) position.y, getWidth(), getHeight()));
    }

    public JFrame getJFrame() {
        return frame;
    }

    public Graphics2D getGraphics() {
        return g2d;
    }

    public void setGraphics(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
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

    public float getRotationRate() {
        return rotationRate;
    }

    public void setRotationRate(float rotationRate) {
        this.rotationRate = rotationRate;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public void setTotalFrames(int totalFrames) {
        this.totalFrames = totalFrames;
    }

    public int getAnimationDirection() {
        return animationDirection;
    }

    public void setAnimationDirection(int animationDirection) {
        this.animationDirection = animationDirection;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public int getFrameDelay() {
        return frameDelay;
    }

    public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public float getMoveAngle() {
        return moveAngle;
    }

    public void setMoveAngle(float moveAngle) {
        this.moveAngle = moveAngle;
    }

    public float getFaceAngle() {
        return faceAngle;
    }

    public void setFaceAngle(float faceAngle) {
        this.faceAngle = faceAngle;
    }
}

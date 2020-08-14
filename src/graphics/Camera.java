package graphics;

import core.Core;
import entities.Creature;
import tiles.Tile;
import utils.Handler;

public class Camera {

    public static final float FIELD_OF_VIEW = 70.0f;
    public static final float Z_NEAR = 1.0f;
    public static final float Z_FAR = 1000.0f;

    private Handler handler;
    private float xOffset;
    private float yOffset;
    private float x;
    private float y;

    public Camera(Handler handler, float xOffset, float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void center(Creature creature) {
        xOffset = creature.getX() - handler.getWidth() / 2 + creature.getWidth() / 2;
        yOffset = creature.getY() - handler.getHeight() / 2 + creature.getHeight() / 2;
        checkBlankSpace();
    }

    private void checkBlankSpace() {
        if(xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > handler.getWorld().getWidth() * Tile.TILE_SIZE - handler.getWidth()) {
            xOffset = handler.getWorld().getWidth() * Tile.TILE_SIZE - handler.getWidth();
        }
        if (yOffset < 0) {
            yOffset = 0;
        } else if(yOffset > handler.getWorld().getHeight() * Tile.TILE_SIZE - handler.getHeight()){
            yOffset = handler.getWorld().getHeight() * Tile.TILE_SIZE - handler.getHeight();
        }
    }

    public void moveCamera(float x, float y) {
        xOffset += x;
        yOffset += y;
        checkBlankSpace();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}

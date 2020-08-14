package utils;

import entities.Creature;
import math3D.vectors.Vector2f;

public class AABB {

    private Vector2f position;
    private Vector2f offset;
    private float width;
    private float height;
    private float radius;
    private int size;
    private Creature creature;

    public AABB(Vector2f position, float width, float height, Creature creature) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.creature = creature;
        offset = new Vector2f();
        size = Math.max((int) width, (int) height);
    }

    public AABB(Vector2f position, float radius, Creature creature) {
        this.position = position;
        this.radius = radius;
        offset = new Vector2f();
        this.creature = creature;
        size = (int) radius;
    }

    public boolean boxCollision(AABB box) {
        float x = ((position.getWorldVar().x + (offset.x)) + (width / 2));
        float y = ((position.getWorldVar().y + (offset.y)) + (height / 2));
        float boxX = ((box.position.getWorldVar().x + (box.offset.x / 2)) + (width / 2));
        float boxY = ((box.position.getWorldVar().y + (box.offset.y / 2)) + (height / 2));

        if (Math.abs(x - boxX) < (width / 2) + (box.width / 2)) {
            if (Math.abs(y - boxY) < (height / 2) + (box.height / 2)) {
                return true;
            }
        }
        return false;
    }

    public boolean circleCollision(AABB box) {
        float x = (float) (position.getWorldVar().x + radius / Math.sqrt(2) - creature.getWidth() / Math.sqrt(2));
        float y = (float) (position.getWorldVar().y + radius / Math.sqrt(2) - creature.getHeight() / Math.sqrt(2));
        float xDelta = x - Math.max(box.position.getWorldVar().x + (box.getWidth() / 2), Math.min(x, box.position.getWorldVar().x));
        float yDelta = y - Math.max(box.position.getWorldVar().y + (box.getHeight() / 2), Math.min(y, box.position.getWorldVar().y));

        if ((xDelta * xDelta + yDelta * yDelta) < ((radius / Math.sqrt(2)) * (radius / Math.sqrt(2)))) {
            return true;
        }
        return false;
    }

    public boolean tileCollision(float x, float y, int entityWidth, int entityHeight) {
        for (int c = 0; c < 4; c++) {
            int xt = (int) ((position.x + x) + (c % 2) * width + offset.x) / entityWidth;
            int yt = (int) ((position.y + y) + ((int) (c / 2)) * height + offset.y) / entityHeight;

//            if(TileMapObject.two_blocks.containKey(String.valueOf(xt) + ", " + String.valueOf(yt))) {
//                return TileMapObject.get(String.valueOf(xt) + ", " + String.valueOf(yt)).update(this);
//            }
        }
        return false;
    }

    public void setBox(Vector2f position, float width, float height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public void setCircle(Vector2f position, float radius) {
        this.position = position;
        this.radius = radius;
        size = (int) radius;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Vector2f getOffset() {
        return offset;
    }

    public void setOffset(Vector2f offset) {
        this.offset = offset;
    }

    public void setyOffset(float offset) {
        this.offset.y = offset;
    }

    public void setxOffset(float offset) {
        this.offset.x = offset;
    }
}

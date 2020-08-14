package math3D;

import math3D.vectors.Vector2f;

/**
 * The Transform3D class represents a rotation and translation.
 */
public class Transform {

    protected Vector2f location;
    private float cosAngleX;
    private float sinAngleX;
    private float cosAngleY;
    private float sinAngleY;

    /**
     * Creates a new Transform3D with no translation or rotation.
     */
    public Transform() {
        this(0, 0);
    }

    /**
     * Creates a new Transform3D with the specified translation and no rotation.
     */
    public Transform(float x, float y) {
        location = new Vector2f(x, y);
        setAngle(0, 0);
    }

    /**
     * Creates a new Transform3D
     */
    public Transform(Transform v) {
        location = new Vector2f();
        setTo(v);
    }

    public Object clone() {
        return new Transform(this);
    }

    /**
     * Sets this Transform3D to the specified Transform3D.
     */
    public void setTo(Transform v) {
        location.set(v.location);
        this.cosAngleX = v.cosAngleX;
        this.sinAngleX = v.sinAngleX;
        this.cosAngleY = v.cosAngleY;
        this.sinAngleY = v.sinAngleY;
    }

    /**
     * Gets the location (translation) of this transform.
     */
    public Vector2f getLocation() {
        return location;
    }

    public float getCosAngleX() {
        return cosAngleX;
    }

    public float getSinAngleX() {
        return sinAngleX;
    }

    public float getCosAngleY() {
        return cosAngleY;
    }

    public float getSinAngleY() {
        return sinAngleY;
    }

    public float getAngleX() {
        return (float) Math.atan2(sinAngleX, cosAngleX);
    }

    public void setAngleX(float angleX) {
        cosAngleX = (float) Math.cos(angleX);
        sinAngleX = (float) Math.sin(angleX);
    }

    public float getAngleY() {
        return (float) Math.atan2(sinAngleY, cosAngleY);
    }

    public void setAngleY(float angleY) {
        cosAngleY = (float) Math.cos(angleY);
        sinAngleY = (float) Math.sin(angleY);
    }

    public void setAngle(float angleX, float angleY) {
        setAngleX(angleX);
        setAngleY(angleY);
    }

    public void rotateAngleX(float angle) {
        if (angle != 0) {
            setAngleX(getAngleX() + angle);
        }
    }

    public void rotateAngleY(float angle) {
        if (angle != 0) {
            setAngleY(getAngleY() + angle);
        }
    }

    public void rotateAngle(float angleX, float angleY) {
        rotateAngleX(angleX);
        rotateAngleY(angleY);
    }
}


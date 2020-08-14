package math3D.vectors;

import math3D.internal.MemUtil;
import math3D.internal.Options;

import java.awt.*;
import java.awt.geom.Point2D;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Vector2f {

    public static float worldX, worldY;
    /**
     * The x component of the vector.
     */
    public float x;
    /**
     * The y component of the vector.
     */
    public float y;

    public Vector2f() {
        x = 0.0f;
        y = 0.0f;
    }

    /**
     * Creates a new Vector2f and initialize both of its components
     * with the given value.
     *
     * @param value the value of both components
     */
    public Vector2f(float value) {
        this(value, value);
    }

    /**
     * Creates a new Vector2f and initialize its components to the given values
     *
     * @param x the x component
     * @param y the y component
     */
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new Vector2f and initialize its components to the given vector
     *
     * @param v the Vector2f to copy the values from
     */
    public Vector2f(Vector2f v) {
        this(v.x, v.y);
    }

    /**
     * Creates a new Vector2f and initialize its components to the given vector
     *
     * @param v the Vector2i to copy the values from
     */
    public Vector2f(Vector2i v) {
        this(v.x, v.y);
    }

    public Vector2f(ByteBuffer buffer) {
        this(buffer.position(), buffer);
    }

    public Vector2f(int index, ByteBuffer buffer) {
        MemUtil.INSTANCE.get(this, index, buffer);
    }

    public Vector2f(IntBuffer buffer) {
        this(buffer.position(), buffer);
    }

    public Vector2f(int index, IntBuffer buffer) {
        MemUtil.INSTANCE.get(this, index, buffer);
    }

    public Vector2f(FloatBuffer buffer) {
        this(buffer.position(), buffer);
    }

    public Vector2f(int index, FloatBuffer buffer) {
        MemUtil.INSTANCE.get(this, index, buffer);
    }

    public Vector2f(Point p) {
        this(p.x, p.y);
    }

    public Vector2f(Point2D p) {
        this((int) p.getX(), (int) p.getY());
    }

    public static void setWorldVar(float x, float y) {
        worldX = x;
        worldY = y;
    }

    public static float getWorldVarX(float x) {
        return x - worldX;
    }

    public static float getWorldVarY(float y) {
        return y - worldY;
    }

    /**
     * Get the length squared of a 2-dimensional single-precision vector.
     *
     * @param x The vector's x component
     * @param y The vector's y component
     * @return the length squared of the given vector
     * @author F. Neurath
     */
    public static float lengthSquared(float x, float y) {
        return x * x + y * y;
    }

    /**
     * Get the length of a 2-dimensional single-precision vector.
     *
     * @param x The vector's x component
     * @param y The vector's y component
     * @return the length of the given vector
     * @author F. Neurath
     */
    public static float length(float x, float y) {
        return (float) Math.sqrt(lengthSquared(x, y));
    }

    /**
     * Return the distance between <code>(x1, y1)</code> and <code>(x2, y2)</code>
     *
     * @param x1 the x component of the first vector
     * @param y1 the y component of the first vector
     * @param x2 the x component of the second vector
     * @param y2 the y component of the second vector
     * @return the euclidean distance
     */
    public static float distance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(distanceSquared(x1, y1, x2, y2));
    }

    /**
     * Return the squared distance between <code>(x1, y1)</code> and <code>(x2, y2)</code>.
     *
     * @param x1 the x component of the first vector
     * @param y1 the y component of the first vector
     * @param x2 the x component of the second vector
     * @param y2 the y component of the second vector
     * @return the euclidean distance squared
     */
    public static float distanceSquared(float x1, float y1, float x2, float y2) {
        float dx = x1 - x2;
        float dy = y1 - y2;
        return dx * dx + dy * dy;
    }

    public boolean equals(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2fc#x()
     */
    public float x() {
        return this.x;
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2fc#y()
     */
    public float y() {
        return this.y;
    }

    public Vector2f getWorldVar() {
        return new Vector2f(x - worldX, y - worldY);
    }

    /**
     * Set the x and y components to the supplied value.
     *
     * @param d the value of both components
     * @return this
     */
    public Vector2f set(float d) {
        return set(d, d);
    }

    /**
     * Set the x and y components to the supplied values.
     *
     * @param x the x component
     * @param y the y component
     * @return this
     */
    public Vector2f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Set this {@link Vector2f} to the values of v.
     *
     * @param v the vector to copy from
     * @return this
     */
    public Vector2f set(Vector2f v) {
        return set(v.x(), v.y());
    }

    /**
     * Set this {@link Vector2f} to the values of v.
     *
     * @param v the vector to copy from
     * @return this
     */
    public Vector2f set(Vector2i v) {
        return set(v.x(), v.y());
    }

    public Vector2f set(int index, FloatBuffer b) {
        MemUtil.INSTANCE.get(this, index, b);
        return this;
    }

    public Vector2f set(FloatBuffer b) {
        set(b.position(), b);
        return this;
    }

    public Vector2f set(int index, ByteBuffer b) {
        MemUtil.INSTANCE.get(this, index, b);
        return this;
    }

    public Vector2f set(ByteBuffer b) {
        set(b.position(), b);
        return this;
    }

    public Vector2f set(int index, IntBuffer b) {
        MemUtil.INSTANCE.get(this, index, b);
        return this;
    }

    public Vector2f set(IntBuffer b) {
        set(b.position(), b);
        return this;
    }

    public Vector2f set(Point p) {
        set(p.x, p.y);
        return this;
    }

    public Vector2f set(Point2D p) {
        set((int) p.getX(), (int) p.getY());
        return this;
    }

    public float dot(Vector2f v) {
        return (this.x * v.x + this.y * v.y);
    }

    /**
     * Set the values of this vector by reading 2 float values from off-heap memory,
     * starting at the given address.
     * This method will throw an {@link UnsupportedOperationException} when JOML is used with `-Djoml.nounsafe`.
     * <em>This method is unsafe as it can result in a crash of the JVM process when the specified address range does not belong to this process.</em>
     *
     * @param address the off-heap memory address to read the vector values from
     * @return this
     */
    public Vector2f setFromAddress(long address) {
        if (Options.NO_UNSAFE)
            throw new UnsupportedOperationException("Not supported when using joml.nounsafe");
        MemUtil.MemUtilUnsafe unsafe = (MemUtil.MemUtilUnsafe) MemUtil.INSTANCE;
        unsafe.get(this, address);
        return this;
    }

    public float get(int component) throws IllegalArgumentException {
        return switch (component) {
            case 0 -> x;
            case 1 -> y;
            default -> throw new IllegalArgumentException();
        };
    }

    /**
     * Set the value of the specified component of this vector.
     *
     * @param component the component whose value to set, within <code>[0..1]</code>
     * @param value     the value to set
     * @return this
     * @throws IllegalArgumentException if <code>component</code> is not within <code>[0..1]</code>
     */
    public Vector2f setComponent(int component, float value) throws IllegalArgumentException {
        switch (component) {
            case 0 -> x = value;
            case 1 -> y = value;
            default -> throw new IllegalArgumentException();
        }
        return this;
    }

    public ByteBuffer get(ByteBuffer buffer) {
        return get(buffer.position(), buffer);
    }

    public ByteBuffer get(int index, ByteBuffer buffer) {
        MemUtil.INSTANCE.put(this, index, buffer);
        return buffer;
    }

    public FloatBuffer get(FloatBuffer buffer) {
        return get(buffer.position(), buffer);
    }

    public FloatBuffer get(int index, FloatBuffer buffer) {
        MemUtil.INSTANCE.put(this, index, buffer);
        return buffer;
    }

    public Vector2f getToAddress(long address) {
        if (Options.NO_UNSAFE) {
            throw new UnsupportedOperationException("Not supported when using joml.nounsafe");
        }
        MemUtil.MemUtilUnsafe unsafe = (MemUtil.MemUtilUnsafe) MemUtil.INSTANCE;
        unsafe.put(this, address);
        return this;
    }

    public float angle(Vector2f v) {
        float dot = x * v.x() + y * v.y();
        float det = x * v.y() - y * v.x();
        return (float) Math.atan2(det, dot);
    }

    public float lengthSquared() {
        return lengthSquared(x, y);
    }

    public float length() {
        return (float) Math.sqrt(lengthSquared());
    }

    public float distance(Vector2f v) {
        return distance(v.x(), v.y());
    }

    public float distanceSquared(Vector2f v) {
        return distanceSquared(v.x(), v.y());
    }

    public float distance(float x, float y) {
        float dx = this.x - x;
        float dy = this.y - y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public float distanceSquared(float x, float y) {
        float dx = this.x - x;
        float dy = this.y - y;
        return dx * dx + dy * dy;
    }

    public Vector2f normalize() {
        float norm = 1.0f / length();
        x *= norm;
        y *= norm;
        return this;
    }

    public Vector2f normalize(Vector2f dest) {
        float invLength = (float) (1.0 / Math.sqrt(x * x + y * y));
        dest.x = x * invLength;
        dest.y = y * invLength;
        return dest;
    }

    public Vector2f normalize(float length, Vector2f dest) {
        float invLength = (float) (1.0 / Math.sqrt(x * x + y * y)) * length;
        dest.x = x * invLength;
        dest.y = y * invLength;
        return dest;
    }

    /**
     * Set this vector to be one of its perpendicular vectors.
     *
     * @return this
     */
    public Vector2f perpendicular() {
        return set(y, x * -1);
    }

    public Vector2f add(Vector2f v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vector2f add(Vector2i v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vector2f add(Point v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vector2f add(Point2D v) {
        x += v.getX();
        y += v.getY();
        return this;
    }

    public Vector2f add(Vector2f src, Vector2f dest) {
        return dest.add(src.x, src.y);
    }

    public Vector2f add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2f add(float value) {
        this.x += value;
        this.y += value;
        return this;
    }

    public Vector2f sub(Vector2f v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    public Vector2f sub(Point v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    public Vector2f sub(Point2D v) {
        x -= v.getX();
        y -= v.getY();
        return this;
    }

    public Vector2f sub(Vector2f src, Vector2f dest) {
        return dest.sub(src.x, src.y);
    }

    public Vector2f sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2f sub(float value) {
        this.x -= value;
        this.y -= value;
        return this;
    }

    public Vector2f mul(Vector2f v) {
        x *= v.x;
        y *= v.y;
        return this;
    }

    public Vector2f mul(Point v) {
        x *= v.x;
        y *= v.y;
        return this;
    }

    public Vector2f mul(Point2D v) {
        x *= v.getX();
        y *= v.getY();
        return this;
    }

    public Vector2f mul(Vector2f src, Vector2f dest) {
        return dest.mul(src.x, src.y);
    }

    public Vector2f mul(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vector2f mul(float value) {
        this.x *= value;
        this.y *= value;
        return this;
    }

    public Vector2f div(Vector2f v) {
        x /= v.x;
        y /= v.y;
        return this;
    }

    public Vector2f div(Point v) {
        x /= v.x;
        y /= v.y;
        return this;
    }

    public Vector2f div(Point2D v) {
        x /= v.getX();
        y /= v.getY();
        return this;
    }

    public Vector2f div(Vector2f src, Vector2f dest) {
        return dest.mul(src.x, src.y);
    }

    public Vector2f div(float x, float y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vector2f div(float value) {
        this.x /= value;
        this.y /= value;
        return this;
    }
}



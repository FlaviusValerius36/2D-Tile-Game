package math3D.vectors;

import math3D.internal.MemUtil;

import java.awt.*;
import java.awt.geom.Point2D;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Vector3i {

    /**
     * The x component of the vector.
     */
    public int x;
    /**
     * The y component of the vector.
     */
    public int y;
    /**
     * The z component of the vector.
     */
    public int z;


    public Vector3i() {

    }

    public Vector3i(int d) {
        this(d, d, d);
    }

    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3i(Vector2f v, int z) {
        this((int) v.x, (int) v.y, z);
    }

    public Vector3i(Vector2i v, int z) {
        this(v.x, v.y, z);
    }

    public Vector3i(Vector3i v) {
        this(v.x, v.y, v.z);
    }

    public Vector3i(Vector3f v, int z) {
        this((int) v.x(), (int) v.y(), z);
    }

    public Vector3i(Point p, int z) {
        this(p.x, p.y, z);
    }

    public Vector3i(Point2D p, int z) {
        this((int) p.getX(), (int) p.getY(), z);
    }

    public Vector3i(int index, ByteBuffer buffer) {
        this(buffer.get(index), buffer.get(index + 4), buffer.get(index + 8));
    }

    public Vector3i(ByteBuffer buffer) {
        this(buffer.position(), buffer);
    }

    public Vector3i(int index, IntBuffer buffer) {
        this(buffer.get(index), buffer.get(index + 1), buffer.get(index + 2));
    }

    public Vector3i(IntBuffer buffer) {
        this(buffer.position(), buffer);
    }

    public Vector3i(int index, FloatBuffer buffer) {
        this((int) buffer.get(index), (int) buffer.get(index + 1), (int) buffer.get(index + 2));
    }

    public Vector3i(FloatBuffer buffer) {
        this(buffer.position(), buffer);
    }

    /*
     *
     */
    public int x() {
        return this.x;
    }

    /*
     *
     */
    public int y() {
        return this.y;
    }

    /*
     *
     */
    public int z() {
        return this.z;
    }

    /**
     * Set the x, y and z components to match the supplied vector.
     *
     * @param v contains the values of x, y and z to set
     * @return this
     */
    public Vector3i set(Vector3i v) {
        x = v.x();
        y = v.y();
        z = v.z();
        return this;
    }

    /**
     * Set the first two components from the given <code>v</code> and the z
     * component from the given <code>z</code>
     *
     * @param v the {@link Vector2i} to copy the values from
     * @param z the z component
     * @return this
     */
    public Vector3i set(Vector2i v, int z) {
        return set(v.x(), v.y(), z);
    }

    /**
     * Set the x, y, and z components to the supplied value.
     *
     * @param d the value of all three components
     * @return this
     */
    public Vector3i set(int d) {
        return set(d, d, d);
    }

    /**
     * Set the x, y and z components to the supplied values.
     *
     * @param x the x component
     * @param y the y component
     * @param z the z component
     * @return this
     */
    public Vector3i set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    /**
     * Read this vector from the supplied {@link ByteBuffer} starting at the
     * specified absolute buffer position/index.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     *
     * @param index  the absolute position into the ByteBuffer
     * @param buffer values will be read in <code>x, y, z</code> order
     * @return this
     */
    public Vector3i set(int index, ByteBuffer buffer) {
        MemUtil.INSTANCE.get(this, index, buffer);
        return this;
    }
}

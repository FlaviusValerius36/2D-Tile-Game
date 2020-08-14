package math3D.vectors;

import math3D.internal.MemUtil;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Vector2i implements Externalizable {

    /**
     * The x component of the vector.
     */
    public int x;
    /**
     * The y component of the vector.
     */
    public int y;

    /**
     * Create a new {@link Vector2i} and initialize both of its components with
     * the given value.
     *
     * @param s the value of both components
     */
    public Vector2i(int s) {
        this.x = s;
        this.y = s;
    }

    /**
     * Create a new {@link Vector2i} and initialize its components to the given values.
     *
     * @param x the x component
     * @param y the y component
     */
    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i() {
    }

    /**
     * Create a new {@link Vector2i} and initialize its components to the one of
     * the given vector.
     *
     * @param v the {@link Vector2i} to copy the values from
     */
    public Vector2i(Vector2i v) {
        x = v.x();
        y = v.y();
    }

    /**
     * Create a new {@link Vector2i} and read this vector from the supplied
     * {@link ByteBuffer} at the current buffer
     * {@link ByteBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     * <p>
     * In order to specify the offset into the ByteBuffer at which the vector is
     * read, use {@link #Vector2i(int, ByteBuffer)}, taking the absolute
     * position as parameter.
     *
     * @param buffer values will be read in <code>x, y</code> order
     * @see #Vector2i(int, ByteBuffer)
     */
    public Vector2i(ByteBuffer buffer) {
        this(buffer.position(), buffer);
    }

    /**
     * Create a new {@link Vector2i} and read this vector from the supplied
     * {@link ByteBuffer} starting at the specified absolute buffer
     * position/index.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     *
     * @param index  the absolute position into the ByteBuffer
     * @param buffer values will be read in <code>x, y</code> order
     */
    public Vector2i(int index, ByteBuffer buffer) {
        MemUtil.INSTANCE.get(this, index, buffer);
    }

    /**
     * Create a new {@link Vector2i} and read this vector from the supplied
     * {@link IntBuffer} at the current buffer
     * {@link IntBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given IntBuffer.
     * <p>
     * In order to specify the offset into the IntBuffer at which the vector is
     * read, use {@link #Vector2i(int, IntBuffer)}, taking the absolute position
     * as parameter.
     *
     * @param buffer values will be read in <code>x, y</code> order
     * @see #Vector2i(int, IntBuffer)
     */
    public Vector2i(IntBuffer buffer) {
        this(buffer.position(), buffer);
    }

    /**
     * Create a new {@link Vector2i} and read this vector from the supplied
     * {@link IntBuffer} starting at the specified absolute buffer
     * position/index.
     * <p>
     * This method will not increment the position of the given IntBuffer.
     *
     * @param index  the absolute position into the IntBuffer
     * @param buffer values will be read in <code>x, y</code> order
     */
    public Vector2i(int index, IntBuffer buffer) {
        MemUtil.INSTANCE.get(this, index, buffer);
    }

    // Set Functions

    /**
     * Get the length squared of a 2-dimensional single-precision vector.
     *
     * @param x The vector's x component
     * @param y The vector's y component
     * @return the length squared of the given vector
     */
    public static long lengthSquared(int x, int y) {
        return x * x + y * y;
    }

    /**
     * Get the length of a 2-dimensional single-precision vector.
     *
     * @param x The vector's x component
     * @param y The vector's y component
     * @return the length squared of the given vector
     */
    public static double length(int x, int y) {
        return Math.sqrt(lengthSquared(x, y));
    }

    /**
     * Return the distance between <code>(x1, y1)</code> and <code>(x2, y2)</code>.
     *
     * @param x1 the x component of the first vector
     * @param y1 the y component of the first vector
     * @param x2 the x component of the second vector
     * @param y2 the y component of the second vector
     * @return the euclidean distance
     */
    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(distanceSquared(x1, y1, x2, y2));
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
    public static long distanceSquared(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        return dx * dx + dy * dy;
    }

    /**
     * Set the x and y components to the supplied value.
     *
     * @param s scalar value of both components
     * @return this
     */
    public Vector2i set(int s) {
        return set(s, s);
    }

    /**
     * Set the x and y components to the supplied values.
     *
     * @param x the x component
     * @param y the y component
     * @return this
     */
    public Vector2i set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Set this {@link Vector2i} to the values of v.
     *
     * @param v the vector to copy from
     * @return this
     */
    public Vector2i set(Vector2i v) {
        return set(v.x(), v.y());
    }
    // End Set Functions

    /**
     * Read this vector from the supplied {@link ByteBuffer} at the current
     * buffer {@link ByteBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     * <p>
     * In order to specify the offset into the ByteBuffer at which the vector is
     * read, use {@link #set(int, ByteBuffer)}, taking the absolute position as
     * parameter.
     *
     * @param buffer values will be read in <code>x, y</code> order
     * @return this
     * @see #set(int, ByteBuffer)
     */
    public Vector2i set(ByteBuffer buffer) {
        return set(buffer.position(), buffer);
    }

    /**
     * Read this vector from the supplied {@link ByteBuffer} starting at the
     * specified absolute buffer position/index.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     *
     * @param index  the absolute position into the ByteBuffer
     * @param buffer values will be read in <code>x, y</code> order
     * @return this
     */
    public Vector2i set(int index, ByteBuffer buffer) {
        MemUtil.INSTANCE.get(this, index, buffer);
        return this;
    }

    /**
     * Read this vector from the supplied {@link IntBuffer} at the current
     * buffer {@link IntBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given IntBuffer.
     * <p>
     * In order to specify the offset into the IntBuffer at which the vector is
     * read, use {@link #set(int, IntBuffer)}, taking the absolute position as
     * parameter.
     *
     * @param buffer values will be read in <code>x, y</code> order
     * @return this
     * @see #set(int, IntBuffer)
     */
    public Vector2i set(IntBuffer buffer) {
        return set(buffer.position(), buffer);
    }

    /**
     * Read this vector from the supplied {@link IntBuffer} starting at the
     * specified absolute buffer position/index.
     * <p>
     * This method will not increment the position of the given IntBuffer.
     *
     * @param index  the absolute position into the IntBuffer
     * @param buffer values will be read in <code>x, y</code> order
     * @return this
     */
    public Vector2i set(int index, IntBuffer buffer) {
        MemUtil.INSTANCE.get(this, index, buffer);
        return this;
    }
    // End Get Functions

    // Math Functions

    // Get Functions
    /*
     * @see org.joml.Vector2ic#get(java.nio.ByteBuffer)
     */
    public ByteBuffer get(ByteBuffer buffer) {
        return get(buffer.position(), buffer);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#get(int, java.nio.ByteBuffer)
     */
    public ByteBuffer get(int index, ByteBuffer buffer) {
        MemUtil.INSTANCE.put(this, index, buffer);
        return buffer;
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#get(java.nio.IntBuffer)
     */
    public IntBuffer get(IntBuffer buffer) {
        return get(buffer.position(), buffer);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#get(int, java.nio.IntBuffer)
     */
    public IntBuffer get(int index, IntBuffer buffer) {
        MemUtil.INSTANCE.put(this, index, buffer);
        return buffer;
    }

    /**
     * Subtract the supplied vector from this one and store the result in
     * <code>this</code>.
     *
     * @param v the vector to subtract
     * @return a vector holding the result
     */
    public Vector2i sub(Vector2i v) {
        return sub(v, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#sub(org.joml.Vector2ic, org.joml.Vector2i)
     */
    public Vector2i sub(Vector2i v, Vector2i dest) {
        dest.x = x - v.x;
        dest.y = y - v.y;
        return dest;
    }

    /**
     * Decrement the components of this vector by the given values.
     *
     * @param x the x component to subtract
     * @param y the y component to subtract
     * @return a vector holding the result
     */
    public Vector2i sub(int x, int y) {
        return sub(x, y, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#sub(int, int, org.joml.Vector2i)
     */
    public Vector2i sub(int x, int y, Vector2i dest) {
        dest.x = this.x - x;
        dest.y = this.y - y;
        return dest;
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#lengthSquared()
     */
    public long lengthSquared() {
        return lengthSquared(x, y);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#length()
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#distance(org.joml.Vector2ic)
     */
    public double distance(Vector2i v) {
        return Math.sqrt(distanceSquared(v));
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#distance(int, int)
     */
    public double distance(int x, int y) {
        return Math.sqrt(distanceSquared(x, y));
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#distanceSquared(org.joml.Vector2ic)
     */
    public long distanceSquared(Vector2i v) {
        int dx = this.x - v.x;
        int dy = this.y - v.y;
        return dx * dx + dy * dy;
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#distanceSquared(int, int)
     */
    public long distanceSquared(int x, int y) {
        int dx = this.x - x;
        int dy = this.y - y;
        return dx * dx + dy * dy;
    }

    /**
     * Add <code>v</code> to this vector.
     *
     * @param v the vector to add
     * @return a vector holding the result
     */
    public Vector2i add(Vector2i v) {
        return add(v, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#add(org.joml.Vector2ic, org.joml.Vector2i)
     */
    public Vector2i add(Vector2i v, Vector2i dest) {
        dest.x = x + v.x();
        dest.y = y + v.y();
        return dest;
    }

    /**
     * Increment the components of this vector by the given values.
     *
     * @param x the x component to add
     * @param y the y component to add
     * @return a vector holding the result
     */
    public Vector2i add(int x, int y) {
        return add(x, y, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#add(int, int, org.joml.Vector2i)
     */
    public Vector2i add(int x, int y, Vector2i dest) {
        dest.x = this.x + x;
        dest.y = this.y + y;
        return dest;
    }

    /**
     * Multiply all components of this {@link Vector2i} by the given scalar
     * value.
     *
     * @param scalar the scalar to multiply this vector by
     * @return a vector holding the result
     */
    public Vector2i mul(int scalar) {
        return mul(scalar, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#mul(int, org.joml.Vector2i)
     */
    public Vector2i mul(int scalar, Vector2i dest) {
        dest.x = x * scalar;
        dest.y = y * scalar;
        return dest;
    }

    /**
     * Add the supplied vector by this one.
     *
     * @param v the vector to multiply
     * @return a vector holding the result
     */
    public Vector2i mul(Vector2i v) {
        return mul(v, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#mul(org.joml.Vector2ic, org.joml.Vector2i)
     */
    public Vector2i mul(Vector2i v, Vector2i dest) {
        dest.x = x * v.x();
        dest.y = y * v.y();
        return dest;
    }

    /**
     * Multiply the components of this vector by the given values.
     *
     * @param x the x component to multiply
     * @param y the y component to multiply
     * @return a vector holding the result
     */
    public Vector2i mul(int x, int y) {
        return mul(x, y, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#mul(int, int, org.joml.Vector2i)
     */
    public Vector2i mul(int x, int y, Vector2i dest) {
        dest.x = this.x * x;
        dest.y = this.y * y;
        return dest;
    }

    /* (non-Javadoc)
     * @see org.joml.Vector2ic#equals(int, int)
     */
    public boolean equals(int x, int y) {
        if (this.x != x)
            return false;
        if (this.y != y)
            return false;
        return true;
    }

    /**
     * The object implements the writeExternal method to save its contents
     * by calling the methods of DataOutput for its primitive values or
     * calling the writeObject method of ObjectOutput for objects, strings,
     * and arrays.
     *
     * @param out the stream to write the object to
     * @throws IOException Includes any I/O exceptions that may occur
     * @serialData Overriding methods should use this tag to describe
     * the data layout of this Externalizable object.
     * List the sequence of element types and, if possible,
     * relate the element to a public/protected field and/or
     * method of this Externalizable class.
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(x);
        out.writeInt(y);
    }

    /**
     * The object implements the readExternal method to restore its
     * contents by calling the methods of DataInput for primitive
     * types and readObject for objects, strings and arrays.  The
     * readExternal method must read the values in the same sequence
     * and with the same types as were written by writeExternal.
     *
     * @param in the stream to read data from in order to restore the object
     * @throws IOException            if I/O errors occur
     * @throws ClassNotFoundException If the class for an object being
     *                                restored cannot be found.
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        x = in.readInt();
        y = in.readInt();
    }

    // Mutator and Accessor Methods
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int y() {
        return y;
    }

    public int x() {
        return x;
    }
}

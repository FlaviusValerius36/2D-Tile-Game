package math3D.vectors;

import math3D.Math;
import math3D.internal.MemUtil;
import math3D.internal.Runtime;
import math3D.matrices.Matrix3f;
import math3D.matrices.Quaternionf;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

@SuppressWarnings("oracle.jdeveloper.java.semantic-warning")
public class Vector3f {

    /**
     * The x component of the vector.
     */
    public float x;
    /**
     * The y component of the vector.
     */
    public float y;
    /**
     * The z component of the vector.
     */
    public float z;

    public Vector3f() {
        _x(0);
        _y(0);
        _z(0);
    }

    public Vector3f(float v) {
        _x(v);
        _y(v);
        _z(v);
    }

    public Vector3f(float x, float y, float z) {
        this._x(x);
        this._y(y);
        this._z(z);
    }

    public Vector3f(int x, int y, int z) {
        this._x(x);
        this._y(y);
        this._z(z);
    }

    public Vector3f(Vector3f v) {
        _x(v.x());
        _y(v.y());
        _z(v.z());
    }

    public Vector3f(Vector3i v) {
        this(v.x, v.y, v.z);
    }

    /**
     * Get the length squared of a 3-dimensional single-precision vector.
     *
     * @param x The vector's x component
     * @param y The vector's y component
     * @param z The vector's z component
     * @return the length squared of the given vector
     * @author F. Neurath
     */
    public static float lengthSquared(float x, float y, float z) {
        return x * x + y * y + z * z;
    }

    /**
     * Get the length of a 3-dimensional single-precision vector.
     *
     * @param x The vector's x component
     * @param y The vector's y component
     * @param z The vector's z component
     * @return the length of the given vector
     * @author F. Neurath
     */
    public static float length(float x, float y, float z) {
        return (float) Math.sqrt(lengthSquared(x, y, z));
    }

    /**
     * Set the x, y and z components to match the supplied vector.
     *
     * @param v contains the values of x, y and z to set
     * @return this
     */
    public Vector3f set(Vector3f v) {
        return set(v.x(), v.y(), v.z());
    }

    /**
     * Set the x, y and z components to match the supplied vector.
     *
     * @param v contains the values of x, y and z to set
     * @return this
     */
    public Vector3f set(Vector3i v) {
        return set(v.x(), v.y(), v.z());
    }

    /**
     * Set the first two components from the given <code>v</code>
     * and the z component from the given <code>z</code>
     *
     * @param v the {@link Vector2f} to copy the values from
     * @param z the z component
     * @return this
     */
    public Vector3f set(Vector2f v, float z) {
        return set(v.x(), v.y(), z);
    }

    /**
     * Set the first two components from the given <code>v</code>
     * and the z component from the given <code>z</code>
     *
     * @param v the {@link Vector2i} to copy the values from
     * @param z the z component
     * @return this
     */
    public Vector3f set(Vector2i v, float z) {
        return set(v.x(), v.y(), z);
    }

    /**
     * Set the x, y, and z components to the supplied value.
     *
     * @param d the value of all three components
     * @return this
     */
    public Vector3f set(float d) {
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
    public Vector3f set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    /**
     * Read this vector from the supplied {@link ByteBuffer} at the current
     * buffer {@link ByteBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     * <p>
     * In order to specify the offset into the ByteBuffer at which
     * the vector is read, use {@link #set(int, ByteBuffer)}, taking
     * the absolute position as parameter.
     *
     * @param buffer values will be read in <code>x, y, z</code> order
     * @return this
     * @see #set(int, ByteBuffer)
     */
    public Vector3f set(ByteBuffer buffer) {
        return set(buffer.position(), buffer);
    }

    /**
     * Read this vector from the supplied {@link ByteBuffer} starting at the specified
     * absolute buffer position/index.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     *
     * @param index  the absolute position into the ByteBuffer
     * @param buffer values will be read in <code>x, y, z</code> order
     * @return this
     */
    public Vector3f set(int index, ByteBuffer buffer) {
        MemUtil.INSTANCE.get(this, index, buffer);
        return this;
    }

    /**
     * Read this vector from the supplied {@link FloatBuffer} at the current
     * buffer {@link FloatBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given FloatBuffer.
     * <p>
     * In order to specify the offset into the FloatBuffer at which
     * the vector is read, use {@link #set(int, FloatBuffer)}, taking
     * the absolute position as parameter.
     *
     * @param buffer values will be read in <code>x, y, z</code> order
     * @return this
     * @see #set(int, FloatBuffer)
     */
    public Vector3f set(FloatBuffer buffer) {
        return set(buffer.position(), buffer);
    }

    /**
     * Read this vector from the supplied {@link FloatBuffer} starting at the specified
     * absolute buffer position/index.
     * <p>
     * This method will not increment the position of the given FloatBuffer.
     *
     * @param index  the absolute position into the FloatBuffer
     * @param buffer values will be read in <code>x, y, z</code> order
     * @return this
     */
    public Vector3f set(int index, FloatBuffer buffer) {
        MemUtil.INSTANCE.get(this, index, buffer);
        return this;
    }

    public float get(int component) throws IllegalArgumentException {
        return switch (component) {
            case 0 -> x;
            case 1 -> y;
            case 2 -> z;
            default -> throw new IllegalArgumentException();
        };
    }

    public FloatBuffer get(FloatBuffer buffer) {
        return get(buffer.position(), buffer);
    }

    public FloatBuffer get(int index, FloatBuffer buffer) {
        MemUtil.INSTANCE.put(this, index, buffer);
        return buffer;
    }

    public ByteBuffer get(ByteBuffer buffer) {
        return get(buffer.position(), buffer);
    }

    public ByteBuffer get(int index, ByteBuffer buffer) {
        MemUtil.INSTANCE.put(this, index, buffer);
        return buffer;
    }

    /**
     * Subtract the supplied vector from this one and store the result in <code>this</code>.
     *
     * @param v the vector to subtract
     * @return a vector holding the result
     */
    public Vector3f sub(Vector3f v) {
        return sub(v, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#sub(org.joml.Vector3fc, org.joml.Vector3f)
     */
    public Vector3f sub(Vector3f v, Vector3f dest) {
        dest.x = x - v.x();
        dest.y = y - v.y();
        dest.z = z - v.z();
        return dest;
    }

    /**
     * Decrement the components of this vector by the given values.
     *
     * @param x the x component to subtract
     * @param y the y component to subtract
     * @param z the z component to subtract
     * @return a vector holding the result
     */
    public Vector3f sub(float x, float y, float z) {
        return sub(x, y, z, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#sub(float, float, float, org.joml.Vector3f)
     */
    public Vector3f sub(float x, float y, float z, Vector3f dest) {
        dest.x = this.x - x;
        dest.y = this.y - y;
        dest.z = this.z - z;
        return dest;
    }

    /**
     * Add the supplied vector to this one.
     *
     * @param v the vector to add
     * @return a vector holding the result
     */
    public Vector3f add(Vector3f v) {
        return add(v, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#add(org.joml.Vector3fc, org.joml.Vector3f)
     */
    public Vector3f add(Vector3f v, Vector3f dest) {
        dest.x = x + v.x();
        dest.y = y + v.y();
        dest.z = z + v.z();
        return dest;
    }

    /**
     * Increment the components of this vector by the given values.
     *
     * @param x the x component to add
     * @param y the y component to add
     * @param z the z component to add
     * @return a vector holding the result
     */
    public Vector3f add(float x, float y, float z) {
        return add(x, y, z, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#add(float, float, float, org.joml.Vector3f)
     */
    public Vector3f add(float x, float y, float z, Vector3f dest) {
        dest.x = this.x + x;
        dest.y = this.y + y;
        dest.z = this.z + z;
        return dest;
    }

    /**
     * Multiply all components of this {@link Vector3f} by the given scalar
     * value.
     *
     * @param scalar the scalar to multiply this vector by
     * @return a vector holding the result
     */
    public Vector3f mul(float scalar) {
        return mul(scalar, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#mul(float, org.joml.Vector3f)
     */
    public Vector3f mul(float scalar, Vector3f dest) {
        dest.x = x * scalar;
        dest.y = y * scalar;
        dest.z = z * scalar;
        return dest;
    }

    /**
     * Multiply the components of this Vector3f by the given scalar values and store the result in <code>this</code>.
     *
     * @param x the x component to multiply this vector by
     * @param y the y component to multiply this vector by
     * @param z the z component to multiply this vector by
     * @return a vector holding the result
     */
    public Vector3f mul(float x, float y, float z) {
        return mul(x, y, z, this);
    }

    /**
     * Multiply the given matrix with this Vector3f and store the result in <code>this</code>.
     *
     * @param mat the matrix
     * @return a vector holding the result
     */
    public Vector3f mul(Matrix3f mat) {
        return mul(mat, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#mul(org.joml.Matrix3fc, org.joml.Vector3f)
     */
    public Vector3f mul(Matrix3f mat, Vector3f dest) {
        float rx = mat.m00() * x + mat.m10() * y + mat.m20() * z;
        float ry = mat.m01() * x + mat.m11() * y + mat.m21() * z;
        float rz = mat.m02() * x + mat.m12() * y + mat.m22() * z;
        dest.x = rx;
        dest.y = ry;
        dest.z = rz;
        return dest;
    }

    /**
     * Multiply this Vector3f component-wise by another Vector3fc.
     *
     * @param v the vector to multiply by
     * @return a vector holding the result
     */
    public Vector3f mul(Vector3f v) {
        return mul(v, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#mul(org.joml.Vector3fc, org.joml.Vector3f)
     */
    public Vector3f mul(Vector3f v, Vector3f dest) {
        dest.x = x * v.x();
        dest.y = y * v.y();
        dest.z = z * v.z();
        return dest;
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#mul(float, float, float, org.joml.Vector3f)
     */
    public Vector3f mul(float x, float y, float z, Vector3f dest) {
        dest.x = this.x * x;
        dest.y = this.y * y;
        dest.z = this.z * z;
        return dest;
    }

    /**
     * Divide all components of this {@link Vector3f} by the given scalar
     * value.
     *
     * @param scalar the scalar to divide by
     * @return a vector holding the result
     */
    public Vector3f div(float scalar) {
        return div(scalar, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#div(float, org.joml.Vector3f)
     */
    public Vector3f div(float scalar, Vector3f dest) {
        float inv = 1.0f / scalar;
        dest.x = x * inv;
        dest.y = y * inv;
        dest.z = z * inv;
        return dest;
    }

    /**
     * Divide this Vector3f component-wise by another Vector3fc.
     *
     * @param v the vector to divide by
     * @return a vector holding the result
     */
    public Vector3f div(Vector3f v) {
        return div(v.x(), v.y(), v.z(), this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#div(org.joml.Vector3fc, org.joml.Vector3f)
     */
    public Vector3f div(Vector3f v, Vector3f dest) {
        dest.x = x / v.x();
        dest.y = y / v.y();
        dest.z = z / v.z();
        return dest;
    }

    /**
     * Divide the components of this Vector3f by the given scalar values and store the result in <code>this</code>.
     *
     * @param x the x component to divide this vector by
     * @param y the y component to divide this vector by
     * @param z the z component to divide this vector by
     * @return a vector holding the result
     */
    public Vector3f div(float x, float y, float z) {
        return div(x, y, z, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#div(float, float, float, org.joml.Vector3f)
     */
    public Vector3f div(float x, float y, float z, Vector3f dest) {
        dest.x = this.x / x;
        dest.y = this.y / y;
        dest.z = this.z / z;
        return dest;
    }

    /**
     * Rotate this vector by the given quaternion <code>quat</code> and store the result in <code>this</code>
     *
     * @param quat the quaternion to rotate this vector
     * @return a vector holding the result
     */
    public Vector3f rotate(Quaternionf quat) {
        return rotate(quat, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#rotate(org.joml.Quaternionfc, org.joml.Vector3f)
     */
    public Vector3f rotate(Quaternionf quat, Vector3f dest) {
        return quat.transform(this, dest);
    }

    public Quaternionf rotationTo(Vector3f toDir, Quaternionf dest) {
        return dest.rotationTo(this, toDir);
    }

    public Quaternionf rotationTo(float toDirX, float toDirY, float toDirZ, Quaternionf dest) {
        return dest.rotationTo(x, y, z, toDirX, toDirY, toDirZ);
    }

    /**
     * Rotate this vector the specified radians around the X axis.
     *
     * @param angle the angle in radians
     * @return a vector holding the result
     */
    public Vector3f rotateX(float angle) {
        return rotateX(angle, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#rotateX(float, org.joml.Vector3f)
     */
    public Vector3f rotateX(float angle, Vector3f dest) {
        float sin = (float) Math.sin(angle), cos = (float) Math.cosFromSin(sin, angle);
        float y = this.y * cos - this.z * sin;
        float z = this.y * sin + this.z * cos;
        dest.x = this.x;
        dest.y = y;
        dest.z = z;
        return dest;
    }

    /**
     * Rotate this vector the specified radians around the Y axis.
     *
     * @param angle the angle in radians
     * @return a vector holding the result
     */
    public Vector3f rotateY(float angle) {
        return rotateY(angle, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#rotateY(float, org.joml.Vector3f)
     */
    public Vector3f rotateY(float angle, Vector3f dest) {
        float sin = (float) Math.sin(angle), cos = (float) Math.cosFromSin(sin, angle);
        float x = this.x * cos + this.z * sin;
        float z = -this.x * sin + this.z * cos;
        dest.x = x;
        dest.y = this.y;
        dest.z = z;
        return dest;
    }

    /**
     * Rotate this vector the specified radians around the Z axis.
     *
     * @param angle the angle in radians
     * @return a vector holding the result
     */
    public Vector3f rotateZ(float angle) {
        return rotateZ(angle, this);
    }

    public Vector3f rotateZ(float angle, Vector3f dest) {
        float sin = (float) Math.sin(angle), cos = (float) Math.cosFromSin(sin, angle);
        float x = this.x * cos - this.y * sin;
        float y = this.x * sin + this.y * cos;
        dest.x = x;
        dest.y = y;
        dest.z = this.z;
        return dest;
    }

    public float lengthSquared() {
        return lengthSquared(x, y, z);
    }

    public float length() {
        return (float) Math.sqrt(lengthSquared());
    }

    /**
     * Normalize this vector.
     *
     * @return a vector holding the result
     */
    public Vector3f normalize() {
        return normalize(this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#normalize(org.joml.Vector3f)
     */
    public Vector3f normalize(Vector3f dest) {
        float invLength = 1.0f / length();
        dest.x = x * invLength;
        dest.y = y * invLength;
        dest.z = z * invLength;
        return dest;
    }

    /**
     * Scale this vector to have the given length.
     *
     * @param length the desired length
     * @return a vector holding the result
     */
    public Vector3f normalize(float length) {
        return normalize(length, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Vector3fc#normalize(float, org.joml.Vector3f)
     */
    public Vector3f normalize(float length, Vector3f dest) {
        float invLength = 1.0f / length() * length;
        dest.x = x * invLength;
        dest.y = y * invLength;
        dest.z = z * invLength;
        return dest;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector3f other = (Vector3f) obj;
        if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
            return false;
        if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
            return false;
        return Float.floatToIntBits(z) == Float.floatToIntBits(other.z);
    }

    public boolean equals(Vector3f v, float delta) {
        if (this == v)
            return true;
        if (v == null)
            return false;
        if (!(v instanceof Vector3f))
            return false;
        if (!Runtime.equals(x, v.x(), delta))
            return false;
        if (!Runtime.equals(y, v.y(), delta))
            return false;
        return Runtime.equals(z, v.z(), delta);
    }

    public boolean equals(float x, float y, float z) {
        if (Float.floatToIntBits(this.x) != Float.floatToIntBits(x))
            return false;
        if (Float.floatToIntBits(this.y) != Float.floatToIntBits(y))
            return false;
        return Float.floatToIntBits(this.z) == Float.floatToIntBits(z);
    }

    /**
     * The x component of the vector.
     */
    public float x() {
        return x;
    }

    public void _x(float x) {
        this.x = x;
    }

    /**
     * The y component of the vector.
     */
    public float y() {
        return y;
    }

    public void _y(float y) {
        this.y = y;
    }

    /**
     * The z component of the vector.
     */
    public float z() {
        return z;
    }

    public void _z(float z) {
        this.z = z;
    }
}

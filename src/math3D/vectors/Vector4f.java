package math3D.vectors;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class Vector4f implements Externalizable {

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
    /**
     * The w component of the vector.
     */
    public float w;

    public Vector4f() {
        x = 0;
        y = 0;
        z = 0;
        w = 0;
    }

    public Vector4f(float[] v) {
        x = v[0];
        y = v[1];
        z = v[2];
        w = v[3];
    }

    public Vector4f(float v) {
        x = v;
        y = v;
        z = v;
        w = v;
    }

    public Vector4f(Vector3f v, float w) {
        x = v.x();
        y = v.y();
        z = v.z();
        this.w = w;
    }

    public Vector4f(FloatBuffer v) {
        x = v.get(0);
        y = v.get(1);
        z = v.get(2);
        w = v.get(3);
    }

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float length() {
        return (float) Math.sqrt((x * x) + (y * y) + (z * z) + (w * w));
    }

    public Vector4f normalize() {
        return new Vector4f(x / length(), y / length(), z / length(), w / length());
    }

    public void print() {
        System.out.printf("<x:%.1f/y:%.1f/z:%.1f/w:%.1f>%n", x, y, z, w);
    }

    public void print(String message) {
        System.out.printf("%s  -  <x:%.1f/y:%.1f/z:%.1f/w:%.1f>%n", message, x, y, z, w);
    }

    public Vector4f mul(float scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;
        w *= scalar;
        return this;
    }

    public Vector4f mul(Vector4f v) {
        x *= v.x;
        y *= v.y;
        z *= v.z;
        w *= v.w;
        return this;
    }

    public Vector4f sub(float scalar) {
        x -= scalar;
        y -= scalar;
        z -= scalar;
        w -= scalar;
        return this;
    }

    public Vector4f sub(Vector4f v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
        w -= v.w;
        return this;
    }

    public Vector4f add(float scalar) {
        x += scalar;
        y += scalar;
        z += scalar;
        w += scalar;
        return this;
    }

    public Vector4f add(Vector4f v) {
        x += v.x;
        y += v.y;
        z += v.z;
        w += v.w;
        return this;
    }

    public Vector4f div(float scalar) {
        x /= scalar;
        y /= scalar;
        z /= scalar;
        w /= scalar;
        return this;
    }

    public Vector4f div(Vector4f v) {
        x /= v.x;
        y /= v.y;
        z /= v.z;
        w /= v.w;
        return this;
    }

    public Vector4f remainder(float scalar) {
        x %= scalar;
        y %= scalar;
        z %= scalar;
        w %= scalar;
        if (x % scalar == 0) {
            System.out.printf("x = %d is even%n", x);
        } else {
            System.out.printf("x = %d is odd%n", x);
        }
        if (y % scalar == 0) {
            System.out.printf("y = %d is even%n", y);
        } else {
            System.out.printf("y = %d is odd%n", y);
        }
        if (z % scalar == 0) {
            System.out.printf("z = %d is even", z);
        } else {
            System.out.printf("z = %d is odd%n", z);
        }
        if (w % scalar == 0) {
            System.out.printf("w = %d is even", w);
        } else {
            System.out.printf("w = %d is odd%n", w);
        }
        return this;
    }

    public float[] toArray() {
        return new float[]{x, y, z, w};
    }

    public List<Float> toList() {
        List<Float> v = new ArrayList<>();
        v.add(x);
        v.add(y);
        v.add(z);
        v.add(w);
        return v;
    }

    public Vector4f set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public Vector4f set(Vector4f v) {
        return set(v.x, v.y, v.z, v.w);
    }

    public Vector4f set(Vector3f v, float w) {
        return set(v.x(), v.y(), v.z(), w);
    }

    public Vector4f set(float[] v) {
        return set(v[0], v[1], v[2], v[3]);
    }

    public Vector4f set(float v) {
        return set(v, v, v, v);
    }

    public FloatBuffer toFloatBuffer(Vector4f v) {
        FloatBuffer buffer = FloatBuffer.allocate((int) v.length());
        buffer.put(v.x).put(v.y).put(v.z).put(v.w);
        return buffer;
    }

    public float dot(Vector4f v) {
        float tx = x * v.x;
        float ty = y * v.y;
        float tz = z * v.z;
        float tw = w * v.w;
        return (tx + ty + tz + tw);
    }

    public Vector4f normalize(Vector4f v) {
        float invLength = 1.0f / length();
        v.x = x * invLength;
        v.y = y * invLength;
        v.z = z * invLength;
        v.w = w * invLength;
        return v;
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
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

    }
}

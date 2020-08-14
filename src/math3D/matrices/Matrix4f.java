/**
 *
 */
package math3D.matrices;

import math3D.internal.MemUtil;
import math3D.vectors.Vector3f;
import math3D.vectors.Vector4f;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Flavius Valerius
 *
 */
public class Matrix4f implements Externalizable {

    private static final long serialVersionUID = 1L;

    private final Stack<Matrix4f> matrices = new Stack<Matrix4f>();
    private float m00, m01, m02, m03;
    private float m10, m11, m12, m13;
    private float m20, m21, m22, m23;
    private float m30, m31, m32, m33;
    private float[][] matrix = new float[][]{{m00, m01, m02, m03},
            {m10, m11, m12, m13},
            {m20, m21, m22, m23},
            {m30, m31, m32, m33}};

    /**
     *
     */
    public Matrix4f() {
        matrix[0][0] = 1.0f;
        matrix[1][0] = m10;
        matrix[2][0] = m20;
        matrix[3][0] = m30;
        matrix[0][1] = m01;
        matrix[1][1] = 1.0f;
        matrix[2][1] = m21;
        matrix[3][1] = m31;
        matrix[0][2] = m02;
        matrix[1][2] = m12;
        matrix[2][2] = 1.0f;
        matrix[3][2] = m32;
        matrix[0][3] = m03;
        matrix[1][3] = m13;
        matrix[2][3] = m23;
        matrix[3][3] = 1.0f;
        matrices.add(this);
    }

    public Matrix4f(float m00, float m01, float m02, float m03,
                    float m10, float m11, float m12, float m13,
                    float m20, float m21, float m22, float m23,
                    float m30, float m31, float m32, float m33) {
        matrix[0][0] = m00;
        matrix[1][0] = m10;
        matrix[2][0] = m20;
        matrix[3][0] = m30;
        matrix[0][1] = m01;
        matrix[1][1] = m11;
        matrix[2][1] = m21;
        matrix[3][1] = m31;
        matrix[0][2] = m02;
        matrix[1][2] = m12;
        matrix[2][2] = m22;
        matrix[3][2] = m32;
        matrix[0][3] = m03;
        matrix[1][3] = m13;
        matrix[2][3] = m23;
        matrix[3][3] = m33;
        matrices.add(this);
    }

    public Matrix4f(float[] f) {
        this(f[0], f[1], f[2], f[3], f[4], f[5], f[6], f[7], f[8],
                f[9], f[10], f[11], f[12], f[13], f[14], f[15]);
    }

    public Matrix4f(ArrayList<Float> list) {
        matrix[0][0] = list.get(0);
        matrix[1][0] = list.get(4);
        matrix[2][0] = list.get(8);
        matrix[3][0] = list.get(12);
        matrix[0][1] = list.get(1);
        matrix[1][1] = list.get(5);
        matrix[2][1] = list.get(9);
        matrix[3][1] = list.get(13);
        matrix[0][2] = list.get(2);
        matrix[1][2] = list.get(6);
        matrix[2][2] = list.get(10);
        matrix[3][2] = list.get(14);
        matrix[0][3] = list.get(3);
        matrix[1][3] = list.get(7);
        matrix[2][3] = list.get(11);
        matrix[3][3] = list.get(15);
    }

    public Matrix4f(Vector4f v) {
        matrix[0][0] = v.getX();
        matrix[1][0] = 0;
        matrix[2][0] = 0;
        matrix[3][0] = 0;
        matrix[0][1] = 0;
        matrix[1][1] = v.getY();
        matrix[2][1] = 0;
        matrix[3][1] = 0;
        matrix[0][2] = 0;
        matrix[1][2] = 0;
        matrix[2][2] = v.getZ();
        matrix[3][2] = 0;
        matrix[0][3] = 0;
        matrix[1][3] = 0;
        matrix[2][3] = 0;
        matrix[3][3] = v.getW();
    }

    public Matrix4f(float[][] m) {
        matrix[0][0] = m[0][0];
        matrix[1][0] = m[1][0];
        matrix[2][0] = m[2][0];
        matrix[3][0] = m[3][0];
        matrix[0][1] = m[0][1];
        matrix[1][1] = m[1][1];
        matrix[2][1] = m[2][1];
        matrix[3][1] = m[3][1];
        matrix[0][2] = m[0][2];
        matrix[1][2] = m[1][2];
        matrix[2][2] = m[2][2];
        matrix[3][2] = m[3][2];
        matrix[0][3] = m[0][3];
        matrix[1][3] = m[1][3];
        matrix[2][3] = m[2][3];
        matrix[3][3] = m[3][3];
    }

    /**
     * Set the given matrix to 0.
     * @param m The matrix to set to 0
     * @return m
     */
    public static Matrix4f setZero(Matrix4f m) {
        m.m00 = 0.0f;
        m.m01 = 0.0f;
        m.m02 = 0.0f;
        m.m03 = 0.0f;
        m.m10 = 0.0f;
        m.m11 = 0.0f;
        m.m12 = 0.0f;
        m.m13 = 0.0f;
        m.m20 = 0.0f;
        m.m21 = 0.0f;
        m.m22 = 0.0f;
        m.m23 = 0.0f;
        m.m30 = 0.0f;
        m.m31 = 0.0f;
        m.m32 = 0.0f;
        m.m33 = 0.0f;
        return m;
    }

    /**
     * Copy the source matrix to the destination matrix
     * @param src The source matrix
     * @param dest The destination matrix, or null of a new one is to be created
     * @return The copied matrix
     */
    public static Matrix4f store(Matrix4f src, Matrix4f dest) {
        if (dest == null) {
            dest = new Matrix4f();
        }
        dest.matrix[0][0] = src.matrix[0][0];
        dest.matrix[2][0] = src.matrix[2][0];
        dest.matrix[0][1] = src.matrix[0][1];
        dest.matrix[2][1] = src.matrix[2][1];
        dest.matrix[0][2] = src.matrix[0][2];
        dest.matrix[2][2] = src.matrix[2][2];
        dest.matrix[0][3] = src.matrix[0][3];
        dest.matrix[2][3] = src.matrix[2][3];

        dest.matrix[1][0] = src.matrix[1][0];
        dest.matrix[3][0] = src.matrix[3][0];
        dest.matrix[1][1] = src.matrix[1][1];
        dest.matrix[3][1] = src.matrix[3][1];
        dest.matrix[1][2] = src.matrix[1][2];
        dest.matrix[3][2] = src.matrix[3][2];
        dest.matrix[1][3] = src.matrix[1][3];
        dest.matrix[3][3] = src.matrix[3][3];
        return dest;
    }

    /**
     * Transform a Vector by a matrix and return the result in a destination vector.
     * @param left The left matrix
     * @param right The right vector
     * @param dest The destination vector, or null if a new one is to be created
     * @return the destination vector
     */
    public static Vector4f transform(Matrix4f left, Vector4f right, Vector4f dest) {
        if (dest == null) {
            dest = new Vector4f();
        }
        float x = left.m00 * right.getX() + left.m10 * right.getY() + left.m20 * right.getZ() + left.m30 * right.getW();
        float y = left.m01 * right.getX() + left.m11 * right.getY() + left.m21 * right.getZ() + left.m31 * right.getW();
        float z = left.m02 * right.getX() + left.m12 * right.getY() + left.m22 * right.getZ() + left.m32 * right.getW();
        float w = left.m03 * right.getX() + left.m13 * right.getY() + left.m23 * right.getZ() + left.m33 * right.getW();
        dest.set(x, y, z, w);
        return dest;
    }

    /**
     * Set this matrix to 0.
     * @return this
     */
    public Matrix4f setZero() {
        return setZero(this);
    }

    /**
     * Set this matrix to be the identity matrix.
     * @return this
     */
    public Matrix4f identity() {
        return identity(this);
    }

    public Matrix4f identity(Matrix4f m) {
        m.m00 = 1;
        m.m01 = 0;
        m.m02 = 0;
        m.m03 = 0;
        m.m10 = 0;
        m.m11 = 1;
        m.m12 = 0;
        m.m13 = 0;
        m.m20 = 0;
        m.m21 = 0;
        m.m22 = 1;
        m.m23 = 0;
        m.m30 = 0;
        m.m31 = 0;
        m.m32 = 0;
        m.m33 = 1;
        m.matrix[0][0] = 1;
        m.matrix[0][1] = 0;
        m.matrix[0][2] = 0;
        m.matrix[0][3] = 0;
        m.matrix[1][0] = 0;
        m.matrix[1][1] = 1;
        m.matrix[1][2] = 0;
        m.matrix[1][3] = 0;
        m.matrix[2][0] = 0;
        m.matrix[2][1] = 0;
        m.matrix[2][2] = 1;
        m.matrix[2][3] = 0;
        m.matrix[3][0] = 0;
        m.matrix[3][1] = 0;
        m.matrix[3][2] = 0;
        m.matrix[3][3] = 1;
        return this;
    }

    /**
     * Load from another matrix4f
     * @param src The source matrix
     * @return this
     */
    public Matrix4f store(Matrix4f src) {
        return store(src, this);
    }

    /**
     * Load from a float buffer. The buffer stores the matrix in column major
     * (OpenGL) order.
     * @param buf A float buffer to read from
     * @return this
     */
    public Matrix4f storeFloatBufferInMatrix(FloatBuffer buf) {
        m00 = buf.get();
        m10 = buf.get();
        m01 = buf.get();
        m11 = buf.get();
        m02 = buf.get();
        m12 = buf.get();
        m03 = buf.get();
        m13 = buf.get();

        m20 = buf.get();
        m30 = buf.get();
        m21 = buf.get();
        m31 = buf.get();
        m22 = buf.get();
        m32 = buf.get();
        m23 = buf.get();
        m33 = buf.get();
        return this;
    }

    /**
     * Store this matrix in a float buffer. The matrix is stored in row
     * major (maths) order.
     * @param buf The buffer to store this matrix in
     */
    public Matrix4f storeMatrixInFloatBuffer(FloatBuffer buf) {
        buf.put(m00);
        buf.put(m10);
        buf.put(m01);
        buf.put(m11);
        buf.put(m02);
        buf.put(m12);
        buf.put(m03);
        buf.put(m13);

        buf.put(m20);
        buf.put(m30);
        buf.put(m21);
        buf.put(m31);
        buf.put(m22);
        buf.put(m32);
        buf.put(m23);
        buf.put(m33);
        return this;
    }

    public FloatBuffer storeMatrixInFloatBuffer(Matrix4f m) {
        m.orthogonal(-2.0f, 2.0f, -1.5f, 1.5f, 1.0f, 1.0f);
        final FloatBuffer matrixBuffer = FloatBuffer.allocate(16);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                matrixBuffer.put(m.get(x, y));
            }
        }
        return matrixBuffer;
    }

    /**
     * Store the rotation portion of this matrix in a float buffer. The matrix is stored in column
     * major (openGL) order.
     * @param buf The buffer to store this matrix in
     */
    public Matrix4f storeRotationInFloatBuffer(FloatBuffer buf) {
        buf.put(m00);
        buf.put(m01);
        buf.put(m02);

        buf.put(m10);
        buf.put(m11);
        buf.put(m12);

        buf.put(m20);
        buf.put(m21);
        buf.put(m22);
        return this;
    }

    /**
     * Sets a specified value in a specified column and row
     * which will over-write a present value
     * @param column the first set of brackets on an array
     * @param row the second set of brackets on an array
     * @param value the amount to be set
     * @return
     */
    public Matrix4f setElementAt(int column, int row, float value) {
        if (column >= 0 && column < 4 && row >= 0 && row < 4) {
            matrix[column][row] = value;
        }
        return this;
    }

    public Matrix4f translation(Vector3f translation) {
        m00 = 1;
        m01 = 0;
        m02 = 0;
        m03 = translation.x();
        m10 = 0;
        m11 = 1;
        m12 = 0;
        m13 = translation.y();
        m20 = 0;
        m21 = 0;
        m22 = 1;
        m23 = translation.z();
        m30 = 0;
        m31 = 0;
        m32 = 0;
        m33 = 1;
        return this;
    }

    public Matrix4f add(Matrix4f other) {
        m00 += other.m00;
        m01 += other.m01;
        m02 += other.m02;
        m03 += other.m03;
        m10 += other.m10;
        m11 += other.m11;
        m12 += other.m12;
        m13 += other.m13;
        m20 += other.m20;
        m21 += other.m21;
        m22 += other.m22;
        m23 += other.m23;
        m30 += other.m30;
        m31 += other.m31;
        m32 += other.m32;
        m33 += other.m33;
        return this;
    }

    public Matrix4f add(float scalar) {
        m00 += scalar;
        m01 += scalar;
        m02 += scalar;
        m03 += scalar;
        m10 += scalar;
        m11 += scalar;
        m12 += scalar;
        m13 += scalar;
        m20 += scalar;
        m21 += scalar;
        m22 += scalar;
        m23 += scalar;
        m30 += scalar;
        m31 += scalar;
        m32 += scalar;
        m33 += scalar;
        return this;
    }

    public Matrix4f sub(Matrix4f other) {
        m00 -= other.m00;
        m01 -= other.m01;
        m02 -= other.m02;
        m03 -= other.m03;
        m10 -= other.m10;
        m11 -= other.m11;
        m12 -= other.m12;
        m13 -= other.m13;
        m20 -= other.m20;
        m21 -= other.m21;
        m22 -= other.m22;
        m23 -= other.m23;
        m30 -= other.m30;
        m31 -= other.m31;
        m32 -= other.m32;
        m33 -= other.m33;
        return this;
    }

    public Matrix4f sub(float scalar) {
        m00 -= scalar;
        m01 -= scalar;
        m02 -= scalar;
        m03 -= scalar;
        m10 -= scalar;
        m11 -= scalar;
        m12 -= scalar;
        m13 -= scalar;
        m20 -= scalar;
        m21 -= scalar;
        m22 -= scalar;
        m23 -= scalar;
        m30 -= scalar;
        m31 -= scalar;
        m32 -= scalar;
        m33 -= scalar;
        return this;
    }

    public Matrix4f multiply(Matrix4f other) {
        m00 *= other.m00;
        m01 *= other.m01;
        m02 *= other.m02;
        m03 *= other.m03;
        m10 *= other.m10;
        m11 *= other.m11;
        m12 *= other.m12;
        m13 *= other.m13;
        m20 *= other.m20;
        m21 *= other.m21;
        m22 *= other.m22;
        m23 *= other.m23;
        m30 *= other.m30;
        m31 *= other.m31;
        m32 *= other.m32;
        m33 *= other.m33;
        return this;
    }

    public Matrix4f multiply(float scalar) {
        m00 *= scalar;
        m01 *= scalar;
        m02 *= scalar;
        m03 *= scalar;
        m10 *= scalar;
        m11 *= scalar;
        m12 *= scalar;
        m13 *= scalar;
        m20 *= scalar;
        m21 *= scalar;
        m22 *= scalar;
        m23 *= scalar;
        m30 *= scalar;
        m31 *= scalar;
        m32 *= scalar;
        m33 *= scalar;
        return this;
    }

    public Matrix4f divide(Matrix4f other) {
        m00 /= other.m00;
        m01 /= other.m01;
        m02 /= other.m02;
        m03 /= other.m03;
        m10 /= other.m10;
        m11 /= other.m11;
        m12 /= other.m12;
        m13 /= other.m13;
        m20 /= other.m20;
        m21 /= other.m21;
        m22 /= other.m22;
        m23 /= other.m23;
        m30 /= other.m30;
        m31 /= other.m31;
        m32 /= other.m32;
        m33 /= other.m33;
        return this;
    }

    public Matrix4f divide(float scalar) {
        m00 /= scalar;
        m01 /= scalar;
        m02 /= scalar;
        m03 /= scalar;
        m10 /= scalar;
        m11 /= scalar;
        m12 /= scalar;
        m13 /= scalar;
        m20 /= scalar;
        m21 /= scalar;
        m22 /= scalar;
        m23 /= scalar;
        m30 /= scalar;
        m31 /= scalar;
        m32 /= scalar;
        m33 /= scalar;
        return this;
    }

    public Matrix4f orthogonal(float left, float right, float bottom, float top, float near, float far) {
        identity();
        _m00(2.0f / (right - left));
        _m11(2.0f / (top - bottom));
        _m22(-2.0f / (far - near));
        _m32((far + near) / (far - near));
        _m30((right + left) / (right - left));
        _m31((top + bottom) / (top - bottom));
        return this;
    }

    public Matrix4f orthographic2D(int width, int height) {
        _m00(2.0f / width);
        _m10(0);
        _m01(0);
        _m11(2.0f / height);
        _m02(0);
        _m12(0);
        _m03(1.0f);
        _m13(1.0f);

        _m20(0);
        _m30(0.0f);
        _m21(0);
        _m31(0.0f);
        _m22(1.0f);
        _m32(0.0f);
        _m23(0.0f);
        _m33(1.0f);
        return this;
    }

    /**
     * Stores the matrix in a given Buffer.
     * @return the buffer with the matrix stored in it
     */
    public FloatBuffer toBuffer(Matrix4f m) {
        FloatBuffer buffer = FloatBuffer.allocate(16);
        buffer.put(m.m00);
        buffer.put(m.m01);
        buffer.put(m.m02);
        buffer.put(m.m03);
        buffer.put(m.m10);
        buffer.put(m.m11);
        buffer.put(m.m12);
        buffer.put(m.m13);
        buffer.put(m.m20);
        buffer.put(m.m21);
        buffer.put(m.m22);
        buffer.put(m.m23);
        buffer.put(m.m30);
        buffer.put(m.m31);
        buffer.put(m.m32);
        buffer.put(m.m33);
        return buffer;
    }

    public Matrix4f perspective(float fov, float aspectRatio, float near, float far) {
        float perp = (float) (1.0f / Math.tan(Math.toRadians(fov) / 2.0f));
        identity();
        m00 = perp / aspectRatio;
        m11 = perp;
        m22 = (far + near) / (near - far);
        m32 = -1.0f;
        m23 = (2.0f * far * near) / (near - far);
        m33 = 0.0f;
        return this;
    }

    public Matrix4f translate(float x, float y, float z) {
        identity();
        m03 = x;
        m13 = y;
        m23 = z;
        return this;
    }

    public Matrix4f translate(Vector3f v) {
        identity();
        m03 = v.x();
        m13 = v.y();
        m23 = v.z();
        return this;
    }

    /**
     * Transpose this matrix
     * @return this
     */
    public Matrix4f transpose() {
        return transpose(this);
    }

    /**
     * Transpose this matrix and place the result in another matrix
     * @param dest The destination matrix or null if a new matrix is to be created
     * @return the transposed matrix
     */
    public Matrix4f transpose(Matrix4f dest) {
        return transpose(this, dest);
    }

    /**
     * Transpose the source matrix and place the result in the destination matrix
     * @param src The source matrix
     * @param dest The destination matrix or null if a new matrix is to be created
     * @return the transposed matrix
     */
    public Matrix4f transpose(Matrix4f src, Matrix4f dest) {
        if (dest == null) {
            dest = new Matrix4f();
        }
        dest.m00 = src.m00;
        dest.m10 = src.m01;
        dest.m01 = src.m10;
        dest.m11 = src.m11;
        dest.m02 = src.m20;
        dest.m12 = src.m21;
        dest.m03 = src.m30;
        dest.m13 = src.m31;

        dest.m20 = src.m02;
        dest.m30 = src.m03;
        dest.m21 = src.m12;
        dest.m31 = src.m13;
        dest.m22 = src.m22;
        dest.m32 = src.m23;
        dest.m23 = src.m32;
        dest.m33 = src.m33;
        return dest;
    }

    public void print() {
        for (int i = 0; i < 4; i++) {
            System.out.print("|" + matrix[0][i] + "|");
            for (int j = 0; j < 4; j++) {
                System.out.print("|" + matrix[1][j] + "|");
                for (int k = 0; k < 4; k++) {
                    System.out.print("|" + matrix[2][k] + "|");
                    for (int l = 0; l < 4; l++) {
                        System.out.print("|" + matrix[3][l] + "|");
                    }
                }
            }
        }
    }

    public Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {
        Matrix4f m = new Matrix4f().identity();
        m.m00 = 2f / (right - left);
        m.m11 = 2f / (top - bottom);
        m.m22 = -2f / (far - near);
        m.m03 = -(right + left) / (right - left);
        m.m13 = -(top + bottom) / (top - bottom);
        m.m23 = -(far + near) / (far - near);
        return m;
    }

    public Matrix4f rotationX(float ang) {
        float cos = (float) Math.cos(ang);
        float sin = (float) Math.sin(ang);
        m00 = 1.0f;
        m10 = 0.0f;
        m01 = 0.0f;
        m11 = cos;
        m02 = 0.0f;
        m12 = sin;
        m03 = 0.0f;
        m13 = 0.0f;

        m20 = 0.0f;
        m30 = 0.0f;
        m21 = -sin;
        m31 = 0.0f;
        m22 = cos;
        m32 = 0.0f;
        m23 = 0.0f;
        m33 = 1.0f;
        return this;
    }

    public Matrix4f rotationY(float ang) {
        float cos = (float) Math.cos(ang);
        float sin = (float) Math.sin(ang);
        m00 = cos;
        m10 = 0.0f;
        m01 = 0.0f;
        m11 = 1.0f;
        m02 = -sin;
        m12 = 0.0f;
        m03 = 0.0f;
        m13 = 0.0f;

        m20 = sin;
        m30 = 0.0f;
        m21 = 0.0f;
        m31 = 0.0f;
        m22 = cos;
        m32 = 0.0f;
        m23 = 0.0f;
        m33 = 1.0f;
        return this;
    }

    public Matrix4f rotationZ(float ang) {
        float cos = (float) Math.cos(ang);
        float sin = (float) Math.sin(ang);
        m00 = cos;
        m10 = -sin;
        m01 = sin;
        m11 = cos;
        m02 = 0.0f;
        m12 = 0.0f;
        m03 = 0.0f;
        m13 = 0.0f;

        m20 = 0.0f;
        m30 = 0.0f;
        m21 = 0.0f;
        m31 = 0.0f;
        m22 = 1.0f;
        m32 = 0.0f;
        m23 = 0.0f;
        m33 = 1.0f;
        return this;
    }

    public Matrix4f scale(Vector3f v) {
        identity();
        m00 = v.x();
        m11 = v.y();
        m22 = v.z();
        m33 = 1;
        return this;
    }

    public Matrix4f scale(float[] f) {
        return scale(f[0], f[1], f[2]);
    }

    public Matrix4f scale(float s) {
        return scale(s, s, s);
    }

    public Matrix4f scale(Vector3f v, Matrix4f res) {
        return scale(v.x(), v.y(), v.z(), res);
    }

    public Matrix4f scale(float x, float y, float z) {
        return scale(x, y, z, this);
    }

    public Matrix4f scale(float x, float y, float z, Matrix4f res) {
        res.m00 = m00 * x;
        res.m01 = m01 * x;
        res.m02 = m02 * x;
        res.m03 = m03 * x;
        res.m10 = m10 * y;
        res.m11 = m11 * y;
        res.m12 = m12 * y;
        res.m13 = m13 * y;
        res.m20 = m20 * z;
        res.m21 = m21 * z;
        res.m22 = m22 * z;
        res.m23 = m23 * z;
        res.m30 = m30;
        res.m31 = m31;
        res.m32 = m32;
        res.m33 = m33;
        return res;
    }

    public float[] toArray(float[] m, int index) {
        m[index] = matrix[0][0];
        m[index + 1] = matrix[0][1];
        m[index + 2] = matrix[0][2];
        m[index + 3] = matrix[0][3];

        m[index + 4] = matrix[1][0];
        m[index + 5] = matrix[1][1];
        m[index + 6] = matrix[1][2];
        m[index + 7] = matrix[1][3];

        m[index + 8] = matrix[2][0];
        m[index + 9] = matrix[2][1];
        m[index + 10] = matrix[2][2];
        m[index + 11] = matrix[2][3];

        m[index + 12] = matrix[3][0];
        m[index + 13] = matrix[3][1];
        m[index + 14] = matrix[3][2];
        m[index + 15] = matrix[3][3];
        return m;
    }

    /**
     * Returns a string representation of this matrix
     */
    public String toString() {
        String buf = String.valueOf(m00) + ' ' + m10 + ' ' + m20 + ' ' + m30 + '\n' +
                m01 + ' ' + m11 + ' ' + m21 + ' ' + m31 + '\n' +
                m02 + ' ' + m12 + ' ' + m22 + ' ' + m32 + '\n' +
                m03 + ' ' + m13 + ' ' + m23 + ' ' + m33 + '\n';
        return buf;
    }

    /**
     * Gets the value at the specified column row
     * @param row the second place (m[col][row] = m00)
     * @param col the first place
     * @return the value from the specified position
     */
    public float get(int row, int col) {
        return matrix[col][row];
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m00()
     */
    public float m00() {
        return m00;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m01()
     */
    public float m01() {
        return m01;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m02()
     */
    public float m02() {
        return m02;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m03()
     */
    public float m03() {
        return m03;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m10()
     */
    public float m10() {
        return m10;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m11()
     */
    public float m11() {
        return m11;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m12()
     */
    public float m12() {
        return m12;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m13()
     */
    public float m13() {
        return m13;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m20()
     */
    public float m20() {
        return m20;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m21()
     */
    public float m21() {
        return m21;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m22()
     */
    public float m22() {
        return m22;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m23()
     */
    public float m23() {
        return m23;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m30()
     */
    public float m30() {
        return m30;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m31()
     */
    public float m31() {
        return m31;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m32()
     */
    public float m32() {
        return m32;
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix4fc#m33()
     */
    public float m33() {
        return m33;
    }

    /**
     * @param m00 the m00 to set
     */
    public void _m00(float m00) {
        this.m00 = m00;
        matrix[0][0] = m00;
    }

    /**
     * @param m01 the m01 to set
     */
    public void _m01(float m01) {
        this.m01 = m01;
        matrix[0][1] = m01;
    }

    /**
     * @param m02 the m02 to set
     */
    public void _m02(float m02) {
        this.m02 = m02;
        matrix[0][2] = m02;
    }

    /**
     * @param m03 the m03 to set
     */
    public void _m03(float m03) {
        this.m03 = m03;
        matrix[0][3] = m03;
    }

    /**
     * @param m10 the m10 to set
     */
    public void _m10(float m10) {
        this.m10 = m10;
        matrix[1][0] = m10;
    }

    /**
     * @param m11 the m11 to set
     */
    public void _m11(float m11) {
        this.m11 = m11;
        matrix[1][1] = m11;
    }

    /**
     * @param m12 the m12 to set
     */
    public void _m12(float m12) {
        this.m12 = m12;
        matrix[1][2] = m12;
    }

    /**
     * @param m13 the m13 to set
     */
    public void _m13(float m13) {
        this.m13 = m13;
        matrix[1][3] = m13;
    }

    /**
     * @param m20 the m20 to set
     */
    public void _m20(float m20) {
        this.m20 = m20;
        matrix[2][0] = m20;
    }

    /**
     * @param m21 the m21 to set
     */
    public void _m21(float m21) {
        this.m21 = m21;
        matrix[2][1] = m21;
    }

    /**
     * @param m22 the m22 to set
     */
    public void _m22(float m22) {
        this.m22 = m22;
        matrix[2][2] = m22;
    }

    /**
     * @param m23 the m23 to set
     */
    public void _m23(float m23) {
        this.m23 = m23;
        matrix[2][3] = m23;
    }

    /**
     * @param m30 the m30 to set
     */
    public void _m30(float m30) {
        this.m30 = m30;
        matrix[3][0] = m30;
    }

    /**
     * @param m31 the m31 to set
     */
    public void _m31(float m31) {
        this.m31 = m31;
        matrix[3][1] = m31;
    }

    /**
     * @param m32 the m32 to set
     */
    public void _m32(float m32) {
        this.m32 = m32;
        matrix[3][2] = m32;
    }

    /**
     * @param m33 the m33 to set
     */
    public void _m33(float m33) {
        this.m33 = m33;
        matrix[3][3] = m33;
    }

    /**
     * @param matrix the matrix to set
     */
    public void setMatrix(float[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix4f Set() {
        return Set(0);
    }

    /**
     * Set the upper left 3x3 submatrix of this {@link Matrix4f} to the given {@link Matrix3f}
     * and the rest to identity.
     *
     * @param mat
     *          the {@link Matrix3f}
     * @return this
     */
    public Matrix4f set(Matrix3f mat) {
        if (mat instanceof Matrix3f) {
            MemUtil.INSTANCE.copy(mat, this);
        } else {
            setMatrix3f(mat);
        }
        return this;
    }

    private void setMatrix3f(Matrix3f mat) {
        m00 = mat.m00();
        m01 = mat.m01();
        m02 = mat.m02();
        m03 = 0.0f;
        m10 = mat.m10();
        m11 = mat.m11();
        m12 = mat.m12();
        m13 = 0.0f;
        m20 = mat.m20();
        m21 = mat.m21();
        m22 = mat.m22();
        m23 = 0.0f;
        m30 = 0.0f;
        m31 = 0.0f;
        m32 = 0.0f;
        m33 = 1.0f;
    }

    public Matrix4f Set(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13,
                        float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33) {
        return new Matrix4f(m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23,
                m30, m31, m32, m33);
    }

    public Matrix4f Set(float v) {
        return Set(v, 0, 0, 0,
                0, v, 0, 0,
                0, 0, v, 0,
                0, 0, 0, v);
    }

    public Matrix4f Set(Vector4f v) {
        return Set(v.getX(), 0, 0, 0,
                0, v.getY(), 0, 0,
                0, 0, v.getZ(), 0,
                0, 0, 0, v.getW());
    }

    public Matrix4f Set(float[] m) {
        return Set(m[0], m[1], m[2], m[3],
                m[4], m[5], m[6], m[7],
                m[8], m[9], m[10], m[11],
                m[12], m[13], m[14], m[15]);
    }

    public Matrix4f Set(int column, int row, float value) {
        if (column >= 0 && column < 4 && row >= 0 && row < 4) {
            float[] m = toArray(new float[16], 0);
            matrix[column * 4][row * 4] = value;
            Set(m);
        }
        return this;
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
        out.writeFloat(m00);
        out.writeFloat(m01);
        out.writeFloat(m02);
        out.writeFloat(m03);
        out.writeFloat(m10);
        out.writeFloat(m11);
        out.writeFloat(m12);
        out.writeFloat(m13);
        out.writeFloat(m20);
        out.writeFloat(m21);
        out.writeFloat(m22);
        out.writeFloat(m23);
        out.writeFloat(m30);
        out.writeFloat(m31);
        out.writeFloat(m32);
        out.writeFloat(m33);
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
        this._m00(in.readFloat());
        this._m01(in.readFloat());
        this._m02(in.readFloat());
        this._m03(in.readFloat());
        this._m10(in.readFloat());
        this._m11(in.readFloat());
        this._m12(in.readFloat());
        this._m13(in.readFloat());
        this._m20(in.readFloat());
        this._m21(in.readFloat());
        this._m22(in.readFloat());
        this._m23(in.readFloat());
        this._m30(in.readFloat());
        this._m31(in.readFloat());
        this._m32(in.readFloat());
        this._m33(in.readFloat());
    }
}

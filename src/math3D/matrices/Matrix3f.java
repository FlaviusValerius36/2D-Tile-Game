package math3D.matrices;

import math3D.internal.MemUtil;
import math3D.vectors.Vector3f;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Matrix3f implements Externalizable {

    public float m00, m01, m02;
    public float m10, m11, m12;
    public float m20, m21, m22;

    /**
     * Creates a new {@link Matrix3f} and set it to identity}.
     */
    public Matrix3f() {
        _m00(1.0f);
        _m11(1.0f);
        _m22(1.0f);
    }

    /**
     * Create a new 3x3 matrix using the supplied float values. The order of the parameter is column-major,
     * so the first three parameters specify the three elements of the first column.
     *
     * @param m00 the value of m00
     * @param m01 the value of m01
     * @param m02 the value of m02
     * @param m10 the value of m10
     * @param m11 the value of m11
     * @param m12 the value of m12
     * @param m20 the value of m20
     * @param m21 the value of m21
     * @param m22 the value of m22
     */
    public Matrix3f(float m00, float m01, float m02,
                    float m10, float m11, float m12,
                    float m20, float m21, float m22) {
        this._m00(m00);
        this._m10(m10);
        this._m20(m20);
        this._m01(m01);
        this._m11(m11);
        this._m21(m21);
        this._m02(m02);
        this._m12(m12);
        this._m22(m22);
    }

    /**
     * Creates a new {@link Matrix3f} by reading its 9 float components from the given {@link FloatBuffer}
     * at the buffer's current position.
     * <p>That FloatBuffer is expected to hold the values in column-major order.  The buffer's position
     * will not be changed by this method.</p>
     *
     * @param buffer the {@link FloatBuffer} to read the matrix values from
     */
    public Matrix3f(FloatBuffer buffer) {
        MemUtil.INSTANCE.get(this, buffer.position(), buffer);
    }

    /**
     * Creates a new {@link Matrix3f} by reading its 9 float components from the given {@link ByteBuffer}
     * at the buffer's current position.
     * <p>That FloatBuffer is expected to hold the values in column-major order.  The buffer's position
     * will not be changed by this method.</p>
     *
     * @param buffer the {@link ByteBuffer} to read the matrix values from
     */
    public Matrix3f(ByteBuffer buffer) {
        MemUtil.INSTANCE.get(this, buffer.position(), buffer);
    }

    /**
     * Creates a new {@link Matrix3f} by reading its 9 float components from the given {@link IntBuffer}
     * at the buffer's current position.
     * <p>That FloatBuffer is expected to hold the values in column-major order.  The buffer's position
     * will not be changed by this method.</p>
     *
     * @param buffer the {@link IntBuffer} to read the matrix values from
     */
    public Matrix3f(IntBuffer buffer) {
        MemUtil.INSTANCE.get(this, buffer.position(), buffer);
    }

    public Matrix3f(Vector3f col_0, Vector3f col_1, Vector3f col_2) {
        if (col_0 instanceof Vector3f && col_1 instanceof Vector3f && col_2 instanceof Vector3f) {
            MemUtil.INSTANCE.set(this, col_0, col_1, col_2);
            _m00(col_0.x());
            _m10(col_1.x());
            _m20(col_2.x());
            _m01(col_0.y());
            _m11(col_1.y());
            _m21(col_2.y());
            _m02(col_0.z());
            _m12(col_1.z());
            _m22(col_2.z());
        }
    }

    /**
     * Creates a new {@link Matrix3f} and make it a copy of the given matrix
     *
     * @param m the Matrix to copy the values from
     */
    public Matrix3f(Matrix3f m) {
        if (m instanceof Matrix3f) {
            MemUtil.INSTANCE.copy(m, this);
        }
    }

    /**
     * Creates a new {@link Matrix3f} and make it a copy of the given matrix
     *
     * @param m the matrix to copy the values from
     */
    public Matrix3f(Matrix4f m) {
        if (m instanceof Matrix4f) {
            MemUtil.INSTANCE.copy(m, this);
        }
    }

    /**
     * Multiply this matrix by the supplied <code>right</code> matrix.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>R</code> the <code>right</code> matrix,
     * then the new matrix will be <code>M * R</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>M * R * v</code>, the
     * transformation of the right matrix will be applied first!
     *
     * @param right the right operand of the matrix multiplication
     * @return this
     */
    public Matrix3f mul(Matrix3f right) {
        return mul(right, this);
    }

    /**
     * @param right
     * @param dest
     * @return
     */
    public Matrix3f mul(Matrix3f right, Matrix3f dest) {
        float nm00 = m00 * right.m00() + m10 * right.m01() + m20 * right.m02();
        float nm01 = m01 * right.m00() + m11 * right.m01() + m21 * right.m02();
        float nm02 = m02 * right.m00() + m12 * right.m01() + m22 * right.m02();
        float nm10 = m00 * right.m10() + m10 * right.m11() + m20 * right.m12();
        float nm11 = m01 * right.m10() + m11 * right.m11() + m21 * right.m12();
        float nm12 = m02 * right.m10() + m12 * right.m11() + m22 * right.m12();
        float nm20 = m00 * right.m20() + m10 * right.m21() + m20 * right.m22();
        float nm21 = m01 * right.m20() + m11 * right.m21() + m21 * right.m22();
        float nm22 = m02 * right.m20() + m12 * right.m21() + m22 * right.m22();
        dest.m00 = nm00;
        dest.m01 = nm01;
        dest.m02 = nm02;
        dest.m10 = nm10;
        dest.m11 = nm11;
        dest.m12 = nm12;
        dest.m20 = nm20;
        dest.m21 = nm21;
        dest.m22 = nm22;
        return dest;
    }

    /**
     * Pre-multiply this matrix by the supplied <code>left</code> matrix and store the result in <code>this</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>L</code> the <code>left</code> matrix,
     * then the new matrix will be <code>L * M</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>L * M * v</code>, the
     * transformation of <code>this</code> matrix will be applied first!
     *
     * @param left the left operand of the matrix multiplication
     * @return this
     */
    public Matrix3f mulLocal(Matrix3f left) {
        return mulLocal(left, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix3fc#mulLocal(org.joml.Matrix3fc, org.joml.Matrix3f)
     */
    public Matrix3f mulLocal(Matrix3f left, Matrix3f dest) {
        float nm00 = left.m00() * m00 + left.m10() * m01 + left.m20() * m02;
        float nm01 = left.m01() * m00 + left.m11() * m01 + left.m20() * m02;
        float nm10 = left.m00() * m10 + left.m10() * m11 + left.m20() * m12;
        float nm11 = left.m01() * m10 + left.m11() * m11 + left.m20() * m12;
        float nm20 = left.m00() * m20 + left.m10() * m21 + left.m20() * m22;
        float nm21 = left.m01() * m20 + left.m11() * m21 + left.m21() * m22;
        dest.m00 = nm00;
        dest.m01 = nm01;
        dest.m10 = nm10;
        dest.m11 = nm11;
        dest.m20 = nm20;
        dest.m21 = nm21;
        return dest;
    }

    /**
     * Set the values within this matrix to the supplied float values. The result looks like this:
     * <p>
     * m00, m10, m20<br>
     * m01, m11, m21<br>
     * m02, m12, m22<br>
     *
     * @param m00 the new value of m00
     * @param m01 the new value of m01
     * @param m02 the new value of m02
     * @param m10 the new value of m10
     * @param m11 the new value of m11
     * @param m12 the new value of m12
     * @param m20 the new value of m20
     * @param m21 the new value of m21
     * @param m22 the new value of m22
     * @return this
     */
    public Matrix3f set(float m00, float m01, float m02,
                        float m10, float m11, float m12,
                        float m20, float m21, float m22) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        return this;
    }

    /**
     * Set the values in this matrix based on the supplied float array. The result looks like this:
     * <p>
     * 0, 3, 6<br>
     * 1, 4, 7<br>
     * 2, 5, 8<br>
     * <p>
     * This method only uses the first 9 values, all others are ignored.
     *
     * @param m the array to read the matrix values from
     * @return this
     */
    public Matrix3f set(float[] m) {
        MemUtil.INSTANCE.copy(m, 0, this);
        return this;
    }

    /**
     * Set the three columns of this matrix to the supplied vectors, respectively.
     *
     * @param col0 the first column
     * @param col1 the second column
     * @param col2 the third column
     * @return this
     */
    public Matrix3f set(Vector3f col0, Vector3f col1, Vector3f col2) {
        if (col0 instanceof Vector3f &&
                col1 instanceof Vector3f &&
                col2 instanceof Vector3f) {
            MemUtil.INSTANCE.set(this, col0, col1, col2);
        } else {
            setVector3f(col0, col1, col2);
        }
        return this;
    }

    /**
     * Set the elements of this matrix to the ones in <code>m</code>.
     *
     * @param m the matrix to copy the elements from
     * @return this
     */
    public Matrix3f set(Matrix3f m) {
        if (m instanceof Matrix3f) {
            MemUtil.INSTANCE.copy(m, this);
        } else {
            setMatrix3f(m);
        }
        return this;
    }

    private void setMatrix3f(Matrix3f mat) {
        m00 = mat.m00();
        m01 = mat.m01();
        m02 = mat.m02();
        m10 = mat.m10();
        m11 = mat.m11();
        m12 = mat.m12();
        m20 = mat.m20();
        m21 = mat.m21();
        m22 = mat.m22();
    }

    private void setVector3f(Vector3f col0, Vector3f col1, Vector3f col2) {
        this.m00 = col0.x();
        this.m01 = col0.y();
        this.m02 = col0.z();
        this.m10 = col1.x();
        this.m11 = col1.y();
        this.m12 = col1.z();
        this.m20 = col2.x();
        this.m21 = col2.y();
        this.m22 = col2.z();
    }

    /**
     * Computes the scalar value from the elements of a square matrix
     *
     * @return the scalar value computed from the matrix elements
     */
    public float determinant() {
        return (m00 * m11 - m01 * m10) * m22
                + (m02 * m10 - m00 * m12) * m21
                + (m01 * m12 - m02 * m11) * m20;
    }

    public Quaternionf getUnnormalizedRotation(Quaternionf dest) {
        return dest.setFromUnnormalized(this);
    }

    public Quaternionf getNormalizedRotation(Quaternionf dest) {
        return dest.setFromNormalized(this);
    }

    /**
     * Invert this matrix.
     *
     * @return this
     */
    public Matrix3f invert() {
        return invert(this);
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix3fc#invert(org.joml.Matrix3f)
     */
    public Matrix3f invert(Matrix3f dest) {
        float s = 1.0f / determinant();
        float nm00 = (m11 * m22 - m21 * m12) * s;
        float nm01 = (m21 * m02 - m01 * m22) * s;
        float nm02 = (m01 * m12 - m11 * m02) * s;
        float nm10 = (m20 * m12 - m10 * m22) * s;
        float nm11 = (m00 * m22 - m20 * m02) * s;
        float nm12 = (m10 * m02 - m00 * m12) * s;
        float nm20 = (m10 * m21 - m20 * m11) * s;
        float nm21 = (m20 * m01 - m00 * m21) * s;
        float nm22 = (m00 * m11 - m10 * m01) * s;
        dest.m00 = nm00;
        dest.m01 = nm01;
        dest.m02 = nm02;
        dest.m10 = nm10;
        dest.m11 = nm11;
        dest.m12 = nm12;
        dest.m20 = nm20;
        dest.m21 = nm21;
        dest.m22 = nm22;
        return dest;
    }

    /**
     * Transpose this matrix.
     *
     * @return this
     */
    public Matrix3f transpose() {
        return transpose(this);
    }

    /* (non-Javadoc)
     * @see org.joml.Matrix3fc#transpose(org.joml.Matrix3f)
     */
    public Matrix3f transpose(Matrix3f dest) {
        dest.set(m00, m10, m20,
                m01, m11, m21,
                m02, m12, m22);
        return dest;
    }

    /**
     * Return a string representation of this matrix.
     * <p>
     * This method creates a new {@link DecimalFormat} on every invocation with the format string "<code>0.000E0;-</code>".
     *
     * @return the string representation
     */
    public String toString() {
        DecimalFormat formatter = new DecimalFormat(" 0.000E0;-");
        String str = toString(formatter);
        StringBuilder res = new StringBuilder();
        int eIndex = Integer.MIN_VALUE;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'E') {
                eIndex = i;
            } else if (c == ' ' && eIndex == i - 1) {
                // workaround Java 1.4 DecimalFormat bug
                res.append('+');
                continue;
            } else if (Character.isDigit(c) && eIndex == i - 1) {
                res.append('+');
            }
            res.append(c);
        }
        return res.toString();
    }

    /**
     * Return a string representation of this matrix by formatting the matrix elements with the given {@link NumberFormat}.
     *
     * @param formatter the {@link NumberFormat} used to format the matrix values with
     * @return the string representation
     */
    public String toString(NumberFormat formatter) {
        return formatter.format(m00) + " " + formatter.format(m10) + " " + formatter.format(m20) + "\n"
                + formatter.format(m01) + " " + formatter.format(m11) + " " + formatter.format(m21) + "\n"
                + formatter.format(m02) + " " + formatter.format(m12) + " " + formatter.format(m22) + "\n";
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
     * Get the current values of <code>this</code> matrix and store them into
     * <code>dest</code>.
     * <p>
     * This is the reverse method of {@link #set(Matrix3f)} and allows to obtain
     * intermediate calculation results when chaining multiple transformations.
     *
     * @param dest the destination matrix
     * @return the passed in destination
     * @see #set(Matrix3f)
     */
    public Matrix3f get(Matrix3f dest) {
        return dest.set(this);
    }

    public Matrix4f get(Matrix4f dest) {
        return dest.set(this);
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

    public float m00() {
        return m00;
    }

    public void _m00(float m00) {
        this.m00 = m00;
    }

    public float m01() {
        return m01;
    }

    public void _m01(float m01) {
        this.m01 = m01;
    }

    public float m02() {
        return m02;
    }

    public void _m02(float m02) {
        this.m02 = m02;
    }

    public float m10() {
        return m10;
    }

    public void _m10(float m10) {
        this.m10 = m10;
    }

    public float m11() {
        return m11;
    }

    public void _m11(float m11) {
        this.m11 = m11;
    }

    public float m12() {
        return m12;
    }

    public void _m12(float m12) {
        this.m12 = m12;
    }

    public float m20() {
        return m20;
    }

    public void _m20(float m20) {
        this.m20 = m20;
    }

    public float m21() {
        return m21;
    }

    public void _m21(float m21) {
        this.m21 = m21;
    }

    public float m22() {
        return m22;
    }

    public void _m22(float m22) {
        this.m22 = m22;
    }
}

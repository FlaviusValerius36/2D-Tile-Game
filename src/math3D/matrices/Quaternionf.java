package math3D.matrices;

import math3D.Math;
import math3D.internal.MemUtil;
import math3D.vectors.Vector3f;
import math3D.vectors.Vector4f;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Quaternionf implements Externalizable {

    /**
     * The first component of the vector part.
     */
    public float x;

    /**
     * The second component of the vector part.
     */
    public float y;

    /**
     * The third component of the vector part.
     */
    public float z;

    /**
     * The real/scalar part of the quaternion.
     */
    public float w;


    /**
     * Create a new {@link Quaternionf} and initialize it with <code>(x=0, y=0, z=0, w=1)</code>,
     * where <code>(x, y, z)</code> is the vector part of the quaternion and <code>w</code> is the real/scalar part.
     */
    public Quaternionf() {
        this.w = 1.0f;
    }

    /**
     * Create a new {@link Quaternionf} and initialize its components to the given values.
     *
     * @param x the first component of the imaginary part
     * @param y the second component of the imaginary part
     * @param z the third component of the imaginary part
     * @param w the real part
     */
    public Quaternionf(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Create a new {@link Quaternionf} and initialize its components to the same values as the given {@link Quaternionf}.
     *
     * @param source the {@link Quaternionf} to take the component values from
     */
    public Quaternionf(Quaternionf source) {
        MemUtil.INSTANCE.copy(source, this);
    }


    /**
     * Normalize this quaternion.
     *
     * @return this
     */
    public Quaternionf normalize() {
        float invNorm = (float) (1.0 / Math.sqrt(x * x + y * y + z * z + w * w));
        x *= invNorm;
        y *= invNorm;
        z *= invNorm;
        w *= invNorm;
        return this;
    }

    /* (non-Javadoc)
     * @see org.joml.Quaternionfc#normalize(org.joml.Quaternionf)
     */
    public Quaternionf normalize(Quaternionf dest) {
        float invNorm = (float) (1.0 / Math.sqrt(x * x + y * y + z * z + w * w));
        dest.x = x * invNorm;
        dest.y = y * invNorm;
        dest.z = z * invNorm;
        dest.w = w * invNorm;
        return dest;
    }

    /**
     * Add the quaternion <code>(x, y, z, w)</code> to this quaternion.
     *
     * @param x the x component of the vector part
     * @param y the y component of the vector part
     * @param z the z component of the vector part
     * @param w the real/scalar component
     * @return this
     */
    public Quaternionf add(float x, float y, float z, float w) {
        return add(x, y, z, w, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Quaternionfc#add(float, float, float, float, org.joml.Quaternionf)
     */
    public Quaternionf add(float x, float y, float z, float w, Quaternionf dest) {
        dest.x = this.x + x;
        dest.y = this.y + y;
        dest.z = this.z + z;
        dest.w = this.w + w;
        return dest;
    }

    /**
     * Add <code>q2</code> to this quaternion.
     *
     * @param q2 the quaternion to add to this
     * @return this
     */
    public Quaternionf add(Quaternionf q2) {
        x += q2.x();
        y += q2.y();
        z += q2.z();
        w += q2.w();
        return this;
    }

    /* (non-Javadoc)
     * @see org.joml.Quaternionfc#add(org.joml.Quaternionfc, org.joml.Quaternionf)
     */
    public Quaternionf add(Quaternionf q2, Quaternionf dest) {
        dest.x = x + q2.x();
        dest.y = y + q2.y();
        dest.z = z + q2.z();
        dest.w = w + q2.w();
        return dest;
    }


    /**
     * Multiply this quaternion by <code>q</code>.
     * <p>
     * If <code>T</code> is <code>this</code> and <code>Q</code> is the given
     * quaternion, then the resulting quaternion <code>R</code> is:
     * <p>
     * <code>R = T * Q</code>
     * <p>
     * So, this method uses post-multiplication like the matrix classes, resulting in a
     * vector to be transformed by <code>Q</code> first, and then by <code>T</code>.
     *
     * @param q the quaternion to multiply <code>this</code> by
     * @return this
     */
    public Quaternionf mul(Quaternionf q) {
        return mul(q, this);
    }

    /* (non-Javadoc)
     * @see org.joml.Quaternionfc#mul(org.joml.Quaternionfc, org.joml.Quaternionf)
     */
    public Quaternionf mul(Quaternionf q, Quaternionf dest) {
        dest.set(w * q.x() + x * q.w() + y * q.z() - z * q.y(), w * q.y() - x * q.z() + y * q.w() + z * q.x(),
                w * q.z() + x * q.y() - y * q.x() + z * q.w(), w * q.w() - x * q.x() - y * q.y() - z * q.z());
        return dest;
    }

    /**
     * Multiply this quaternion by the quaternion represented via <code>(qx, qy, qz, qw)</code>.
     * <p>
     * If <code>T</code> is <code>this</code> and <code>Q</code> is the given
     * quaternion, then the resulting quaternion <code>R</code> is:
     * <p>
     * <code>R = T * Q</code>
     * <p>
     * So, this method uses post-multiplication like the matrix classes, resulting in a
     * vector to be transformed by <code>Q</code> first, and then by <code>T</code>.
     *
     * @param qx the x component of the quaternion to multiply <code>this</code> by
     * @param qy the y component of the quaternion to multiply <code>this</code> by
     * @param qz the z component of the quaternion to multiply <code>this</code> by
     * @param qw the w component of the quaternion to multiply <code>this</code> by
     * @return this
     */
    public Quaternionf mul(float qx, float qy, float qz, float qw) {
        set(w * qx + x * qw + y * qz - z * qy, w * qy - x * qz + y * qw + z * qx, w * qz + x * qy - y * qx + z * qw,
                w * qw - x * qx - y * qy - z * qz);
        return this;
    }

    /* (non-Javadoc)
     * @see org.joml.Quaternionfc#mul(float, float, float, float, org.joml.Quaternionf)
     */
    public Quaternionf mul(float qx, float qy, float qz, float qw, Quaternionf dest) {
        dest.set(w * qx + x * qw + y * qz - z * qy, w * qy - x * qz + y * qw + z * qx,
                w * qz + x * qy - y * qx + z * qw, w * qw - x * qx - y * qy - z * qz);
        return dest;
    }

    public Vector3f transform(Vector3f vec) {
        return transform(vec.x, vec.y, vec.z, vec);
    }

    public Vector3f transform(Vector3f vec, Vector3f dest) {
        return transform(vec.x(), vec.y(), vec.z(), dest);
    }

    public Vector4f transform(Vector4f vec) {
        return transform(vec, vec);
    }

    public Vector4f transform(Vector4f vec, Vector4f dest) {
        return transform(vec.x, vec.y, vec.z, dest);
    }

    public Vector4f transform(float x, float y, float z, Vector4f dest) {
        float w2 = this.w * this.w;
        float x2 = this.x * this.x;
        float y2 = this.y * this.y;
        float z2 = this.z * this.z;
        float zw = this.z * this.w, zwd = zw + zw;
        float xy = this.x * this.y, xyd = xy + xy;
        float xz = this.x * this.z, xzd = xz + xz;
        float yw = this.y * this.w, ywd = yw + yw;
        float yz = this.y * this.z, yzd = yz + yz;
        float xw = this.x * this.w, xwd = xw + xw;
        float m00 = w2 + x2 - z2 - y2;
        float m01 = xyd + zwd;
        float m02 = xzd - ywd;
        float m10 = xyd - zwd;
        float m11 = y2 - z2 + w2 - x2;
        float m12 = yzd + xwd;
        float m20 = ywd + xzd;
        float m21 = yzd - xwd;
        float m22 = z2 - y2 - x2 + w2;
        dest.x = m00 * x + m10 * y + m20 * z;
        dest.y = m01 * x + m11 * y + m21 * z;
        dest.z = m02 * x + m12 * y + m22 * z;
        return dest;
    }

    public Vector3f transform(float x, float y, float z, Vector3f dest) {
        float w2 = this.w * this.w;
        float x2 = this.x * this.x;
        float y2 = this.y * this.y;
        float z2 = this.z * this.z;
        float zw = this.z * this.w, zwd = zw + zw;
        float xy = this.x * this.y, xyd = xy + xy;
        float xz = this.x * this.z, xzd = xz + xz;
        float yw = this.y * this.w, ywd = yw + yw;
        float yz = this.y * this.z, yzd = yz + yz;
        float xw = this.x * this.w, xwd = xw + xw;
        float m00 = w2 + x2 - z2 - y2;
        float m01 = xyd + zwd;
        float m02 = xzd - ywd;
        float m10 = xyd - zwd;
        float m11 = y2 - z2 + w2 - x2;
        float m12 = yzd + xwd;
        float m20 = ywd + xzd;
        float m21 = yzd - xwd;
        float m22 = z2 - y2 - x2 + w2;
        dest.x = m00 * x + m10 * y + m20 * z;
        dest.y = m01 * x + m11 * y + m21 * z;
        dest.z = m02 * x + m12 * y + m22 * z;
        return dest;
    }

    /**
     * Set <code>this</code> quaternion to a rotation that rotates the <code>fromDir</code> vector to point along <code>toDir</code>.
     * <p>
     * Since there can be multiple possible rotations, this method chooses the one with the shortest arc.
     * <p>
     * Reference: <a href="http://stackoverflow.com/questions/1171849/finding-quaternion-representing-the-rotation-from-one-vector-to-another#answer-1171995">stackoverflow.com</a>
     *
     * @param fromDirX the x-coordinate of the direction to rotate into the destination direction
     * @param fromDirY the y-coordinate of the direction to rotate into the destination direction
     * @param fromDirZ the z-coordinate of the direction to rotate into the destination direction
     * @param toDirX   the x-coordinate of the direction to rotate to
     * @param toDirY   the y-coordinate of the direction to rotate to
     * @param toDirZ   the z-coordinate of the direction to rotate to
     * @return this
     */
    public Quaternionf rotationTo(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY,
                                  float toDirZ) {
        x = fromDirY * toDirZ - fromDirZ * toDirY;
        y = fromDirZ * toDirX - fromDirX * toDirZ;
        z = fromDirX * toDirY - fromDirY * toDirX;
        w = (float) Math.sqrt((fromDirX * fromDirX + fromDirY * fromDirY + fromDirZ * fromDirZ) *
                (toDirX * toDirX + toDirY * toDirY + toDirZ * toDirZ)) +
                (fromDirX * toDirX + fromDirY * toDirY + fromDirZ * toDirZ);
        float invNorm = (float) (1.0 / Math.sqrt(x * x + y * y + z * z + w * w));
        if (Float.isInfinite(invNorm)) {
            // Rotation is ambiguous: Find appropriate rotation axis (1. try toDir x +Z)
            x = toDirY;
            y = -toDirX;
            z = 0.0f;
            w = 0.0f;
            invNorm = (float) (1.0 / Math.sqrt(x * x + y * y));
            if (Float.isInfinite(invNorm)) {
                // 2. try toDir x +X
                x = 0.0f;
                y = toDirZ;
                z = -toDirY;
                w = 0.0f;
                invNorm = (float) (1.0 / Math.sqrt(y * y + z * z));
            }
        }
        x *= invNorm;
        y *= invNorm;
        z *= invNorm;
        w *= invNorm;
        return this;
    }

    /**
     * Set <code>this</code> quaternion to a rotation that rotates the <code>fromDir</code> vector to point along <code>toDir</code>.
     * <p>
     * Because there can be multiple possible rotations, this method chooses the one with the shortest arc.
     *
     * @param fromDir the starting direction
     * @param toDir   the destination direction
     * @return this
     * @see #rotationTo(float, float, float, float, float, float)
     */
    public Quaternionf rotationTo(Vector3f fromDir, Vector3f toDir) {
        return rotationTo(fromDir.x(), fromDir.y(), fromDir.z(), toDir.x(), toDir.y(), toDir.z());
    }

    /**
     * Set this quaternion to represent a rotation of the given radians about the x axis.
     *
     * @param angle the angle in radians to rotate about the x axis
     * @return this
     */
    public Quaternionf rotationX(float angle) {
        float sin = (float) Math.sin(angle * 0.5);
        float cos = (float) Math.cosFromSin(sin, angle * 0.5);
        w = cos;
        x = sin;
        y = 0.0f;
        z = 0.0f;
        return this;
    }

    /**
     * Set this quaternion to represent a rotation of the given radians about the y axis.
     *
     * @param angle the angle in radians to rotate about the y axis
     * @return this
     */
    public Quaternionf rotationY(float angle) {
        float sin = (float) Math.sin(angle * 0.5);
        float cos = (float) Math.cosFromSin(sin, angle * 0.5);
        w = cos;
        x = 0.0f;
        y = sin;
        z = 0.0f;
        return this;
    }

    /**
     * Set this quaternion to represent a rotation of the given radians about the z axis.
     *
     * @param angle the angle in radians to rotate about the z axis
     * @return this
     */
    public Quaternionf rotationZ(float angle) {
        float sin = (float) Math.sin(angle * 0.5);
        float cos = (float) Math.cosFromSin(sin, angle * 0.5);
        w = cos;
        x = 0.0f;
        y = 0.0f;
        z = sin;
        return this;
    }

    /**
     * Set this quaternion to the given values.
     *
     * @param x the new value of x
     * @param y the new value of y
     * @param z the new value of z
     * @param w the new value of w
     * @return this
     */
    public Quaternionf set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    /**
     * Set this quaternion to be a copy of q.
     *
     * @param q the {@link Quaternionf} to copy
     * @return this
     */
    public Quaternionf set(Quaternionf q) {
        if (q instanceof Quaternionf)
            MemUtil.INSTANCE.copy(q, this);
        else {
            this.x = q.x();
            this.y = q.y();
            this.z = q.z();
            this.w = q.w();
        }
        return this;
    }

    /**
     * @return the first component of the vector part
     */
    public float x() {
        return this.x;
    }

    /**
     * @return the second component of the vector part
     */
    public float y() {
        return this.y;
    }

    /**
     * @return the third component of the vector part
     */
    public float z() {
        return this.z;
    }

    /**
     * @return the real/scalar part of the quaternion
     */
    public float w() {
        return this.w;
    }

    /**
     * Set this quaternion to be a representation of the rotational component of the given matrix.
     * <p>
     * This method assumes that the first three columns of the upper left 3x3 submatrix are no unit vectors.
     *
     * @param mat the matrix whose rotational component is used to set this quaternion
     * @return this
     */
    public Quaternionf setFromUnnormalized(Matrix3f mat) {
        setFromUnnormalized(mat.m00(), mat.m01(), mat.m02(), mat.m10(), mat.m11(), mat.m12(), mat.m20(), mat.m21(),
                mat.m22());
        return this;
    }

    /**
     * Set this quaternion to be a representation of the rotational component of the given matrix.
     * <p>
     * This method assumes that the first three columns of the upper left 3x3 submatrix are unit vectors.
     *
     * @param mat the matrix whose rotational component is used to set this quaternion
     * @return this
     */
    public Quaternionf setFromNormalized(Matrix3f mat) {
        setFromNormalized(mat.m00(), mat.m01(), mat.m02(), mat.m10(), mat.m11(), mat.m12(), mat.m20(), mat.m21(),
                mat.m22());
        return this;
    }

    private void setFromUnnormalized(float m00, float m01, float m02, float m10, float m11, float m12, float m20,
                                     float m21, float m22) {
        float nm00 = m00, nm01 = m01, nm02 = m02;
        float nm10 = m10, nm11 = m11, nm12 = m12;
        float nm20 = m20, nm21 = m21, nm22 = m22;
        float lenX = (float) (1.0 / Math.sqrt(m00 * m00 + m01 * m01 + m02 * m02));
        float lenY = (float) (1.0 / Math.sqrt(m10 * m10 + m11 * m11 + m12 * m12));
        float lenZ = (float) (1.0 / Math.sqrt(m20 * m20 + m21 * m21 + m22 * m22));
        nm00 *= lenX;
        nm01 *= lenX;
        nm02 *= lenX;
        nm10 *= lenY;
        nm11 *= lenY;
        nm12 *= lenY;
        nm20 *= lenZ;
        nm21 *= lenZ;
        nm22 *= lenZ;
        setFromNormalized(nm00, nm01, nm02, nm10, nm11, nm12, nm20, nm21, nm22);
    }

    private void setFromNormalized(float m00, float m01, float m02, float m10, float m11, float m12, float m20,
                                   float m21, float m22) {
        float t;
        float tr = m00 + m11 + m22;
        if (tr >= 0.0f) {
            t = (float) Math.sqrt(tr + 1.0f);
            w = t * 0.5f;
            t = 0.5f / t;
            x = (m12 - m21) * t;
            y = (m20 - m02) * t;
            z = (m01 - m10) * t;
        } else {
            if (m00 >= m11 && m00 >= m22) {
                t = (float) Math.sqrt(m00 - (m11 + m22) + 1.0);
                x = t * 0.5f;
                t = 0.5f / t;
                y = (m10 + m01) * t;
                z = (m02 + m20) * t;
                w = (m12 - m21) * t;
            } else if (m11 > m22) {
                t = (float) Math.sqrt(m11 - (m22 + m00) + 1.0);
                y = t * 0.5f;
                t = 0.5f / t;
                z = (m21 + m12) * t;
                x = (m10 + m01) * t;
                w = (m20 - m02) * t;
            } else {
                t = (float) Math.sqrt(m22 - (m00 + m11) + 1.0);
                z = t * 0.5f;
                t = 0.5f / t;
                x = (m02 + m20) * t;
                y = (m21 + m12) * t;
                w = (m01 - m10) * t;
            }
        }
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
        out.writeFloat(x);
        out.writeFloat(y);
        out.writeFloat(z);
        out.writeFloat(w);
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
        x = in.readFloat();
        y = in.readFloat();
        z = in.readFloat();
        w = in.readFloat();
    }
}

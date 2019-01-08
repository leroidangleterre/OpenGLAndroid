package com.example.arthurmanoha.openglandroid;

import android.util.Log;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Vector {

    private float x, y, z;

    public Vector() {
        this(0, 0, 0);
    }

    public Vector(float newX, float newY, float newZ) {
        x = newX;
        y = newY;
        z = newZ;
    }

    @Override
    public Vector clone() {
        return new Vector(x, y, z);
    }


    public boolean equals(Vector v) {
        return x == v.x && y == v.y && z == v.z;
    }

    public String toString() {
        return "{" + x + ", " + y + ", " + z + "}";
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getNorm() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Changes this vector.
     */
    public void add(Vector v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
    }

    public void subtract(Vector v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
    }

    public void add(float dx, float dy, float dz) {
        this.x += dx;
        this.y += dy;
        this.z += dz;
    }

    /**
     * Returns a new vector that is the difference between this vector and the parameter.
     */
    public Vector diff(Vector other) {
        return new Vector(x - other.x, y - other.y, z - other.z);
    }

    /**
     * Returns a newly created vector that is the sum of this and the parameter.
     */
    public Vector sum(Vector v) {
        return new Vector(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    /**
     * Returns a newly created vector that is the scalar product of this and the parameter.
     */
    public Vector mult(float fact) {
        return new Vector(fact * x, fact * y, fact * z);
    }

    /**
     * Rotate the empty around the global X-axis.
     */
    public void rotateGlobalX(float angle) {
        float oldY = y;
        float oldZ = z;
        y = (float) (oldY * cos(angle) - oldZ * sin(angle));
        z = (float) (oldZ * cos(angle) + oldY * sin(angle));
    }

    /**
     * Rotate the empty around the global Y-axis.
     */
    public void rotateGlobalY(float angle) {
        float oldX = x;
        float oldZ = z;
        x = (float) (oldX * cos(angle) + oldZ * sin(angle));
        z = (float) (-oldX * sin(angle) + oldZ * cos(angle));
    }

    /**
     * Rotate the vector around the global Z axis.
     */
    public void rotateGlobalZ(float angle) {
        float oldX = x;
        float oldY = y;
        x = (float) (oldX * cos(angle) - oldY * sin(angle));
        y = (float) (oldX * sin(angle) + oldY * cos(angle));
    }

    /**
     * Return the dot product between this and another vector.
     */
    public float dot(Vector v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    /**
     * Return the cross product of this and another vector.
     */
    public Vector vectorProduct(Vector v) {
        Vector result = new Vector();
        result.x = this.y * v.z - this.z * v.y;
        result.y = -this.x * v.z + this.z * v.x;
        result.z = this.x * v.y - this.y * v.z;
        return result;
    }

}

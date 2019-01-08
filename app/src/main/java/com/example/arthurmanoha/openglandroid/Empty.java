package com.example.arthurmanoha.openglandroid;

import android.util.Log;

import static com.example.arthurmanoha.openglandroid.HelloOpenGLES10.TAG;


/**
 * This class describes the too that positions and orients a solid or a camera in space.
 */

public class Empty {

    private Vector pos;
    private Vector target, left, vertic; // Using the position of the empty as a start.
    private float width; // Half-distance between the two cameras
    private Vector posLeft, posRight;
    private float distanceToTarget; // The distance to the target is the norm of the target vector.
    private float zAngle; // Total rotation around the Z-axis applied on the empty.

    public Empty() {
        pos = new Vector();
        resetRotation();
        width = 0.3f;
        resetRotation();
        computeLeftRightPos();
        distanceToTarget = 1;
    }

    public void resetRotation() {
        target = new Vector(distanceToTarget, 0, 0);
        left = new Vector(0, 1, 0);
        vertic = new Vector(0, 0, 1);
        zAngle = 0;
    }

    private void computeLeftRightPos() {
        posLeft = pos.sum(left.mult(width));
        posRight = pos.sum(left.mult(-width));
        posLeft = pos.clone();
        posRight = pos.clone();
    }

    /**
     * Set the position of the origin.
     */
    public void setPos(float x, float y, float z) {
        pos = new Vector(x, y, z);
        computeLeftRightPos();
    }

    /**
     * Set the target of the empty, from the referential of its current position.
     *
     * @param xTarget, yTarget, zTarget the components of the new target
     */
    public void setTarget(float xTarget, float yTarget, float zTarget) {
        this.target = new Vector(xTarget, yTarget, zTarget);
        distanceToTarget = target.getNorm();
        computeLeftVector();
    }

    /**
     * Compute the coordinates of the left vector, using the current target and after making sure
     * that the vertical vector is properly set.
     */
    private void computeLeftVector() {
        vertic = new Vector(0, 0, 1);
        left = vertic.vectorProduct(target);
    }

    /**
     * Get the position of the empty
     *
     * @return the current position
     */

    public Vector getPos() {
        return pos;
    }

//    /**
//     * Set the position of the target.
//     */
//    public void setTarget(float x, float y, float z) {
//        target = new Vector(x, y, z);
//        computeLeftRightPos();
//    }
//
//    public void setVertic(float x, float y, float z) {
//        vertic = new Vector(x, y, z);
//        computeLeftRightPos();
//    }

    /**
     * Get the position of a point located on the left of this empty.
     */
    public Vector getLeftPos() {
        return posLeft;
    }

    /**
     * Get the position of a point located on the left of this empty.
     */
    public Vector getRightPos() {
        return posRight;
    }

    public Vector getTarget() {
        return target;
    }

    public Vector getVertic() {
        return vertic;
    }

    /**
     * Rotate the empty around the global X-axis.
     */
    public void rotateGlobalX(float angle){
        target.rotateGlobalX(angle);
        left.rotateGlobalX(angle);
        vertic.rotateGlobalX(angle);
    }

    /**
     * Rotate the empty around the global Y-axis.
     */
    public void rotateGlobalY(float angle) {
        target.rotateGlobalY(angle);
        left.rotateGlobalY(angle);
        vertic.rotateGlobalY(angle);
    }

    /**
     * Rotate the empty around its local Y-axis.
     */
    public void rotateLocalY(float angle) {
        float currentZAngle = this.zAngle;
        // Step 1: relocate the empty so that it faces the X-axis, looking toward positive values.
        rotateGlobalZ(-currentZAngle);

        // Step 2: perform the rotation around the global Y-axis.
        rotateGlobalY(angle);

        // Step 3: apply the inverse of the z-rotation in step 1.
        rotateGlobalZ(currentZAngle);
    }


    /**
     * Rotate the empty around the Z axis, without changing its center position.
     *
     * @param angle
     */
    public void rotateGlobalZ(float angle) {

        target.rotateGlobalZ(angle);
        left.rotateGlobalZ(angle);
        vertic.rotateGlobalZ(angle);
//        computeLeftRightPos();
    }

    /**
     * Rotate the empty around the Z axis while maintaining the target at the same place.
     *
     * @param angle
     */
    public void rotateGlobalZAroundTarget(float angle) {
        Vector realTarget = pos.sum(target);
        rotateGlobalZ(angle);
        centerOnTarget(realTarget);
        Log.d(TAG, "rotateGlobalZAroundTarget: centering on " + realTarget);
    }

    /**
     * Translate the empty so that the target is located at the desired position.
     *
     * @param newTarget the new target
     */
    public void centerOnTarget(Vector newTarget) {
        Vector diff = newTarget.diff(pos.sum(target));
//        Log.d(TAG, "centerOnTarget: pos is " + pos + ", target is " + target + ", diff is " + diff);

        pos.add(diff);
//        Log.d(TAG, "centerOnTarget: new target norm: " + target.getNorm());
    }

    public void centerOnTarget(float x, float y, float z) {
        centerOnTarget(new Vector(x, y, z));
    }

    /**
     * Translate the empty without changing its orientation.
     *
     * @param dx
     * @param dy
     * @param dz
     */
    public void translate(float dx, float dy, float dz) {
        pos.add(dx, dy, dz);
    }
}

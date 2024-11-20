// 322657909 Ziv Olewsky
/**
 * An interface for objects that can collide with other objects.
 */
public interface Collidable {
    /**

     Returns the "collision shape" of the object.
     @return a Rectangle object representing the collision shape of the object
     */
// Return the "collision shape" of the object.
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
// Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    /**

     Notifies the object that a collision has occurred at the specified point
     with the given velocity.
     @param collisionPoint the point at which the collision occurred.
     @param currentVelocity the current velocity of the colliding object.
     @param hitter the Ball hitter.
     @return the new velocity expected after the hit.
     */

    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

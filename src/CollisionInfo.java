// 322657909 Ziv Olewsky
/**
 * A class representing information about a collision between objects.
 * Contains a collision point and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collidable;

    /**
     * Constructs a new CollisionInfo object with the specified collision point and collidable object.
     *
     * @param collisionPoint the point at which the collision occurred.
     * @param collidable     the collidable object involved in the collision.
     */
// constructor
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /**
     * Returns the point at which the collision occurred.
     *
     * @return the collision point
     */
// the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
// the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collidable;
    }
}
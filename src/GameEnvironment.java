// 322657909 Ziv Olewsky

import java.util.ArrayList;

/**
 * The GameEnvironment class is responsible for managing the collidable objects in the game, including adding new
 * collidables and detecting collisions with the given trajectory.
 */
public class GameEnvironment {
    private final ArrayList<Collidable> arr;

    /**
     * Instantiates a new Game environment.
     *
     * @param arr the arr
     */
    public GameEnvironment(ArrayList<Collidable> arr) {
        this.arr = arr;
    }

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.arr = new ArrayList<Collidable>();
    }


    /**
     * Add collidable.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.arr.add(c);
    }


    /**
     * Get closest collision collision info.
     *
     * @param trajectory the trajectory
     * @return the collision info
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point[] collPoints = new Point[this.arr.size()];
        for (int i = 0; i < collPoints.length; i++) {
            // Sets new rectangle of each "Collidable" object
            Rectangle rectangle1 = new Rectangle(arr.get(i).getCollisionRectangle().getUpperLeft(),
                    arr.get(i).getCollisionRectangle().getWidth(),
                    arr.get(i).getCollisionRectangle().getHeight());
            // Add the closest Point of each rectanle and the trajectory to an array
            collPoints[i] = trajectory.closestIntersectionToStartOfLine(rectangle1);
        }
        // no collide point.
        boolean nullFlag = true;
        //flag for the first iteration.
        boolean firstIteration = true;
        double minDistance = 0;
        // the index for the min distance collation point.
        int index = 0;
        // Loop for check the min distance
        for (int i = 0; i < collPoints.length; i++) {
            if (collPoints[i] != null) {
                // There is a collision point
                nullFlag = false;
                if (firstIteration) {
                    minDistance = trajectory.start().distance(collPoints[i]);
                    index = i;
                    firstIteration = false;
                } else {
                    if (trajectory.start().distance(collPoints[i]) < minDistance) {
                        // Replace to a the new min distance
                        minDistance = trajectory.start().distance(collPoints[i]);
                        // Save the index of the min Distance
                        index = i;
                    }
                }
            }
        }
        // if there is no collide with any of the collidables.
        if (nullFlag) {
            return null;
        }
        return new CollisionInfo(collPoints[index], this.arr.get(index));
    }


    /**
     * If inside block boolean.
     *
     * @param p      the p
     * @param radius the radius
     * @return the boolean
     */
    public Boolean ifInsideBlock(Point p, int radius) {
        for (int i = 0; i < this.arr.size(); i++) {
            if (arr.get(i).getCollisionRectangle().checkIfPointInsideRec(p, radius)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return list array list.
     *
     * @return the array list
     */
    public ArrayList returnList() {
        return this.arr;
    }
}

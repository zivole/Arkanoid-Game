// 322657909 Ziv Olewsky
/**
 * The type Point.
 */
public class Point {
    private static final double EPSILON = 0.0001;
    private double x;
    private double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the double
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double distance = ((other.x - this.x) * (other.x - this.x)) + ((other.y - this.y) * (other.y - this.y));
        return Math.sqrt(distance);
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        return (Math.abs(this.x - other.x) < EPSILON) && (Math.abs(this.y - other.y) < EPSILON);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// Return the x and y values of this point
    public double getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x2 the x 2
     */
    public void setX(double x2) {
        this.x = x2;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y2 the y 2
     */
    public void setY(double y2) {
        this.y = y2;
    }
}
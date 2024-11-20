// 322657909 Ziv Olewsky
/**
 * The type Velocity.
 * set the velocity of an object by dx and dy
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
// constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Instantiates a new Velocity.
     */
    public Velocity() {
        Velocity v = new Velocity();
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Get dx double.
     *
     * @return the double
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Set dx.
     *
     * @param dx the dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Get dy double.
     *
     * @return the double
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Set dy.
     *
     * @param dy the dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Apply to point point.
     *
     * @param p the p
     * @return the point
     */
// Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        double plusDx = p.getX() + this.dx;
        double plusDy = p.getY() + this.dy;
        return new Point(plusDx, plusDy);
    }
}


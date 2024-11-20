// 322657909 Ziv Olewsky
import java.util.ArrayList;

/**
 * A class representing a Rectangle object.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;
    private Rectangle rectangle;

    /**
     * Creates a new Rectangle object with the specified upper left point, width, and height.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }


    /**
     * Get width double.
     *
     * @return the double
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Get height double.
     *
     * @return the double
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get upper left point.
     *
     * @return the point
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * Returns a list of intersection points between the rectangle and the specified line.
     *
     * @param line the line to check for intersection.
     * @return a list of intersection points between the rectangle and the specified line.
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> listCollusion = new ArrayList<>();
        // upper right point
        Point p1 = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        // down right point
        Point p2 = new Point(p1.getX(), p1.getY() + this.height);
        // down left point
        Point p3 = new Point(this.upperLeft.getX(), p2.getY());
        // create the lines
        Line l1 = new Line(this.upperLeft, p1);
        Line l2 = new Line(p2, p1);
        Line l3 = new Line(p3, p2);
        Line l4 = new Line(p3, this.upperLeft);
        Line[] arr = new Line[4];
        arr[0] = l1;
        arr[1] = l2;
        arr[2] = l3;
        arr[3] = l4;
        // check for intersection and add it to an array if true
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].isIntersecting(line)) {
                listCollusion.add(arr[i].intersectionWith(line));
            }
        }
        return listCollusion;
    }

    /**
     * Check if point inside the rectangle.
     *
     * @param p      the p
     * @param radius the radius
     * @return true or false
     */
    public Boolean checkIfPointInsideRec(Point p, int radius) {
        double x1 = p.getX();
        double y1 = p.getY();
        double xUpperRight = this.upperLeft.getX() + this.width;
        double yUpperRight = this.upperLeft.getY();
        double yBottomRight = yUpperRight;
        double yBottemLeft = yBottomRight;


        return (x1 >= Math.min(this.upperLeft.getX(), xUpperRight) - (radius / 2)
                && x1 <= Math.max(this.upperLeft.getX(), xUpperRight) + (radius / 2)
                && y1 >= Math.min(this.upperLeft.getY(), yBottemLeft) - (radius / 2)
                && y1 <= Math.max(this.upperLeft.getY(), yBottemLeft) + (radius / 2));
    }
}
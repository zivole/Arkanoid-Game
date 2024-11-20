// 322657909 Ziv Olewsky

import java.util.List;

/**
 * The type Line.
 */
public class Line {
    private final Point start;
    private final Point end;
    private static final double EPSILON = 1E-10;


    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Length double.
     *
     * @return the double
     */
// Return the length of the line
    public double length() {
        return start.distance(end());
    }

    /**
     * Middle point.
     *
     * @return the point
     */
// Returns the middle point of the line
    public Point middle() {
        double x = (this.start().getX() + this.end().getX()) / 2;
        double y = (this.start().getY() + this.end().getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Start point.
     *
     * @return the point
     */
// Returns the start point of the line
    public Point start() {
        return start;
    }

    /**
     * End point.
     *
     * @return the point
     */
// Returns the end point of the line
    public Point end() {
        return end;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return the boolean
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        // Extract coordinates of the start and end points of both lines.
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        // Calculate the determinant of the matrix representing the lines
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        // Check for special cases where lines share a common point
        if (this.start.equals(other.end)) {
            return this.start;
        }
        if (this.start.equals(other.start)) {
            return this.start;
        }
        if (this.end.equals(other.end)) {
            return this.end;
        }
        if (this.end.equals(other.start)) {
            return this.end;
        }
        // parallel

        if (d == 0) {
            return null;
        }
        // Calculate intersection point

        double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
        double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
        // Check if the intersection point is within the bounds of both lines

        if (xi >= Math.min(x1, x2) - EPSILON && xi <= Math.max(x1, x2) + EPSILON && xi >= Math.min(x3, x4)
                - EPSILON && xi <= Math.max(x3, x4) + EPSILON
                && yi >= Math.min(y1, y2) - EPSILON && yi <= Math.max(y1, y2) + EPSILON && yi >= Math.min(y3, y4)
                - EPSILON && yi <= Math.max(y3, y4) + EPSILON) {
            return new Point(xi, yi);
        } else {
            return null;
        }
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        boolean flag1, flag2;
        flag1 = this.start().equals(other.start()) && this.end().equals(other.end());
        flag2 = this.start().equals(other.end()) && this.end().equals(other.start());
        return flag2 || flag1;
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the closest point
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> listInter = rect.intersectionPoints(this);
        //no intersections.
        if (listInter.size() == 0) {
            return null;
        }
        //only one intersection - must be the closest one.
        if (listInter.size() == 1) {
            return listInter.get(0);
        }
        //two intersections.
        double d1 = this.start().distance(listInter.get(0));
        double d2 = this.start().distance(listInter.get(1));

        //check who is closer
        if (d1 < d2) {
            return listInter.get(0);
        } else {
            return listInter.get(1);
        }
    }
}


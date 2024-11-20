// 322657909 Ziv Olewsky

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The Block class represents a collidable block in the game.
 * It implements both the Collidable and Sprite interfaces.
 * The block can be added to the game by calling the addToGame method.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final double EPSILON = 1E-10;
    private final Rectangle block;
    private final List<HitListener> hitListeners;
    private final java.awt.Color color;

    /**
     * Constructs a new Block object with the given rectangle shape and color.
     *
     * @param block the rectangle shape of the block
     * @param color the color of the block
     */
    public Block(Rectangle block, Color color) {
        this.block = block;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }
    // private List<HitListener> hitListeners = new LinkedList<HitListener>();

    // Return the "collision shape" of the object.

    /**
     * @return the collision rectangle of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * Compare points boolean.
     *
     * @param p1 the p 1
     * @param p2 the p 2
     * @return the boolean
     */
    public Boolean comparePoints(Point p1, Point p2) {
        return Math.abs(p1.getX() - p2.getX()) <= EPSILON && Math.abs(p1.getY() - p2.getY()) <= EPSILON;
    }

    // Notify the object that we collided with it at collisionPoint with a given velocity.
    // The return is the new velocity expected after the hit
    // (based on the force the object inflicted on us).

    /**
     * Notifies the object that it has been hit at the given collision point with a given velocity,
     * and returns the new velocity expected after the hit based on the force of the object inflicted on us.
     *
     * @param collisionPoint  the collision point with the block.
     * @param currentVelocity the current velocity of the object.
     * @return the new velocity expected after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point upperRight = new Point(this.block.getUpperLeft().getX() + this.block.getWidth(),
                this.block.getUpperLeft().getY());
        Point downRight = new Point(upperRight.getX(), upperRight.getY() + this.block.getHeight());
        Point downLeft = new Point(this.block.getUpperLeft().getX(), downRight.getY());
        //check the corners
        if (comparePoints(collisionPoint, upperRight) || comparePoints(collisionPoint, this.block.getUpperLeft())
                || comparePoints(collisionPoint, downLeft) || comparePoints(collisionPoint, downRight)) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy() * (-1));
            //left wall
        } else if (Math.abs(collisionPoint.getX() - getCollisionRectangle().getUpperLeft().getX()) < EPSILON
                && collisionPoint.getY() >= getCollisionRectangle().getUpperLeft().getY()
                && collisionPoint.getY() <= downLeft.getY()) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
            //upper wall
        } else if (Math.abs(collisionPoint.getY() - getCollisionRectangle().getUpperLeft().getY()) < EPSILON
                && collisionPoint.getX() >= getCollisionRectangle().getUpperLeft().getX()
                && collisionPoint.getX() <= upperRight.getX()) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
            //right wall
        } else if (Math.abs(collisionPoint.getX() - downRight.getX()) < EPSILON
                && collisionPoint.getY() >= upperRight.getY()
                && collisionPoint.getY() <= downRight.getY()) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
            //floor
        } else if (Math.abs(collisionPoint.getY() - downRight.getY()) < EPSILON
                && collisionPoint.getX() >= downLeft.getX()
                && collisionPoint.getX() <= downRight.getX()) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * A method that draw the block on the DrawSurface by the
     * color, upperleft Point, width and height.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }

    /**
     * By the time is passing, the block shold not move or change anything
     * so the method does not do nothing.
     */
    @Override
    public void timePassed() {
        // nothing
    }

    /**
     * Add the Sprites and the Colliadables to game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Gets rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return this.block;
    }

    /**
     * Remove the sprite and the collidable from game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * notify that a hit was occurred.
     *
     * @param hitter the ball that hit the block
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    // Add hl as a listener to hit events.

    /**
     * add a listener to the list.
     *
     * @param hl the Listener
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    // Remove hl from the list of listeners to hit events.

    /**
     * remove a listener to the list.
     *
     * @param hl the Listener
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}




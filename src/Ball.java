// 322657909 Ziv Olewsky
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Ball.
 */
public class Ball implements Sprite {
    private final int r;
    private final Color color;
    private Velocity velocity;
    private Point center;
    private GameEnvironment game;

    /**
     * Instantiates a new Ball.
     *
     * @param center the center point
     * @param r      the radius
     * @param color  the color
     * @param game   the game
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.game = game;
        Velocity v = new Velocity(0, 0);
        this.velocity = v;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x value
     * @param y     the y value
     * @param r     the r value
     * @param color the color
     * @param game  the game
     */
    public Ball(int x, int y, int r, Color color, GameEnvironment game) {
        Point p = new Point(x, y);
        this.center = p;
        this.r = r;
        this.game = game;
        this.color = color;
        Velocity v = new Velocity(0, 0);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Gets the radius.
     *
     * @return the size
     */
    public int getSize() {
        return r;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return color;
    }


    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.velocity = v;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Move the ball one step.
     */
    public void moveOneStep() {
        CollisionInfo c = null;
        Line l1 = new Line(this.center.getX(), this.center.getY(), this.center.getX()
        + this.velocity.getDx() + this.r, this.center.getY() + this.velocity.getDy() + this.r);
        Line l2 = new Line(this.center.getX(), this.center.getY(), this.center.getX() + this.velocity.getDx()
                - this.r, this.center.getY() + this.velocity.getDy() - this.r);
        if (game.getClosestCollision(l1) != null) {
            c = game.getClosestCollision(l1);
            Collidable collidable = c.collisionObject();
            //set the velocity after start the hit method
            setVelocity(collidable.hit(this, c.collisionPoint(), this.velocity));
        } else if (game.getClosestCollision(l2) != null) {
            c = game.getClosestCollision(l2);
            Collidable collidable = c.collisionObject();
            //set the velocity after start the hit method
            setVelocity(collidable.hit(this, c.collisionPoint(), this.velocity));
        }
        // temp point that makes the next move, to check if the next step is inside the rectangle
        Point p = this.getVelocity().applyToPoint(this.center);
        if (game.ifInsideBlock(p, this.r)) {
            if (c != null && c.collisionObject() instanceof Block) {
                Block block = (Block) c.collisionObject();
                block.notifyHit(this);
            }
            // set the velocity
            this.velocity = new Velocity((-1) * this.velocity.getDx(), (-1) * this.velocity.getDy());
        }
        // make one step
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * The drawOn() method draws the ball on a given DrawSurface object.
     * It sets the color of the ball,
     * fills a ball with the given sizes of the ball object.
     * @param d the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        d.setColor(Color.BLACK);
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Sets game environment.
     *
     * @param game the game
     */
    public void setGameEnvironment(GameEnvironment game) {
        this.game = game;
    }

    /**
     * Gets game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.game;
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove a sprite from game.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}


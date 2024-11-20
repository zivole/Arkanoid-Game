// 322657909 Ziv Olewsky

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * This code defines a class Paddle which implements Sprite and Collidable interfaces,
 * representing a paddle in a game of.
 * The paddle is controlled by the player using the left and right arrow keys on the keyboard.
 */
public class Paddle implements Sprite, Collidable {
    private static final double EPSILON = 1E-10;
    private static final int BORDER_LEFT = 5;
    private static double STEP = 15;
    private static final double BORDER_RIGHT = 795;
    private final biuoop.KeyboardSensor keyboard;
    private final GameEnvironment game;
    private Color color;
    private Block paddle;


    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard the keyboard
     * @param paddle   the paddle
     * @param game     the game environment
     */
    public Paddle(KeyboardSensor keyboard, Block paddle, GameEnvironment game) {
        this.keyboard = keyboard;
        this.paddle = paddle;
        this.game = game;
    }


    /**
     * Move  the keybaord left.
     */
    public void moveLeft() {
        if (this.paddle.getRectangle().getUpperLeft().getX() <= BORDER_LEFT) {
            // the paddle is on the edge, do not move it
        } else {
            Point point = new Point(this.paddle.getRectangle().getUpperLeft().getX() - STEP,
                    this.paddle.getRectangle().getUpperLeft().getY());
            Rectangle paddleRec = new Rectangle(point, this.paddle.getRectangle().getWidth(),
                    this.paddle.getRectangle().getHeight());
            Block paddleBlock = new Block(paddleRec, this.color);
            this.paddle = paddleBlock;
        }
    }

    /**
     * Move  the keybaord right.
     */
    public void moveRight() {
        if (this.paddle.getRectangle().getUpperLeft().getX() + paddle.getRectangle().getWidth() >= BORDER_RIGHT) {
            // the paddle is on the edge, do not move it
        } else {
            Point p2 = new Point((int) (this.paddle.getRectangle().getUpperLeft().getX() + STEP),
                    (int) (this.paddle.getRectangle().getUpperLeft().getY()));
            Rectangle paddleRec = new Rectangle(p2, this.paddle.getRectangle().getWidth(),
                    this.paddle.getRectangle().getHeight());
            Block paddleBlock = new Block(paddleRec, this.color);
            this.paddle = paddleBlock;
        }
    }

    /**
     * check if the paddle move right or left, and do so.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * The drawOn() method draws the paddle on a given DrawSurface object.
     * It sets the color of the paddle,
     * fills a rectangle with the given sizes of the Block object representing the paddle,
     * and draws a black outline around the rectangle.
     *
     * @param d the DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.paddle.getRectangle().getUpperLeft().getX(),
                (int) this.paddle.getRectangle().getUpperLeft().getY(),
                (int) this.paddle.getRectangle().getWidth(),
                (int) this.paddle.getRectangle().getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddle.getRectangle().getUpperLeft().getX(),
                (int) this.paddle.getRectangle().getUpperLeft().getY(),
                (int) this.paddle.getRectangle().getWidth(),
                (int) this.paddle.getRectangle().getHeight());
    }

    /**
     * returns the paddle rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle.getRectangle();
    }

    /**
     * the method check where the hit of an object occured, and give a the right speed.
     * and the right angle after the hit, depend on the hitting place on the paddle
     *
     * @param collisionPoint  the coliision point
     * @param currentVelocity the current velocity
     * @param hitter          the hitter ball
     */


    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point p = new Point(collisionPoint.getX(), this.paddle.getRectangle().getUpperLeft().getY());
        double distanceFromStart = this.paddle.getRectangle().getUpperLeft().distance(p);
        //double sizePaddle = this.paddle.getRectangle().getWidth();
        // if the object is "almost" hit the paddle
        if (Math.abs(collisionPoint.getY() - this.paddle.getRectangle().getUpperLeft().getY()) < EPSILON) {
            double speed = Math.sqrt((currentVelocity.getDx() * currentVelocity.getDx())
                    + (currentVelocity.getDy() * currentVelocity.getDy()));
            // the first fifth of the paddle
            if (Math.abs(distanceFromStart - this.paddle.getRectangle().getUpperLeft().getX())
                    <= collisionPoint.getX() * 0.2) {
                Velocity newV = Velocity.fromAngleAndSpeed(60, speed);
                newV = new Velocity(newV.getDx(), (-1) * newV.getDy());
                return newV;
            }
            // the second fifth of the paddle
            if (Math.abs(distanceFromStart - this.paddle.getRectangle().getUpperLeft().getX())
                    <= collisionPoint.getX() * 0.4) {
                Velocity newV = Velocity.fromAngleAndSpeed(30, speed);
                newV = new Velocity(newV.getDx(), (-1) * newV.getDy());
                return newV;
            }
            // the third fifth of the paddle
            if (Math.abs(distanceFromStart - this.paddle.getRectangle().getUpperLeft().getX())
                    <= collisionPoint.getX() * 0.6) {
                return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            }
            // the fourth fifth of the paddle
            if (Math.abs(distanceFromStart - this.paddle.getRectangle().getUpperLeft().getX())
                    <= collisionPoint.getX() * 0.8) {
                Velocity newV = Velocity.fromAngleAndSpeed(330, speed);
                newV = new Velocity(newV.getDx(), (-1) * newV.getDy());
                return newV;
            }
            // the five fifth of the paddle
            if (Math.abs(distanceFromStart - this.paddle.getRectangle().getUpperLeft().getX())
                    <= collisionPoint.getX()) {
                Velocity newV = Velocity.fromAngleAndSpeed(300, speed);
                newV = new Velocity(newV.getDx(), (-1) * newV.getDy());
                return newV;
            }
        }
        // if the object is not close to the paddle
        return currentVelocity;
    }

    /**
     * Add to the Sprites and the Collidables to the Game.
     *
     * @param g the game
     */
// Add this paddle to the game.
    //@Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Sets paddle speed.
     *
     * @param speed the speed
     */
    public void setPaddleSpeed(int speed) {
        STEP = speed;
    }

}

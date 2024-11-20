// 322657909 Ziv Olewsky

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Arkanoid game. It holds the game environment and sprites,
 * and manages the initialization and running of the game.
 */
public class GameLevel implements Animation {
    private static final int GUI_SIZE_X = 800;
    private static final int GUI_SIZE_Y = 600;
    private static final int HUNDRED = 100;
    private final GUI gui;
    private final Counter counterBlock = new Counter();
    private final Counter counterBall = new Counter();
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;


    /**
     * Constructs a new Game.
     *
     * @param levelInformation the level information
     * @param keyboardSensor   the keyboard sensor
     * @param runner           the runner
     * @param counter          the counter
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     AnimationRunner runner, Counter counter) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = runner.getGui();
        this.runner = new AnimationRunner(gui, 60);
        this.keyboard = this.gui.getKeyboardSensor();
        this.score = counter;
        this.levelInformation = levelInformation;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes the game by creating the blocks, ball, and a paddle,
     * and adding them to the game Gui.
     */
// Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        Sprite backgroundBlock = levelInformation.getBackground();
        this.sprites.addSprite(backgroundBlock);
        // Border Rectangle from UP
        Rectangle upR = new Rectangle(new Point(0, 0), 800, 20);
        // Border Rectangle from DOWN
        Rectangle downR = new Rectangle(new Point(0, 595), 800, 5);
        // Border Rectangle from LEFT
        Rectangle leftR = new Rectangle(new Point(0, 5), 5, 590);
        // Border Rectangle from RIGHT
        Rectangle rightR = new Rectangle(new Point(795, 5), 5, 590);
        // Set the colors of the BORDER BLOCKS
        Block left = new Block(leftR, Color.BLACK);
        Block right = new Block(rightR, Color.BLACK);
        Block up = new Block(upR, Color.BLACK);
        Block down = new Block(downR, Color.BLACK);
        // Adding the borders into the GAME
        left.addToGame(this);
        right.addToGame(this);
        up.addToGame(this);
        down.addToGame(this);
        List listOfBlocks = levelInformation.blocks();
        counterBlock.increase(listOfBlocks.size());
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        ScoreIndicator s = new ScoreIndicator(scoreTrackingListener, levelInformation.levelName());
        s.addToGame(this);
        BlockRemover p = new BlockRemover(this, counterBlock);
        for (int i = 0; i < listOfBlocks.size(); i++) {
                Block block = (Block) listOfBlocks.get(i);
                block.addHitListener(p);
                block.addHitListener(scoreTrackingListener);
                // Adding the block into the GAME
                block.addToGame(this);
            }
         //Set a new KeyboardSensor
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Rectangle paddleRec = new Rectangle(new Point((GUI_SIZE_X - levelInformation.paddleWidth()) / 2, 580),
        levelInformation.paddleWidth(),
                15);
        Block bPaddle = new Block(paddleRec, Color.BLACK);
        Paddle paddle = new Paddle(keyboard, bPaddle, environment);
        // Set the COLOR of the paddle
        paddle.setColor(Color.YELLOW);
        paddle.setPaddleSpeed(levelInformation.paddleSpeed());
        // Add the PADDLE to the GAME
        paddle.addToGame(this);
        BallRemover ballRemover = new BallRemover(this, counterBall);
        down.addHitListener(ballRemover);
        this.createBallsOnTopOfPaddle(); // or a similar method
    }
    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * Performs one frame of the all game screen animation.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // draw all the sprites of the level
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // if the user wants to stop the game
        if (this.keyboard.isPressed("p")) {
            // create a new Key, with a PauseScreen Animation
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }
        // if there are no more blocks in the level
        if (counterBlock.getValue() == 0) {
            //increase the points bt 100
            score.increase(HUNDRED);
            //stop the runner
            this.running = false;
        }
        // if there is no more balls
        if (counterBall.getValue() == 0) {
            //stop the runner
            this.running = false;
        }
    }

    /**
     * Create balls on top of paddle.
     */
    public void createBallsOnTopOfPaddle() {
        List<Velocity> listOfVelocity = levelInformation.initialBallVelocities();
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            // Set a new BALL
            Point pBall = new Point(405, 575);
            Ball ball = new Ball(pBall, 5, Color.WHITE, environment);
            Velocity v = listOfVelocity.get(i);
            ball.setVelocity(v);
            // Add the Ball to the GAME
            ball.addToGame(this);
            counterBall.increase(1);
        }
    }

    /**
     * The RUN method starts the animation loop of the GAME
     * it gets to all the SPRITES and start their timePassed and draw
     * over the drawface.
     */
//Run the game -- start the animation loop.
    public void run() {
        // create a new countdown in each level
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        // make the runner be true, so the game will start/keep going
        this.running = true;
        // run the game
        this.runner.run(this);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        ArrayList list = sprites.returnList();
        list.remove(s);
        SpriteCollection newSprite = new SpriteCollection(list);
        this.sprites = newSprite;
    }

    /**
     * Remove collidable.
     *
     * @param c the Collidable
     */
    public void removeCollidable(Collidable c) {
        ArrayList list = environment.returnList();
        list.remove(c);
        GameEnvironment newEnvironment = new GameEnvironment(list);
        this.environment = newEnvironment;
    }

    /**
     * Gets num of blocks.
     *
     * @return the num of blocks
     */
    public int getNumOfBlocks() {
        return counterBlock.getValue();
    }

    /**
     * Gets num of balls.
     *
     * @return the num of balls
     */
    public int getNumOfBalls() {
        return counterBall.getValue();
    }

}
// 322657909 Ziv Olewsky

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of the Level 2 of the game.
 */
public class Level1 implements LevelInformation {
    private static final int NUM_OF_RAY = 20;

    /**
     * Returns the number of balls in this level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()

    /**
     * Returns the initial velocities of each ball.
     *
     * @return a list of initial velocities for the balls
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v = new Velocity(0, -5);
        ArrayList<Velocity> listOfVelocity = new ArrayList<>();
        listOfVelocity.add(v);
        return listOfVelocity;
    }

    /**
     * Returns the speed of the paddle in the level.
     *
     * @return the paddle speed
     */
    @Override
    public int paddleSpeed() {
        return 10;
    }

    /**
     * Returns the width of the paddle in the level.
     *
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return 60;
    }

    // the level name will be displayed at the top of the screen.

    /**
     * Returns the name of the level.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    // Returns a sprite with the background of the level

    /**
     * Returns a sprite representing the background of the level.
     *
     * @return the background sprite
     */
    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                // color of background
                d.setColor(new Color(71, 79, 147));
                d.fillRectangle(0, 20, 800, 600);


                d.setColor(new Color(6, 17, 77));
                d.fillCircle(405, 115, 60);

                d.setColor(new Color(6, 17, 77));
                d.fillCircle(405, 115, 100);
                d.setColor(new Color(71, 79, 147));

                d.fillCircle(405, 115, 99);

                d.setColor(new Color(205, 210, 243));
                d.drawLine(405, 100, 405, 50);
                d.drawLine(405, 130, 405, 180);
                d.drawLine(390, 115, 340, 115);
                d.drawLine(420, 115, 470, 115);
            }

            @Override
            public void timePassed() {
            }
        };
    }


    // The Blocks that make up this level, each block contains
    // its size, color and location.

    /**
     * Returns a list of blocks of this level.
     *
     * @return a list of blocks
     */
    @Override
    public List<Block> blocks() {
        List<Block> listOfBlocks = new ArrayList<>();
        Rectangle rectangle = new Rectangle(new Point(390, 100), 30, 30);
        Block block = new Block(rectangle, Color.RED);
        listOfBlocks.add(block);
        return listOfBlocks;
    }

    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    /**
     * Returns the num of blocks that need to be removed.
     *
     * @return the num of blocks that need to be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

}

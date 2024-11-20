// 322657909 Ziv Olewsky
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of the Level 2 of the game.
 */
public class Level2 implements LevelInformation {
    private static final int BALL_SPEED = 5;
    private static final int NUM_OF_RAY = 20;


    /**
     * Returns the number of balls in this level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 10;
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
        ArrayList<Velocity> listOfVelocity = new ArrayList();
        // set the velocity of the first half of the balls in the level
        for (int i = 0; i < 10; i++) {
            listOfVelocity.add(Velocity.fromAngleAndSpeed(-15 * i - 90, BALL_SPEED));
        }
        return listOfVelocity;
    }

    /**
     * Returns the speed of the paddle in the level.
     *
     * @return the paddle speed
     */
    @Override
    public int paddleSpeed() {
        return 5;
    }

    /**
     * Returns the width of the paddle in the level.
     *
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return 400;
    }

    /**
     * Returns the name of the level.
     *
     * @return the level name
     */
    // the level name will be displayed at the top of the screen.
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    // Returns a sprite with the background of the level

    /**
     * Returns a sprite representing the background of the level.
     *
     * @return the background sprite
     */

//    @Override
//    public Sprite getBackground() {
//        Rectangle rectangle = new Rectangle(new Point(0, 0), 800, 600);
//        Block block = new Block(rectangle, Color.WHITE);
//        return block;
//    }
    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(42, 60, 161));
                d.fillRectangle(0, 20, 800, 600);
                d.setColor(new Color(255, 255, 255));
                d.fillCircle(100, 200, 60);
                d.setColor(new Color(42, 60, 161));
                d.fillCircle(120, 180, 60);

                d.setColor(new Color(80, 255, 6));
                d.fillCircle(700, 200, 60);
                double changeOfAngle = 2 * Math.PI / NUM_OF_RAY;
                for (int i = 0; i < NUM_OF_RAY; i++) {
                    double angle = i * changeOfAngle;
                    int endOfRayByX = (int) (700 + 80 * Math.cos(angle));
                    int endOfRayByY = (int) (200 + 80 * Math.sin(angle));
                    d.drawLine(700, 200, endOfRayByX, endOfRayByY);
                }
            }

            @Override
            public void timePassed() {
            }
        };
    }

    /**
     * Returns a list of blocks of this level.
     *
     * @return a list of blocks
     */
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    @Override
    public List<Block> blocks() {
        // make an array of colors
        Color[] colors = new Color[7];
        for (int i = 0; i < 7; i++) {
            colors[0] = new Color(0xCE0629);
            colors[1] = new Color(0xC95B09);
            colors[2] = new Color(0x528505);
            colors[3] = new Color(0x528505);
            colors[4] = new Color(0x040479);
            colors[5] = new Color(0x1F776D);
            colors[6] = new Color(0x00FFE2);
        }
        List listOfBlocks = new ArrayList<Block>();
        int j = 0;
        for (int i = 0; i < 15; i++) {
            Rectangle rectangle = new Rectangle(new Point(743 - 52.6667 * i, 100), 52.6667, 20);
            Block block = new Block(rectangle, colors[j++]);
            if (j == 6) {
                j = 0;
            }
            listOfBlocks.add(block);
        }
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
        return 15;
    }
}

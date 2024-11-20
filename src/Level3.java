// 322657909 Ziv Olewsky

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of the Level 2 of the game.
 */
public class Level3 implements LevelInformation {
    /**
     * Returns the number of balls in this level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 2;
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
        ArrayList listOfVelocity = new ArrayList<Velocity>();
        Velocity v1 = new Velocity(3, -3);
        Velocity v2 = new Velocity(-3, -3);
        listOfVelocity.add(v1);
        listOfVelocity.add(v2);
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
        return "Green 3";
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
//        Color c = new Color(0xC7EE66);
//        Block block = new Block(rectangle, c);
//        return block;
//    }
    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(44, 128, 148));
                d.fillRectangle(0, 20, 800, 600);
                d.setColor(new Color(34, 147, 168));
                d.fillRectangle(0, 20, 800, 400);
                d.setColor(new Color(109, 176, 189));
                d.fillRectangle(0, 20, 800, 200);
                d.setColor(new Color(195, 242, 255));
                d.fillRectangle(0, 20, 800, 0);

                d.setColor(new Color(206, 6, 41));
                d.drawLine(30, 0, 30, 600);
                d.drawLine(45, 0, 45, 600);
                d.drawLine(0, 520, 800, 520);
                d.drawLine(0, 535, 800, 535);

                d.drawLine(0, 70, 800, 70);
                d.drawLine(0, 85, 800, 85);
                d.drawLine(770, 600, 770, 0);
                d.drawLine(755, 600, 755, 0);
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
        List listOfBlocks = new ArrayList<Block>();
        Color[] colors = new Color[6];
        for (int i = 0; i < 6; i++) {
            colors[0] = new Color(0x79A8F6);
            colors[1] = new Color(0x3E71E0);
            colors[2] = new Color(255);
            colors[3] = new Color(204);
            colors[4] = new Color(153);
            colors[5] = new Color(0x02003F);
        }
        for (int i = 0; i < 6; i++) {
            // Dec one block in each iteration
            for (int j = 0; j < 10 - i; j++) {
                Rectangle rectangle = new Rectangle(new Point(745 - 50 * j, 100 + 20 * i), 50, 20);
                Block block = new Block(rectangle, colors[i]);
                listOfBlocks.add(block);
            }
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
        return 40;
    }
}

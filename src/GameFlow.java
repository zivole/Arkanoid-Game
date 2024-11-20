// 322657909 Ziv Olewsky

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.util.List;


/**
 * GameFlow class is responsible for make throw the levels when each one is done.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final Counter score;
    private final GUI gui;

    /**
     * Builder.
     *
     * @param ar  the AnimationRunner to run the game animations
     * @param ks  the KeyboardSensor
     * @param gui the GUI of the game
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.score = new Counter();
    }

    /**
     * Run the Animations (levels).
     * Check if the user has no balls or he won the game,
     * and make a message to announce about it.
     *
     * @param levels the list of game levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        // A loop that goes throw the levels
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);
            level.initialize();
            // run while there are balls and blocks
            while (level.getNumOfBalls() > 0 && level.getNumOfBlocks() > 0) {
                level.run();
            }
            if (level.getNumOfBalls() == 0) {
                // make a stop before moving to the Looser screen
                Sleeper sleeper = new Sleeper();
                sleeper.sleepFor(200);
                // there are no balls, so make a new LooseScreen
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new LooserScreen(score.getValue())));
                gui.close();
                break;
            }
        }
        // the user passed all the levels, so make a new WinnerScreen
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new WinnerScreen(score.getValue())));
        gui.close();
    }
}
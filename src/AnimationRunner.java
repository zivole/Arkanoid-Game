// 322657909 Ziv Olewsky

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private SpriteCollection sprites;
    private final GUI gui;
    private final int framesPerSecond;

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui             the gui
     * @param framesPerSecond the frames per second
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
    }

    /**
     * Get gui gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Run the animation.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                biuoop.Sleeper sleeper = new biuoop.Sleeper();
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}

// 322657909 Ziv Olewsky

import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * The CountdownAnimation class represents an animation that displays a countdown on the screen.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final SpriteCollection gameScreen;
    private final long timeToSleep;
    private final Sleeper sleeper = new Sleeper();
    private final int firstItration;
    private int countFrom;


    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.timeToSleep = (long) (numOfSeconds / countFrom * 1000);
        this.firstItration = countFrom;

    }

    /**
     * Performs one frame of the countdown animation.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), 50);
        if (countFrom <= 0) {
            this.shouldStop();
        }
        if (countFrom != firstItration) {
            sleeper.sleepFor(timeToSleep);
        }
        countFrom--;
    }

    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return !(this.countFrom >= 0);
    }
}
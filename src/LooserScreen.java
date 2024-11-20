// 322657909 Ziv Olewsky

import biuoop.DrawSurface;

/**
 * The LooserScreen class represents an animation that displays a loosing message and the player's score on the screen.
 */
public class LooserScreen implements Animation {
    private final int score;
    private final Boolean stop;

    /**
     * Instantiates a new Looser screen.
     *
     * @param score the score
     */
    public LooserScreen(int score) {
        this.score = score;
        this.stop = false;
    }
    /**
     * Performs one frame of the looser screen animation.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
    }
    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

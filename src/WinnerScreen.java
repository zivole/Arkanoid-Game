// 322657909 Ziv Olewsky

import biuoop.DrawSurface;

/**
 * The WinnerScreen class represents an animation that displays a winning message and the player's score on the screen.
 */
public class WinnerScreen implements Animation {
    private final int score;
    private final Boolean stop;

    /**
     * Instantiates a new Winner screen.
     *
     * @param score the score
     */
    public WinnerScreen(int score) {
        this.score = score;
        this.stop = false;
    }
    /**
     * Performs one frame of the winner screen animation.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score, 32);
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

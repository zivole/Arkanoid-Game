// 322657909 Ziv Olewsky

import biuoop.DrawSurface;

/**
 * The PauseScreen class represents an animation that displays a pause message on the screen when pressed.
 */
public class PauseScreen implements Animation {
    private final boolean stop;

    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * Performs a frame of the pause screen animation.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
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
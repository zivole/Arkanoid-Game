// 322657909 Ziv Olewsky

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The ScoreIndicator class represents a drawText that displays the current score on the screen.
 */
public class ScoreIndicator implements Sprite {
    private final ScoreTrackingListener scoreTrackingListener;
    private final String levelName;

    /**
     * Constructs a new ScoreIndicator object with the specified ScoreTrackingListener.
     *
     * @param scoreTrackingListener the ScoreTrackingListener to track the score
     */
    public ScoreIndicator(ScoreTrackingListener scoreTrackingListener, String levelInformation) {
        this.scoreTrackingListener = scoreTrackingListener;
        this.levelName = levelInformation;
    }

    /**
     * Draws the score indicator.
     *
     * @param d the DrawSurface on which to draw the score indicator
     */

    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(370, 15, "Score: " + scoreTrackingListener.getScore(), 15);
        d.drawText(600, 15, "Level Name: " + levelName, 15);
    }

    /**
     * do nothing.
     */
    public void timePassed() {
        //nothing
    }

    /**
     * Add the Sprite to the game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

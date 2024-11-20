// 322657909 Ziv Olewsky
/**
 * The ScoreTrackingListener class is responsible for tracking the score in the game.
 * It implements the HitListener interface to handle hit events.
 */
public class ScoreTrackingListener implements HitListener {
    private static final int FIVE = 5;
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * It increases the score by 5 points.
     *
     * @param beingHit the Block object that is being hit
     * @param hitter   the Ball object that is doing the hitting
     */

    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(FIVE);
    }
    /**
     * Returns the current score value.
     *
     * @return the current score value
     */
    public int getScore() {
        return this.currentScore.getValue();
    }
}
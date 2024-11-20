// 322657909 Ziv Olewsky
/**
 * The HitListener interface represents an object that listens if hit was occurred.
 * Classes implementing this interface can register themselves as listeners to
 * be notified when a specific object is hit by a ball.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    /**
     * called whenever the beingHit ball is hit by a ball.
     *
     * @param beingHit the Block object that is being hit
     * @param hitter   the Ball object that is doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
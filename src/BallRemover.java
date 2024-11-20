// 322657909 Ziv Olewsky
/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    private static final int ONE = 1;
    /**
     * Constructs a new BallRemover.
     *
     * @param gameLevel the Game object from which blocks will be removed
     * @param removedBlocks the Counter object to keep count remaining blocks
     */
    public BallRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * This method is called when a block is hit by a ball. It removes the ball from the game
     * and decreases the counter for the remaining blocks.
     *
     * @param beingHit the Block object that is being hit
     * @param hitter   the Ball object that is doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove the block
        hitter.removeFromGame(gameLevel);
        //decrease the count by one
        remainingBlocks.decrease(ONE);
    }
}
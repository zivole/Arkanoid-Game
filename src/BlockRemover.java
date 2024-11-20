// 322657909 Ziv Olewsky
/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */

public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;
    private static final int ONE = 1;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * called when a block is hit by a ball. It removes the block from the game,
     * removes this listener from the block, and decreases the counter for the remaining blocks.
     *
     * @param beingHit the Block object that is being hit
     * @param hitter   the Ball object that is doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        beingHit.removeHitListener(this);
        // decrease one count of the block
        remainingBlocks.decrease(ONE);
    }
}
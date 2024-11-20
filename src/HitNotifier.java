// 322657909 Ziv Olewsky
/**
 * The HitNotifier interface represents an object that can notify listeners
 * about hit.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the HitListener to be added
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}

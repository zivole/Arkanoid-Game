// 322657909 Ziv Olewsky
import biuoop.DrawSurface;

/**
 * The Sprite interface represents a game object that can be drawn on a DrawSurface
 * and can be notified when time has passed.
 */
public interface Sprite {
    /**
     * Draw on the DRAWSURFACE.
     *
     * @param d the DRAWSURFACE
     */
// draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     *  Notify the sprite that time has passed..
     */
// notify the sprite that time has passed
    void timePassed();
    //void addToGame();
}
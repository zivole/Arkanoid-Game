// 322657909 Ziv Olewsky
import java.util.ArrayList;
import biuoop.DrawSurface;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     *
     * @param sprites the sprites
     */
    public SpriteCollection(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Calls the timePassed() method on all Sprites in the collection.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * Draw all the sprites.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }

    /**
     * Return list array list.
     *
     * @return the array list
     */
    public ArrayList returnList() {
        return this.sprites;
    }
}
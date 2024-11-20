import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The KeyPressStoppableAnimation class is a decorator class, that gather all the animations that
 * are need to be display on the screen, and end when a key is pressed.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private Boolean stop = false;
    private boolean pressed = true;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }
    /**
     * Performs one frame of the key press stoppable animation.
     *
     * @param d the DrawSurface to draw on
     */

    @Override
    public void doOneFrame(DrawSurface d) {
        // make the doOneFrame of the animation
        animation.doOneFrame(d);
        // if the key is pressed
        if (this.sensor.isPressed(key)) {
            if (!pressed) {
                stop = true;
            }
        }
        if (!this.sensor.isPressed(key)) {
            pressed = false;
        }
    }
    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return stop;
    }

}
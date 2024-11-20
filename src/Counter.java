// 322657909 Ziv Olewsky
/**
 * The Counter class represents a counter that can be incremented or decremented.
 */
public class Counter {
    private int count;

    /**
     * Increase by number.
     *
     * @param number the number
     */
// add number to current count.
    public void increase(int number) {
        count += number;
    }

    /**
     * Decrease by number.
     *
     * @param number the number
     */
// subtract number from current count.
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
// get current count.
    public int getValue() {
        return this.count;
    }
}
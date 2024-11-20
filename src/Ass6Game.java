// 322657909 Ziv Olewsky

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;

/**
 * A class representing the main entry point for the "Ass3Game" application.
 * The Ass3Game application is a game that uses the Game class to initialize
 * and run the game.
 */
public class Ass6Game {
    private static final int GUI_SIZE_X = 800;
    private static final int GUI_SIZE_Y = 600;

    /**
     * The main method for the "Ass3Game" application.
     * Initializes a new Game object and runs the game.
     *
     * @param args an array of command-line arguments passed to the application
     */

    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid game", GUI_SIZE_X, GUI_SIZE_Y);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(runner, keyboard, gui);
        ArrayList<LevelInformation> levelInformationList = new ArrayList<>();
        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("1")) {
                levelInformationList.add(new Level1());
            } else if(args[i].equals("2")) {
                levelInformationList.add(new Level2());
            } else if(args[i].equals("3")) {
                levelInformationList.add(new Level3());
            }
        }
        if(levelInformationList.size() == 0) {
            levelInformationList.add(new Level1());
            levelInformationList.add(new Level2());
            levelInformationList.add(new Level3());
        }
        // send to the GameFlow the list of the levels in the game
        gameFlow.runLevels(levelInformationList);
    }
}

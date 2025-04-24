package de.tu_darmstadt.informatik.fop.donkeykong.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.map.MapLoader;
import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.Button;
import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.MapElement;
import de.tu_darmstadt.informatik.fop.donkeykong.util.highscore.Highscores;
import de.tu_darmstadt.informatik.fop.donkeykong.util.highscore.Score;
import de.tu_darmstadt.informatik.fop.donkeykong.util.interfaces.IScorable;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class GameplayManager {

    Highscores highscores;

    private int lifes = Consts.MAX_LIFES;
    private int bonus = 0;
    private int score = 0;
    private static GameplayManager instance;
    private int currentMap;
    private String[] mapCycle = new String[] {Consts.MAP_LEVEL1, Consts.MAP_LEVEL2, Consts.MAP_LEVEL1, Consts.MAP_LEVEL3, Consts.MAP_LEVEL2};
    private int numberOfButtons = -1;
    private int pressedButtons = 0;

    private GameplayManager() {
        this.highscores = new Highscores();
        newGame();
    }

    public void newGame() {
        resetBonus();
        resetScore();
        resetLifes();
        resetButtons();
        resetMapCycle();
    }

    /**
     * decrements Mario's health and checks whether the game is over or if the current level should be reset
     */
    public void decreaseLife() {
        this.lifes--;
        checkEndGame();
    }

    /**
     * Checks if the game is over, if it is the current score is saved and the highscore menu gets displayed
     */
    public void checkEndGame() {
        if(isGameOver()) {
            this.endGame();
        } else {
            this.resetLevel();
        }
    }

    /**
     * load next level
     */
    public void nextLevel() {
        applyBonus();
        resetBonus();
        resetButtons();
        applyCompletedLevelBonus(new IScorable() {
            @Override
            public int getPoints() {
                return Consts.POINTS_MAP;
            }
        });
        this.currentMap = (this.currentMap + 1) % this.mapCycle.length;
        loadMap(this.mapCycle[this.currentMap]);
    }

    /**
     * reset level
     */
    public void resetLevel() {
        resetBonus();
        resetButtons();
        loadMap(this.mapCycle[this.currentMap]);
    }


    /**
     * end the game save highscore and open highscore page
     */
    public void endGame() {
        // get current player name
        String playerName = "";
        if(DonkeyKong.isTesting) playerName = "dummy";
        else {
            playerName = JOptionPane.showInputDialog("What is your name?");
            if( playerName == null || playerName.isEmpty()) playerName = "dummy";
        }
        saveScore(playerName, this.score);
        newGame();
        DonkeyKong.getInstance().enterState(Consts.STATE_MAINMENU);
    }

    /**
     * Loads a new map
     */
    public void loadMap(String mapFile) {
        MapLoader.getInstance().changeMap(mapFile);
    }

    /**
     * Saves the current score + bonus 
     * @param playerName the player name
     * @param points the amount of points to save
     */
    public void saveScore(String playerName, int points) {
        Score score = new Score(LocalDateTime.now(), points, playerName);
        highscores.addEntry(score);
        highscores.saveToFile();
    }

    /**
     * Resets everything
     */
    public void resetAll() {
        resetLifes();
        resetMapCycle();
        resetBonus();
        resetScore();
    }    

    /**
     * Resets the map cycle
     */
    public void resetMapCycle() {
        this.currentMap = 0;
    }

    /**
     * resets the lifes the player has
     */
    public void resetLifes() {
        this.lifes = Consts.MAX_LIFES;
    }

    /**
     * Resets the score
     */
    public void resetScore() {
        this.score = 0;
    }

    /**
     * Resets the bonus to the original value
     */
    public void resetBonus() {
        this.bonus = Consts.MAX_BONUS;
    }

    /**
     * Increase the current score of the player
     *
     * @param scorable the scorable object the player interacted with
     */
    public void scorePoints(IScorable scorable) {
        this.score += scorable.getPoints();
    }

    /**
     * Bonus points for completing a level
     */
    public void applyCompletedLevelBonus(IScorable points) {
        scorePoints(points);
    }

    /**
     * add remaining bonus to the player's score
     */
    public void applyBonus() {
        this.score += this.bonus;
    }

    /**
     * decrease remaining bonus points for the level
     */
    public void decreaseBonus() {
        this.bonus -= Consts.BONUS_DECREASE;
        if (this.bonus < 0)
            this.bonus = 0;
    }

    /**
     * @return current amount of lifes of the player
     */
    public int getLifes() {
        return lifes;
    }

    /**
     * @return the current bonus
     */
    public int getBonus() {
        return bonus;
    }

    /**
     * @return player's current score
     */
    public int getScore() {
        return this.score;
    }

    public void setMapCycle(String[] maps) {
        this.currentMap = 0;
        this.mapCycle = maps;
    }

    /**
     * @return checks if the game is over (remaining lifes == 0)
     */
    public boolean isGameOver() {
        return this.lifes == 0;
    }

    /**
     * @return the instance of GameplayManager
     */
    public static synchronized GameplayManager getInstance() {
        if (instance == null)
            instance = new GameplayManager();
        return instance;
    }

    /**
     * acknowledges a pressed button
     */
    public void useButton() {
        this.pressedButtons++;
        if(pressedButtons == numberOfButtons) nextLevel();
    }

    /**
     * resets counter for pressed buttons
     */
    public void resetButtons() {
        this.pressedButtons = 0;
    }

    /**
     * counts number of existing buttons in level
     */
    public void setNumberOfButtons() {
        MapElement[][] mapElements = MapLoader.getInstance().getCurrentMap().getMapElements();
        Stream stream = Arrays.stream(mapElements).flatMap(Arrays::stream);
        this.numberOfButtons = (int) stream.filter(m -> m instanceof Button).count();
    }

    public int getNumberOfUnusedButtons() {
        return this.numberOfButtons - this.pressedButtons;
    }

    public int getNumberOfButtons() {
        return this.numberOfButtons;
    }
}

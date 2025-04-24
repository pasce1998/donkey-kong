package de.tu_darmstadt.informatik.fop.donkeykong.test.adapters;

import java.time.LocalDateTime;
import java.util.List;

import de.tu_darmstadt.informatik.fop.donkeykong.util.GameplayManager;
import de.tu_darmstadt.informatik.fop.donkeykong.util.abstracts.AbstractScore;
import de.tu_darmstadt.informatik.fop.donkeykong.util.highscore.Highscores;
import de.tu_darmstadt.informatik.fop.donkeykong.util.highscore.Score;
import de.tu_darmstadt.informatik.fop.donkeykong.util.interfaces.IScorable;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class DKTestAdapterExtended1 extends DKTestAdapterMinimal {
  
  Highscores highscores;

  public DKTestAdapterExtended1() {
    super();
  }

  /* *************************************************** 
	 * ******************* Highscores ********************
	 * *************************************************** */

  /**
   * Load highscores from specified file
   * @param fileName
   */
  public void loadHighscores(String fileName) {
    highscores = new Highscores(fileName);
  }

  /**
   * Gibt den Spielernamen des Highscores an gegebener Posiition zurück.
   * Ein valider Highscore Eintrag sind nur welche, die unter den Top 10 sind
   * @param position Position des Eintrags (1 = Bester Score; 10 Schlechtester Highscore; Alles andere ist invalid oder kein Highscore)
   * @return Spielername des Highscore Eintrags an gegebener Position, null wenn an dieser Position kein Eintrag vorhanden ist
   */
  public String getNameAtHighscorePosition(int position) {
    List<AbstractScore> scores = highscores.getHighscores();
    if(position < 1 || position > 10 || position > getHighscoreCount()) return null;
    String playerName = scores.get(position - 1).getPlayerName();
    return playerName;
  }

  /**
   * Gibt den Score des Highscores an gegebener Posiition zurück.
   * Ein valider Highscore Eintrag sind nur welche, die unter den Top 10 sind
   * @param position Position des Eintrags (1 = Bester Score; 10 Schlechtester Highscore; Alles andere ist invalid oder kein Highscore)
   * @return Score des Highscore Eintrags an gegebener Position, -1 wenn an dieser Position kein Eintrag vorhanden ist
   */
  public int getScoreAtHighscorePosition(int position) {
    List<AbstractScore> scores = highscores.getHighscores();
    if(position < 1 || position > 10 || position > getHighscoreCount()) return -1;
    int score = scores.get(position - 1).getScore();
    return score;
  }

  /**
   * Gibt das Datum des Highscores an gegebener Posiition zurück.
   * Ein valider Highscore Eintrag sind nur welche, die unter den Top 10 sind
   * Das Datum muss die Form "dd.MM.yyyy HH:mm" haben
   * @param position Position des Eintrags (1 = Bester Score; 10 Schlechtester Highscore; Alles andere ist invalid oder kein Highscore)
   * @return Datum des Highscore Eintrags an gegebener Position, null wenn an dieser Position kein Eintrag vorhanden ist
   */
  public String getDateAtHighscorePosition(int position) {
    List<AbstractScore> scores = highscores.getHighscores();
    if(position < 1 || position > 10 || position > getHighscoreCount()) return null;
    String date = scores.get(position - 1).getDate();
    return date;
  }

  /**
   * Fügt einen neuen Eintrag zu den bestehenden Highscores hinzu
   * @param score Der erzielte Score
   * @param playerName Der Spielername
   * @param date Datum an dem der Score erzielt wurde
   */
  public void addHighscore(int score, String playerName, LocalDateTime date) {
    highscores.addEntry(new Score(date, score, playerName));
  }

  /**
	 * @return Anzahl aktuell geladener Highscores
	 */
  public int getHighscoreCount() {
    return highscores.getHighscores().size();
  }

  /* *************************************************** 
	 * *******************   Score    ********************
	 * *************************************************** */

  /**
   * @return Aktueller Punktestand des Spielers
   */
  public int getCurrentScore() {
    return GameplayManager.getInstance().getScore();
  }

  /**
   * "Collects" an item and should add the points to the score
   * @param points
   */
  public void collectItem(int points) {
    GameplayManager.getInstance().scorePoints(new IScorable(){
      @Override
      public int getPoints() {
        return points;
      }
    });
  }

  /**
   * Nach beenden eines Levels werden zussätzliche Bonuspunkte fürs abschließen des Levels angerechnet.
   * @param points Punkte die angerechnet werden sollen
   */
  public void collectMapFinishedPoints(int points) {
    GameplayManager.getInstance().applyCompletedLevelBonus(new IScorable(){
      @Override
      public int getPoints() {
        return points;
      }
    });
  }

  /**
   * Resettet den aktuellen Score -> getCurrentScore sollte danach 0 liefern
   */
  public final void resetScore() {
    GameplayManager.getInstance().resetScore();
  }

}

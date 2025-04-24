package de.tu_darmstadt.informatik.fop.donkeykong.test.tutors.testcases;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.informatik.fop.donkeykong.test.adapters.DKTestAdapterExtended1;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class HighscoreTest {

  DKTestAdapterExtended1 adapter;

  private final String highscores = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/students/testcases/highscores/highscoresTest1.txt";

  @Before
  public void setUp() {
    adapter = new DKTestAdapterExtended1();
  }

  @After
  public void finish() {
    adapter.stopGame();
  }

  @Test
  public final void testMaxHighscoreCount() {
    adapter.loadHighscores(highscores);

    assertEquals("Es sollten keine Einträge vorhanden sein!", 0, adapter.getHighscoreCount());
    
    LocalDateTime currentDate = LocalDateTime.now();

    for(int i = 1; i < 15; i++) {
      adapter.addHighscore(i, "Test1", currentDate);
    }

    for(int i = 1; i < 15; i++) {
      if(i >= 11) assertEquals(-1, adapter.getScoreAtHighscorePosition(i));
      else assertEquals(14 - i + 1, adapter.getScoreAtHighscorePosition(i));
    }
  }

  @Test
  public final void testSortHighscoresByScore() {
    adapter.loadHighscores(highscores);

    assertEquals("Highscore count should be 0", 0, adapter.getHighscoreCount());

    LocalDateTime currentTime = LocalDateTime.now();
   
    adapter.addHighscore(420, "T3", currentTime);
    adapter.addHighscore(12345, "T5", currentTime);
    adapter.addHighscore(42, "T2", currentTime);
    adapter.addHighscore(9999999, "T6", currentTime);
    adapter.addHighscore(Integer.MAX_VALUE, "T7", currentTime);
    adapter.addHighscore(1337, "T4", currentTime);
    adapter.addHighscore(1, "T1", currentTime);
    

    assertEquals("Highscore count should be 7", 7, adapter.getHighscoreCount());

    for(int i = 1; i < 8; i++) {
      assertEquals(currentTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")), adapter.getDateAtHighscorePosition(i));
    }

    assertEquals("T7", adapter.getNameAtHighscorePosition(1));
    assertEquals("T6", adapter.getNameAtHighscorePosition(2));
    assertEquals("T5", adapter.getNameAtHighscorePosition(3));
    assertEquals("T4", adapter.getNameAtHighscorePosition(4));
    assertEquals("T3", adapter.getNameAtHighscorePosition(5));
    assertEquals("T2", adapter.getNameAtHighscorePosition(6));
    assertEquals("T1", adapter.getNameAtHighscorePosition(7));

  }
}

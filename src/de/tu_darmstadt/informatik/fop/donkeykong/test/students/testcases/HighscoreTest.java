package de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
    this.adapter = new DKTestAdapterExtended1();
  }

  @After
  public void finish() {
    this.adapter.stopGame();
  }

  @Test
  public final void testCreateHighscoreEntry() {
    adapter.loadHighscores(highscores);

    assertEquals("Es sollten keine Einträge vorhanden sein!", 0, adapter.getHighscoreCount());

    LocalDateTime currentDate = LocalDateTime.now();

    adapter.addHighscore(1337, "Test1", currentDate);

    assertEquals("Es sollte ein Eintrag vorhanden sein!", 1, adapter.getHighscoreCount());
    assertEquals("Spielername des Eintrags ist falsch!", "Test1", adapter.getNameAtHighscorePosition(1));
    assertEquals("Der Score des Eintrags ist falsch!", 1337, adapter.getScoreAtHighscorePosition(1));
    assertEquals("Das gespeicherte Datum des Eintrags ist falsch!", currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")), adapter.getDateAtHighscorePosition(1));
  }

  @Test
  public final void testSortHighscoresByDate() {
    adapter.loadHighscores(highscores);

    assertEquals("Highscore count should be 0", 0, adapter.getHighscoreCount());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    adapter.addHighscore(1337, "T5", LocalDateTime.parse("01.02.2021 01:01", formatter));
    adapter.addHighscore(1337, "T1", LocalDateTime.parse("01.01.2020 00:30", formatter));
    adapter.addHighscore(1337, "T7", LocalDateTime.parse("02.02.2022 00:30", formatter));
    adapter.addHighscore(1337, "T2", LocalDateTime.parse("01.01.2020 01:00", formatter));
    adapter.addHighscore(1337, "T3", LocalDateTime.parse("01.01.2020 01:00", formatter));
    adapter.addHighscore(1337, "T6", LocalDateTime.parse("01.02.2021 02:00", formatter));
    adapter.addHighscore(1337, "T4", LocalDateTime.parse("01.02.2021 01:00", formatter));
    

    assertEquals("Highscore count should be 7", 7, adapter.getHighscoreCount());

    assertEquals("T1", adapter.getNameAtHighscorePosition(1));
    assertEquals("T2", adapter.getNameAtHighscorePosition(2));
    assertEquals("T3", adapter.getNameAtHighscorePosition(3));
    assertEquals("T4", adapter.getNameAtHighscorePosition(4));
    assertEquals("T5", adapter.getNameAtHighscorePosition(5));
    assertEquals("T6", adapter.getNameAtHighscorePosition(6));
    assertEquals("T7", adapter.getNameAtHighscorePosition(7));

    for (int i = 1; i < 8; i++) {
			assertEquals("The amount of points is incorrect", 1337, adapter.getScoreAtHighscorePosition(i));
		}
  }

  @Test
  public final void testNullAccess() {
    adapter.loadHighscores(highscores);

    assertEquals("Highscore count should be 0", 0, adapter.getHighscoreCount());

    LocalDateTime currentDate = LocalDateTime.now();
    adapter.addHighscore(1337, "Test1", currentDate);

    assertNotNull(adapter.getNameAtHighscorePosition(1));

    assertNull(adapter.getNameAtHighscorePosition(-1));
    assertNull(adapter.getNameAtHighscorePosition(12));

    assertNull(adapter.getDateAtHighscorePosition(-1));
    assertNull(adapter.getDateAtHighscorePosition(12));

    assertEquals(-1, adapter.getScoreAtHighscorePosition(-1));
    assertEquals(-1, adapter.getScoreAtHighscorePosition(12));
  }

}

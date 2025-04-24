package de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.informatik.fop.donkeykong.test.adapters.DKTestAdapterMinimal;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class EndGameTest {
  
  DKTestAdapterMinimal adapter;

  private final String level1 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/students/testcases/maps/level1.dk";

  @Before
  public void setUp() {
    adapter = new DKTestAdapterMinimal();
    adapter.initializeGame();
  }

  @After
  public void finish() {
    adapter.stopGame();
  }

  @Test
  public final void testDecreaseLife() {
    adapter.setIngame(this.level1);
    adapter.runGame(1000);
    assertEquals(3, adapter.getCurrentLifes()); // 3 Leben soll der Spieler zu Beginn haben
    adapter.decreaseLife();
    assertEquals(2, adapter.getCurrentLifes()); // 1 Leben verloren => 2 Leben verbleibend
    adapter.decreaseLife();
    assertEquals(1, adapter.getCurrentLifes()); // 2 Leben verloren => 1 Leben verbleibend
  }

  @Test
  public final void testDeath() {
    adapter.setIngame(this.level1);
    adapter.runGame(1000);
    adapter.decreaseLife(); // Verliere alle 3 Leben
    assertFalse(adapter.isGameOver());
    adapter.decreaseLife();
    assertFalse(adapter.isGameOver());
    adapter.decreaseLife();
    adapter.runGame(1000);
    assertEquals(3, adapter.getCurrentLifes()); // Leben zurückgesetzt?
  }
}

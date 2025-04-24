package de.tu_darmstadt.informatik.fop.donkeykong.test.tutors.testcases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

  private final String level2 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/tutors/testcases/maps/level2.dk";

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
  public final void testState() {
    assertTrue(adapter.isCurrentStateMainMenu()); // Zu Beginn sollte man sich im Hauptmenü befinden
    assertFalse(adapter.isCurrentStateGameplay());
    adapter.setIngame(this.level2);
    adapter.runGame(1000);
    assertTrue(adapter.isCurrentStateGameplay()); // Jetzt muss der Zustand Gameplay aktiv sein
    assertFalse(adapter.isCurrentStateMainMenu());
    // Verliere alle 3 Leben
    adapter.decreaseLife();
    adapter.runGame(1000);
    assertTrue(adapter.isCurrentStateGameplay());
    assertFalse(adapter.isCurrentStateMainMenu());
    adapter.decreaseLife();
    adapter.runGame(1000);
    assertTrue(adapter.isCurrentStateGameplay());
    assertFalse(adapter.isCurrentStateMainMenu());
    adapter.decreaseLife();
    adapter.runGame(1000);
    assertTrue(adapter.isCurrentStateMainMenu()); // Nach dem Tod sollte das Spiel in das Hauptmenü wecchseln
    assertFalse(adapter.isCurrentStateGameplay());
  }

}

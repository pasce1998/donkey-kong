package de.tu_darmstadt.informatik.fop.donkeykong.test.tutors.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.informatik.fop.donkeykong.test.adapters.DKTestAdapterExtended2;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class GameplayTest {
  
  DKTestAdapterExtended2 adapter;

  private final String level1 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/tutors/testcases/maps/level1.dk";
  private final String level2 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/tutors/testcases/maps/level2.dk";
  private final String level3 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/tutors/testcases/maps/level3.dk";

  @Before
  public void setUp() {
    adapter = new DKTestAdapterExtended2();
    adapter.initializeGame();
  }

  @After
  public void finish() {
    adapter.stopGame();
  }

  @Test
  public final void testInfiniteGameplay() {
    adapter.setIngame(level1, level2, level3);
    adapter.runGame(1000);
    assertTrue(adapter.isCurrentStateGameplay());
    assertFalse(adapter.isCurrentStateMainMenu());
    assertEquals("T1", adapter.getCurrentMapName());
    assertEquals(3, adapter.getCurrentLifes());

    String oldMapName = adapter.getCurrentMapName();
    for(int i = 0; i < 1000; i++) {
      adapter.advanceToNextLevel();
      adapter.runGame(1000);
      assertEquals(3, adapter.getCurrentLifes());
      assertFalse(oldMapName.equals(adapter.getCurrentMapName()));
      oldMapName = adapter.getCurrentMapName();
    }
    assertTrue(adapter.isCurrentStateGameplay());
    assertFalse(adapter.isCurrentStateMainMenu());
    adapter.decreaseLife();
    adapter.runGame(1000);
    assertEquals(2, adapter.getCurrentLifes());
    assertTrue(adapter.isCurrentStateGameplay());
    assertFalse(adapter.isCurrentStateMainMenu());
    adapter.decreaseLife();
    adapter.runGame(1000);
    assertEquals(1, adapter.getCurrentLifes());
    assertTrue(adapter.isCurrentStateGameplay());
    assertFalse(adapter.isCurrentStateMainMenu());
    adapter.decreaseLife();
    adapter.runGame(1000);
    assertEquals(3, adapter.getCurrentLifes());
    assertTrue(adapter.isCurrentStateMainMenu());
    assertFalse(adapter.isCurrentStateGameplay());
  }
}

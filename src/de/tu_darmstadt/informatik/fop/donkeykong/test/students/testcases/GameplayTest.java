package de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases;

import static org.junit.Assert.assertEquals;

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

  private final String level1 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/students/testcases/maps/level1.dk";
  private final String level2 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/students/testcases/maps/level2.dk";


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
  public final void testSwitchLevel() {
    adapter.setIngame(level1, level2);
    adapter.runGame(1000);
    assertEquals("T1", adapter.getCurrentMapName());
    adapter.advanceToNextLevel();
    adapter.runGame(1000);
    assertEquals("T2", adapter.getCurrentMapName());
  }

  @Test
  public final void testLifeTransfer() {
    adapter.setIngame(level1, level2);
    adapter.runGame(1000);
    assertEquals("T1", adapter.getCurrentMapName());
    assertEquals(3, adapter.getCurrentLifes());
    adapter.decreaseLife();
    assertEquals("T1", adapter.getCurrentMapName());
    assertEquals(2, adapter.getCurrentLifes());
    adapter.advanceToNextLevel();
    adapter.runGame(1000);
    assertEquals("T2", adapter.getCurrentMapName());
    assertEquals(2, adapter.getCurrentLifes());
  }
}

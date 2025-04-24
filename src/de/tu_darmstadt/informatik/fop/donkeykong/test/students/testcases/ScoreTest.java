package de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.informatik.fop.donkeykong.test.adapters.DKTestAdapterExtended1;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class ScoreTest {
  
  DKTestAdapterExtended1 adapter;

  @Before
  public void setUp() {
    adapter = new DKTestAdapterExtended1();
    adapter.initializeGame();
  }

  @After
  public void finish() {
    adapter.stopGame();
  }

  @Test
  public final void testPointsDontChange() {
    adapter.resetScore();
    assertEquals("Der Punktestand sollte zu Beginn 0 sein!", 0, adapter.getCurrentScore());
    adapter.runGame(100);
    assertEquals("Der Punktestand sollte immer noch 0 sein!", 0, adapter.getCurrentScore());
  }

  @Test
  public final void testCollectItem() {
    adapter.resetScore();
    assertEquals("Der Punktestand sollte zu Beginn 0 sein!", 0, adapter.getCurrentScore());
    adapter.collectItem(20);
    assertEquals("Der Punktestand sollte 20 sein!", 20, adapter.getCurrentScore());
    adapter.collectItem(952);
    assertEquals("Der Punktestand sollte 972 sein!", 972, adapter.getCurrentScore());
    adapter.collectItem(-20);
    assertEquals("Der Punktestand sollte 20 sein!", 952, adapter.getCurrentScore());
  }
}

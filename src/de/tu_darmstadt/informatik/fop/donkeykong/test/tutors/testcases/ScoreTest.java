package de.tu_darmstadt.informatik.fop.donkeykong.test.tutors.testcases;

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
  }

  @After
  public void finish() {
    adapter.stopGame();
  }

  @Test
  public final void testFinishMap() {
    adapter.resetScore();
    assertEquals("Der Punktestand sollte zu Beginn 0 sein!", 0, adapter.getCurrentScore());
    adapter.collectMapFinishedPoints(200);
    assertEquals("Der Punktestand sollte 200 sein!", 200, adapter.getCurrentScore());
    adapter.collectMapFinishedPoints(952);
    assertEquals("Der Punktestand sollte 1152 sein!", 1152, adapter.getCurrentScore());
    adapter.collectMapFinishedPoints(-20);
    assertEquals("Der Punktestand sollte 1132 sein!", 1132, adapter.getCurrentScore());
  }

  @Test
  public final void testCombinedPoints() {
    adapter.resetScore();
    assertEquals("Der Punktestand sollte zu Beginn 0 sein!", 0, adapter.getCurrentScore());
    adapter.collectMapFinishedPoints(200);
    assertEquals("Der Punktestand sollte 200 sein!", 200, adapter.getCurrentScore());
    adapter.collectItem(1337);;
    assertEquals("Der Punktestand sollte 1537 sein!", 1537, adapter.getCurrentScore());
    adapter.collectItem(-20);
    assertEquals("Der Punktestand sollte 1517 sein!", 1517, adapter.getCurrentScore());
  }
}

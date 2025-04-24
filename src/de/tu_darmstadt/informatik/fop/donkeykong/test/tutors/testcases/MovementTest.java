package de.tu_darmstadt.informatik.fop.donkeykong.test.tutors.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.informatik.fop.donkeykong.test.adapters.DKTestAdapterMinimal;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class MovementTest {
  
  DKTestAdapterMinimal adapter;

  private final String level1 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/tutors/testcases/maps/level1.dk";

  @Before
  public void setUp() {
    adapter = new DKTestAdapterMinimal();
    adapter.initializeGame();
    adapter.setIngame(this.level1);
    adapter.runGame(1000);
  }

  @After
  public void finish() {
    adapter.stopGame();
  }

  @Test
  public final void testMoveLeftArrow() {
    assertTrue(adapter.isCurrentStateGameplay());
    float[] oldPos = adapter.getCurrentPositionMario();
    adapter.handleKeyDownLeftArrow();
    float[] newPos = adapter.getCurrentPositionMario();
    assertTrue(oldPos[0] > newPos[0]);
  }

  @Test
  public final void testMoveRightArrow() {
    assertTrue(adapter.isCurrentStateGameplay());
    float[] oldPos = adapter.getCurrentPositionMario();
    adapter.handleKeyDownRightArrow();
    float[] newPos = adapter.getCurrentPositionMario();
    assertTrue(oldPos[0] < newPos[0]);
  }

  @Test
  public final void testMoveUpArrow() {
    assertTrue(adapter.isCurrentStateGameplay());
    float[] oldPos = adapter.getCurrentPositionMario();
    adapter.handleKeyDownUpArrow();
    float[] newPos = adapter.getCurrentPositionMario();
    assertTrue(oldPos[1] < newPos[1]);
  }

  @Test
  public final void testMoveDownArrow() {
    assertTrue(adapter.isCurrentStateGameplay());
    float[] oldPos = adapter.getCurrentPositionMario();
    adapter.handleKeyDownDownArrow();
    float[] newPos = adapter.getCurrentPositionMario();
    assertTrue(oldPos[1] + 600 > newPos[1]); // +600 because mario is not touching the platform in the beginning
  }

  @Test
  public final void testJump() {
    assertTrue(adapter.isCurrentStateGameplay());
    float[] oldPos = adapter.getCurrentPositionMario();
    adapter.handleKeyPressedSpace();
    adapter.runGame(50);
    float[] newPos = adapter.getCurrentPositionMario();
    assertTrue(oldPos[1] < newPos[1]);
    assertEquals(oldPos[0], newPos[0], 0.5);
  }

}

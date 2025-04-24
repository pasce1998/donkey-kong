package de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases;

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

  private final String level1 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/students/testcases/maps/level1.dk";

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
  public final void testMoveLeft() {
    assertTrue(adapter.isCurrentStateGameplay());
    float[] oldPos = adapter.getCurrentPositionMario();
    adapter.handleKeyDownA();
    float[] newPos = adapter.getCurrentPositionMario();
    assertTrue(oldPos[0] > newPos[0]);
  }

  @Test
  public final void testMoveRight() {
    assertTrue(adapter.isCurrentStateGameplay());
    float[] oldPos = adapter.getCurrentPositionMario();
    adapter.handleKeyDownD();
    float[] newPos = adapter.getCurrentPositionMario();
    assertTrue(oldPos[0] < newPos[0]);
  }

  @Test
  public final void testMoveUp() {
    assertTrue(adapter.isCurrentStateGameplay());
    float[] oldPos = adapter.getCurrentPositionMario();
    adapter.handleKeyDownW();
    float[] newPos = adapter.getCurrentPositionMario();
    assertTrue(oldPos[1] < newPos[1]);
  }

  @Test
  public final void testMoveDown() {
    assertTrue(adapter.isCurrentStateGameplay());
    float[] oldPos = adapter.getCurrentPositionMario();
    adapter.handleKeyDownS();
    float[] newPos = adapter.getCurrentPositionMario();
    assertTrue(oldPos[1] + 600 > newPos[1]); // +600 because mario is not touching the platform in the beginning
  }

}

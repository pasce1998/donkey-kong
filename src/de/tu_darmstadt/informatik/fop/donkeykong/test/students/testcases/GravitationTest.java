package de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases;

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
public class GravitationTest {
  
  DKTestAdapterMinimal adapter;

  private final String level4 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/students/testcases/maps/level4.dk";

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
  public final void testGravityMario() {
    adapter.setIngame(this.level4);
    assertEquals(400, adapter.getCurrentPositionMario()[0], 0.5);
    assertEquals(416, adapter.getCurrentPositionMario()[1], 0.5);
    
    float[] oldPos = adapter.getCurrentPositionMario();
    for(int i = 0; i < 500; i++) {
      adapter.runGame(5);
      assertTrue(oldPos[0] == adapter.getCurrentPositionMario()[0]);
      assertTrue(oldPos[1] < adapter.getCurrentPositionMario()[1]);
      oldPos = adapter.getCurrentPositionMario();
    }
  }

  @Test
  public final void testNoGravityDeath() {
    adapter.setIngame(this.level4);
    adapter.runGame(100);
    assertTrue(adapter.isCurrentStateGameplay());
    assertEquals(3, adapter.getCurrentLifes());
    adapter.setMarioPosition(150, 1000 - adapter.getJumpHeight() - adapter.getSteelHeight() + 2);
    adapter.spawnMetalBeam(136, 1000);
    adapter.spawnMetalBeam(142, 1000);
    adapter.spawnMetalBeam(150, 1000);
    adapter.spawnMetalBeam(158, 1000);
    adapter.spawnMetalBeam(164, 1000);
    assertEquals(150, adapter.getCurrentPositionMario()[0], 0.5);
    assertEquals(1000 - adapter.getJumpHeight() - adapter.getSteelHeight() + 2, adapter.getCurrentPositionMario()[1], 0.5);
    adapter.runGame(60);
    assertEquals(150, adapter.getCurrentPositionMario()[0], 0.5);
    assertEquals(1000, adapter.getCurrentPositionMario()[1], 16);
    assertEquals(3, adapter.getCurrentLifes());
  }

}

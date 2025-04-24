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
public class CollisionTest {
  
  DKTestAdapterMinimal adapter;
  private final String level4 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/students/testcases/maps/level4.dk";

  @Before
  public void setUp() {
    adapter = new DKTestAdapterMinimal();
    adapter.initializeGame();
    adapter.setIngame(this.level4);
    adapter.runGame(1000);
  }

  @After
  public void finish() {
    adapter.stopGame();
  }

  @Test
  public final void testCollisionWithHat() {
    assertTrue(adapter.isCurrentStateGameplay());
    adapter.setMarioPosition(150, 150);
    adapter.setHatPosition(142, 642);
    adapter.runGame(1000);
    assertTrue(adapter.isHatConsumed());
  }

  @Test
  public final void testCollisionWithHammer() {
    assertTrue(adapter.isCurrentStateGameplay());
    adapter.setMarioPosition(150, 150);
    adapter.setHammerPosition(142, 642);
    adapter.runGame(1000);
    assertTrue(adapter.isHammerConsumed());
  }

  @Test
  public final void testCollisionWithDonkeyKong() {
    assertTrue(adapter.isCurrentStateGameplay());
    assertEquals(3, adapter.getCurrentLifes());
    adapter.setMarioPosition(142, 610);
    adapter.setDonkeyKongPosition(142, 642);

    adapter.runGame(1000);
    assertEquals(2, adapter.getCurrentLifes());
  }
}

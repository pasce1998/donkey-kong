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
public class CollisionTest {

  DKTestAdapterMinimal adapter;
  private final String level4 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/tutors/testcases/maps/level4.dk";

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
  public final void testCollisionWithUmbrella() {
    assertTrue(adapter.isCurrentStateGameplay());
    adapter.setMarioPosition(150, 150);
    adapter.setUmbrellaPosition(142, 642);
    adapter.runGame(1000);
    assertTrue(adapter.isUmbrellaConsumed());
  }

  @Test
  public final void testCollisionWithPurse() {
    assertTrue(adapter.isCurrentStateGameplay());
    adapter.setMarioPosition(150, 150);
    adapter.setPursePosition(142, 642);
    adapter.runGame(1000);
    assertTrue(adapter.isPurseConsumed());
  }

  @Test
  public final void testCollisionWithFireball() {
    assertTrue(adapter.isCurrentStateGameplay());
    assertEquals(3, adapter.getCurrentLifes());
    adapter.setMarioPosition(142, 634);
    adapter.setFireballPosition(142, 642);

    adapter.runGame(1);

    assertEquals(2, adapter.getCurrentLifes());
  }

  @Test
  public final void testCollisionWithPauline() {
    assertTrue(adapter.isCurrentStateGameplay());
    assertEquals(3, adapter.getCurrentLifes());
    adapter.setMarioPosition(150, 150);
    adapter.setPaulinePosition(142, 642);
    adapter.runGame(1000);
    assertEquals(3, adapter.getCurrentLifes());
  }
}

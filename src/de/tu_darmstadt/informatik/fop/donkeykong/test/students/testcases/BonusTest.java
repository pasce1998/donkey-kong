package de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.informatik.fop.donkeykong.test.adapters.DKTestAdapterExtended3;


/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class BonusTest {
  
  DKTestAdapterExtended3 adapter;

  @Before
  public void setUp() {
    adapter = new DKTestAdapterExtended3();
    adapter.initializeGame();
  }

  @After
  public void finish() {
    adapter.stopGame();
  }

  @Test
  public final void testPointsDontChange() {
    adapter.resetBonus();
    assertEquals(adapter.getMaxBonus(), adapter.getCurrentBonus());
    adapter.runGame(1000);
    assertEquals(adapter.getMaxBonus(), adapter.getCurrentBonus());
  }

  @Test
  public final void testBonusDecrease() {
    adapter.resetBonus();
    int max = adapter.getMaxBonus();
    int decrease = adapter.getBonusDecreasePoints();
    assertEquals(max, adapter.getCurrentBonus());
    for(int i = 0; i < 13; i++) {
      adapter.decreaseBonus();
    }
    assertEquals(max - (13 * decrease), adapter.getCurrentBonus());
  }
}

package de.tu_darmstadt.informatik.fop.donkeykong.test.tutors.testcases;

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
  public final void testNegativePoints() {
    adapter.resetBonus();
    int max = adapter.getMaxBonus();
    int decrease = adapter.getBonusDecreasePoints();
    assertEquals(max, adapter.getCurrentBonus());
    for(int i = 0; i < (max / decrease) + 20; i++) {
      adapter.decreaseBonus();
    }
    assertEquals(0, adapter.getCurrentBonus());
  }

  @Test
  public final void testNextLevelAppliedBonus() {
    adapter.resetBonus();
    int max = adapter.getMaxBonus();
    int decrease = adapter.getBonusDecreasePoints();
    assertEquals(max, adapter.getCurrentBonus());
    for(int i = 0; i < 13; i++) {
      adapter.decreaseBonus();
    }
    assertEquals(max - (13 * decrease), adapter.getCurrentBonus());
    int acutalBonus = adapter.getCurrentBonus();
    int currentScore = adapter.getCurrentScore();
    int levelBonus = adapter.getLevelFinishedBonus();
    adapter.nextLevel();
    assertEquals(currentScore + acutalBonus + levelBonus, adapter.getCurrentScore());
  }

}

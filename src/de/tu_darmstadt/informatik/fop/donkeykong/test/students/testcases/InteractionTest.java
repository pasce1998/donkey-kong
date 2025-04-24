package de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;

import de.tu_darmstadt.informatik.fop.donkeykong.test.adapters.DKTestAdapterExtended2;
import org.junit.Test;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class InteractionTest {

  DKTestAdapterExtended2 adapter;
  private final String level4 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/students/testcases/maps/level4.dk";

  @Before
  public void setUp() {
    adapter = new DKTestAdapterExtended2();
    adapter.initializeGame();
    adapter.setIngame(this.level4);
    adapter.countButtons();
    adapter.runGame(1000);
  }

  @After
  public void finish() {
    adapter.stopGame();
  }


  @Test
  public final void testPlayerCanActivateButtons() {
    int unusedButtons = adapter.numberOfUnusedButtons();

    adapter.setMarioPosition(150, 150);
    adapter.setButtonPosition(150, 150);

    int score = adapter.getCurrentScore();

    adapter.runGame(965);

    assertTrue("Test ob ein Button aktiviert wurde", score < adapter.getCurrentScore());
  }

}

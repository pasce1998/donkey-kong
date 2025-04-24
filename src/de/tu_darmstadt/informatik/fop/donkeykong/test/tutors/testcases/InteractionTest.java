package de.tu_darmstadt.informatik.fop.donkeykong.test.tutors.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.informatik.fop.donkeykong.test.adapters.DKTestAdapterExtended2;
import eea.engine.entity.Entity;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class InteractionTest {

  DKTestAdapterExtended2 adapter;
  private final String level6 = "src/de/tu_darmstadt/informatik/fop/donkeykong/test/tutors/testcases/maps/level6.dk";

  @Before
  public void setUp() {
    adapter = new DKTestAdapterExtended2();
    adapter.initializeGame();
    adapter.setIngame(this.level6);
    adapter.runGame(1000);
  }

  @After
  public void finish() {
    adapter.stopGame();
  }

  @Test
  public final void testNoPrematureWin() {
    int unusedButtons = adapter.numberOfUnusedButtons();

    assertEquals("Es existieren zwei unbenutzte Buttons",2, adapter.numberOfUnusedButtons());

    Object[] buttons = adapter.getArrayOfButtons();
    adapter.setMarioPosition(150, 142);
    ((Entity) buttons[0]).setSize(adapter.getButtonSize());
    ((Entity) buttons[0]).setPosition(new Vector2f(150, 150));

    adapter.runGame(1);

    assertEquals("Es existiert 1 unbenutzter Button",1, adapter.numberOfUnusedButtons());
  }

  @Test
  public final void testAllButtonsWinCondition() {
    Object[] buttons = adapter.getArrayOfButtons();
    adapter.setMarioPosition(150, 142);
    ((Entity) buttons[0]).setSize(adapter.getButtonSize());
    ((Entity) buttons[0]).setPosition(new Vector2f(150, 150));

    adapter.runGame(1);

    assertEquals("Es existiert 1 unbenutzter Button",1, adapter.numberOfUnusedButtons());

    adapter.setMarioPosition(200, 142);
    ((Entity) buttons[1]).setSize(adapter.getButtonSize());
    ((Entity) buttons[1]).setPosition(new Vector2f(200, 150));

    int score = adapter.getCurrentScore();

    adapter.runGame(1);

    assertTrue("Es existieren keine unbenutzten Buttons mehr",adapter.getCurrentScore() > score);
  }

  @Test
  public final void testButtonDisappears() {
    Object[] buttons = adapter.getArrayOfButtons();
    adapter.setMarioPosition(150, 142);
    ((Entity) buttons[0]).setSize(adapter.getButtonSize());
    ((Entity) buttons[0]).setPosition(new Vector2f(150, 150));

    adapter.runGame(1);

    assertTrue("Der Button ist verschwunden", !adapter.buttonHasDisappeared((Entity) buttons[0]));
  }
}

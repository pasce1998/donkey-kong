package de.tu_darmstadt.informatik.fop.donkeykong.test.tutors.testcases;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.informatik.fop.donkeykong.test.adapters.DKTestAdapterExtended3;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class HammerTest {

  DKTestAdapterExtended3 adapter;

  @Before
  public void setUp() {
    adapter = new DKTestAdapterExtended3();
    adapter.initializeGame();
    adapter.setIngame("src/de/tu_darmstadt/informatik/fop/donkeykong/test/students/testcases/maps/level1.dk");
  }

  @After
  public void finish() {
    adapter.stopGame();
  }

  @Test
  public final void testEnemyCollisionWithHammer() {
    Entity player = adapter.getPlayer();
    assertTrue("Player exist in the map", player != null);

            Entity hammer = adapter.getHammer();
    adapter.setHammerSize(hammer);
    assertTrue("Player has no hammer", !adapter.playerHasHammer());

    adapter.setMarioPosition(hammer.getPosition().x, hammer.getPosition().y);
    adapter.runGame(1);

    assertTrue("Player has hammer", adapter.playerHasHammer());

    Entity barrel = adapter.getBarrel();
    StateBasedEntityManager.getInstance().addEntity(Consts.STATE_GAMEPLAY, barrel);
    barrel.setSize(adapter.getBarrelSize());
    barrel.setPosition(new Vector2f(player.getPosition().x, player.getPosition().y - barrel.getSize().y/2));

    int score = adapter.getCurrentScore();

    adapter.runGame(100);

    assertTrue("Score has increased", adapter.getCurrentScore() > score);
  }


}
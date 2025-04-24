package de.tu_darmstadt.informatik.fop.donkeykong.test.students.testcases;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public final void testPlayerHammerCollision() {

        Entity player = adapter.getPlayer();
        assertTrue("Player exist in the map", player != null);

        Entity hammer = adapter.getHammer();
        adapter.setHammerSize(hammer);
        assertTrue("Player has no hammer", !adapter.playerHasHammer());

        adapter.setMarioPosition(hammer.getPosition().x, hammer.getPosition().y);
        adapter.runGame(1);

        assertTrue("Player has hammer", adapter.playerHasHammer());

        boolean itemDestroyed = !StateBasedEntityManager.getInstance()
                .getEntitiesByState(Consts.STATE_GAMEPLAY).contains(hammer);
        boolean playerDestroyed = !StateBasedEntityManager.getInstance()
                .getEntitiesByState(Consts.STATE_GAMEPLAY).contains(player);

        assertTrue("Test if the destroyed Entity was an Item", itemDestroyed);
        assertTrue("Test if the still exists", !playerDestroyed);
    }
}

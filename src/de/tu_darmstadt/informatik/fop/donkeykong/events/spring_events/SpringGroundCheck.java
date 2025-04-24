package de.tu_darmstadt.informatik.fop.donkeykong.events.spring_events;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.Spring;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class SpringGroundCheck extends Event {

    public SpringGroundCheck() {
        super("SpringGroundCheck");
    }

    @Override
    protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
        Spring spring = (Spring) this.getOwnerEntity();
        return StateBasedEntityManager.getInstance().getEntitiesByState(sb.getCurrentStateID()).stream()
                .anyMatch(e -> e.collides(spring) && !e.isPassable())
                && spring.getJumpFrames() >= -spring.getMaxJumpFrames();
    }
}

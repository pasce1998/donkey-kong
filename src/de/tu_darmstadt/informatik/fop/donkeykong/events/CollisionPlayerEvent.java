package de.tu_darmstadt.informatik.fop.donkeykong.events;

import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.Border;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class CollisionPlayerEvent extends Event {


    public CollisionPlayerEvent() {
        super("CollisionPlayerEvent");
    }

    @Override
    protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
        return StateBasedEntityManager.getInstance()
                .getEntitiesByState(sb.getCurrentStateID())
                .stream().anyMatch(c -> c.collides(this.getOwnerEntity()) && c instanceof Border);
    }
}

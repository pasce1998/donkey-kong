package de.tu_darmstadt.informatik.fop.donkeykong.events;

import java.util.List;

import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.MapElement;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import eea.engine.entity.Entity;
import eea.engine.event.Event;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class CheckLadderEvent extends Event {

    public CheckLadderEvent() {
        super("CheckLadderEvent");
    }

    @Override
    protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
        List<Entity> colliders = Utilities.collides(sb.getCurrentStateID(), this.getOwnerEntity());

        return colliders.stream().anyMatch(c -> c instanceof MapElement && ((MapElement) c).isClimbable());
    }
}

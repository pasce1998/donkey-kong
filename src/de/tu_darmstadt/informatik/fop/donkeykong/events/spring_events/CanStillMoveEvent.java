package de.tu_darmstadt.informatik.fop.donkeykong.events.spring_events;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.Spring;
import eea.engine.event.Event;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class CanStillMoveEvent extends Event {


    public CanStillMoveEvent() {
        super("CanStillMoveEvent");
    }

    @Override
    protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
        Spring spring = (Spring) this.getOwnerEntity();

        return spring.getJumpFrames() >= -spring.getMaxJumpFrames();
    }
}

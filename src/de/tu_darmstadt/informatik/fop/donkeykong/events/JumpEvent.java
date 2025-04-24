package de.tu_darmstadt.informatik.fop.donkeykong.events;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class JumpEvent extends Event {

    float speed = 0.5f;

    public JumpEvent() {
        super("JumpEvent");
    }

    @Override
    protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {

        AbstractCharacter ac = (AbstractCharacter) this.getOwnerEntity();
        return ac.getJumpFrames() > 0;
    }
}

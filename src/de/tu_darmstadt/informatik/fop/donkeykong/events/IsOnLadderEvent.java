package de.tu_darmstadt.informatik.fop.donkeykong.events;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import eea.engine.event.Event;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class IsOnLadderEvent extends Event {

    public IsOnLadderEvent() {
        super("IsOnLadderEvent");
    }

    @Override
    protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
        AbstractCharacter ac = (AbstractCharacter) this.getOwnerEntity();
        AnimationKey movement = ac.getState();

        return movement == AnimationKey.CLIMB_DOWN || movement == AnimationKey.CLIMB_UP;
    }
}

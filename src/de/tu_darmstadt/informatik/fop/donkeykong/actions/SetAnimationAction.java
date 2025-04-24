package de.tu_darmstadt.informatik.fop.donkeykong.actions;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Direction;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.event.Event;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class SetAnimationAction implements Action {

    private Direction key;

    public SetAnimationAction(Direction key) {
        this.key = key;
    }

    public SetAnimationAction() {
        key = null;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
        if(event instanceof Event && event.getOwnerEntity() instanceof AbstractCharacter) {
            AbstractCharacter owner = (AbstractCharacter) event.getOwnerEntity();
            if (this.key != null) {
                owner.setState(AnimationKey.MOVE);
                owner.setDirection(this.key);
            }
            else {
                owner.setState(AnimationKey.STAND);
            }
        }
    }
}

package de.tu_darmstadt.informatik.fop.donkeykong.actions;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import eea.engine.action.Action;
import eea.engine.component.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class SetJumpModeAction implements Action {

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
        AbstractCharacter ac = (AbstractCharacter) event.getOwnerEntity();

        if (ac.getJumpMode() == 0) {
            ac.setJump();
            ac.setJumpFrames(Consts.MARIO_JUMPFRAMES);
        }
    }
}

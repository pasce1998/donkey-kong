package de.tu_darmstadt.informatik.fop.donkeykong.actions.spring_actions;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.Spring;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import eea.engine.action.Action;
import eea.engine.component.Component;

/**
 * SpringRefreshAction erneurt die Anzahl an Sprungframes
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class SpringRefreshAction implements Action {


    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
        Spring spring = (Spring) event.getOwnerEntity();

        spring.setToMaxJumpFrames();
        spring.setState(AnimationKey.STAND);
    }
}

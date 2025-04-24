package de.tu_darmstadt.informatik.fop.donkeykong.actions.spring_actions;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.Spring;
import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import eea.engine.action.Action;
import eea.engine.component.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * SpringJumpAction führ die Sprungaktion für die Feder aus
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class SpringJumpAction implements Action {

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
        Spring spring = (Spring) event.getOwnerEntity();

        Vector2f pos = spring.getPosition();
        pos.y -= (Consts.MARIO_SPEED * delta);

        spring.setState(AnimationKey.MOVE);
    }
}

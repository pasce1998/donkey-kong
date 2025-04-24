package de.tu_darmstadt.informatik.fop.donkeykong.actions;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.basicactions.Movement;
import eea.engine.component.Component;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class JumpAction extends Movement {

    public JumpAction() {
        super(0.5f);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
        super.update(gc,sb,delta,event);
        AbstractCharacter ac = (AbstractCharacter) event.getOwnerEntity();

        ac.setJumpFrames(ac.getJumpFrames() - delta);

        if(ac.getJumpFrames() <= 0) {
            ac.setFall();
            ac.setJumpFrames(0);
        }
//        Vector2f pos = getNextPosition(event.getOwnerEntity().getPosition(), this.speed, event.getOwnerEntity().getRotation(), delta);
//        event.getOwnerEntity().setPosition(pos);
    }

    @Override
    public Vector2f getNextPosition(Vector2f position, float speed, float rotation, int delta) {
        // retrieve the current position (x, y)
        Vector2f pos = new Vector2f(position);

        // update the y position by the displacement
        pos.y -= this.speed * delta;

        // return the new position
        return pos;
    }
}

package de.tu_darmstadt.informatik.fop.donkeykong.actions;

import java.util.Observable;
import java.util.Observer;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import eea.engine.action.basicactions.Movement;
import eea.engine.component.Component;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class MoveLeftRightAction extends Movement implements Observer {

    private float stairwayCorrection = 0;

    public MoveLeftRightAction(float speed) {
        super(speed);
    }

    @Override
    public Vector2f getNextPosition(Vector2f position, float speed, float rotation, int delta) {
        Vector2f pos = new Vector2f(position);

        pos.x += speed * (float)delta;
        pos.y -= this.stairwayCorrection * delta;

        this.stairwayCorrection = 0;
        return pos;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
        AnimationKey animation = ((AbstractCharacter) event.getOwnerEntity()).getState();
        if(animation == AnimationKey.JUMP || animation == AnimationKey.FALL) {
            this.stairwayCorrection = 0;
        }
        super.update(gc, sb, delta, event);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.stairwayCorrection = (Float) arg;
    }
}

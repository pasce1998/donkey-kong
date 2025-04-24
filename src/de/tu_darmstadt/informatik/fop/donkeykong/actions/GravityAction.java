package de.tu_darmstadt.informatik.fop.donkeykong.actions;

import java.util.Observable;
import java.util.Observer;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class GravityAction implements Action, Observer {


    private float speed;

    public GravityAction(float speed) {
        this.speed = speed;
    }



    @Override
    public void update(Observable o, Object currentSpeed) {
        this.speed = (Float) currentSpeed;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
        // retrieve the current position (x, y)
        AbstractCharacter ac = (AbstractCharacter) event.getOwnerEntity();
        Vector2f pos = ac.getPosition();

        // update the y position by the displacement
        pos.y += speed * delta;
        ac.setFall();
    }
}

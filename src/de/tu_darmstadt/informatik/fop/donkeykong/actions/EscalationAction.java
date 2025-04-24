package de.tu_darmstadt.informatik.fop.donkeykong.actions;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.ElevatorPlatform;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;

/**
 * Die Bewegungen der Aufzüge und Entitäten auf den Aufzügen werden hier bestimmt
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class EscalationAction implements Action {

    private float elevatorSpeed;

    public EscalationAction(float elevatorSpeed) {
        this.elevatorSpeed = elevatorSpeed;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
        ElevatorPlatform ep = (ElevatorPlatform) event.getOwnerEntity();

        if(ep.getTop() == null || ep.getBottom() == null) return;

        Vector2f position = ep.getPosition();
        position.y += this.elevatorSpeed * delta;

        if(this.elevatorSpeed < 0) {
            if(ep.getPosition().y <= ep.getTop().getPosition().y)
                position.y = ep.getBottom().getPosition().y;
        }
        else {
            if(ep.getPosition().y >= ep.getBottom().getPosition().y)
                position.y = ep.getTop().getPosition().y;
        }

        if(this.elevatorSpeed < 0) {
            Utilities.collides(sb.getCurrentStateID(), ep).stream()
                    .filter(c -> c instanceof AbstractCharacter)
                    .forEach(c -> escalate(c,delta));
        }

    }

    private void escalate(Entity ac, int delta) {
        ac.getPosition().y += this.elevatorSpeed * delta;
    }
}

package de.tu_darmstadt.informatik.fop.donkeykong.events;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import de.tu_darmstadt.informatik.fop.donkeykong.util.observer.StairObservable;
import eea.engine.entity.Entity;
import eea.engine.event.Event;
import eea.engine.interfaces.IMovement;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import java.util.List;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class MovementAcceptsStairways extends Event {

    private StairObservable so;
    private float speed;
    private IMovement move;

    public MovementAcceptsStairways(float currentSpeed, IMovement movement, StairObservable so) {
        super("MovementAcceptsStairways");
        this.speed = currentSpeed;
        this.move = movement;
        this.so = so;
    }

    protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
        AbstractCharacter ac = (AbstractCharacter) this.getOwnerEntity();
        Vector2f position = new Vector2f(ac.getPosition());
        position.x += this.speed * delta;

        Entity entity = new Entity(ac.getID());
        entity.setPosition(position);
        entity.setSize(new Vector2f(ac.getSize()));
        List<Entity> colliders = Utilities.collides(sb.getCurrentStateID(), entity);

        boolean out = true;
        float diff = 0;

        for(Entity collider : colliders) {
            out = collider == null ? true : collider.isPassable();

            if (!out) {
                float feetHeightY = entity.getPosition().y + entity.getSize().y / 2;
                float platformHeightY = collider.getPosition().y - collider.getSize().y / 2;

                float tempDiff = feetHeightY - platformHeightY;

                if (tempDiff < 3 && tempDiff >= 0 && ac.getJumpMode() == 0) {
                    diff = tempDiff > diff ? tempDiff : diff;
                }
            }
        }
        so.notifyObservers(diff);
        return true;
    }

}

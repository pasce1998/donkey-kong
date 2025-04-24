package de.tu_darmstadt.informatik.fop.donkeykong.events;

import java.util.List;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.Spring;
import de.tu_darmstadt.informatik.fop.donkeykong.util.observer.SpeedObservable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import eea.engine.entity.Entity;
import eea.engine.event.Event;


/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class GravityEvent extends Event {

    /**
     * the current movement speed
     */
    private float speed;
    private SpeedObservable observable;

    /**
     * creates a new MovementDoesNotCollide event based on the current speed and
     * the representation of the movement.
     *
     * @param speed
     *          the current movement speed
     */
    public GravityEvent (float speed, SpeedObservable observable) {
        super("GravityEvent");
        this.speed = speed;
        this.observable = observable;
    }

    /**
     * A GravityEvent will cause the action(s) associated with this
     * event to be performed if the movement does not cause a collision with
     * another entity.
     *
     * @param gc
     *          the GameContainer object that handles the game loop, recording of
     *          the frame rate, and managing the input system
     * @param sb
     *          the StateBasedGame that isolates different stages of the game
     *          (e.g., menu, ingame, highscores etc.) into different states so
     *          they can be easily managed and maintained.
     * @param delta
     *          the time passed in nanoseconds (ns) since the start of the event,
     *          used to interpolate the current target position
     *
     * @return true if the action(s) associated with this event shall be
     *         performed, else false
     */
    @Override
    protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {

        // note: we should NOT modify the position of the owning entity
        // here, since this is the job of the method
        // Event.update(GameContainer, StateBasedGame, int)
        // therefore, we create a "clone" here.
        Entity owner = this.getOwnerEntity();
        if(owner instanceof Spring && ((Spring) owner).getJumpFrames() < -((Spring) owner).getMaxJumpFrames())
            return true;

        // retrieve the next position to assume based on the movement and speed
        Vector2f position = owner.getPosition();

        Vector2f newPosition = new Vector2f(position.x, position.y + speed * delta - Consts.DEBUG_DISTANCE);

        // create a new entity with the owner entity's ID,
        Entity entity = new Entity(owner.getID());
        // adjust its position to the determined position,
        entity.setPosition(newPosition);
        // and make its size match the size of the owner entity
        entity.setSize(new Vector2f(owner.getSize()));

        // now, determine if there is a collision between this
        // "clone of the owning entity" and another object
        List<Entity> colliders = Utilities.collides(sb.getCurrentStateID(), entity);

        // if this is not the case, or the colliding entity can be passed
        // without an effect, indicate that the movement can be performed.
        boolean out = true;
        float diff = speed;

        for(Entity collider : colliders) {
            if (collider == null || collider.isPassable()) {
                diff = speed; // no collision => go ahead
            } else {
                diff = collider.getPosition().y - (collider.getSize().y + owner.getSize().y) / 2 - position.y;
            }

            if(collider != null) {
                float colliderY = collider.getPosition().y - collider.getSize().y/2;
                float entityY = this.getOwnerEntity().getPosition().y + this.getOwnerEntity().getSize().y/2;
//                System.out.println((entityY > colliderY || collider.isPassable()));
                out &= (entityY > colliderY || collider.isPassable());

//                System.out.println(entityY > colliderY);
            }
            if(!out) break;
        }


        if (out) {
            ((AbstractCharacter) this.getOwnerEntity()).setFall();
        }
        else {
            ((AbstractCharacter) this.getOwnerEntity()).setStand();
        }

        diff = diff < 0 ? speed : diff;
        if(observable != null)
            observable.notifySpeedChange(diff);

        AbstractCharacter ac = (AbstractCharacter) this.getOwnerEntity();

        return out && !(ac.getState() == AnimationKey.CLIMB_DOWN || ac.getState() == AnimationKey.CLIMB_UP);
    }
}

package de.tu_darmstadt.informatik.fop.donkeykong.events;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.AbstractEnemy;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.Fireball;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.Spring;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Direction;
import eea.engine.event.Event;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class EnemyMovementEvent extends Event {


    private float currentSpeed;

    public EnemyMovementEvent(float currentSpeed) {
        super("EnemyMovementEvent");
        this.currentSpeed = currentSpeed;
    }

    @Override
    protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
        AbstractEnemy ae = (AbstractEnemy) this.getOwnerEntity();

        Vector2f oldpos = ae.getPosition();
        Vector2f testpos = new Vector2f(ae.getPosition());

        ae.setPosition(testpos);

        if(ae instanceof Spring || ae.getState() != AnimationKey.MOVE && ae.getState() != AnimationKey.FALL)
            return false;

        float direction = ae.getDirection() == Direction.LEFT ? -1 : 1;
        ae.getPosition().x += (direction * this.currentSpeed * delta);

        if(ae instanceof Fireball) {
            GravityEvent ge = (new GravityEvent(Consts.DROP_SPEED, null));
            ge.setOwnerEntity(ae);
        }
        ae.setPosition(oldpos);
        return true;
    }
}

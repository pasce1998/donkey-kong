package de.tu_darmstadt.informatik.fop.donkeykong.actions;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.Barrel;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.AbstractEnemy;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.util.Direction;
import eea.engine.action.Action;
import eea.engine.component.Component;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class EnemyMovementAction implements Action {

    float currentSpeed;

    /**
     * Links-/Rechtsbeweung der Gegner
     * @param currentSpeed
     */
    public EnemyMovementAction(float currentSpeed) {
        this.currentSpeed = currentSpeed;
    }


    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
        AbstractEnemy ae = (AbstractEnemy) event.getOwnerEntity();

        float direction = ae.getDirection() == Direction.LEFT ? -1 : 1;

        ae.getPosition().x += direction * currentSpeed * delta;

        float stairwayCorrection = event.getOwnerEntity() instanceof Barrel ? 0f : 0.01f;

        ae.getPosition().y -= stairwayCorrection * delta;
    }
}

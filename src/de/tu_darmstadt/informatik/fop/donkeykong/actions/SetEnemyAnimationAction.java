package de.tu_darmstadt.informatik.fop.donkeykong.actions;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.AbstractEnemy;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.event.Event;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class SetEnemyAnimationAction  implements Action {

    public SetEnemyAnimationAction() {
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
        if(event instanceof Event && event.getOwnerEntity() instanceof AbstractEnemy) {
            AbstractEnemy owner = (AbstractEnemy) event.getOwnerEntity();

        }
    }
}

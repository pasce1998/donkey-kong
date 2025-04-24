package de.tu_darmstadt.informatik.fop.donkeykong.actions;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.player.Player;
import de.tu_darmstadt.informatik.fop.donkeykong.map.MapLoader;
import de.tu_darmstadt.informatik.fop.donkeykong.util.GameplayManager;
import eea.engine.action.Action;
import eea.engine.component.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Entfernt Objekte die mit der Border in Kontakt kommen.
 * Bei Kontakt mit Mario verliert Mario ein Leben.
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class BorderCollisionAction implements Action {

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
        AbstractCharacter ac = (AbstractCharacter) event.getOwnerEntity();

        if(ac instanceof Player) {
            GameplayManager.getInstance().decreaseLife();
        }
        else {
            MapLoader.getInstance().getCurrentMap().removeEntity(ac);
        }
    }
}

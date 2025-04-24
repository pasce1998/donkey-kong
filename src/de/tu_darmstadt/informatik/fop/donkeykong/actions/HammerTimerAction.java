package de.tu_darmstadt.informatik.fop.donkeykong.actions;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.player.Player;
import eea.engine.action.Action;
import eea.engine.component.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class HammerTimerAction implements Action {


    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
        Player player = (Player) event.getOwnerEntity();

        player.decreaseHammerTime(delta);

        if(player.getHammerTime() <= 0) {
            player.setHammerTime(0);
            player.setHammer(false);
        }
    }
}

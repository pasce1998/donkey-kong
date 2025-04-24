package de.tu_darmstadt.informatik.fop.donkeykong.events;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.player.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class HammerCheckEvent extends Event {

    public HammerCheckEvent() {
        super("HammerCheckEvent");
    }

    @Override
    protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
        Player ac = (Player) this.getOwnerEntity();

        return ac.getHammerTime() > 0;
    }
}

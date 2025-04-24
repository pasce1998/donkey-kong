package de.tu_darmstadt.informatik.fop.donkeykong.view.entity.listener;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import eea.engine.action.Action;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class MouseOutOfWindowListener extends Entity {

	public MouseOutOfWindowListener(Action action, int stateID) {
		super("MouseOut_Listener");
		
		Event mouseOutEvent = new Event("MouseOutEvent") {
			@Override
			protected boolean performAction(GameContainer container, StateBasedGame game, int delta) {
				if(Mouse.getX() > Consts.DISPLAY_WIDTH) return true;
				if(Mouse.getY() > Consts.DISPLAY_HEIGHT) return true;
				if(Mouse.getX() < 0) return true;
				if(Mouse.getY() < 0) return true;
				return false;
			}
		};
		mouseOutEvent.addAction(action);
		this.addComponent(mouseOutEvent);
		StateBasedEntityManager.getInstance().addEntity(stateID, this);
	}
}

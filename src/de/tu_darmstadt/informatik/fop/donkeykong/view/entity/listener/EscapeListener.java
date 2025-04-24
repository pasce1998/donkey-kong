package de.tu_darmstadt.informatik.fop.donkeykong.view.entity.listener;

import org.newdawn.slick.Input;

import eea.engine.action.Action;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.KeyPressedEvent;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class EscapeListener extends Entity {

	public EscapeListener(Action action, int stateID) {
		super("ESC_Listener");
		KeyPressedEvent escPressed = new KeyPressedEvent(Input.KEY_ESCAPE);
		escPressed.addAction(action);
		this.addComponent(escPressed);
		StateBasedEntityManager.getInstance().addEntity(stateID, this);
	}

}

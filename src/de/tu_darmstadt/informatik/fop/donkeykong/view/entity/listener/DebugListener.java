package de.tu_darmstadt.informatik.fop.donkeykong.view.entity.listener;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.KeyPressedEvent;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class DebugListener extends Entity {

  public DebugListener(int stateID) {
    super("DebugListener");
    KeyPressedEvent debugPressed = new KeyPressedEvent(Input.KEY_P);
    debugPressed.addAction(new Action() {
      @Override
      public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
        Consts.DEBUG = !Consts.DEBUG;
      }
    });
		this.addComponent(debugPressed);
		StateBasedEntityManager.getInstance().addEntity(stateID, this);
  }
  
}

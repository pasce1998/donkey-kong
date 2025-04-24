package de.tu_darmstadt.informatik.fop.donkeykong.view.entity.listener;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.util.GameplayManager;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.KeyPressedEvent;

public class DebugEndGameListener extends Entity {
  public DebugEndGameListener(int stateID) {
    super("DebugEndGameListener");
    KeyPressedEvent debugPressed = new KeyPressedEvent(Input.KEY_L);
    debugPressed.addAction(new Action() {
      @Override
      public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
        GameplayManager.getInstance().decreaseLife();
      }
    });
		this.addComponent(debugPressed);
		StateBasedEntityManager.getInstance().addEntity(stateID, this);
  }
}

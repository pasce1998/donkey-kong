package de.tu_darmstadt.informatik.fop.donkeykong.util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Life extends Entity {
  public Life() throws SlickException {
    super("Life");
    if(!DonkeyKong.isTesting) addComponent(new ImageRenderComponent(Utilities.loadImageScaled(Consts.IMAGE_MARIO_LIFE)));
    StateBasedEntityManager.getInstance().addEntity(Consts.STATE_GAMEPLAY, this);
  }

  @Override
  public void render(GameContainer gc, StateBasedGame sb, Graphics graphicsContext) {
    if(isVisible()) super.render(gc, sb, graphicsContext);
  }
}

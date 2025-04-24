package de.tu_darmstadt.informatik.fop.donkeykong.view.entity.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.map.MapLoader;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.GameplayManager;
import eea.engine.action.Action;
import eea.engine.component.Component;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class LoadMapAction implements Action {

  private String[] newMap;

  public LoadMapAction(String... newMap) {
    this.newMap = newMap;
  }

  @Override
  public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
    GameplayManager.getInstance().setMapCycle(newMap);
    GameplayManager.getInstance().resetAll();
    MapLoader.getInstance().changeMap(newMap[0]);
    sb.enterState(Consts.STATE_GAMEPLAY);
  }
  
}

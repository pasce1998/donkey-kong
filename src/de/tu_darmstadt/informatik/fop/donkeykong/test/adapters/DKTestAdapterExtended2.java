package de.tu_darmstadt.informatik.fop.donkeykong.test.adapters;

import java.util.Arrays;

import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.map.MapLoader;
import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.Button;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.GameplayManager;
import eea.engine.entity.Entity;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class DKTestAdapterExtended2 extends DKTestAdapterExtended1 {
  
  public DKTestAdapterExtended2() {
    super();
  }

  /* * ***************************************************
	 * ******************   Gameplay   *******************
	 * *************************************************** */

  /**
   * Setzt das Spiel in den Gameplay-State.
   * Das geladene Spiel muss unendlich laufen können.
   * D.h. wenn Mario nie stirbt, gibt es auch keine Ende des Spiels.
   * Wenn Mario stirbt, soll das aktuelle Level neu geladen werden, also das Spiel soll nicht von vorne beginnen.
   * @param maps Maps (als Dateiname), die als Map-Rotation verwendet werden sollen
   */
  public void setIngame(String... maps) {
    GameplayManager.getInstance().setMapCycle(maps);
    MapLoader.getInstance().changeMap(maps[0]);
    DonkeyKong.getInstance().enterState(Consts.STATE_GAMEPLAY);
  }

  /**
   * Die Methode soll das erreichen von Pauline simulieren, um zum nächsten Level der Rotation zu gelangen.
   */
  public void advanceToNextLevel() {
    GameplayManager.getInstance().nextLevel();
  }


  /* ***************************************************
   * ******************   Button   *********************
   * *************************************************** */

  public int numberOfUnusedButtons() {
    return GameplayManager.getInstance().getNumberOfUnusedButtons();
  }

  /**
   * Setzt die Position von dem ersten zu findenden Button auf die gegebenen Werte
   * @param x Neue X-Position
   * @param y Neue Y-Position
   */
  public void setButtonPosition(float x, float y) {
    Entity button = Arrays.stream(MapLoader.getInstance().getCurrentMap().getMapElements())
            .flatMap(Arrays::stream).filter(m -> m instanceof Button).findFirst().get();
    button.setPosition(new Vector2f(x, y));
    button.setSize(new Vector2f(16, 16));
  }

  public Vector2f getButtonSize() {
    return new Vector2f(16,16);
  }

  public void countButtons() {
    GameplayManager.getInstance().getNumberOfButtons();
  }

  public Vector2f getButtonPosition() {
    Entity button = Arrays.stream(MapLoader.getInstance().getCurrentMap().getMapElements())
            .flatMap(Arrays::stream).filter(m -> m instanceof Button).findFirst().get();
    return button.getPosition();
  }

  public Object[] getArrayOfButtons() {
    return Arrays.stream(MapLoader.getInstance().getCurrentMap().getMapElements())
            .flatMap(Arrays::stream).filter(b -> b instanceof Button).toArray();
  }

  public boolean buttonHasDisappeared(Entity button) {
    return button.isVisible();
  }
}

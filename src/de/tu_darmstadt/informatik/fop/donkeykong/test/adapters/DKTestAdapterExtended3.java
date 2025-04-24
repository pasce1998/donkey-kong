package de.tu_darmstadt.informatik.fop.donkeykong.test.adapters;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.Barrel;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.player.Player;
import eea.engine.entity.Entity;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.informatik.fop.donkeykong.items.Hammer;
import de.tu_darmstadt.informatik.fop.donkeykong.map.MapLoader;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.GameplayManager;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class DKTestAdapterExtended3 extends DKTestAdapterExtended2 {

  public DKTestAdapterExtended3() {
    super();
  }

  /* *************************************************** 
	 * *******************   Bonus    ********************
	 * *************************************************** */
  
  /**
   * @return Gibt den aktuellen Bonus zurück (der Bonus verringert sich mit vergangener Zeit)
   *    Der Bonus kann nur 0 erreichen, er kann nie negativ werden
   */
  public int getCurrentBonus() {
    return GameplayManager.getInstance().getBonus();
  }

  /**
   * Verringert den Bonus nach vergangener Zeit (Die Methode soll das ablaufen der Zeit simulieren)
   */
  public void decreaseBonus() {
    GameplayManager.getInstance().decreaseBonus();
  }

  /**
   * @return Anzahl der Punkte, die bei voranschreiten der Zeit vom Bonus abgezogen werden soll
   */
  public int getBonusDecreasePoints() {
    return Consts.BONUS_DECREASE;
  }

  /**
   * @return Maximaler Bonus der angerechnet werden kann
   */
  public int getMaxBonus() {
    return Consts.MAX_BONUS;
  }

  /**
   * Die Methode soll das schaffen eines Levels simulieren
   * Nach erfolgreichem beenden eines Levels sollte der aktuelle Bonus
   * und zusätzlich ein Bonus für das generelle schaffen des Levels 
   * auf den Score gerechnet werden
   */
  public void nextLevel() {
    GameplayManager.getInstance().nextLevel();
  }

  /**
   * Die Methode soll den aktuellen Bonus vollständig zurücksetzen, es soll also getCurrentBonus  = getMaxBonus gelten
   * Es soll quasi wirken, als müsste man das aktuelle Level wiederholen
   */
  public void resetBonus() {
    GameplayManager.getInstance().resetBonus();
  }

  /**
   * @return Anzahl der Punkte, die es für das beenden eines Levels gibt
   */
  public int getLevelFinishedBonus() {
    return Consts.POINTS_MAP;
  }

  /* *************************************************** 
	 * *******************   Hammer    *******************
	 * *************************************************** */

  /**
   * Erschafft einen Hammer an der gegebenen Position
   * 
   * @param x Koordinate
   * @param y Koordinate
   * @return Hammer Entität
   */
  public Entity createHammer(float x, float y) {
    Hammer hammer = null;

    try {
      hammer = new Hammer();
      int xSize = (int) (16 * Consts.SCALE);
      int ySize = (int) (16 * Consts.SCALE);

      hammer.setSize(new Vector2f(xSize, ySize));

      MapLoader.getInstance().getCurrentMap().addEntity(hammer, 0, 0);
      hammer.getPosition().x = x;
      hammer.getPosition().y = y;
    } catch (SlickException e) {
      e.printStackTrace();
    }

    return hammer;
  }

  /**
   * @return first hammer found in level
   */
  public Entity getHammer() {
    return MapLoader.getInstance().getCurrentMap().getEntities().stream()
            .filter(e -> e instanceof Hammer).findFirst().get();
  }

  /**
   * @return true, if player picked up hammer, else false
   */
  public boolean playerHasHammer() {
    return ((Player)getPlayer()).hasHammer();
  }

  /**
   * set hammer size. Use the size, you used for your own implementation
   */
  public void setHammerSize(Entity hammer) {
    hammer.setSize(new Vector2f(32f,32f));
  }

  /**
   * creates a barrel entity
   * @return creates entity
   */
  public Entity getBarrel() {
    return new Barrel();
  }

  /**
   * @return used barrel size as Vector2f
   */
  public Vector2f getBarrelSize() {
    return new Vector2f(28f,24f);
  }
}

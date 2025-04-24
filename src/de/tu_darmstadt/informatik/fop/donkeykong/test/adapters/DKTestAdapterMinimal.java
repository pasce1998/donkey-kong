package de.tu_darmstadt.informatik.fop.donkeykong.test.adapters;

import java.util.NoSuchElementException;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.Fireball;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.npc.Pauline;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.player.Player;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.items.Hammer;
import de.tu_darmstadt.informatik.fop.donkeykong.items.Hat;
import de.tu_darmstadt.informatik.fop.donkeykong.items.Purse;
import de.tu_darmstadt.informatik.fop.donkeykong.items.Umbrella;
import de.tu_darmstadt.informatik.fop.donkeykong.map.MapLoader;
import de.tu_darmstadt.informatik.fop.donkeykong.map.MapParseException;
import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.MapElement;
import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.MetalBeam;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.GameplayManager;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.test.TestAppGameContainer;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class DKTestAdapterMinimal {

  de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong dk; // erbt von StateBasedGame
  TestAppGameContainer app; // spezielle Variante des AppGameContainer, welche keine UI erzeugt (nur für Tests!)
  boolean mapParseException;
  Entity player;

  /* *************************************************** 
	 * *******************   Setup    ********************
	 * *************************************************** */

  /**
   * Verwenden Sie diesen Konstruktor zur Initialisierung von allem, was Sie benötigen
   */
  public DKTestAdapterMinimal() {
    super();
    mapParseException = false;
    setLibraryPath();
  }

  public StateBasedGame getStateBasedGame() {
    return dk;
  }

  /**
   * Sets the library path for the appropriate operating system
   * Sets a different directory when run as jar
   */
  private void setLibraryPath() {
    System.setProperty("org.lwjgl.input.Mouse.allowNegativeMouseCoords", "true");
    // Setze den library Pfad abhaengig vom Betriebssystem
    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
      System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "\\native\\windows");
    } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
      System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/macosx");
    } else {
      System.setProperty("org.lwjgl.librarypath",
          System.getProperty("user.dir") + "/native/" + System.getProperty("os.name").toLowerCase());
    }
  }

  /**
   * Diese Methode initialisiert das Spiel im Debug-Modus, d.h. es wird
   * ein AppGameContainer gestartet, der keine Fenster erzeugt und aktualisiert.
   * 
   * Sie müsssen diese Methode erweitern
   */
  public void initializeGame() {
    setLibraryPath();
    dk = new de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong(true);
    try {
      app = new TestAppGameContainer(dk);
      app.start(0);
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

  /**
   * Stoppe das im Hintergrund laufende Spiel
   */
  public void stopGame() {
    if(app != null) {
      app.exit();
      app.destroy();
    }
    StateBasedEntityManager.getInstance().clearAllStates();
    dk = null;
  }

  /**
   * Lässt das Spiel für eine angegebene Zeit laufen
   */
  public void runGame(int ms) {
    if(dk != null && app != null) {
      try {
        app.updateGame(ms);
      } catch (SlickException e) {
        e.printStackTrace();
      }
    } 
  }

  /* *************************************************** 
	 * ********************   Map    *********************
	 * *************************************************** */

  /**
   * Lädt eine Karte aus einer Datei
   * @param fileName Name/Pfad der Datei
   */
  public void loadMapFromFile(String fileName) {
    try {
      MapLoader.getInstance().loadMap(fileName);
      mapParseException = false;
    } catch (MapParseException e) {
      mapParseException = true;
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

  /**
   * @return Konnte die Karte ausgelesen und geladen werden?
   */
  public boolean isCorrectMap() {
    return !mapParseException;
  }

  /**
   * @return Der Name der aktuellen Map (der der in der Datei steht)
   */
  public String getCurrentMapName() {
    return MapLoader.getInstance().getCurrentMap().getName();
  }

  /**
   * @return Anzahl der Chunks auf der X-Achse der aktuell geladenen Map
   */
  public int getChunksX() {
    return MapLoader.getInstance().getCurrentMap().getSizeX();
  }

  /**
   * @return Anzahl der Chunks auf der Y-Achse der aktuell geladenen Map
   */
  public int getChunksY() {
    return MapLoader.getInstance().getCurrentMap().getSizeY();
  }

  /**
   * @return Anzahl der Pixel, die ein Chunk in X und Y Richtung einnimmt
   */
  public int getChunkSize() {
    return MapLoader.getInstance().getCurrentMap().getChunkSize();
  }

  /**
   * @return Anzahl der Pixel, die die Karte in X-Richtung verschoben werden soll
   */
  public int getOffsetX() {
    return MapLoader.getInstance().getCurrentMap().getOffsetX();
  }

  /**
   * @return Anzahl der Pixel, die die Karte in Y-Richtung verschoben werden soll
   */
  public int getOffsetY() {
    return MapLoader.getInstance().getCurrentMap().getOffsetY();
  }

  /**
   * @return Anzahl der Map-Elemente, die geparsed wurden
   */
  public int getMapElementsAmount() {
    int i = 0;
    for(MapElement[] row : MapLoader.getInstance().getCurrentMap().getMapElements()){
      for(MapElement elem : row){
        if(elem != null) i++;
      }
    }
    return i;
  }

  /**
   * @return Anzahl der generierten Border Elemente
   */
  public int getBorderAmount() {
    return MapLoader.getInstance().getCurrentMap().getBorder().size();
  }

  /**
   * @return Anzahl der geparsten Entities
   */
  public int getEntitiesAmount() {
    return MapLoader.getInstance().getCurrentMap().getEntities().size();
  }

  /**
   * Berrechnete Pixel-Position von Mario (Pixelposition wird aus Chunk-Position und Chunk-Pixel Größe berechnet)
   * @return Array der Position, wobei Array-Länge = 2, array[0] = X-Position und array[1] = Y-Position
   */
  public float[] getTranslatedMarioPosition() {
    Entity mario = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Player).findFirst().get();
    return new float[] {mario.getPosition().getX(), mario.getPosition().getY()};
  }

  /**
   * Berrechnete Pixel-Position von Pauline (Pixelposition wird aus Chunk-Position und Chunk-Pixel Größe berechnet)
   * @return Array der Position, wobei Array-Länge = 2, array[0] = X-Position und array[1] = Y-Position
   */
  public float[] getTranslatedPaulinePosition() {
    Entity pauline = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Pauline).findFirst().get();
    return new float[] {pauline.getPosition().getX(), pauline.getPosition().getY()};
  }

  /**
   * Berrechnete Pixel-Position von DonkeyKong (Pixelposition wird aus Chunk-Position und Chunk-Pixel Größe berechnet)
   * @return Array der Position, wobei Array-Länge = 2, array[0] = X-Position und array[1] = Y-Position
   */
  public float[] getTranslatedDonkeyKongPosition() {
    Entity donkeyKong = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof DonkeyKong).findFirst().get();
    return new float[] {donkeyKong.getPosition().getX(), donkeyKong.getPosition().getY()};
  }

  /**
   * @return player entity
   */
  public Entity getPlayer() {
    return this.player;
  }

  public Vector2f getPlayerSize() {
    return this.player.getSize();
  }

  /* *************************************************** 
	 * ******************   EndGame    *******************
	 * *************************************************** */

  /**
   * @return Prüft, ob das Spiel vorbei ist
   */
  public boolean isGameOver() {
    return GameplayManager.getInstance().isGameOver();
  }

  /**
   * @return Gibt die aktuelle Anzahl an Leben zurück
   */
  public int getCurrentLifes() {
    return GameplayManager.getInstance().getLifes();
  }

  /**
   * Zieht ein Leben von Mario ab.
   * Zudem soll geprüft werden, ob das Spiel vorbei ist und entsprechend gehandelt werden (Leben zurücksetzen und der State auf Hauptmenü geändert werden).
   */
  public void decreaseLife() {
    GameplayManager.getInstance().decreaseLife();
  }

  /**
   * @return Prüft, ob das Spiel aktuell im Spiel-Zustand ist
   */
  public boolean isCurrentStateGameplay() {
    return de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong.getInstance().getCurrentStateID() == Consts.STATE_GAMEPLAY;
  }

  /**
   * @return Prüft, ob das Spiel aktuell im Hauptmenü ist
   */
  public boolean isCurrentStateMainMenu() {
    return de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong.getInstance().getCurrentStateID() == Consts.STATE_MAINMENU;
  }

  /**
   * Setzt das Spiel in den Gamplay-Zustand und lädt eine beliebige Map.
   * @param fileName Map, die geladen werden soll
   */
  public void setIngame(String fileName) {
    GameplayManager.getInstance().setMapCycle(new String[]{ fileName });
    MapLoader.getInstance().changeMap(fileName);
    de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong.getInstance().enterState(Consts.STATE_GAMEPLAY);
    MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Player)
            .findFirst().ifPresent(entity -> this.player = entity);
  }

  /* *************************************************** 
	 * ******************   Movement    ******************
	 * *************************************************** */

  /**
   * @return Gibt Marios aktuelle Position zurück wobei array[0] = X-Position und array[1] = Y-Position
   */
  public float[] getCurrentPositionMario() {
    Entity mario = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Player).findFirst().get();
    return new float[] { mario.getPosition().getX(), mario.getPosition().getY() };
  }

   /* *************************************************** 
    * *****************   Collision    ******************
    * *************************************************** */

   /**
    * Setzt die Position von Mario auf die gegebenen Werte
    * @param x Neue X-Position
    * @param y Neue Y-Position
    */
   public void setMarioPosition(float x, float y) {
    Entity mario = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Player).findFirst().get();
    mario.setPosition(new Vector2f(x, y));
    mario.setSize(new Vector2f(32, 32));
   }

   /**
    * Setzt die Position von dem Regenschirm auf die gegebenen Werte
    * @param x Neue X-Position
    * @param y Neue Y-Position
    */
   public void setUmbrellaPosition(float x, float y) {
    Entity umbrella = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Umbrella).findFirst().get();
    umbrella.setPosition(new Vector2f(x, y));
    umbrella.setSize(new Vector2f(16, 16));
   }

   /**
    * Setzt die Position von dem Geldbeutel auf die gegebenen Werte
    * @param x Neue X-Position
    * @param y Neue Y-Position
    */
    public void setPursePosition(float x, float y) {
      Entity purse = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Purse).findFirst().get();
      purse.setPosition(new Vector2f(x, y));
      purse.setSize(new Vector2f(16, 16));
    }
   /**
    * Setzt die Position von dem Hut auf die gegebenen Werte
    * @param x Neue X-Position
    * @param y Neue Y-Position
    */
    public void setHatPosition(float x, float y) {
      Entity hat = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Hat).findFirst().get();
      hat.setPosition(new Vector2f(x, y));
      hat.setSize(new Vector2f(16, 16));
    }

   /**
    * Setzt die Position von dem Hammer auf die gegebenen Werte
    * @param x Neue X-Position
    * @param y Neue Y-Position
    */
    public void setHammerPosition(float x, float y) {
      Entity hammer = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Hammer).findFirst().get();
      hammer.setPosition(new Vector2f(x, y));
      hammer.setSize(new Vector2f(16, 16));
    }

   /**
    * Setzt die Position von dem ersten Feuerball auf die gegebenen Werte
    * @param x Neue X-Position
    * @param y Neue Y-Position
    */
    public void setFireballPosition(float x, float y) {
      Entity fireball = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Fireball).findFirst().get();
      fireball.setPosition(new Vector2f(x, y));
      fireball.setSize(new Vector2f(16, 16));
     }

    /**
    * Setzt die Position von DonkeyKong auf die gegebenen Werte
    * @param x Neue X-Position
    * @param y Neue Y-Position
    */
   public void setDonkeyKongPosition(float x, float y) {
    Entity dk = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof DonkeyKong).findFirst().get();
    dk.setPosition(new Vector2f(x, y));
    dk.setSize(new Vector2f(64, 64));
   }

   /**
    * Setzt die Position von Pauline auf die gegebenen Werte
    * @param x Neue X-Position
    * @param y Neue Y-Position
    */
  public void setPaulinePosition(float x, float y) {
      Entity pauline = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Pauline).findFirst().get();
      pauline.setPosition(new Vector2f(x, y));
      pauline.setSize(new Vector2f(16, 32));
  }

   /**
    * @return Gibt true zurück, wenn der Regenschirm verschwunden ist
    */
  public boolean isUmbrellaConsumed() {
    try{
      MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Umbrella).findFirst().get();
      return false;
    } catch (NoSuchElementException e) {
      return true;
    }
  }

   /**
    * @return Gibt true zurück, wenn der Geldbeutel verschwunden ist
    */
  public boolean isPurseConsumed() {
    try{
      MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Purse).findFirst().get();
      return false;
    } catch (NoSuchElementException e) {
      return true;
    }
  }

   /**
    * @return Gibt true zurück, wenn der Hut verschwunden ist
    */
    public boolean isHatConsumed() {
      try{
        MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Hat).findFirst().get();
        return false;
      } catch (NoSuchElementException e) {
        return true;
      }
    }

   /**
    * @return Gibt true zurück, wenn der Hammer verschwunden ist
    */
  public boolean isHammerConsumed() {
    try{
      MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Hammer).findFirst().get();
      return false;
    } catch (NoSuchElementException e) {
      return true;
    }
  }


   /* *************************************************** 
    * ******************   Gravity    *******************
    * *************************************************** */

  /**
   * @return Gibt Donkey Kongs aktuelle Position zurück wobei array[0] = X-Position und array[1] = Y-Position
   */
  public float[] getCurrentPositionDonkeyKong() {
    Entity dk = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof DonkeyKong).findFirst().get();
    return new float[] { dk.getPosition().getX(), dk.getPosition().getY() };
  }

  /**
   * @return Gibt die aktuelle Position des zuerst hinzugefügten Fireballs zurück wobei array[0] = X-Position und array[1] = Y-Position
   */
  public float[] getCurrentPositionFireball() {
    Entity fireball = MapLoader.getInstance().getCurrentMap().getEntities().stream().filter(e -> e instanceof Fireball).findFirst().get();
    return new float[] { fireball.getPosition().getX(), fireball.getPosition().getY() };
  }

  /**
   * @return Gibt die Sprunghöhe von Mario zurück
   */
  public float getJumpHeight() {
    return 24;
  }

  /**
   * @return Gibt die Höhe eines Stahlträgers zurück
   */
  public int getSteelHeight() {
    return 8;
  }

  /**
   * Fügt einen Stahlträger an gegebener Position der aktuellen Map hinzu 
   * @param x X-Position des neuen Stahlträgers
   * @param y Y-Position des neuen Stahlträgers
   */
  public void spawnMetalBeam(float x, float y) {
    try {
      MetalBeam beam = new MetalBeam();
      MapLoader.getInstance().getCurrentMap().addEntity(beam, 0, 0);
      beam.setPosition(new Vector2f(x, y));
      beam.setSize(new Vector2f(8, 8));
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

   /* *************************************************** 
    * *******************   Input    ********************
    * *************************************************** */


   /**
    * Die Methode emuliert das Key-Down Event und soll so die Bewegung testen
    * @param updateTime Zeit bis update-Aufruf
    * @param input die zu drückenden Tasten (siehe auch {@see org.newdawn.slick.Input})
    */
  public void handleKeyDown(int updateTime, Integer... input) {
    if(dk != null && app != null) {
      app.getTestInput().setKeyDown(input);
      try {
        app.updateGame(updateTime);
      } catch (SlickException e) {
        e.printStackTrace();
      }
    } 
  }

  /**
    * Die Methode emuliert das Key-Pressed Event und soll so die Bewegung testen
    * @param updateTime Zeit bis update-Aufruf
    * @param input die zu drückenden Tasten (siehe auch {@see org.newdawn.slick.Input})
    */
  public void handleKeyPressed(int updateTime, Integer... input) {
    if(dk != null && app != null) {
      app.getTestInput().setKeyPressed(input);
      try {
        app.updateGame(updateTime);
      } catch (SlickException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Diese Methode emuliert das drücken der W-Taste.
   * Dadurch sollte sich Mario nach oben bewegen.
   */
  public void handleKeyDownW() {
    handleKeyDown(1000, Input.KEY_W);
  }

  /**
   * Diese Methode emuliert das drücken der A-Taste.
   * Dadurch sollte sich Mario nach links bewegen.
   */
  public void handleKeyDownA() {
    handleKeyDown(1000, Input.KEY_A);
  }

  /**
   * Diese Methode emuliert das drücken der S-Taste.
   * Dadurch sollte sich Mario nach unten bewegen.
   */
  public void handleKeyDownS() {
    handleKeyDown(1000, Input.KEY_S);
  }

  /**
   * Diese Methode emuliert das drücken der D-Taste.
   * Dadurch sollte sich Mario nach rechts bewegen.
   */
  public void handleKeyDownD() {
    handleKeyDown(1000, Input.KEY_D);
  }

  /**
   * Diese Methode emuliert das drücken der 'nach-oben'-Taste.
   * Dadurch sollte sich Mario nach oben bewegen.
   */
  public void handleKeyDownUpArrow() {
    handleKeyDown(1000, Input.KEY_UP);
  }

  /**
   * Diese Methode emuliert das drücken der 'nach-links'-Taste.
   * Dadurch sollte sich Mario nach links bewegen.
   */
  public void handleKeyDownLeftArrow() {
    handleKeyDown(1000, Input.KEY_LEFT);
  }

  /**
   * Diese Methode emuliert das drücken der 'nach-unten'-Taste.
   * Dadurch sollte sich Mario nach unten bewegen.
   */
  public void handleKeyDownDownArrow() {
    handleKeyDown(1000, Input.KEY_DOWN);
  }

  /**
   * Diese Methode emuliert das drücken der 'nach-rechts'-Taste.
   * Dadurch sollte sich Mario nach rechts bewegen.
   */
  public void handleKeyDownRightArrow() {
    handleKeyDown(1000, Input.KEY_RIGHT);
  }

  /**
   * Diese Methode emuliert das drücken der Leertaste.
   * Dadurch sollte Mario springen.
   */
  public void handleKeyPressedSpace() {
    handleKeyPressed(0, Input.KEY_SPACE);
  }
}

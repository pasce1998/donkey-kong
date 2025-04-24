package de.tu_darmstadt.informatik.fop.donkeykong.map;

import java.util.ArrayList;
import java.util.List;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.Border;
import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.MapElement;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Map {

  private MapElement[][] mapElements;
  private List<Entity> entities;
  private List<MapElement> border;
  private int offsetX, offsetY;
  private int chunkSize;
  private String name;

  /**
   * Creates a new map
   * 
   * @param chunksX   amount of chunks in x direction
   * @param chunksY   amount of chunks in y direction
   * @param chunkSize amount of pixels per chunk
   * @param offsetX   X-Offset where the map is drawn
   * @param offsetY   Y-Offset where the map is drawn
   * @throws SlickException
   */
  public Map(int chunksX, int chunksY, int chunkSize, int offsetX, int offsetY, String name) throws SlickException {
    //if(Consts.DEBUG) System.out.println("Creating new map (" + chunksX + ", " + chunksY + ", " + chunkSize + ", " + offsetX + ", " + offsetY + ", " + name + ")");
    this.mapElements = new MapElement[chunksX][chunksY];
    this.entities = new ArrayList<>();
    this.border = new ArrayList<>();
    this.offsetX = offsetX;
    this.offsetY = offsetY;
    this.chunkSize = chunkSize;
    this.name = name;
    this.generateBorder();
  }

  /**
   * Creates a map. Functions just like the other constructor but uses default
   * values from the config file
   * 
   * @param offsetX X-Offset where the map is drawn
   * @param offsetY Y-Offset where the map is drawn
   * @throws SlickException
   */
  public Map(int offsetX, int offsetY, String name) throws SlickException {
    this(Consts.MAP_CHUNKS_X, Consts.MAP_CHUNKS_Y, Consts.MAP_PIXELS_PER_CHUNK, offsetX, offsetY, name);
  }

  /**
   * Loads all entities into the GameplayState
   */
  public void load() {
    if(Consts.DEBUG) System.out.println("Loading map into GameplayState...");
    StateBasedEntityManager em = StateBasedEntityManager.getInstance();
    for(MapElement border : this.border) {
      em.addEntity(Consts.STATE_GAMEPLAY, border);
    }
    if(Consts.DEBUG) System.out.println("Loaded border from map (" + this.name + ")!");
    if(Consts.DEBUG) System.out.println("Loading MapElements into GameplayState...");
    for(int x = 0; x < getSizeX(); x++) {
      for(int y = 0; y< getSizeY(); y++) {
        MapElement element = this.mapElements[x][y];
        if(element != null) em.addEntity(Consts.STATE_GAMEPLAY, element);
      }
    }
    if(Consts.DEBUG) System.out.println("Loaded MapElements from map (" + this.name + ")!");
    if(Consts.DEBUG) System.out.println("Loading Entities into GameplayState...");
    for(Entity entity : entities) {
      em.addEntity(Consts.STATE_GAMEPLAY, entity);
    }
    if(Consts.DEBUG) System.out.println("Loaded entities from map (" + this.name + ")!");
    if(Consts.DEBUG) System.out.println("Finished loading!");
  }  

  /**
   * Generates the border segments of the map
   * 
   * @throws SlickException
   */
  private void generateBorder() throws SlickException {
    if(Consts.DEBUG) System.out.println("Generating border for given map size...");
    // generate top and bottom border
    for (int x = -1; x < getSizeX() + 2; x++) {
      Border border = new Border("Border_" + x + "_-1");
      Border border2 = new Border("Border_" + x + "_" + (getSizeY() + 1));

      int posX = x * this.chunkSize + this.offsetX;
      int posY = this.offsetY - this.chunkSize;
        
      border.setPosition(new Vector2f(posX, posY));

      posY = this.offsetY + getSizeY() * this.chunkSize + this.chunkSize;
      border2.setPosition(new Vector2f(posX, posY));

      this.border.add(border);
      this.border.add(border2);
    }
    // generate border on the left and right
    for(int y = 0; y < getSizeY() + 1; y++) {
      Border border = new Border("Border_-1_" + y);
      Border border2 = new Border("Border_" + (getSizeX() + 1) + "_" + y);

      int posX = this.offsetX - this.chunkSize;
      int posY = y * this.chunkSize + this.offsetY;

      border.setPosition(new Vector2f(posX, posY));

      posX = this.offsetX + getSizeX() * this.chunkSize  + this.chunkSize;
      border2.setPosition(new Vector2f(posX, posY));

      this.border.add(border);
      this.border.add(border2);
    }
    if(Consts.DEBUG) System.out.println("Generated " + this.border.size() + " border elements!");
  }

  /**
   * Removes the border from the map
   */
  public void clearBorder() {
    if(Consts.DEBUG) System.out.println("Clearing border...");
    StateBasedEntityManager em = StateBasedEntityManager.getInstance();
    for(MapElement border : this.border) {
      em.removeEntity(Consts.STATE_GAMEPLAY, border);
    }
  }

  /**
   * Inserts a map element into the map at the given coordinates
   * @param element The element which needs to be inserted
   * @param chunkX X-Chunk on the map
   * @param chunkY Y-Chunk on the map
   * @return returns true if insertion was successfull and false if there already exists an element at that position
   * @throws MapParseException
   */
  public boolean setElement(MapElement element, int chunkX, int chunkY) throws MapParseException {
    if(!isInside(chunkX, chunkY)) throw new MapParseException("No such coordinates! (" + chunkX + ", " + chunkY + ")");
    if(mapElements[chunkX][chunkY] == null) { // dont overwrite already existing elements
      // check if given chunks are in boundaries
      if(!isInside(chunkX, chunkY)) throw new IllegalArgumentException("Map element outside of the map! (" + chunkX + ", " + chunkY + ")");
      int posX = chunkX * this.chunkSize + this.offsetX;
      int posY = chunkY * this.chunkSize + this.offsetY;
      element.setPosition(new Vector2f(posX, posY));
      mapElements[chunkX][chunkY] = element;
      return true;
    }
    return false;
  }

  /**
   * Removes a map element from the map
   * @param chunkX position on x axis
   * @param chunkY position on y axis
   */
  public void removeElement(int chunkX, int chunkY) {
    if(!isInside(chunkX, chunkY)) throw new IllegalArgumentException("No such coordinates! (" + chunkX + ", " + chunkY + ")");
    if(mapElements[chunkX][chunkY] != null) {
      StateBasedEntityManager em = StateBasedEntityManager.getInstance();
      em.removeEntity(Consts.STATE_GAMEPLAY, mapElements[chunkX][chunkY]);
      mapElements[chunkX][chunkY] = null;
    }
  }

  /**
   * Adds an entity to the map
   * @param entity the entity to add
   * @param chunkX x chunk coordinate 
   * @param chunkY y chunk coordinate
   * @return returns true if the entity could be added, false if not
   */
  public boolean addEntity(Entity entity, int chunkX, int chunkY) {
    int posX = chunkX * this.chunkSize + this.offsetX;
    int posY = chunkY * this.chunkSize + this.offsetY;
    if(Consts.DEBUG && entity instanceof IPlacable) System.out.println("Adding Entity " + ((IPlacable)entity).getIdentifier() + " (" + posX + ", " + posY + ")");
    entity.setPosition(new Vector2f(posX, posY)); // adjust coordinates to account for grid and map offset
    return this.entities.add(entity);
  }

  /**
   * Removes an entity from the map
   * @param entity the entity which needs to be removed
   * @return true if the entity could be removed, false if it couldn't be found
   */
  public boolean removeEntity(Entity entity) {
    if (this.entities.remove(entity)) {
      if(entity instanceof AbstractCharacter) {
        AbstractCharacter abstractChar = (AbstractCharacter) entity;
        abstractChar.deleteObserver();
      }
      StateBasedEntityManager em = StateBasedEntityManager.getInstance();
      em.removeEntity(Consts.STATE_GAMEPLAY, entity);
      return true;
    }
    return false;
  }

  /**
   * Removes every entity and map element from the loaded map
   */
  public void clearAll() {
    int numberOfEntities = entities.size();
    for(int i = 0; i < numberOfEntities; i++) {
      removeEntity(entities.get(0));
    }
    for (int x = 0; x < mapElements.length; x++) {
      for(int y = 0; y < mapElements[x].length; y++) {
        removeElement(x, y);
      }
    }
    clearBorder();
  }

  /**
   * Checks if the given chunk is within the map
   * @param chunkX chunk x coordinate
   * @param chunkY chunk y coordinate
   * @return returns false when chunk is outside of the map, true when it is inside
   */
  private boolean isInside(int chunkX, int chunkY) {
    return !(chunkX < 0 || chunkX >= getSizeX() || chunkY < 0 || chunkY >= getSizeY());
  }

  /**
   * @return returns the x axis size
   */
  public int getSizeX() {
    return mapElements.length;
  }

  /**
   * @return returns the y axis size
   */
  public int getSizeY() {
    return mapElements[0].length;
  }

  /**
   * @return returns the chunk size of the map
   */
  public int getChunkSize() {
    return chunkSize;
  }

  /**
   * @return returns the name of the map
   */
  public String getName() {
    return name;
  }

  /**
   * @return returns the offset in x direction
   */
  public int getOffsetX() {
    return offsetX;
  }

  /**
   * @return returns the offset in y direction
   */
  public int getOffsetY() {
    return offsetY;
  }

  /**
   * @return returns the entities currently on the map
   */
  public List<Entity> getEntities() {
    return entities;
  }
  
  /**
   * @return returns the border of the map
   */
  public List<MapElement> getBorder() {
    return border;
  }

  /**
   * @return returns all (static) map elements
   */
  public MapElement[][] getMapElements() {
    return mapElements;
  }
}

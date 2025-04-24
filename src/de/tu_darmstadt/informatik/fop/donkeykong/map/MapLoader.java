package de.tu_darmstadt.informatik.fop.donkeykong.map;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import de.tu_darmstadt.informatik.fop.donkeykong.util.GameplayManager;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import org.newdawn.slick.SlickException;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class MapLoader {

  private static MapLoader instance;

  private static Map currentMap;

  private static Map[] defaultMapCycle;

  /**
   * PRIVATE! constructor of the maploader
   */
  private MapLoader() {
    currentMap = null;
    parseDefaultMaps();
  }

  /**
   * Returns the singleton instance of the MapLoader
   * 
   * @return the instance
   */
  public static synchronized MapLoader getInstance() {
    if (instance == null) {
      instance = new MapLoader();
    }
    return instance;
  }

  /**
   * Loads a map into the GameplayState
   * 
   * @param fileName
   * @throws MapParseException
   * @throws SlickException
   */
  public void loadMap(String fileName) throws MapParseException, SlickException {
    Parser parser = new Parser(fileName);
    Map map = parser.parseMap();
    currentMap = map;
    map.load();
    GameplayManager.getInstance().setNumberOfButtons();
  }

  /**
   * Saves a map to a file
   * 
   * @param map      The map that should be saved
   * @param fileName The filename where the map should be saved
   * @return returns true if the file could be saved, false if not
   */
  public boolean saveMap(Map map, String fileName) {
    String mapContent = map.toString();
    return Utilities.writeFile(fileName, mapContent);
  }

  /**
   * Changes the current map in the GameplayState
   * 
   * @param fileName The new map that should be loaded
   * @return returns true if loading was successfull and false if an error
   *         occurred
   */
  public synchronized boolean changeMap(String fileName) {
    if (currentMap != null) {
      currentMap.clearAll();
    }
    try {
      String old = null;
      if(currentMap != null) old = currentMap.getName();
      loadMap(fileName);
      String current = getCurrentMap().getName();
      if(Consts.DEBUG) System.out.println("Successfully changed map" + (old == null ? "" :  " from " + old) + " to " + current + "!");
      return true;
    } catch (MapParseException e) {
      e.printStackTrace();
      return false;
    } catch (SlickException e) {
      e.printStackTrace();
      return false;
    }
  }

  public Map getCurrentMap() {
    return currentMap;
  }

  /**
   * Lists all available maps
   * @return list of all available maps
   */
  public List<String> getAvailableMaps() {
    List<String> availableMaps = new ArrayList<>();

    availableMaps.add(Consts.MAP_LEVEL1);
    availableMaps.add(Consts.MAP_LEVEL2);
    availableMaps.add(Consts.MAP_LEVEL3);

    File custom = new File(Consts.MAP_FOLDER_CUSTOM);
    File[] files = custom.listFiles(new FilenameFilter(){
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith(".dk");
      }
    });

    for(File file : files) {
      availableMaps.add(Consts.MAP_FOLDER_CUSTOM + file.getName());
    }
    return availableMaps;
  }

  /**
   * Loads all default levels/maps
   */
  private void parseDefaultMaps() {
    defaultMapCycle = new Map[3];
    try {
      Parser parser = new Parser(Consts.MAP_LEVEL1);
      defaultMapCycle[0] = parser.parseMap();
      parser = new Parser(Consts.MAP_LEVEL2);
      defaultMapCycle[1] = parser.parseMap();
      parser = new Parser(Consts.MAP_LEVEL3);
      defaultMapCycle[2] = parser.parseMap();
    } catch (MapParseException | SlickException e) {
      e.printStackTrace();
      if(Consts.DEBUG) System.out.println("Error while parsing the default maps!");
    }
  }
}

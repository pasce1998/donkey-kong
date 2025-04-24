package de.tu_darmstadt.informatik.fop.donkeykong.map;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.Barrel;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.Fireball;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies.OilDrum;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.npc.Pauline;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.player.Player;
import de.tu_darmstadt.informatik.fop.donkeykong.items.Hammer;
import de.tu_darmstadt.informatik.fop.donkeykong.items.Hat;
import de.tu_darmstadt.informatik.fop.donkeykong.items.Purse;
import de.tu_darmstadt.informatik.fop.donkeykong.items.Umbrella;
import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.*;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Parser {

  private String fileName;
  private String fileContents;
  private int currentLine;

  public Parser(String fileName) {
    this.fileName = fileName;
    this.fileContents = Utilities.readFile(fileName);
  }

  public Map parseMap() throws MapParseException, SlickException {
    Map map = null;
    this.currentLine = 0;
    if(this.fileContents == null || this.fileContents.isEmpty()) throw new MapParseException("No content found in the save (" + this.fileName + ") !");

	  for(String line : this.fileContents.split(System.lineSeparator())) {
      this.currentLine++;
      if(line.isEmpty()) continue; // skip empty lines
      if(line.startsWith(Consts.SAVEGAME_COMMENT)) continue; // skip comments

      String[] tokensOfCurrentLine = line.split(Consts.SAVEGAME_SEPERATOR); // "tokenize" current line

      // load settings (only stored in first line)
      if(map == null && this.currentLine == 1 && line.startsWith(Consts.SAVEGAME_SETTINGS_IDENTIFIER) && tokensOfCurrentLine.length == 5) {
        if(Consts.DEBUG) System.out.println("Loading map specific settings");
        try {
          int chunksX = Integer.parseInt(tokensOfCurrentLine[1]);
          int chunksY = Integer.parseInt(tokensOfCurrentLine[2]);
          int chunkSize = Integer.parseInt(tokensOfCurrentLine[3]);
          String name = tokensOfCurrentLine[4];
          map = new Map(chunksX, chunksY, chunkSize, Consts.MAP_OFFSET_X, Consts.MAP_OFFSET_Y, name);
          continue; // skip settings line because it is already parsed
        } catch (NumberFormatException e) {
          throw new MapParseException("Savegame settings are invalid!");
        }
      } else if(map == null) {
        if(Consts.DEBUG) System.out.println("No map specific settings found... loading default values");
        map = new Map(Consts.MAP_OFFSET_X, Consts.MAP_OFFSET_Y, "404-name-not-found");
      }

      if(tokensOfCurrentLine.length != 3) throw new MapParseException("No valid data found in line " + this.currentLine);
      
      String identifier = tokensOfCurrentLine[0]; // load identifier
      String xCoordinate = tokensOfCurrentLine[1]; // load x chunk coordinate
      String yCoordinate = tokensOfCurrentLine[2]; // load y chunk coordinate
      int x, y = -1;
      try { // parse chunk coordinates properly
        x = Integer.parseInt(xCoordinate);
        y = Integer.parseInt(yCoordinate);
      } catch (NumberFormatException e) {
        if(Consts.DEBUG) System.out.println("Invalid coords found in line " + this.currentLine);
        throw new MapParseException("No valid coordinates in line " + this.currentLine);
      }
      if(Consts.DEBUG) System.out.println("Found Identifier: " + Identifier.valueOf(identifier.toUpperCase()));
      switch (Identifier.valueOf(identifier.toUpperCase())) { // create appropriate Entity/MapElement out of the parsed line
        case MARIO:
        if(Consts.DEBUG) System.out.println("Loading mario at chunk: (" + x + ", " + y + ")");
          map.addEntity(new Player(), x, y);
          break;
        case DONKEY_KONG:
          map.addEntity(new DonkeyKong(), x, y);
          break;
        case PAULINE:
          map.addEntity(new Pauline(), x, y);
          break;
        case FIREBALL:
          map.addEntity(new Fireball(), x, y);
          break;
        case HAMMER:
          map.addEntity(new Hammer(), x, y);
          break;
        case HAT:
          map.addEntity(new Hat(), x, y);
          break;
        case PURSE:
          map.addEntity(new Purse(), x, y);
          break;
        case UMBRELLA:
          map.addEntity(new Umbrella(), x, y);
          break;
        case BARREL:
          map.addEntity(new Barrel(), x, y);
          break;
        case BARREL_STACK:
          map.addEntity(new Barrel(false), x, y);
          map.addEntity(new Barrel(false), x+3, y);
          map.addEntity(new Barrel(false), x, y-4);
          map.addEntity(new Barrel(false), x+3, y-4);
          break;
        case OIL_BARREL_UNLIT:
          map.addEntity(new OilDrum(), x, y);
          break;
        case LADDER:
          map.setElement(new Ladder(true), x, y);
          break;
        case LADDER_BROKEN:
          map.setElement(new Ladder(false), x, y);
          break;
        case METAL_BEAM_CLIMBABLE_7:
          MetalBeam beam15 = new MetalBeam(true);
          map.setElement(beam15, x, y);
          beam15.getPosition().add(new Vector2f(0, -7));
          break;
        case METAL_BEAM_CLIMBABLE_6:
          MetalBeam beam14 = new MetalBeam(true);
          map.setElement(beam14, x, y);
          beam14.getPosition().add(new Vector2f(0, -6));
          break;
        case METAL_BEAM_CLIMBABLE_5:
          MetalBeam beam13 = new MetalBeam(true);
          map.setElement(beam13, x, y);
          beam13.getPosition().add(new Vector2f(0, -5));
          break;
        case METAL_BEAM_CLIMBABLE_4:
          MetalBeam beam12 = new MetalBeam(true);
          map.setElement(beam12, x, y);
          beam12.getPosition().add(new Vector2f(0, -4));
          break;
        case METAL_BEAM_CLIMBABLE_3:
          MetalBeam beam11 = new MetalBeam(true);
          map.setElement(beam11, x, y);
          beam11.getPosition().add(new Vector2f(0, -3));
          break;
        case METAL_BEAM_CLIMBABLE_2:
          MetalBeam beam10 = new MetalBeam(true);
          map.setElement(beam10, x, y);
          beam10.getPosition().add(new Vector2f(0, -2));
          break;
        case METAL_BEAM_CLIMBABLE_1:
          MetalBeam beam9 = new MetalBeam(true);
          map.setElement(beam9, x, y);
          beam9.getPosition().add(new Vector2f(0, -1));
          break;
        case METAL_BEAM_CLIMBABLE:
          MetalBeam beam8 = new MetalBeam(true);
          map.setElement(beam8, x, y);
          break;
        case METAL_BEAM_7:
          MetalBeam beam7 = new MetalBeam(false);
          map.setElement(beam7, x, y);
          beam7.getPosition().add(new Vector2f(0, -7));
          break;
        case METAL_BEAM_6:
          MetalBeam beam6 = new MetalBeam(false);
          map.setElement(beam6, x, y);
          beam6.getPosition().add(new Vector2f(0, -6));
          break;
        case METAL_BEAM_5:
          MetalBeam beam5 = new MetalBeam(false);
          map.setElement(beam5, x, y);
          beam5.getPosition().add(new Vector2f(0, -5));
          break;
        case METAL_BEAM_4:
          MetalBeam beam4 = new MetalBeam(false);
          map.setElement(beam4, x, y);
          beam4.getPosition().add(new Vector2f(0, -4));
          break;
        case METAL_BEAM_3:
          MetalBeam beam3 = new MetalBeam(false);
          map.setElement(beam3, x, y);
          beam3.getPosition().add(new Vector2f(0, -3));
          break;
        case METAL_BEAM_2:
          MetalBeam beam2 = new MetalBeam(false);
          map.setElement(beam2, x, y);
          beam2.getPosition().add(new Vector2f(0, -2));
          break;
        case METAL_BEAM_1:
          MetalBeam beam1 = new MetalBeam(false);
          map.setElement(beam1, x, y);
          beam1.getPosition().add(new Vector2f(0, -1));
          break;
        case METAL_BEAM:
          MetalBeam beam = new MetalBeam(false);
          map.setElement(beam, x, y);
          break;
        case BUTTON_ON:
          map.setElement(new Button(), x, y);
          break;
        case ELEVATOR_UPPER:
          map.setElement(new ElevatorBaseTop(), x, y);
          break;
        case ELEVATOR_LOWER:
          map.setElement(new ElevatorBaseBot(), x, y);
          break;
        case ELEVATOR_BEAM: 
          map.setElement(new ElevatorBeam(), x, y);
          break;
        case ELEVATOR_PLATFORM:
          map.addEntity(new ElevatorPlatform(), x, y);
          break;
        case BORDER:
          map.setElement(new Border(), x, y);
          break;
        default:
          throw new MapParseException("No valid identifier in line " + this.currentLine);
      }
    }
    return map;
  }

}

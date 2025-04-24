package de.tu_darmstadt.informatik.fop.donkeykong.util;

import java.time.format.DateTimeFormatter;

import org.newdawn.slick.Color;

/**
 * All constants needed in the game
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class Consts {

	// Name of the game
	public static final String GAME_NAME = "Donkey Kong";
	public static final String GAME_ICON = "/resources/assets/characters/mario/mario_standing.png";

	// settings for system-libraries. These are needed to start the game in different operating systems
	public static final String NATIVE_FOLDER = "/native";
	public static final String WINDOWS_LIB_PATH = "org.lwjgl.librarypath";
	public static final String WINDOWS_USER_DIR = NATIVE_FOLDER + "/windows";
	public static final String MAC_LIB_PATH = "org.lwjgl.librarypath";
	public static final String MAC_USER_DIR = NATIVE_FOLDER + "/macosx";
	public static final String LINUX_LIB_PATH = "org.lwjgl.librarypath";
	public static final String LINUX_USER_DIR = NATIVE_FOLDER + "/";
	public static final String OS_NAME = "os.name";
	public static final String USER_DIR = "user.dir";
	public static final String WINDOWS_OS_NAME = "windows";
	public static final String MAC_OS_NAME = "mac";

	// window settings
	public static final boolean FULLSCREEN = false;
	public static final int DISPLAY_WIDTH = 1280;
	public static final int DISPLAY_HEIGHT = 720;
	public static final boolean VSYNC = false;
	public static final boolean SHOW_FPS = true;

	// game states
	public static final int STATE_MAINMENU = 0;
	public static final int STATE_GAMEPLAY = 1;
	public static final int STATE_HIGHSCORE = 2;
	public static final int STATE_SELECTLEVEL = 3;
	public static final int STATE_CREDITS = 4;
	public static final int STATE_OPTIONS = 5;
	public static final int STATE_PAUSE = 6;
	// ...

	// properties
	public static final String APP_VERSION = "1.2";

	// gameplay manager properties
	public static final int MAX_LIFES = 3;
	public static final int BONUS_DECREASE = 100;
	public static final int MAX_BONUS = 4000;

	// font
	public static final int FONT_SIZE_BUTTON_MAINMENU = 40;
	public static final String FONT_NAME_MAINMENU = "Courier New";
	
	public static final int FONT_SIZE_BACKBUTTON = 40;
	public static final String FONT_NAME_BACKBUTTON = "Courier New";

	public static final int FONT_SIZE_CREDITS = 20;
	public static final String FONT_NAME_CREDITS = "Courier New";
	
	public static final int FONT_SIZE_HIGHSCORES = 30;
	public static final String FONT_NAME_HIGHSCORES = "Courier New";

	public static final int FONT_SIZE_MAPNAME = 35;
	public static final String FONT_NAME_MAPNAME = "Courier New";
	
	public static final int FONT_SIZE_SCORE = 35;
	public static final String FONT_NAME_SCORE = "Courier New";

	public static final int FONT_SIZE_MAPSELECTOR = 20;
	public static final String FONT_NAME_MAPSELECTOR = "Courier New";
	// public static final TrueTypeFont FONT_MAPSELECTOR = new TrueTypeFont(new Font(FONT_NAME_MAPSELECTOR, Font.BOLD, FONT_SIZE_MAPSELECTOR), false);

	// text
	public static final Color COLOR_TEXT_CREDITS = Color.white;
	public static final Color COLOR_TEXT_MAPNAME = Color.blue;
	public static final Color COLOR_TEXT_SCORETITLE = Color.red;
	public static final Color COLOR_TEXT_SCORE = Color.white;
	public static final Color COLOR_TEXT_BONUSTITLE = Color.red;
	public static final Color COLOR_TEXT_BONUS = Color.cyan;

	// main menu background picture
	public static final String IMAGE_BACKGROUND_MAINMENU = "src/resources/assets/menu/menu.jpg";
	public static final String IMAGE_BACKGROUND_CREDITS = IMAGE_BACKGROUND_MAINMENU;
	public static final String IMAGE_BACKGROUND_HIGHSCORES = IMAGE_BACKGROUND_MAINMENU;
	public static final String IMAGE_BACKGROUND_PAUSE = IMAGE_BACKGROUND_MAINMENU;
	public static final String IMAGE_BACKGROUND_SELECTLEVEL = IMAGE_BACKGROUND_MAINMENU;

	// sprite scaling
	public static final float SCALE = 2;

    private static final String SPRITES_BASE_PATH = "src/resources/assets/";

	// player sprites
	public static final String IMAGE_MARIO_STANDING = SPRITES_BASE_PATH + "characters/mario/mario_standing.png";
	public static final String IMAGE_MARIO_WALKING_1 = SPRITES_BASE_PATH + "characters/mario/mario_walking_1.png";
	public static final String IMAGE_MARIO_WALKING_2 = SPRITES_BASE_PATH + "characters/mario/mario_walking_2.png";
	public static final String IMAGE_MARIO_CLIMBING_1 = SPRITES_BASE_PATH + "characters/mario/mario_climbing_1.png";
	public static final String IMAGE_MARIO_CLIMBING_2 = SPRITES_BASE_PATH + "characters/mario/mario_climbing_2.png";
	public static final String IMAGE_MARIO_DEATH_1 = SPRITES_BASE_PATH + "characters/mario/mario_death_1.png";
	public static final String IMAGE_MARIO_DEATH_2 = SPRITES_BASE_PATH + "characters/mario/mario_death_2.png";
	public static final String IMAGE_MARIO_DEATH_3 = SPRITES_BASE_PATH + "characters/mario/mario_death_3.png";
	public static final String IMAGE_MARIO_DEATH_4 = SPRITES_BASE_PATH + "characters/mario/mario_death_4.png";
	public static final String IMAGE_MARIO_HAMMER_1 = SPRITES_BASE_PATH + "characters/mario/mario_hammer_1.png";
	public static final String IMAGE_MARIO_HAMMER_2 = SPRITES_BASE_PATH + "characters/mario/mario_hammer_2.png";
	public static final String IMAGE_MARIO_HAMMER_3 = SPRITES_BASE_PATH + "characters/mario/mario_hammer_3.png";
	public static final String IMAGE_MARIO_HAMMER_4 = SPRITES_BASE_PATH + "characters/mario/mario_hammer_4.png";
	public static final String IMAGE_MARIO_JUMPING = SPRITES_BASE_PATH + "characters/mario/mario_jumping.png";

	public static final String IMAGE_MARIO_LIFE = SPRITES_BASE_PATH + "level_elements/healthbar_mario.png";

	// map elements
	public static final String IMAGE_BORDER = SPRITES_BASE_PATH + "level_elements/border.png";
	public static final String IMAGE_METAL_BEAM = SPRITES_BASE_PATH + "level_elements/metal_beam.png";
	public static final String IMAGE_LADDER = SPRITES_BASE_PATH + "level_elements/ladder.png";
	public static final String IMAGE_BUTTON = SPRITES_BASE_PATH + "level_elements/button.png";
	public static final String IMAGE_ELEVATOR_BASE_BOT = SPRITES_BASE_PATH + "level_elements/elevator_base_bot.png";
	public static final String IMAGE_ELEVATOR_BASE_TOP = SPRITES_BASE_PATH + "level_elements/elevator_base_top.png";
	public static final String IMAGE_ELEVATOR_BEAM = SPRITES_BASE_PATH + "level_elements/elevator_beam.png";
	public static final String IMAGE_ELEVATOR_PLATFORM = SPRITES_BASE_PATH + "level_elements/elevator_platform.png";


	// enemy sprites
	public static final String IMAGE_BARREL_SIDE = SPRITES_BASE_PATH + "enemies/barrel/barrel_side.png";
	public static final String IMAGE_BARREL_FRONT_1 = SPRITES_BASE_PATH + "enemies/barrel/barrel_front_1.png";
	public static final String IMAGE_BARREL_FRONT_2 = SPRITES_BASE_PATH + "enemies/barrel/barrel_front_2.png";
	public static final String IMAGE_BARREL_FRONT_3 = SPRITES_BASE_PATH + "enemies/barrel/barrel_front_3.png";
	public static final String IMAGE_BARREL_FRONT_4 = SPRITES_BASE_PATH + "enemies/barrel/barrel_front_4.png";

	// fireball
	public static final String IMAGE_FIREBALL_1 = SPRITES_BASE_PATH + "enemies/fireball/fireball_1.png";
	public static final String IMAGE_FIREBALL_2 = SPRITES_BASE_PATH + "enemies/fireball/fireball_2.png";

	// oil barrel
	public static final String IMAGE_OILBARREL_LIT_1 = SPRITES_BASE_PATH + "enemies/oil_barrel/oil_barrel_lit1.png";
	public static final String IMAGE_OILBARREL_LIT_2 = SPRITES_BASE_PATH + "enemies/oil_barrel/oil_barrel_lit2.png";
	public static final String IMAGE_OILBARREL_UNLIT = SPRITES_BASE_PATH + "enemies/oil_barrel/oil_barrel_unlit.png";

	// donkey kong
	public static final String IMAGE_DK_BOXING_1 = SPRITES_BASE_PATH + "enemies/donkey_kong/dk_boxing_1.png";
	public static final String IMAGE_DK_BOXING_2 = SPRITES_BASE_PATH + "enemies/donkey_kong/dk_boxing_2.png";
	public static final String IMAGE_DK_FALLING_1 = SPRITES_BASE_PATH + "enemies/donkey_kong/dk_falling_1.png";
	public static final String IMAGE_DK_FALLING_2 = SPRITES_BASE_PATH + "enemies/donkey_kong/dk_falling_2.png";
	public static final String IMAGE_DK_STANDING = SPRITES_BASE_PATH + "enemies/donkey_kong/dk_standing.png";
	public static final String IMAGE_DK_THROWING_1 = SPRITES_BASE_PATH + "enemies/donkey_kong/dk_throwing_1.png";
	public static final String IMAGE_DK_THROWING_2 = SPRITES_BASE_PATH + "enemies/donkey_kong/dk_throwing_2.png";

	// spring
	public static final String IMAGE_SPRING_EXPAND = SPRITES_BASE_PATH + "enemies/spring/spring_extended.png";
	public static final String IMAGE_SPRING_CONTRACT = SPRITES_BASE_PATH + "enemies/spring/spring.png";


	// item sprites
	public static final String IMAGE_HAMMER = SPRITES_BASE_PATH + "items/hammer.png";
	public static final String IMAGE_UMBRELLA = SPRITES_BASE_PATH + "items/umbrella.png";
	public static final String IMAGE_HAT = SPRITES_BASE_PATH + "items/hat.png";
	public static final String IMAGE_PURSE = SPRITES_BASE_PATH + "items/purse.png";


	// pauline sprites
	public static final String IMAGE_PAULINE_1 = SPRITES_BASE_PATH + "characters/pauline/pauline_1.png";
	public static final String IMAGE_PAULINE_2 = SPRITES_BASE_PATH + "characters/pauline/pauline_2.png";
	public static final String IMAGE_PAULINE_STANDING = SPRITES_BASE_PATH + "characters/pauline/pauline_standing.png";


	// debug sprites
	public static final String IMAGE_DEBUG = SPRITES_BASE_PATH + "characters/mario_placeholder.png";

	// frame pause
	public static final int FIREBALL_FRAMEPAUSE = 2000;

	// debug gravity
	public static final float DEBUG_DISTANCE = 0.001f;

	// speed settings
	public static final float ELEVATOR_SPEED = 0.05f;
	public static final float DROP_SPEED = 0.25f;
	public static final float CHANGE_OF_SPEED = 0.1f;

	// mario speed
	public static final float MARIO_SPEED = 0.1f;

	// enemy speed
	public static final float ENEMY_SPEED = 0.1f;

	// hammer time
	public static final int HAMMER_TIME = 15000;

	// number of jumpframes
	public static final int MARIO_JUMPFRAMES = 150;

	// points
	public static final int POINTS_PURSE = 200;
	public static final int POINTS_BARREL = 50;
	public static final int POINTS_FIREBALL = 100;
	public static final int POINTS_HAMMER = 20;
	public static final int POINTS_HAT = 150;
	public static final int POINTS_UMBRELLA = 130;
	public static final int POINTS_MAP = 500;

	// savegame settings
	public static final String SAVEGAME_COMMENT = "#";
	public static final String SAVEGAME_SEPERATOR = " ";
	public static final String SAVEGAME_SETTINGS_IDENTIFIER = "@Settings";

	// map settings
	public static final int MAP_CHUNKS_X = 64;
	public static final int MAP_CHUNKS_Y = 96;
	public static final int MAP_PIXELS_PER_CHUNK = 8;
	public static final int MAP_OFFSET_X = 320;
	public static final int MAP_OFFSET_Y = 16;

	// some maps
	public static final String MAP_FOLDER = "src/resources/saves/";
	public static final String MAP_FOLDER_CUSTOM = MAP_FOLDER + "custom/";
	public static final String MAP_LEVEL1 = MAP_FOLDER + "level1.dk";
	public static final String MAP_LEVEL2 = MAP_FOLDER + "level2.dk";
	public static final String MAP_LEVEL3 = MAP_FOLDER + "level3.dk";

	// level select settings
	public static final double SELECTLEVEL_MAX_ELEMENTS = 20d;

	// debug settings
	public static boolean DEBUG = false;
	public static boolean TESTING = false;
	
	// button settings

	// main menu button settings
	public static final Color BUTTON_COLOR_MAINMENU = Color.white;
	public static final Color BUTTON_HOVER_COLOR_MAINMENU = Color.lightGray;
	public static final boolean BUTTON_FILLED_MAINMENU = false;
	public static final Color BUTTON_FILLED_COLOR_MAINMENU = Color.lightGray;
	public static final Color BUTTON_FILLED_HOVER_COLOR_MAINMENU = Color.darkGray;

	// main menu button texts
	public static final String BUTTON_PLAY = "Play";
	public static final String BUTTON_LEVEL_SELECT = "Select a level";
	public static final String BUTTON_HIGHSCORE = "Highscores";
	public static final String BUTTON_CREDITS = "Credits";
	public static final String BUTTON_CLOSE = "Exit";

	// back button
	public static final String BUTTON_BACK = "Back";
	public static final String BUTTON_BACK_GAME = "Resume the game";
	public static final Color BUTTON_COLOR_BACK = Color.white;
	public static final Color BUTTON_HOVER_COLOR_BACK = Color.lightGray;
	public static final boolean BUTTON_FILLED_BACK = false;
	public static final Color BUTTON_FILLED_COLOR_BACK = Color.lightGray;
	public static final Color BUTTON_FILLED_HOVER_COLOR_BACK = Color.darkGray;

	// map selector button
	public static final Color BUTTON_COLOR_MAPSELECT = Color.blue;
	public static final Color BUTTON_HOVER_COLOR_MAPSELECT = new Color(77, 121, 255);
	public static final boolean BUTTON_FILLED_MAPSSELECT = true;
	public static final Color BUTTON_FILLED_COLOR_MAPSELECT = Color.lightGray;
	public static final Color BUTTON_FILLED_HOVER_COLOR_MAPSELECT = Color.darkGray;

	// credits text
	public static final String TEXT_CREDITS_LINE_1 = "This game was made for the lecture 'Praktikum: Java Spiele Framework' at TU Darmstadt.";
	public static final String TEXT_CREDITS_LINE_2 = "Developed by Pascal Schikora and Egemen Ulutürk!";

	// highscore menu
	public static final int HIGHSCORES_MAX_ENTRIES = 10;
	public static final String SCORE_SEPERATOR = ";";
	public static final String SCORE_DATE_FORMAT = "dd.MM.yyyy HH:mm";
	public static final DateTimeFormatter SCORE_FORMATTER = DateTimeFormatter.ofPattern(SCORE_DATE_FORMAT);
	public static final String HIGHSCORE_FILE = "src/resources/highscores/highscores.txt";
	public static final String HIGHSCORE_PATTERN = "&p.    &d   &s   %u";
	public static final String HIGHSCORE_LIST_TITLE = "Place       Date           Score      Player ";
	public static final String HIGHSCORE_TITLE = "The top ten highscores:";
	public static final int HIGHSCORE_MAX_PLAYERNAME_LENGTH = 8;
}
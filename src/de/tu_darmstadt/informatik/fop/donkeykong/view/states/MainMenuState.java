package de.tu_darmstadt.informatik.fop.donkeykong.view.states;

import java.awt.Font;

import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.actions.LoadMapAction;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.buttons.MainMenuButton;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.listener.DebugListener;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.listener.EscapeListener;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.Background;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.action.basicactions.QuitAction;
import eea.engine.entity.StateBasedEntityManager;

/**
 * The state for the main menu
 * 
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class MainMenuState extends BasicGameState {
	
	// identifier from this game state
	private int stateID;
	// related entityManager
	private StateBasedEntityManager entityManager;
	
	public MainMenuState(final int stateID) {
		this.stateID = stateID;
		this.entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		// Load background picture
		new Background(Consts.IMAGE_BACKGROUND_MAINMENU, this.stateID, container);

		if(!DonkeyKong.isTesting) {
			TrueTypeFont font = new TrueTypeFont(new Font(Consts.FONT_NAME_MAINMENU, Font.BOLD, Consts.FONT_SIZE_BUTTON_MAINMENU), false);

			// Play button
			Vector2f playPos = new Vector2f(Consts.DISPLAY_WIDTH / 2 , 200);
			new MainMenuButton(Consts.BUTTON_PLAY, playPos, new LoadMapAction(Consts.MAP_LEVEL1, Consts.MAP_LEVEL2, Consts.MAP_LEVEL1, Consts.MAP_LEVEL3, Consts.MAP_LEVEL2), font);
			
			// level select button
			Vector2f selectPos = new Vector2f(Consts.DISPLAY_WIDTH / 2, playPos.getY() + font.getHeight() + 5);
			new MainMenuButton(Consts.BUTTON_LEVEL_SELECT, selectPos, new ChangeStateAction(Consts.STATE_SELECTLEVEL), font);
			
			// highscores button
			Vector2f highscoresPos = new Vector2f(Consts.DISPLAY_WIDTH / 2, selectPos.getY() + font.getHeight() + 5);
			new MainMenuButton(Consts.BUTTON_HIGHSCORE, highscoresPos, new ChangeStateAction(Consts.STATE_HIGHSCORE), font);
			
			// credits button
			Vector2f creditsPos = new Vector2f(Consts.DISPLAY_WIDTH / 2, Consts.DISPLAY_HEIGHT - font.getHeight() * 2 - 25);
			new MainMenuButton(Consts.BUTTON_CREDITS, creditsPos, new ChangeStateAction(Consts.STATE_CREDITS), font);
			
			Vector2f escapePos = new Vector2f(Consts.DISPLAY_WIDTH / 2, Consts.DISPLAY_HEIGHT - font.getHeight() - 20);
			new MainMenuButton(Consts.BUTTON_CLOSE, escapePos, new QuitAction(), font);
		}
		
		new EscapeListener(new QuitAction(), this.stateID);
		// add debug listener
		new DebugListener(this.stateID);
	}

	/**
	 * Executed with the frame
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
		this.entityManager.renderEntities(container, game, graphics);
		if(Consts.DEBUG) {
			graphics.setColor(Color.red);
			graphics.drawLine(Consts.DISPLAY_WIDTH / 2, 0, Consts.DISPLAY_WIDTH / 2, Consts.DISPLAY_HEIGHT);
		}
		if(Consts.DEBUG) {
			graphics.setColor(Color.red);
			graphics.drawLine(0, Consts.DISPLAY_HEIGHT / 2, Consts.DISPLAY_WIDTH, Consts.DISPLAY_HEIGHT / 2);
		}
	}

	/**
	 * Executed before the frame
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.entityManager.updateEntities(container, game, delta);
	}

	@Override
	public int getID() {
		return this.stateID;
	}

}

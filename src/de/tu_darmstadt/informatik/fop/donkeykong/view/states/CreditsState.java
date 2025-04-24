package de.tu_darmstadt.informatik.fop.donkeykong.view.states;

import java.awt.Font;

import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.Text;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.buttons.BackButton;
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
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.Background;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.entity.StateBasedEntityManager;

/**
 * Class for the credits menu
 * @author Pascal Schikora
 * @author Egemen ulutürk
 *
 */
public class CreditsState extends BasicGameState {

	// identifier from this game state
	private int stateID;
	// related entityManager
	private StateBasedEntityManager entityManager;

	public CreditsState(final int stateID) {
		this.stateID = stateID;
		this.entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// load background
		new Background(Consts.IMAGE_BACKGROUND_CREDITS, this.stateID, container);
		
		// create backbutton
		if(!DonkeyKong.isTesting) {
			TrueTypeFont font = new TrueTypeFont(new Font(Consts.FONT_NAME_MAINMENU, Font.BOLD, Consts.FONT_SIZE_BUTTON_MAINMENU), false);
			Vector2f backPos = new Vector2f(font.getWidth(Consts.BUTTON_BACK) / 2 + 20, Consts.DISPLAY_HEIGHT - font.getHeight() / 2 - 10);
			new BackButton(backPos, new ChangeStateAction(Consts.STATE_MAINMENU), this.stateID, font);
		}
		
		if(!DonkeyKong.isTesting) {
			TrueTypeFont font = new TrueTypeFont(new Font(Consts.FONT_NAME_MAINMENU, Font.BOLD, Consts.FONT_SIZE_CREDITS), false);
		// create text
		Vector2f textPos1 = Utilities.getCenterPoint(container).add(new Vector2f(0f, -20f));
		new Text(Consts.TEXT_CREDITS_LINE_1, textPos1, Consts.COLOR_TEXT_CREDITS, font, this.stateID);
		
		Vector2f textPos2 = new Vector2f(textPos1.getX(), textPos1.getY() + font.getHeight() + 15);
		new Text(Consts.TEXT_CREDITS_LINE_2, textPos2, Consts.COLOR_TEXT_CREDITS, font, this.stateID);
		}

		// add esc listener
		new EscapeListener(new ChangeStateAction(Consts.STATE_MAINMENU), this.stateID);
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

package de.tu_darmstadt.informatik.fop.donkeykong.view.states;

import java.awt.Font;

import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.buttons.BackButton;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.buttons.Button;
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
 * The paused state of the game
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class PauseState extends BasicGameState{
	
	private int stateID;
	private StateBasedEntityManager entityManager;
	
	public PauseState(int stateID) {
		this.stateID = stateID;
		this.entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// load background
		new Background(Consts.IMAGE_BACKGROUND_PAUSE, this.stateID, container);
		
		// button to resume the game
		if(!DonkeyKong.isTesting) {
			TrueTypeFont font = new TrueTypeFont(new Font(Consts.FONT_NAME_MAINMENU, Font.BOLD, Consts.FONT_SIZE_BUTTON_MAINMENU), false);
			Vector2f backPos = Utilities.getCenterPoint(container).add(new Vector2f(0f, - font.getHeight()));
			BackButton bb = new BackButton(backPos, new ChangeStateAction(Consts.STATE_GAMEPLAY), this.stateID, font);
			bb.setText(Consts.BUTTON_BACK_GAME);

			new Button("Main Menu", Utilities.getCenterPoint(container).add(new Vector2f(0f, font.getHeight())), new ChangeStateAction(Consts.STATE_MAINMENU), Consts.STATE_PAUSE, false, font, Consts.BUTTON_COLOR_BACK, Consts.BUTTON_HOVER_COLOR_BACK, Consts.BUTTON_FILLED_COLOR_BACK, Consts.BUTTON_FILLED_HOVER_COLOR_BACK);
		}
		
		// returns to game when esc was pressed (again)
		new EscapeListener(new ChangeStateAction(Consts.STATE_GAMEPLAY), this.stateID);
		// add debug listener
		new DebugListener(this.stateID);
	}

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

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.entityManager.updateEntities(container, game, delta);
	}

	@Override
	public int getID() {
		return this.stateID;
	}

	
	
}

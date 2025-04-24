package de.tu_darmstadt.informatik.fop.donkeykong.view.states;

import java.awt.Font;

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
import de.tu_darmstadt.informatik.fop.donkeykong.util.abstracts.AbstractScore;
import de.tu_darmstadt.informatik.fop.donkeykong.util.highscore.Highscores;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.Background;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.Text;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.entity.StateBasedEntityManager;

/**
 * The state for displaying the hiscore menu
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class HighscoreState extends BasicGameState {

	// identifier from this game state
	private int stateID;
	// related entityManager
	private StateBasedEntityManager entityManager;
	
	private Highscores highscores;

	public HighscoreState(final int stateID) {
		this.stateID = stateID;
		this.entityManager = StateBasedEntityManager.getInstance();
		this.highscores = new Highscores(); // laod highscores
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// add background
		new Background(Consts.IMAGE_BACKGROUND_HIGHSCORES, this.stateID, container);
		
		if(!DonkeyKong.isTesting) {
			TrueTypeFont font = new TrueTypeFont(new Font(Consts.FONT_NAME_HIGHSCORES, Font.BOLD, Consts.FONT_SIZE_HIGHSCORES), false);
	
			// add title
			Vector2f titlePos = new Vector2f(Consts.DISPLAY_WIDTH / 2, 100);
			new Text(Consts.HIGHSCORE_TITLE, titlePos, Color.white, font, stateID);
		}
		
		// add back button
		if(!DonkeyKong.isTesting) {
			TrueTypeFont font = new TrueTypeFont(new Font(Consts.FONT_NAME_MAINMENU, Font.BOLD, Consts.FONT_SIZE_BUTTON_MAINMENU), false);
			Vector2f backPos = new Vector2f(font.getWidth(Consts.BUTTON_BACK) / 2 + 20, Consts.DISPLAY_HEIGHT - font.getHeight() / 2 - 10);
			new BackButton(backPos, new ChangeStateAction(Consts.STATE_MAINMENU), this.stateID, font);
		}
		
		// add esc listener
		new EscapeListener(new ChangeStateAction(Consts.STATE_MAINMENU), this.stateID);
		// add debug listener
		new DebugListener(this.stateID);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		this.highscores = new Highscores(); // reload the highscores
	}

	/**
	 * Executed with the frame
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
		this.entityManager.renderEntities(container, game, graphics);
		if(DonkeyKong.isTesting) return;
		TrueTypeFont font = new TrueTypeFont(new Font(Consts.FONT_NAME_HIGHSCORES, Font.BOLD, Consts.FONT_SIZE_HIGHSCORES), false);
		graphics.setColor(Color.white);
		graphics.setFont(font);
		// draw table title
		float xTitle = Consts.DISPLAY_WIDTH / 2 - font.getWidth(Consts.HIGHSCORE_LIST_TITLE) / 2;
		float yTitle = 200 - font.getHeight() * 1.5f;
		graphics.drawString(Consts.HIGHSCORE_LIST_TITLE, xTitle, yTitle);
		// draw each score
		int counter = 0;
		for(AbstractScore score : highscores.getHighscores()) {
			if(counter == 0) graphics.setColor(Color.green);
			else if(counter == 1) graphics.setColor(Color.orange);
			else if(counter == 2) graphics.setColor(Color.red);
			else graphics.setColor(Color.white);
			String output = toPatternString(++counter, score);
			float x = Consts.DISPLAY_WIDTH / 2 - font.getWidth(output) / 2;
			float y = (counter - 1) * font.getHeight() + 200;
			graphics.drawString(output, x, y);
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

	/**
	 * Uses the defined pattern and uses it to display the score
	 * @param place the place where the score landed
	 * @param score the score where the pattern needs to be applied to
	 * @return returns the string according to the pattern
	 */
	private String toPatternString(int place, AbstractScore score) {
		String output = Consts.HIGHSCORE_PATTERN;
		if(place < 10) output = output.replaceAll("&p", " " + place);
		else output = output.replaceAll("&p", "" + place);
		output = output.replaceAll("&d", score.getDate());
		// calculate padding for the score
		String maxVal = Integer.MAX_VALUE + "";
		String scoreStr = score.getScore() + "";
		int paddingScore = maxVal.length() - scoreStr.length();
		while(paddingScore > 0) {
			scoreStr = " " + scoreStr;
			paddingScore--;
		}
		output = output.replaceAll("&s", scoreStr);
		// calculate padding for the name
		String nameStr = score.getPlayerName();
		int paddingName = Consts.HIGHSCORE_MAX_PLAYERNAME_LENGTH - score.getPlayerName().length();
		while(paddingName > 0) {
			nameStr += " ";
			paddingName--;
		}
		output = output.replaceAll("%u", nameStr);
		return output;
	}
}

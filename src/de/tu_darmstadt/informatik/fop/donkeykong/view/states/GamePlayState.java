package de.tu_darmstadt.informatik.fop.donkeykong.view.states;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.Text;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.listener.DebugEndGameListener;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.listener.DebugListener;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.listener.EscapeListener;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.listener.MouseOutOfWindowListener;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.map.MapLoader;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.GameplayManager;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Life;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.entity.StateBasedEntityManager;

/**
 * The main state for playing the game
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class GamePlayState extends BasicGameState {

	// identifier from this game state
	private int stateID;
	// related entityManager
	private StateBasedEntityManager entityManager;
	// the maploader
	private MapLoader mapLoader;

	private Text levelName;
	private Text score;
	private Text bonus;

	private List<Life> healthbar;

	public GamePlayState(final int stateID) {
		this.stateID = stateID;
		this.entityManager = StateBasedEntityManager.getInstance();
		this.mapLoader = MapLoader.getInstance();
		this.healthbar = new ArrayList<>();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		if(!DonkeyKong.isTesting) {
			TrueTypeFont fontMapName = new TrueTypeFont(new Font(Consts.FONT_NAME_MAPNAME, Font.BOLD, Consts.FONT_SIZE_MAPNAME), false);
			TrueTypeFont fontScore = new  TrueTypeFont(new Font(Consts.FONT_NAME_SCORE, Font.BOLD, Consts.FONT_SIZE_SCORE), false);

			// init sidebar with level infos
			Vector2f namePos = new Vector2f(150, 50);
			levelName = new Text("LevelName", namePos, Consts.COLOR_TEXT_MAPNAME, fontMapName, this.stateID);

			// init score
			Vector2f scoreTitlePos = new Vector2f(150, 100);
			new Text("SCORE", scoreTitlePos, Consts.COLOR_TEXT_SCORETITLE, fontScore, this.stateID);
			Vector2f scorePos = new Vector2f(150, 125);
			score = new Text("score", scorePos, Consts.COLOR_TEXT_SCORE, fontScore, this.stateID);

			// init bonus
			Vector2f bonusTitlePos = new Vector2f(150, 150);
			new Text("BONUS", bonusTitlePos, Consts.COLOR_TEXT_BONUSTITLE, fontScore, this.stateID);
			Vector2f bonusPos = new Vector2f(150, 175);
			bonus = new Text("bonus", bonusPos, Consts.COLOR_TEXT_BONUS, fontScore, this.stateID);
		} else {
			levelName = null;
			score = null;
			bonus = null;
		}

		// init healthbar
		float x = 120;
		float y = 225;
		for(int i = 0; i < Consts.MAX_LIFES;  i++) {
			Life life = new Life();
			life.setPosition(new Vector2f(x + i * 25, y));
			this.healthbar.add(life);
		}

		new EscapeListener(new ChangeStateAction(Consts.STATE_PAUSE), this.stateID);
		new MouseOutOfWindowListener(new ChangeStateAction(Consts.STATE_PAUSE), this.stateID);
		new DebugListener(this.stateID);
		new DebugEndGameListener(this.stateID);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		if(mapLoader.getCurrentMap() == null) mapLoader.changeMap(Consts.MAP_LEVEL1);
	}

	/**
	 * Executed with the frame
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, Consts.DISPLAY_WIDTH - 1, Consts.DISPLAY_WIDTH - 1);
		
		// update sidebar information
		if(this.levelName != null) this.levelName.setText("L=" + mapLoader.getCurrentMap().getName());
		if(this.score != null)this.score.setText("" + GameplayManager.getInstance().getScore());
		if(this.bonus != null)this.bonus.setText("" + GameplayManager.getInstance().getBonus());

		// update healthbar
		int i = 0;
		for(Life life : this.healthbar) {
			life.setVisible(i < GameplayManager.getInstance().getLifes());
			i++;
		}

		this.entityManager.renderEntities(container, game, graphics);
	}

	/**
	 * Executed before the frame
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
//		System.out.println("focus: " + container.hasFocus() + " grabbed: " + container.isMouseGrabbed() + " paused: " + container.isPaused());
//		System.out.println("Mouse: (" + Mouse.getX() + ", " + Mouse.getY() + ")");
//		System.out.println("Window dimensions: " + container.getWidth() + " " + container.getHeight());
	if(!DonkeyKong.isTesting && (!container.hasFocus() || container.isPaused())) {
			game.enterState(Consts.STATE_PAUSE);
			return;
		}
		else this.entityManager.updateEntities(container, game, delta);
	}

	@Override
	public int getID() {
		return this.stateID;
	}

}

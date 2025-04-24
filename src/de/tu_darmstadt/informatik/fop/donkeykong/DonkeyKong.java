package de.tu_darmstadt.informatik.fop.donkeykong;

import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.view.states.CreditsState;
import de.tu_darmstadt.informatik.fop.donkeykong.view.states.GamePlayState;
import de.tu_darmstadt.informatik.fop.donkeykong.view.states.HighscoreState;
import de.tu_darmstadt.informatik.fop.donkeykong.view.states.MainMenuState;
import de.tu_darmstadt.informatik.fop.donkeykong.view.states.PauseState;
import de.tu_darmstadt.informatik.fop.donkeykong.view.states.SelectLevelState;
import eea.engine.entity.StateBasedEntityManager;

/**
 * 
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 * 
 * This is the main class of the game. It is needed to start the game.
 *
 */
public class DonkeyKong extends StateBasedGame {

	public static boolean isTesting = false;
	private static DonkeyKong instance;

	/**
	 * Initializes the name of the game
	 * 
	 * @param name name of the game
	 */
	public DonkeyKong(boolean testing) {
		super(Consts.GAME_NAME);
		instance = this;
		isTesting = testing;
	}

	public static DonkeyKong getInstance() {
		return instance;
	}

	/**
	 * Initializes the GameStates
	 * 
	 * @param container The GameContainer
	 * @throws SlickException Throws a SlickException if an error occures in the
	 *                        slick framework
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		StateBasedEntityManager sbem = StateBasedEntityManager.getInstance();
		// adds the GameStates to the StateBasedGame (the first added state is the one
		// which is started as the first state)
		addState(new MainMenuState(Consts.STATE_MAINMENU));
		addState(new GamePlayState(Consts.STATE_GAMEPLAY));
		addState(new SelectLevelState(Consts.STATE_SELECTLEVEL));
		addState(new HighscoreState(Consts.STATE_HIGHSCORE));
		addState(new CreditsState(Consts.STATE_CREDITS));
		addState(new PauseState(Consts.STATE_PAUSE));

		// add the states to the StateBasedEntityManager
		sbem.addState(Consts.STATE_MAINMENU);
		sbem.addState(Consts.STATE_GAMEPLAY);
		sbem.addState(Consts.STATE_SELECTLEVEL);
		sbem.addState(Consts.STATE_HIGHSCORE);
		sbem.addState(Consts.STATE_CREDITS);
		sbem.addState(Consts.STATE_PAUSE);
	}

	@Override
	public void enterState(int id) {
		super.enterState(id);
		if(Consts.DEBUG) System.out.println("Entering new State (" + id + ")!");
	}

}

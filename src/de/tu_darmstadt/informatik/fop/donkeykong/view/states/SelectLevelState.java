package de.tu_darmstadt.informatik.fop.donkeykong.view.states;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.actions.LoadMapAction;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.buttons.BackButton;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.buttons.Button;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.buttons.MapSelectorButton;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.listener.DebugListener;
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.listener.EscapeListener;
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
import de.tu_darmstadt.informatik.fop.donkeykong.view.entity.Background;
import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;

/**
 * The state for selecting a level to start with
 * 
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class SelectLevelState extends BasicGameState {

	// identifier from this game state
	private int stateID;
	// related entityManager
	private StateBasedEntityManager entityManager;
	// the maploader instance
	private MapLoader mapLoader;
	// all loaded map buttons
	private List<MapSelectorButton> mapButtons;
	// current page
	private int currentPage;
	// max page
	private int maxPage;
	// backwards button
	private Button backwardsBtn;
	// forwards button
	private Button forwardsBtn;

	public SelectLevelState(final int stateID) {
		this.stateID = stateID;
		this.entityManager = StateBasedEntityManager.getInstance();
		this.mapLoader = MapLoader.getInstance();
		this.mapButtons = new ArrayList<>();
		this.currentPage = 0;
		this.maxPage = 0;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// load background
		new Background(Consts.IMAGE_BACKGROUND_SELECTLEVEL, this.stateID, container);

		// create backbutton
		if(!DonkeyKong.isTesting) {
			TrueTypeFont font = new TrueTypeFont(new Font(Consts.FONT_NAME_MAINMENU, Font.BOLD, Consts.FONT_SIZE_BUTTON_MAINMENU), false);
			TrueTypeFont fontPagination = new TrueTypeFont(new Font(Consts.FONT_NAME_MAINMENU, Font.BOLD, Consts.FONT_SIZE_BUTTON_MAINMENU), false);

			Vector2f backPos = new Vector2f(font.getWidth(Consts.BUTTON_BACK) / 2 + 20, Consts.DISPLAY_HEIGHT - font.getHeight() / 2 - 10);
			new BackButton(backPos, new ChangeStateAction(Consts.STATE_MAINMENU), this.stateID, font);
		
			// add pagination buttons and hide them
			backwardsBtn = new Button("<-", new Vector2f(Consts.DISPLAY_WIDTH / 2 - 200, Consts.DISPLAY_HEIGHT - 50), new Action() {
				@Override
				public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
					currentPage--;
					if(currentPage < 0) currentPage = 0;
					switchPage();
				}
			}, this.stateID, fontPagination);
			forwardsBtn = new Button("->", new Vector2f(Consts.DISPLAY_WIDTH / 2 + 200, Consts.DISPLAY_HEIGHT - 50), new Action() {
				@Override
				public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
					currentPage++;
					if(currentPage >= maxPage) currentPage = maxPage - 1;
					switchPage();
				}
			}, this.stateID, fontPagination);
		}
		
		if(backwardsBtn != null && forwardsBtn != null) {
			backwardsBtn.setVisible(false);
			forwardsBtn.setVisible(false);
		}

		// add esc listener
		new EscapeListener(new ChangeStateAction(Consts.STATE_MAINMENU), this.stateID);
		// add debug listener
		new DebugListener(this.stateID);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);

		// freshly laod all available maps
		if(Consts.DEBUG) System.out.println("Loading available maps...");
		List<String> availableMaps = mapLoader.getAvailableMaps();
		if(Consts.DEBUG) {
			System.out.println("Found " + availableMaps.size() + " map/s!");
			System.out.println("Available maps:");
			for(String str : availableMaps) {
				System.out.println(str);
			}
		}
		
		// update max available pages
		this.maxPage = (int) Math.ceil(availableMaps.size() / Consts.SELECTLEVEL_MAX_ELEMENTS);

		// create buttons from available maps
		for(String str : availableMaps) {
			if(DonkeyKong.isTesting) break;

			TrueTypeFont font = new TrueTypeFont(new Font(Consts.FONT_NAME_MAPSELECTOR, Font.BOLD, Consts.FONT_SIZE_MAPSELECTOR), false);
			MapSelectorButton btn = new MapSelectorButton(str, new Vector2f(0, 0), new LoadMapAction(str), font);
			mapButtons.add(btn);
		}

		this.currentPage = 0;
		switchPage();
	}

	/**
	 * Reloads all buttons according to the pagination
	 */
	private void switchPage() {
		// make pagination buttons visible if there is more than 1 page and according to current page
		if(this.maxPage > 1) {
			this.forwardsBtn.setVisible(true);
			this.backwardsBtn.setVisible(true);
			if(this.currentPage == 0) this.backwardsBtn.setVisible(false);
			if(this.currentPage == this.maxPage - 1) this.forwardsBtn.setVisible(false);
		} else {
			this.backwardsBtn.setVisible(false);
			this.forwardsBtn.setVisible(false);
		}
		if(Consts.DEBUG) System.out.println("Switching page to page " + this.currentPage + "...");
		// hide all old/other buttons
		for(MapSelectorButton btn : mapButtons) {
			btn.setVisible(false);
		}
		// calculate current index
		int startIdx = (int) (this.currentPage * Consts.SELECTLEVEL_MAX_ELEMENTS);
		int endIdx = (int) (startIdx + Consts.SELECTLEVEL_MAX_ELEMENTS);
		if(endIdx >= this.mapButtons.size())  endIdx = this.mapButtons.size();

		if(Consts.DEBUG) System.out.println("Loading " + (endIdx - startIdx) + " entries in pagination!");
		// set new button position and show button
		int x = Consts.DISPLAY_WIDTH / 2;
		int y = 50;
		for(int i = startIdx; i < endIdx; i++) {
			MapSelectorButton btn = this.mapButtons.get(i);
			btn.setPosition(new Vector2f(x, y));
			btn.setVisible(true);
			y += 25;
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
		// unregister all buttons when leaving
		for(MapSelectorButton btn : mapButtons) {
			btn.removeButton();
		}
		this.mapButtons = new ArrayList<>();
	}

	/**
	 * Executed with the frame
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
		this.entityManager.renderEntities(container, game, graphics);
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

package de.tu_darmstadt.informatik.fop.donkeykong.view.entity.buttons;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import eea.engine.action.Action;

/**
 * Class for creating a menu button
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class MainMenuButton extends Button {
	
	/**
	 * Constructor for creating a new button
	 * @param text the text inside the button
	 * @param pos the position of the button
	 * @param clickAction the 
	 * @param font the font of the button
	 */
	public MainMenuButton(final String text, final Vector2f pos, Action clickAction, TrueTypeFont font) {
		super(text, pos, clickAction, Consts.STATE_MAINMENU, Consts.BUTTON_FILLED_MAINMENU, font, Consts.BUTTON_COLOR_MAINMENU, Consts.BUTTON_HOVER_COLOR_MAINMENU, Consts.BUTTON_FILLED_COLOR_MAINMENU, Consts.BUTTON_FILLED_HOVER_COLOR_MAINMENU);
	}
	
	
}

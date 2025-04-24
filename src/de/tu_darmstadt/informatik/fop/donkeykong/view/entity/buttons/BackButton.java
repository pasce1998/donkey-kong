package de.tu_darmstadt.informatik.fop.donkeykong.view.entity.buttons;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import eea.engine.action.Action;

/**
 * Class for creating the back-button for different states
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class BackButton extends Button {

	/**
	 * Creating a new back button
	 * @param pos position of the button
	 * @param clickAction what should happen after button is clicked
	 * @param stateID the state in which the button appears
	 * @param font font of the button
	 */
	public BackButton(Vector2f pos, Action clickAction, int stateID, TrueTypeFont font) {
		super(Consts.BUTTON_BACK, pos, clickAction, stateID, Consts.BUTTON_FILLED_BACK, font, Consts.BUTTON_COLOR_BACK, Consts.BUTTON_HOVER_COLOR_BACK, Consts.BUTTON_FILLED_COLOR_BACK, Consts.BUTTON_FILLED_HOVER_COLOR_BACK);
	}

}

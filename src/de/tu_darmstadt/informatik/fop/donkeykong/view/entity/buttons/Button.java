package de.tu_darmstadt.informatik.fop.donkeykong.view.entity.buttons;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.NOTEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;

/**
 * Main class for creating new buttons
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class Button extends Entity {

	private int stateID;
	private String text;
	private Color currentTextColor;
	private Color currentBackgroundColor;
	private Color textColor;
	private Color textHoverColor;
	private Color backgroundColor;
	private Color backgroundHoverColor;
	private TrueTypeFont font;
	private boolean isFilled;
	
	/**
	 * Create a new button
	 * @param text the button text
	 * @param pos the middlepoint of the button
	 * @param clickAction what happens when the button is clicked
	 * @param stateID in which state the button should be added
	 * @param filled if the button has a background or not
	 * @param font the font of the button text
	 * @param textColor the color of the button text
	 * @param textHoverColor the color of the button text when hovering over the button
	 * @param bgColor the background color of the button
	 * @param bgHoverColor the background color of the button when hovering over the button
	 */
	public Button(String text, Vector2f pos, Action clickAction, int stateID, boolean filled, TrueTypeFont font, Color textColor, Color textHoverColor, Color bgColor, Color bgHoverColor) {
		super(text);
		
		this.stateID = stateID;
		
		// set button properties
		this.text = text; // workaround for updating the size
		this.setPosition(pos);
		this.setFont(font);
		this.setText(text);
		this.setFilled(filled);
		this.setBackgroundColor(bgColor);
		this.setBackgroundHoverColor(bgHoverColor);
		this.setTextColor(textColor);
		this.setTextHoverColor(textHoverColor);
		
		this.currentTextColor = textColor;
		this.currentBackgroundColor = bgColor;
		
		// add hover listeners
		this.addHoverEvents();
		
		// add click listener
		this.addClickEvent(clickAction);
		
		// add button to desired menu
		StateBasedEntityManager.getInstance().addEntity(this.stateID, this);
	}
	
	/**
	 * Creates a new button
	 * @param text the text of the button
	 * @param pos the middlepoint of the button
	 * @param clickAction what happens when the button is clicked
	 * @param stateID the state in which to add the button
	 * @param font the font of the button text
	 */
	public Button(String text, Vector2f pos, Action clickAction, int stateID, TrueTypeFont font) {
		super(text);
		
		this.stateID = stateID;
		this.text = text;
		this.setPosition(pos);
		this.setFont(font);
		this.setText(text);
		this.setFilled(false);
		this.setBackgroundColor(Color.transparent);
		this.setBackgroundHoverColor(Color.transparent);
		this.setTextColor(Color.white);
		this.setTextHoverColor(Color.gray);
		
		this.currentTextColor = Color.white;
		this.currentBackgroundColor = Color.transparent;
		
		this.addHoverEvents();
		this.addClickEvent(clickAction);
		
		this.addButton();
	}

	/**
	 * Adds the button to the assigned state
	 */
	public void addButton() {
		StateBasedEntityManager.getInstance().addEntity(this.stateID, this);
	}

	/**
	 * Removes the button from the assigned state
	 */
	public void removeButton() {
		StateBasedEntityManager.getInstance().removeEntity(this.stateID, this);
	}
	
	/**
	 * Add the action what happens when the button is clicked
	 * @param action what should happen when the button is clicked
	 */
	private void addClickEvent(Action action) {
		ANDEvent click = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
		click.addAction(action);
		this.addComponent(click);
	}
	
	/**
	 * Used to add the hover effect to the button
	 */
	private void addHoverEvents() {
		// mouse entered the button
		Event buttonEnterEvent = new MouseEnteredEvent();
		buttonEnterEvent.addAction(new Action() {
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				currentTextColor = textHoverColor;
				currentBackgroundColor = backgroundHoverColor;
			}
		});
		
		// mouse leaves the button
		Event buttonLeaveEvent = new NOTEvent(new MouseEnteredEvent());
		buttonLeaveEvent.addAction(new Action() {
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				currentTextColor = textColor;
				currentBackgroundColor = backgroundColor;
			}
		});
		
		// add the events to the button
		this.addComponent(buttonEnterEvent);
		this.addComponent(buttonLeaveEvent);
	}
	
	/**
	 * Setter for the font
	 * @param font the new font
	 */
	public void setFont(TrueTypeFont font) {
		this.font = font;
		this.setSize(new Vector2f(this.font.getWidth(text), this.font.getHeight()));
	}
	
	/**
	 * Set the button to filled/unfilled background
	 * @param isFilled the new state
	 */
	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}
	
	/**
	 * Setter for the background hover color
	 * @param backgroundHoverColor the new color
	 */
	public void setBackgroundHoverColor(Color backgroundHoverColor) {
		this.backgroundHoverColor = backgroundHoverColor;
	}
	
	/**
	 * Setter for the background color
	 * @param backgroundColor the new color
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	/**
	 * Setter for the text hover color
	 * @param textHoverColor the new color
	 */
	public void setTextHoverColor(Color textHoverColor) {
		this.textHoverColor = textHoverColor;
	}
	
	/**
	 * Setter for the text color
	 * @param textColor the new color
	 */
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	/**
	 * Setter for the button text
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
		this.setSize(new Vector2f(this.font.getWidth(text), this.font.getHeight()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) {
		super.render(container, game, graphics);

		if(!isVisible()) return;

		float offsetX = this.font.getWidth(this.text) / 2;
		float offsetY =  this.font.getHeight() / 2;
		
		// fill background
		if(this.isFilled) {
			graphics.setColor(this.currentBackgroundColor);
			graphics.fillRect(this.getPosition().x - offsetX, this.getPosition().y - offsetY, this.getSize().x, this.getSize().y);
		}
		
		// draw the button text
		graphics.setFont(this.font);
		graphics.setColor(this.currentTextColor);
		graphics.drawString(this.text, this.getPosition().x - offsetX, this.getPosition().y - offsetY);
		
		// draw debug options
		if(Consts.DEBUG) {
			// draw box
			graphics.setColor(Color.green);
			graphics.drawRect(this.getPosition().x - offsetX, this.getPosition().y - offsetY, this.getSize().x, this.getSize().y);
			// draw positioning point
			graphics.setColor(Color.blue);
			graphics.fillRect(this.getPosition().x, this.getPosition().y, 5, 5);
		}
		
	}
}

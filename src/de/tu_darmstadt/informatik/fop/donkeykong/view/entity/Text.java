package de.tu_darmstadt.informatik.fop.donkeykong.view.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

/**
 * Class for creating text in a menu
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class Text extends Entity {
	
	private int stateID;
	
	private String text;
	private Color currentColor;
	private TrueTypeFont font;

	public Text(String text, Vector2f pos, Color color, TrueTypeFont font, final int stateID) {
		super(text);
		this.currentColor = color;
		this.stateID = stateID;
		this.font = font;
		
		this.setPosition(pos);
		this.setText(text);
		this.setSize(new Vector2f(this.font.getWidth(this.text), this.font.getHeight()));
		
		StateBasedEntityManager.getInstance().addEntity(this.stateID, this);
	}
	
	/**
	 * Change the text color
	 * @param textColor the new color
	 */
	public void setTextColor(Color textColor) {
		this.currentColor = textColor;
	}
	
	/**
	 * Sets a new button text
	 * @param text the new text
	 */
	public void setText(String text){
		this.text = text;
		this.setSize(new Vector2f(this.font.getWidth(text), this.font.getHeight()));
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) {
		super.render(container, game, graphics);
		float offsetX = this.font.getWidth(this.text) / 2;
		float offsetY = this.font.getHeight() / 2;
		graphics.setFont(this.font);
		graphics.setColor(this.currentColor);
		graphics.drawString(this.text, this.getPosition().x - offsetX, this.getPosition().y - offsetY);
	}
	

}

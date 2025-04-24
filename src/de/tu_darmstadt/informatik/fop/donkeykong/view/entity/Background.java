package de.tu_darmstadt.informatik.fop.donkeykong.view.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

/**
 * Class for creating and loading a background picture
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class Background extends Entity {

	private int stateID;

	public Background(String fileName, int stateID, GameContainer container) throws NullPointerException, SlickException {
		super(fileName);
		this.stateID = stateID;
		this.setPosition(Utilities.getCenterPoint(container));
		if(!DonkeyKong.isTesting) this.addComponent(new ImageRenderComponent(Utilities.loadImage(fileName)));
		StateBasedEntityManager.getInstance().addEntity(this.stateID, this);
	}

}

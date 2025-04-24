package de.tu_darmstadt.informatik.fop.donkeykong.map_elements;

import org.newdawn.slick.SlickException;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import eea.engine.component.render.ImageRenderComponent;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Border extends MapElement {

    public Border(String id) throws SlickException {
        super(id);
        if(!DonkeyKong.isTesting) this.addComponent(new ImageRenderComponent(Utilities.loadImage(Consts.IMAGE_BORDER)));
        this.setPassable(true);
    }

    public Border(String id, boolean bool) throws SlickException {
        super(id);
        if(!DonkeyKong.isTesting) this.addComponent(new ImageRenderComponent(Utilities.loadImage(Consts.IMAGE_BORDER).getScaledCopy(2)));
        this.setPassable(true);
    }

    public Border() throws SlickException {
        this("Border");
    }
    
    @Override
    public Identifier getIdentifier() {
    	return Identifier.BORDER;
    }



}

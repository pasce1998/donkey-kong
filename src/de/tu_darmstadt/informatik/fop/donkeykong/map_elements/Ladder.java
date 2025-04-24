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
public class Ladder extends MapElement {

    public Ladder(boolean climbable) throws SlickException {
        super("Ladder", climbable);
        if(!DonkeyKong.isTesting) this.addComponent(new ImageRenderComponent(Utilities.loadImageScaled(Consts.IMAGE_LADDER)));
    }


    @Override
    public Identifier getIdentifier() {
        return Identifier.LADDER;
    }
}

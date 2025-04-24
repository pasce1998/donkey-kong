package de.tu_darmstadt.informatik.fop.donkeykong.map_elements;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import eea.engine.component.render.ImageRenderComponent;
import org.newdawn.slick.SlickException;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class ElevatorBaseTop extends MapElement {

    public ElevatorBaseTop(String id) throws SlickException {
        super(id);
        if(!DonkeyKong.isTesting) this.addComponent(new ImageRenderComponent(Utilities.loadImageScaled(Consts.IMAGE_ELEVATOR_BASE_TOP)));
        this.setPassable(false);
    }

    public ElevatorBaseTop() throws SlickException {
        this("Border");
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.ELEVATOR_UPPER;
    }
}

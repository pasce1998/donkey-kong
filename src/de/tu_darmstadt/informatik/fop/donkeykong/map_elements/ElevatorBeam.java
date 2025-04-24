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
public class ElevatorBeam extends MapElement {

    public ElevatorBeam(String id) throws SlickException {
        super(id);
        if(!DonkeyKong.isTesting) this.addComponent(new ImageRenderComponent(Utilities.loadImageScaled(Consts.IMAGE_ELEVATOR_BEAM)));
        this.setPassable(true);
    }

    public ElevatorBeam() throws SlickException {
        this("ElevatorBeam");
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.ELEVATOR_BEAM;
    }

}


package de.tu_darmstadt.informatik.fop.donkeykong.items;

import org.newdawn.slick.SlickException;

import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Hat extends AbstractItem {

    public Hat(Coordinate position) throws SlickException {
        super("Hat", position, Consts.IMAGE_HAT);
    }

    public Hat() throws SlickException {
        this(new Coordinate(0, 0));
    }

    @Override
    public int getPoints() {
        return Consts.POINTS_HAT;
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.HAT;
    }
}

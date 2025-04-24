package de.tu_darmstadt.informatik.fop.donkeykong.items;

import org.newdawn.slick.SlickException;

import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Umbrella extends AbstractItem {

    public Umbrella(Coordinate position) throws SlickException {
        super("Umbrella", position, Consts.IMAGE_UMBRELLA);
    }

    public Umbrella() throws SlickException {
        this(new Coordinate(0, 0));
    }

    @Override
    public int getPoints() {
        return Consts.POINTS_UMBRELLA;
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.UMBRELLA;
    }
}

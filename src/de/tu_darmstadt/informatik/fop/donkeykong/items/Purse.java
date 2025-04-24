package de.tu_darmstadt.informatik.fop.donkeykong.items;

import org.newdawn.slick.SlickException;

import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */public class Purse extends AbstractItem {

	public Purse(Coordinate position) throws SlickException {
		super("Purse", position, Consts.IMAGE_PURSE);
	}

	public Purse() throws SlickException {
		this(new Coordinate(0, 0));
	}

	@Override
	public int getPoints() {
    	return Consts.POINTS_PURSE;
	}

	@Override
	public Identifier getIdentifier() {
		return Identifier.PURSE;
	}
    

}

package de.tu_darmstadt.informatik.fop.donkeykong.map_elements;

import de.tu_darmstadt.informatik.fop.donkeykong.map.IPlacable;
import eea.engine.entity.Entity;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public abstract class MapElement extends Entity implements IPlacable {

    private boolean climbable;

    public MapElement(String id, boolean climbable) {
        super(id);
        this.climbable = climbable;
    }

    public MapElement(String id) {
        this(id, false);
    }

    public boolean isClimbable() {
        return this.climbable;
    }
}

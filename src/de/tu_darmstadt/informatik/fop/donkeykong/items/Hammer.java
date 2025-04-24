package de.tu_darmstadt.informatik.fop.donkeykong.items;

import java.util.Optional;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.player.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.map.MapLoader;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Hammer extends AbstractItem {

    public Hammer(Coordinate position) throws SlickException {
        super("Hammer", position, Consts.IMAGE_HAMMER);
    }

    public Hammer() throws SlickException {
        this(new Coordinate(0, 0));
    }

    @Override
    public int getPoints() {
        return Consts.POINTS_HAMMER;
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.HAMMER;
    }


    @Override
    public void collisionPreperation(Event itemCollision) {
        itemCollision.addAction(new HammerCollision(itemCollision,this));
    }

    private class HammerCollision extends DestroyEntityAction {

        AbstractItem collider;
        Event event;

        public HammerCollision(Event event, Entity collider) {
            this.collider = (AbstractItem) collider;
            this.event = event;
        }

        @Override
        public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
            Optional<Entity> entity = StateBasedEntityManager.getInstance().getEntitiesByState(sb.getCurrentStateID())
                    .stream().filter(e -> e instanceof Player).findFirst();

            Player collidee = null;
            if(entity.isPresent()) {
                collidee = (Player) entity.get();
            }

            MapLoader.getInstance().getCurrentMap().removeEntity(event.getOwnerEntity());

            if(collidee != null) {
                collidee.setHammerTime(Consts.HAMMER_TIME);
                collidee.setHammer(true);
                super.update(gc,sb,delta,event);
            }
        }

    }
}

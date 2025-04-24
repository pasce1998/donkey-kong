package de.tu_darmstadt.informatik.fop.donkeykong.items;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.player.Player;
import de.tu_darmstadt.informatik.fop.donkeykong.util.interfaces.IScorable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.map.IPlacable;
import de.tu_darmstadt.informatik.fop.donkeykong.map.MapLoader;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;
import de.tu_darmstadt.informatik.fop.donkeykong.util.GameplayManager;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public abstract class AbstractItem extends Entity implements IScorable, IPlacable {

    public AbstractItem(String entityID, Coordinate position, String image) throws SlickException {
        super(entityID);
        this.setPosition(position);
        ItemCollisionEvent itemCollision = new ItemCollisionEvent();
        collisionPreperation(itemCollision);
        this.addComponent(itemCollision);
        if(!DonkeyKong.isTesting) this.addComponent(new ImageRenderComponent(Utilities.loadImageScaled(image)));
    }

    public void collisionPreperation(Event itemCollision) {
        itemCollision.addAction(new AbstractItem.ItemCollisionAction());
    }


    private class ItemCollisionEvent extends Event {

        public ItemCollisionEvent() {
            super("ItemCollisionEvent");
        }

        @Override
        protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
            return StateBasedEntityManager.getInstance().getEntitiesByState(sb.getCurrentStateID())
                    .stream().anyMatch(c -> c instanceof Player && c.collides(this.getOwnerEntity()));
        }
    }

    private class ItemCollisionAction implements Action {

        @Override
        public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
            GameplayManager.getInstance().scorePoints((IScorable) event.getOwnerEntity());
            MapLoader.getInstance().getCurrentMap().removeEntity(event.getOwnerEntity());
        }

    }

}

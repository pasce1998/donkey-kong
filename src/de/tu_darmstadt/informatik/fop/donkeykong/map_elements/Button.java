package de.tu_darmstadt.informatik.fop.donkeykong.map_elements;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.player.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.map.MapLoader;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.GameplayManager;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.event.Event;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Button extends MapElement {

    public Button(String id) throws SlickException {
        super(id);
        if(!DonkeyKong.isTesting) this.addComponent(new ImageRenderComponent(Utilities.loadImageScaled(Consts.IMAGE_BUTTON)));
        this.setPassable(false);

        ActivateButtonEvent abe = new ActivateButtonEvent();
        abe.addAction(new ActivateButtonAction());

        this.addComponent(abe);
    }

    public Button() throws SlickException {
        this("Border");
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.BUTTON_ON;
    }


    private class ActivateButtonAction implements Action {

        @Override
        public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
            event.getOwnerEntity().setVisible(false);
            GameplayManager.getInstance().useButton();
        }
    }

    private class ActivateButtonEvent extends Event {

        public ActivateButtonEvent() {
            super("ActivateButtonEvent");
        }

        @Override
        protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
            this.getOwnerEntity().getPosition().y -= this.getOwnerEntity().getSize().y;

            boolean out = this.getOwnerEntity().isVisible() &&
                    MapLoader.getInstance().getCurrentMap().getEntities()
                    .stream().anyMatch(p -> p instanceof Player && p.collides(this.getOwnerEntity()));

            this.getOwnerEntity().getPosition().y += this.getOwnerEntity().getSize().y;

            return out;
        }
    }
}

package de.tu_darmstadt.informatik.fop.donkeykong.characters.npc;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.player.Player;
import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;
import de.tu_darmstadt.informatik.fop.donkeykong.util.GameplayManager;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.event.Event;


/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Pauline extends AbstractCharacter {


    public Pauline( Coordinate position) {
        super("Pauline", position, 0.1f, 0, Consts.FIREBALL_FRAMEPAUSE);

        PaulineEvent pe = new PaulineEvent();
        PaulineAction pa = new PaulineAction();

        pe.addAction(pa);
        this.addComponent(pe);
    }

    public Pauline() {
        this(new Coordinate(0, 0));
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.PAULINE;
    }

    private class PaulineEvent extends Event {
        public PaulineEvent() {
            super("PaulineEvent");

        }

        @Override
        protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
            List<Entity> colliders = Utilities.collides(sb.getCurrentStateID(), this.getOwnerEntity());

            return colliders.stream().anyMatch(c -> c instanceof Player);
        }
    }

    @Override
    public void loadAnimations() {
        super.loadAnimations();

        this.putAnimation(AnimationKey.STAND, new String[] {Consts.IMAGE_PAULINE_STANDING});
        this.putAnimation(AnimationKey.MOVE, new String[] {Consts.IMAGE_PAULINE_1, Consts.IMAGE_PAULINE_2});
    }

    private class PaulineAction implements Action {

        @Override
        public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i, Component component) {
            GameplayManager.getInstance().nextLevel();
        }
    }


}

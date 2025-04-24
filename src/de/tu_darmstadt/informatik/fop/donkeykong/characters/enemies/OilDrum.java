package de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;


/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class OilDrum extends AbstractEnemy {

    public OilDrum(String entityID, Coordinate position) {
        super(entityID, position, Consts.ENEMY_SPEED, 0, 8, 1);
        this.setLethal(false);
    }

    public OilDrum() {
        this("OilDrum", new Coordinate(0, 0));
    }

    @Override
    public void loadAnimations() {
        super.loadAnimations();
        this.putAnimation(AnimationKey.STAND, new String[]{Consts.IMAGE_OILBARREL_UNLIT});
        this.putAnimation(AnimationKey.BURNING, new String[] {Consts.IMAGE_OILBARREL_LIT_1,Consts.IMAGE_OILBARREL_LIT_2});

        LitEvent le = new LitEvent();
        LitAction la = new LitAction();
        le.addAction(la);

        this.addComponent(le);
    }

    @Override
    public void pathfinder() {
        if(!isNewAction()) return;

    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.OIL_BARREL_UNLIT;
    }

    @Override
    public int getPoints() {
        return 0;
    }


    private class LitEvent extends Event {

        public LitEvent() {
            super("LitEvent");
        }

        @Override
        protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
            OilDrum od = (OilDrum) this.getOwnerEntity();

//            return Utilities.collides(sb.getCurrentStateID(), od).stream()
//                    .anyMatch(c -> c instanceof Barrel);

            return StateBasedEntityManager.getInstance().getEntitiesByState(sb.getCurrentStateID()).stream()
                    .anyMatch(c -> c instanceof Barrel && od.collides(c));
        }
    }

    private class LitAction implements Action {

        @Override
        public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
            OilDrum od = (OilDrum) event.getOwnerEntity();

            if(!od.isLethal()) {
                od.setLethal(true);
                od.setState(AnimationKey.BURNING);
            }

            Fireball fireball = new Fireball();
            Vector2f position = new Vector2f(od.getPosition());
            position.y -= (od.getSize().y + fireball.getSize().y)/2;
            StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(), fireball);
        }
    }
}

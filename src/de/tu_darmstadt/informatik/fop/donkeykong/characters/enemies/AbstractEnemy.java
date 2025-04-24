package de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies;

import de.tu_darmstadt.informatik.fop.donkeykong.map.MapLoader;
import de.tu_darmstadt.informatik.fop.donkeykong.util.interfaces.IScorable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.actions.EnemyMovementAction;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.player.Player;
import de.tu_darmstadt.informatik.fop.donkeykong.events.EnemyMovementEvent;
import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;
import de.tu_darmstadt.informatik.fop.donkeykong.util.GameplayManager;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.event.Event;


/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public abstract class AbstractEnemy extends AbstractCharacter implements IScorable {

    private boolean lethal;
    private AnimationKey state = AnimationKey.STAND;

    private int maxCoodDown;
    private int currentCoolDown = 0;
    private boolean newAction = true;

    public AbstractEnemy(String entityID, Coordinate position, float speed, int DEFAULT_JUMP_FRAMES,
                         int maxFrames, int maxCoolDown) {
        super(entityID, position, 0.1f, 0, maxFrames);
        this.setPassable(true);
        this.lethal = true;

        EnemyHitEvent ehe = new EnemyHitEvent();
        ehe.addAction(new EnemyHitAction());
        EnemyMovementEvent eme = new EnemyMovementEvent(Consts.ENEMY_SPEED);
        eme.addAction(new EnemyMovementAction(Consts.ENEMY_SPEED));

        this.maxCoodDown = maxCoolDown;

        this.addComponent(eme);
        this.addComponent(ehe);
    }

    public abstract void pathfinder();

    public boolean isLethal() {
        return this.lethal;
    }

    public void setLethal(boolean lethal) {
        this.lethal = lethal;
    }

    public boolean isNewAction() {
        return this.newAction;
    }

    public void actionUsed() {
        this.newAction = false;
    }

    public void increaseCurrentCoolDown(int delta) {
        this.currentCoolDown += delta;
        if(this.currentCoolDown >= this.maxCoodDown) {
            this.newAction = true;
            this.currentCoolDown %= this.currentCoolDown;
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        super.update(gc, sb, delta);
        this.pathfinder();
        this.actionUsed();
        this.increaseCurrentCoolDown(delta);
    }

    @Override
    public void changeFramestate() {
        if(this.isNewAction())
            super.changeFramestate();
    }

    public void setNewAction(boolean bool) {
        this.newAction = bool;
    }

    private class EnemyHitEvent extends Event {

        public EnemyHitEvent() {
            super("EnemyHitEvent");
        }

        @Override
        protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
            AbstractEnemy enemy = (AbstractEnemy) this.getOwnerEntity();
            boolean collides = Utilities.collides(sb.getCurrentStateID(), enemy).stream()
                    .anyMatch(c -> c instanceof Player);
            
            boolean isLethal = enemy.isLethal();

            return isLethal && collides;
        }
    }

    private class EnemyHitAction implements Action {

        @Override
        public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
            AbstractEnemy enemy = (AbstractEnemy) event.getOwnerEntity();
            Player collides = (Player) Utilities.collides(sb.getCurrentStateID(), enemy).stream()
                    .filter(c -> c instanceof Player).findFirst().get();

            if(!collides.hasHammer())
                GameplayManager.getInstance().decreaseLife();
            else if(!(event.getOwnerEntity() instanceof DonkeyKong)) {
                GameplayManager.getInstance().scorePoints((IScorable) event.getOwnerEntity());
                MapLoader.getInstance().getCurrentMap().removeEntity(event.getOwnerEntity());
            }
        }
    }

}

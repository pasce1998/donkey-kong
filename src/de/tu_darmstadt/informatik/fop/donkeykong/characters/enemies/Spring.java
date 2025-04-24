package de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies;

import de.tu_darmstadt.informatik.fop.donkeykong.actions.spring_actions.SpringJumpAction;
import de.tu_darmstadt.informatik.fop.donkeykong.actions.spring_actions.SpringRefreshAction;
import de.tu_darmstadt.informatik.fop.donkeykong.events.spring_events.CanStillMoveEvent;
import de.tu_darmstadt.informatik.fop.donkeykong.events.spring_events.SpringGroundCheck;
import de.tu_darmstadt.informatik.fop.donkeykong.events.spring_events.SpringJumpEvent;
import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;
import eea.engine.action.basicactions.MoveRightAction;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Spring extends AbstractEnemy {

    private int jumpFrames;
    private int maxJumpFrames;

    public Spring(String entityID, Coordinate position) {
        super(entityID, position, Consts.ENEMY_SPEED, 400, 0, 1);

        this.maxJumpFrames = 400;
        SpringJumpEvent sje = new SpringJumpEvent();
        sje.addAction(new SpringJumpAction());

        SpringGroundCheck sgc = new SpringGroundCheck();
        sgc.addAction(new SpringRefreshAction());

        CanStillMoveEvent csme = new CanStillMoveEvent();
        csme.addAction(new MoveRightAction(Consts.MARIO_SPEED * 2));

        this.addComponent(sje);
        this.addComponent(sgc);
        this.addComponent(csme);
    }

    public Spring() {
        this("Spring", new Coordinate(0, 0));
    }


    @Override
    public void loadAnimations() {
        super.loadAnimations();
        this.putAnimation(AnimationKey.STAND, new String[] {Consts.IMAGE_SPRING_CONTRACT});
        this.putAnimation(AnimationKey.FALL, new String[] {Consts.IMAGE_SPRING_EXPAND});
        this.putAnimation(AnimationKey.MOVE, new String[] {Consts.IMAGE_SPRING_CONTRACT, Consts.IMAGE_SPRING_EXPAND});
        this.putAnimation(AnimationKey.JUMP, new String[] {Consts.IMAGE_SPRING_EXPAND});
    }

    @Override
    public void pathfinder() {

    }

    public int getMaxJumpFrames() {
        return this.maxJumpFrames;
    }

    public int getJumpFrames() {
        return this.jumpFrames;
    }

    public void setToMaxJumpFrames() {
        this.jumpFrames = this.maxJumpFrames;
    }

    public void setJumpFrames(int i) {
        this.jumpFrames = i;
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.SPRING;
    }


    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        super.update(gc, sb, delta);
        this.setJumpFrames(this.getJumpFrames() - delta);
    }

    @Override
    public int getPoints() {
        return 0;
    }
}

package de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies;

import java.util.Random;

import de.tu_darmstadt.informatik.fop.donkeykong.actions.SetEnemyAnimationAction;
import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Direction;
import eea.engine.event.basicevents.LoopEvent;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Barrel extends AbstractEnemy {

    private Direction beforeLadder = Direction.RIGHT;
    private boolean active;
    private boolean isFalling = false;

    public Barrel(String entityID, Coordinate position, boolean active) {
        super(entityID, position, Consts.ENEMY_SPEED, 0, 50, 1);

        LoopEvent moveEvent = new LoopEvent();
        moveEvent.addAction(new SetEnemyAnimationAction());
        this.active = active;

        if(!active) {
            this.setState(AnimationKey.STAND);
        }
        else {
            this.setState(AnimationKey.MOVE);
        }
    }

    public Barrel() { this("Barrel", new Coordinate(0, 0), true); }

    public Barrel(boolean active) {
        this("Barrel", new Coordinate(0, 0), active);
    }

    @Override
    public void loadAnimations() {
        super.loadAnimations();
        this.putAnimation(AnimationKey.STAND, new String[] { Consts.IMAGE_BARREL_SIDE });
        this.putAnimation(AnimationKey.MOVE, new String[] { Consts.IMAGE_BARREL_FRONT_1,
                Consts.IMAGE_BARREL_FRONT_2, Consts.IMAGE_BARREL_FRONT_3, Consts.IMAGE_BARREL_FRONT_4 });
        this.putAnimation(AnimationKey.FALL, new String[] { Consts.IMAGE_BARREL_FRONT_1,
                Consts.IMAGE_BARREL_FRONT_2, Consts.IMAGE_BARREL_FRONT_3, Consts.IMAGE_BARREL_FRONT_4 });
    }

    @Override
    public void pathfinder() {
        this.setNewAction(true);

        AnimationKey[] possibilities;
        Direction[] directions;

        if(!this.active) return;

        else if (this.getState() == AnimationKey.MOVE) {
            possibilities = new AnimationKey[] { AnimationKey.MOVE};
            directions = new Direction[] {this.getDirection()};
        }

        else if (this.isFalling && this.getJumpMode() != 2) {
            possibilities = new AnimationKey[] {AnimationKey.MOVE};
            directions = new Direction[] { Direction.LEFT, Direction.RIGHT };
        }

        else {
            if (this.beforeLadder == Direction.LEFT) {
                possibilities = new AnimationKey[]{AnimationKey.MOVE};
                directions = new Direction[]{Direction.RIGHT};
            }
            else {
                possibilities = new AnimationKey[]{AnimationKey.MOVE};
                directions = new Direction[]{Direction.LEFT};
            }

            this.isFalling = this.getJumpMode() == 2;
        }

        Random rand = new Random();
        int dirInt = rand.nextInt(directions.length);

        AnimationKey newAction = possibilities[rand.nextInt(possibilities.length)];
        Direction newDirection = directions[dirInt];

        this.setState(newAction);
        this.setDirection(newDirection);
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.BARREL;
    }

    @Override
    public int getPoints() {
        return Consts.POINTS_BARREL;
    }
}

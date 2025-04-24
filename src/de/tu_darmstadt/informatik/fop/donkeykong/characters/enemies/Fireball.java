package de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Direction;
import de.tu_darmstadt.informatik.fop.donkeykong.util.interfaces.IScorable;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Fireball extends AbstractEnemy implements IScorable {

    public Fireball(Coordinate position) {
        super("Fireball", position, Consts.ENEMY_SPEED, 0, 8, Consts.FIREBALL_FRAMEPAUSE);
    }


   public Fireball() {
        this(new Coordinate(0, 0));
    }

    @Override
    public void loadAnimations() {
        super.loadAnimations();
        this.putAnimation(AnimationKey.STAND, new String[] { Consts.IMAGE_FIREBALL_1, Consts.IMAGE_FIREBALL_2 });
        this.putAnimation(AnimationKey.MOVE, new String[] { Consts.IMAGE_FIREBALL_1, Consts.IMAGE_FIREBALL_2 });
        this.putAnimation(AnimationKey.CLIMB_UP, new String[] { Consts.IMAGE_FIREBALL_1, Consts.IMAGE_FIREBALL_2 });

    }

    @Override
    public void pathfinder() {
        if(!isNewAction()) return;
        if(DonkeyKong.isTesting) return;

        Direction[] directions = new Direction[] {Direction.RIGHT, Direction.LEFT};
        AnimationKey[] possibilities = new AnimationKey[] {AnimationKey.MOVE, AnimationKey.MOVE, AnimationKey.CLIMB_UP};

        Random rand = new Random();
        AnimationKey newAction = possibilities[rand.nextInt(possibilities.length)];
        Direction direction = directions[rand.nextInt(directions.length)];

        this.setState(newAction);
        this.setDirection(direction);
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.FIREBALL;
    }

    @Override
    public int getPoints() {
        return Consts.POINTS_FIREBALL;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        super.update(gc, sb, delta);
    }
}

package de.tu_darmstadt.informatik.fop.donkeykong.characters.enemies;

import java.util.Arrays;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.map.MapLoader;
import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.Ladder;
import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;
import eea.engine.entity.StateBasedEntityManager;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class DonkeyKong extends AbstractEnemy {

    private boolean spawnEnemy = false;
    private boolean alreadySpawned = true;

    public DonkeyKong(String entityID, Coordinate position) {
        super(entityID, position, Consts.ENEMY_SPEED, 0, 200, Consts.FIREBALL_FRAMEPAUSE);
    }

    public DonkeyKong() {
        this("DonkeyKong", new Coordinate(0, 0));
    }

    @Override
    public void loadAnimations() {
        super.loadAnimations();
        this.putAnimation(AnimationKey.STAND, new String[] { Consts.IMAGE_DK_STANDING });
        this.putAnimation(AnimationKey.THROW, new String[] { Consts.IMAGE_DK_THROWING_1, Consts.IMAGE_DK_STANDING, Consts.IMAGE_DK_THROWING_2, Consts.IMAGE_DK_STANDING});
        this.putAnimation(AnimationKey.FALL, new String[] { Consts.IMAGE_DK_FALLING_1, Consts.IMAGE_DK_FALLING_2 });
        this.putAnimation(AnimationKey.BOXING, new String[] { Consts.IMAGE_DK_BOXING_1, Consts.IMAGE_DK_BOXING_2, Consts.IMAGE_DK_BOXING_1 });
    }

    @Override
    public void pathfinder() {
        if(!isNewAction()) return;
        String map = MapLoader.getInstance().getCurrentMap().getName();
        AnimationKey[] possibilities;
        if(map.equals("01") || map.equals("03")) {
            possibilities = new AnimationKey[] {AnimationKey.THROW};
        }
        else {
            possibilities = new AnimationKey[] {AnimationKey.BOXING};
        }
        AnimationKey newAction = possibilities[0];

        if(this.getState() != newAction) {
            this.setFramestate(0);
        }
        this.setState(newAction);
    }

    @Override
    public void changeFramestate() {
        super.changeFramestate();
        if(this.getFramestate() == 0)
            this.alreadySpawned = false;

        if(this.getFramestate() == this.getAnimationLength()-1)
            this.spawnEnemy = true;
    }

    public void spawnEnemy(StateBasedGame sb) {
        String map = MapLoader.getInstance().getCurrentMap().getName();
        AbstractEnemy enemy = null;
        if(map.equals("01")) {
            enemy = new Barrel();
        }
        else if(map.equals("02")) {
            enemy = new Fireball();
        }
        else if(map.equals("03")) {
            enemy = new Spring();
        }
        if(enemy != null) {
            MapLoader.getInstance().getCurrentMap().addEntity(enemy, 0, 0);
            StateBasedEntityManager.getInstance().addEntity(Consts.STATE_GAMEPLAY, enemy);

            Vector2f position;
            if(map.equals("02")) {
                Random random = new Random();
                Object[] ladders = Arrays.stream(MapLoader.getInstance().getCurrentMap().getMapElements())
                        .flatMap(Arrays::stream).filter(m -> m instanceof Ladder).toArray();
                position = ((Ladder) ladders[random.nextInt(ladders.length)]).getPosition();
            }
            else {
                position = new Vector2f(this.getPosition());
                enemy.getPosition().x += (this.getSize().x + enemy.getSize().x) / 2;
                enemy.getPosition().y += (this.getSize().y - enemy.getSize().y) / 2;
            }
            enemy.setPosition(position);
        }
        this.spawnEnemy = false;
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.DONKEY_KONG;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        super.update(gc, sb, delta);

        if(this.spawnEnemy && !this.alreadySpawned) {
            this.spawnEnemy(sb);
            this.alreadySpawned = true;
        }
    }

    @Override
    public int getPoints() {
        return 0;
    }
}

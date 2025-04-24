package de.tu_darmstadt.informatik.fop.donkeykong.characters;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.player.Player;
import de.tu_darmstadt.informatik.fop.donkeykong.events.CollisionPlayerEvent;
import de.tu_darmstadt.informatik.fop.donkeykong.util.observer.SpeedObservable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.actions.BorderCollisionAction;
import de.tu_darmstadt.informatik.fop.donkeykong.actions.GravityAction;
import de.tu_darmstadt.informatik.fop.donkeykong.events.GravityEvent;
import de.tu_darmstadt.informatik.fop.donkeykong.map.IPlacable;
import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Direction;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;

/**
 * Abstract class for enemy- and player-entities
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public abstract class AbstractCharacter extends Entity implements IPlacable {
    /**
     * movement speed of the entity
     */
    private float speed;

    private AnimationKey state = AnimationKey.STAND;
    private boolean newState = true;

    private int jumpFrames = 0;
    public final int DEFAULT_JUMP_FRAMES;

    // 0: stand
    // 1: jump
    // 2: fall
    private int jumpMode = 0;

    private int maxAnimationPauseFrames;
    private int currentAnimationPauseFrame = 0;

    private Observable gravityObservable;

    private int framestate = 0;
    private Direction direction = Direction.RIGHT;

    private Map<AnimationKey, String[]> animations = new HashMap<>();

    /**
     * drop-speed of the entity
     */
    private float dropSpeed = Consts.DROP_SPEED;

    /**
     * Constructor for character-entity
     * @param entityID, character's id
     * @param position, character's position
     * @param speed, character's movement speed
     */
    public AbstractCharacter(String entityID, Coordinate position, float speed, int DEFAULT_JUMP_FRAMES, int maxFrames) {

        super(entityID);
        this.setPosition(position);
        this.speed = speed;
        this.DEFAULT_JUMP_FRAMES = DEFAULT_JUMP_FRAMES;
        this.maxAnimationPauseFrames = maxFrames <= 0 ? 1 : maxFrames;

        // put debug sprites
        this.loadAnimations();

        // loading player sprite
        try {
            if(!DonkeyKong.isTesting) this.addComponent(new ImageRenderComponent(Utilities.loadImageScaled(this.animations.get(AnimationKey.STAND)[0])));
        } catch (SlickException e) {
            e.printStackTrace();
        }

        GravityAction ga = new GravityAction(Consts.DROP_SPEED);
        this.gravityObservable = new SpeedObservable();
        gravityObservable.addObserver(ga);

        GravityEvent ge = new GravityEvent(Consts.DROP_SPEED, (SpeedObservable) this.gravityObservable);
        CollisionPlayerEvent cp = new CollisionPlayerEvent();
        cp.addAction(new BorderCollisionAction());

        // adding basic gravity to loop
        ge.addAction((GravityAction) ga);
        this.addComponent(cp);
        this.addComponent(ge);

        this.setPassable(true);
    }

    public float getSpeed() {
        return this.speed;
    }

    public int getJumpFrames() {
        return this.jumpFrames;
    }

    public void setJumpFrames(int frames) {
        this.jumpFrames = frames;
    }

    public int getAnimationLength() {
        return this.animations.get(this.state).length;
    }

    public void changeFramestate() {
        if(!this.newState) return;

        // alte größe
        float oldSize = this.getSize().y;

        Image image = null;
        AnimationKey state = this.state;
        try {
            if(!DonkeyKong.isTesting) {
                if(this instanceof Player && ((Player) this).hasHammer()) {
                    state = AnimationKey.HAMMER;
                    this.framestate = this.framestate % this.animations.get(state).length;
                }
                image = Utilities.loadImageScaled(this.animations.get(state)[this.framestate]);
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
        if (!DonkeyKong.isTesting && this.direction == Direction.LEFT)
            image = image.getFlippedCopy(true, false);
        if(!DonkeyKong.isTesting) this.addComponent(new ImageRenderComponent(image));

        //neue position nach neuer größe
        this.getPosition().y += (Math.floor( oldSize - this.getSize().y )/2);

        this.framestate = (this.framestate + 1) % this.animations.get(state).length;
        this.newState = false;
    }

    public int getFramestate() {
        return this.framestate;
    }

    public void putAnimation(AnimationKey key, String[] asset) {
        this.animations.put(key, asset);
    }


    public void loadAnimations() {
        this.putAnimation(AnimationKey.STAND, new String[] {Consts.IMAGE_DEBUG});
        this.putAnimation(AnimationKey.MOVE, new String[] {Consts.IMAGE_DEBUG});
        this.putAnimation(AnimationKey.JUMP, new String[] {Consts.IMAGE_DEBUG});
        this.putAnimation(AnimationKey.CLIMB_DOWN, new String[] {Consts.IMAGE_DEBUG});
        this.putAnimation(AnimationKey.CLIMB_UP, new String[] {Consts.IMAGE_DEBUG});
        this.putAnimation(AnimationKey.THROW, new String[] {Consts.IMAGE_DEBUG});
        this.putAnimation(AnimationKey.FALL, new String[] {Consts.IMAGE_DEBUG});
        this.putAnimation(AnimationKey.BOXING, new String[] {Consts.IMAGE_DEBUG});
        this.putAnimation(AnimationKey.BURNING, new String[] {Consts.IMAGE_DEBUG});
        this.putAnimation(AnimationKey.HAMMER, new String[] {Consts.IMAGE_DEBUG});
    }

    public void deleteObserver() {
        if(Consts.DEBUG) System.out.println("Removing observers of " + this.getIdentifier());
        this.gravityObservable.deleteObservers();
        this.gravityObservable = null;
    }

    public void setStand() {
        this.jumpMode = 0;
    }

    public void setJump() {
        this.jumpMode = 1;
    }

    public void setFall() {
        this.jumpMode = 2;
    }

    public int getJumpMode() {
        return this.jumpMode;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public AnimationKey getState() {
        return state;
    }

    public void setState(AnimationKey state) {
        if(this.state != state) framestate = 0;

        this.state = state;
    }

    public void setFramestate(int state) {
        this.framestate = state;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        super.update(gc, sb, delta);

        this.currentAnimationPauseFrame += delta;
        if(this.currentAnimationPauseFrame >= this.maxAnimationPauseFrames) {
            this.newState = true;
            this.currentAnimationPauseFrame %= this.maxAnimationPauseFrames;
        }
        this.changeFramestate();
    }
}

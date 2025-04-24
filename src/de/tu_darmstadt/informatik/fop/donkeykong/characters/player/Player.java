package de.tu_darmstadt.informatik.fop.donkeykong.characters.player;

import java.util.Observable;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import de.tu_darmstadt.informatik.fop.donkeykong.actions.ClimbAction;
import de.tu_darmstadt.informatik.fop.donkeykong.actions.HammerTimerAction;
import de.tu_darmstadt.informatik.fop.donkeykong.actions.JumpAction;
import de.tu_darmstadt.informatik.fop.donkeykong.actions.MoveLeftRightAction;
import de.tu_darmstadt.informatik.fop.donkeykong.actions.SetAnimationAction;
import de.tu_darmstadt.informatik.fop.donkeykong.actions.SetJumpModeAction;
import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import de.tu_darmstadt.informatik.fop.donkeykong.events.CheckLadderEvent;
import de.tu_darmstadt.informatik.fop.donkeykong.events.HammerCheckEvent;
import de.tu_darmstadt.informatik.fop.donkeykong.events.IsOnLadderEvent;
import de.tu_darmstadt.informatik.fop.donkeykong.events.JumpEvent;
import de.tu_darmstadt.informatik.fop.donkeykong.events.MovementAcceptsStairways;
import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Coordinate;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Direction;
import de.tu_darmstadt.informatik.fop.donkeykong.util.observer.StairObservable;
import eea.engine.event.ANDEvent;
import eea.engine.event.NOTEvent;
import eea.engine.event.OREvent;
import eea.engine.event.basicevents.KeyDownEvent;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Abstract class for player-entity
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Player extends AbstractCharacter {

    private int hammerTime = 0;
    private Observable stairObservableLeft;
    private Observable stairObservableRight;
    private boolean hasHammer = false;

    /**
     * Player's constructor
     * 
     * @param position, player's position
     */
    public Player(Coordinate position) {
        super("Mario", position, Consts.MARIO_SPEED, Consts.MARIO_JUMPFRAMES, 200);
        IsOnLadderEvent iole = new IsOnLadderEvent();

        // SET NUMBER OF JUMPFRAMES
        SetJumpModeAction sjma = new SetJumpModeAction();
        ANDEvent kde = new ANDEvent(new KeyDownEvent(Input.KEY_SPACE), new NOTEvent(iole));
        kde.addAction(sjma);

        // JUMP ACTION
        ANDEvent je = new ANDEvent(new JumpEvent(), new NOTEvent(iole));
        JumpAction ja = new JumpAction();
        je.addAction(ja);

        // MOVE LEFT
        this.stairObservableLeft = new StairObservable();
        MoveLeftRightAction mla = new MoveLeftRightAction(-this.getSpeed());
        MovementAcceptsStairways masl = new MovementAcceptsStairways(this.getSpeed(), mla, (StairObservable) this.stairObservableLeft);
        this.stairObservableLeft.addObserver(mla);
        ANDEvent moveLeftPressed = new ANDEvent(
                new OREvent(new KeyDownEvent(Input.KEY_LEFT), new KeyDownEvent(Input.KEY_A)),
                masl, new NOTEvent(iole));
        moveLeftPressed.addAction(mla);

        // MOVE RIGHT
        this.stairObservableRight = new StairObservable();
        MoveLeftRightAction mra = new MoveLeftRightAction(this.getSpeed());
        MovementAcceptsStairways masr = new MovementAcceptsStairways(this.getSpeed(), mra, (StairObservable) this.stairObservableRight);
        this.stairObservableRight.addObserver(mra);
        ANDEvent moveRightPressed = new ANDEvent(
                new OREvent(new KeyDownEvent(Input.KEY_RIGHT), new KeyDownEvent(Input.KEY_D)),
                masr, new NOTEvent(iole));
        moveRightPressed.addAction(mra);

        //LADDER UP
        ANDEvent lue = new ANDEvent(
                new OREvent(new KeyDownEvent(Input.KEY_W), new KeyDownEvent(Input.KEY_UP)),
                new OREvent(new CheckLadderEvent(), iole));
        ClimbAction cau = new ClimbAction(AnimationKey.CLIMB_UP);
        lue.addAction(cau);

        //LADDER DOWN
        ANDEvent lde = new ANDEvent(
                new OREvent(new KeyDownEvent(Input.KEY_S), new KeyDownEvent(Input.KEY_DOWN)),
                new OREvent(new CheckLadderEvent(), iole));
        ClimbAction cad = new ClimbAction(AnimationKey.CLIMB_DOWN);
        lde.addAction(cad);

        // SET CORRECT ANIMATION
        ANDEvent leftAnimation = new ANDEvent(moveLeftPressed, new NOTEvent(moveRightPressed), new NOTEvent(new IsOnLadderEvent()));
        leftAnimation.addAction(new SetAnimationAction(Direction.LEFT));
        ANDEvent rightAnimation = new ANDEvent(moveRightPressed, new NOTEvent(moveLeftPressed), new NOTEvent(new IsOnLadderEvent()));
        rightAnimation.addAction(new SetAnimationAction(Direction.RIGHT));
        OREvent standAnimation = new OREvent(new ANDEvent(moveLeftPressed, moveRightPressed, new NOTEvent(new IsOnLadderEvent())),
                new ANDEvent(new NOTEvent(moveLeftPressed), new NOTEvent(moveRightPressed), new NOTEvent(new IsOnLadderEvent())));
        standAnimation.addAction(new SetAnimationAction());


        // SET HAMMERMODE
        HammerCheckEvent hce = new HammerCheckEvent();
        HammerTimerAction hta = new HammerTimerAction();
        hce.addAction(hta);
//
        this.addComponent(kde);
        this.addComponent(je);
        this.addComponent(moveLeftPressed);
        this.addComponent(moveRightPressed);
        this.addComponent(leftAnimation);
        this.addComponent(rightAnimation);
        this.addComponent(standAnimation);
        this.addComponent(lue);
        this.addComponent(lde);
        this.addComponent(hce);

    }

    public Player() throws SlickException {
        this(new Coordinate(0, 0));
    }

    @Override
    public void loadAnimations() {
        super.loadAnimations();
        this.putAnimation(AnimationKey.STAND, new String[] { Consts.IMAGE_MARIO_STANDING });
        this.putAnimation(AnimationKey.MOVE,
                new String[] { Consts.IMAGE_MARIO_WALKING_1, Consts.IMAGE_MARIO_WALKING_2 });
        this.putAnimation(AnimationKey.HAMMER, new String[] { Consts.IMAGE_MARIO_HAMMER_3,
                Consts.IMAGE_MARIO_HAMMER_4});
        this.putAnimation(AnimationKey.CLIMB_DOWN, new String[] {Consts.IMAGE_MARIO_CLIMBING_1,
                Consts.IMAGE_MARIO_CLIMBING_2});
        this.putAnimation(AnimationKey.CLIMB_UP, new String[] {Consts.IMAGE_MARIO_CLIMBING_1,
                Consts.IMAGE_MARIO_CLIMBING_2});
        this.putAnimation(AnimationKey.JUMP, new String[] {Consts.IMAGE_MARIO_JUMPING});
        this.putAnimation(AnimationKey.DEATH, new String[] {Consts.IMAGE_MARIO_DEATH_1, Consts.IMAGE_MARIO_DEATH_2, Consts.IMAGE_MARIO_DEATH_3, Consts.IMAGE_MARIO_DEATH_4});
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.MARIO;
    }

    public void setHammerTime(int time) {
        this.hammerTime = time;
    }

    public void decreaseHammerTime(int delta) {
        this.hammerTime -= delta;
    }

    public int getHammerTime() {
        return this.hammerTime;
    }

    @Override
    public void deleteObserver() {
        super.deleteObserver();
        if(Consts.DEBUG) System.out.println("Removing observers of " + this.getIdentifier());
        this.stairObservableLeft.deleteObservers();
        this.stairObservableLeft = null;
        this.stairObservableRight.deleteObservers();
        this.stairObservableRight = null;
    }

    public boolean hasHammer() {
        return this.hasHammer;
    }

    public void setHammer(boolean hasHammer) {
        this.hasHammer = hasHammer;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        super.update(gc, sb, delta);
    }
}

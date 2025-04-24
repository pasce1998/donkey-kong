package de.tu_darmstadt.informatik.fop.donkeykong.map_elements;

import de.tu_darmstadt.informatik.fop.donkeykong.DonkeyKong;
import de.tu_darmstadt.informatik.fop.donkeykong.actions.EscalationAction;
import de.tu_darmstadt.informatik.fop.donkeykong.map.Identifier;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Utilities;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.LoopEvent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class ElevatorPlatform extends MapElement{

    private boolean goingUp = true;
    private ElevatorBaseBot bottom;
    private ElevatorBaseTop top;

    public ElevatorPlatform(String id) throws SlickException {
        super(id);
        this.setPassable(false);
        if(!DonkeyKong.isTesting) this.addComponent(new ImageRenderComponent(Utilities.loadImageScaled(Consts.IMAGE_ELEVATOR_PLATFORM)));

        LoopEvent escalation = new LoopEvent();
        EscalationAction ea = new EscalationAction((this.goingUp ? -1 : 1) * Consts.ELEVATOR_SPEED);
        escalation.addAction(ea);
        this.addComponent(escalation);
    }

    public ElevatorPlatform() throws SlickException {
        this("ElevatorPlatform");
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.ELEVATOR_PLATFORM;
    }

    public void setUp() {
        this.goingUp = true;
    }

    public void setDown() {
        this.goingUp = false;
    }

    public ElevatorBaseTop getTop() {
        return this.top;
    }

    public ElevatorBaseBot getBottom() {
        return this.bottom;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        super.update(gc, sb, delta);

        if(this.getBottom() == null || this.getTop() == null) {
            this.top = (ElevatorBaseTop) StateBasedEntityManager.getInstance().getEntitiesByState(sb.getCurrentStateID()).stream()
                    .filter(e -> e instanceof ElevatorBaseTop && e.getPosition().x == this.getPosition().x)
                    .findFirst().get();
            this.bottom = (ElevatorBaseBot) StateBasedEntityManager.getInstance().getEntitiesByState(sb.getCurrentStateID()).stream()
                    .filter(e -> e instanceof ElevatorBaseBot && e.getPosition().x == this.getPosition().x)
                    .findFirst().get();
        }
    }
}

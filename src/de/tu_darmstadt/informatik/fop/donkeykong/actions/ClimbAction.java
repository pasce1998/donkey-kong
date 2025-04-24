package de.tu_darmstadt.informatik.fop.donkeykong.actions;


import java.util.Optional;
import java.util.stream.Stream;

import de.tu_darmstadt.informatik.fop.donkeykong.characters.AbstractCharacter;
import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.MapElement;
import de.tu_darmstadt.informatik.fop.donkeykong.map_elements.MetalBeam;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.informatik.fop.donkeykong.util.AnimationKey;
import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

/**
 * Aktion, die für das Klettern an Leitern verantwortlich ist
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class ClimbAction implements Action {

    /**
     * Hier wird gespeichert, ob Mario hoch- oder runterklettert
     */
    private final AnimationKey AK;

    /**
     * Konstruktor der Aktion
     * @param ak Kletterrichtung von Mario
     */
    public ClimbAction(AnimationKey ak) {
        this.AK = ak;
    }

    /**
     * Wenn Mario hochklettert, kann er klettern, bis er mit keinem kletterbarem Objekt mehr in Berührung ist.
     * Wenn Mario runterkletter, kann er klettern, bis er mit auf eine nicht-kletterbare Fläche gelagnt
     * @param gc aktueller Gamecontainer
     * @param sb aktuelles StateBaseGame
     * @param delta vergange Zeit
     * @param event Event, dass mit dieser Aktion Verbunden ist
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
        AbstractCharacter ac = (AbstractCharacter) event.getOwnerEntity();

        Vector2f position = ac.getPosition();

        // FALLS MARIO NICHT KLETTERT, WIRD ER AUF DIE LEITER PLATZIERT
        if(ac.getState() != this.AK) {
            ac.setState(this.AK);
            Optional<Entity> acStream = StateBasedEntityManager.getInstance().getEntitiesByState(sb.getCurrentStateID())
                    .stream().filter(c -> c.collides(ac) && c instanceof MapElement && ((MapElement) c).isClimbable()).findFirst();
            acStream.ifPresent(entity -> ac.getPosition().x = entity.getPosition().x);
        }

        boolean isOnGround;

        // HOCH KLETTERN
        if(this.AK == AnimationKey.CLIMB_UP) {
            // returns true if Mario is touching at least one map element which is not a ladder
            isOnGround = StateBasedEntityManager.getInstance().getEntitiesByState(sb.getCurrentStateID())
                    .stream().anyMatch(c -> c.collides(ac) && c instanceof MapElement);
            position.y -= Consts.MARIO_SPEED * delta;
            if(!isOnGround)
                isOnGround = StateBasedEntityManager.getInstance().getEntitiesByState(sb.getCurrentStateID())
                        .stream().anyMatch(c -> c.collides(ac) && c instanceof MapElement);
        }
        // RUNTER KLETTERN
        else {
            position.y += Consts.MARIO_SPEED * delta;

            Stream<Entity> me = StateBasedEntityManager.getInstance().getEntitiesByState(sb.getCurrentStateID())
                    .stream().filter(c -> ac.collides(ac) && c instanceof MetalBeam && !c.isPassable());

            isOnGround = me.findAny().isPresent();
        }
        if(!isOnGround) ac.setState(AnimationKey.STAND);

    }
}

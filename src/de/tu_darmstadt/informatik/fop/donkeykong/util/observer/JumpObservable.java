package de.tu_darmstadt.informatik.fop.donkeykong.util.observer;

import java.util.Observable;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class JumpObservable extends Observable {

    public void notifyJump() {
        this.setChanged();
        this.notifyObservers(true);
    }

    public void notifyLanding() {
        this.setChanged();
        this.notifyObservers(false);
    }
}

package de.tu_darmstadt.informatik.fop.donkeykong.util.observer;

import java.util.Observable;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class SpeedObservable extends Observable {

    public void notifySpeedChange(float speed) {
        this.setChanged();
        this.notifyObservers(speed);
    }
}

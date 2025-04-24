package de.tu_darmstadt.informatik.fop.donkeykong.util.observer;

import java.util.Observable;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class StairObservable extends Observable {

    @Override
    public void notifyObservers(Object height) {
        super.notifyObservers(height);
        this.setChanged();
    }
}

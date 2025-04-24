package de.tu_darmstadt.informatik.fop.donkeykong.util;

import java.io.Serializable;

import org.newdawn.slick.geom.Vector2f;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public class Coordinate extends Vector2f implements Serializable {

    public Coordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void changeX(float x) {
        this.x += x;
    }

    public void changeY(float y) {
        this.y += y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.x;
    }
}

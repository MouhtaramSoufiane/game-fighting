package com.jeu.v1;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GraphicObkect {
    protected Node corps;
    protected ImageView co;
    private boolean alive=true;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public boolean getDead(){
        return !alive;
    }
    public Node getCorps() {
        return corps;
    }

    public ImageView getCo() {
        return co;
    }

    public void setCorps(Node corps) {
        this.corps = corps;
    }

    public boolean touch(GraphicObkect obj){

        return corps.getBoundsInParent().intersects(obj.getCorps().getBoundsInParent());
    }
}

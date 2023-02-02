package com.jeu.v1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;



public class Arme {
    private Rectangle corps=new Rectangle(-6,0,4,10);
    private Circle sortie=new Circle(0,0,5);
    public Arme(GraphicObkect player){
        corps.setTranslateX(player.getCorps().getTranslateX());
        corps.setTranslateY(player.getCorps().getTranslateY());
        corps.setFill(Color.GRAY);
        sortie.setFill(Color.DARKRED);
        upadateSortie();
    }


    public void upadateSortie(){
        sortie.setTranslateX(corps.getTranslateX()+2);
        sortie.setTranslateY(corps.getTranslateY()+25);
    }

    public Rectangle getCorps() {
        return corps;
    }
    public void attachToPlayer(Player player){
        corps.setTranslateX(player.getCorps().getTranslateX());
        corps.setTranslateY(player.getCorps().getTranslateY());

    }

    public void setCorps(Rectangle corps) {
        this.corps = corps;
    }

    public Circle getSortie() {
        return sortie;
    }

    public void setSortie(Circle sortie) {
        this.sortie = sortie;
    }
    //x:tourner right
    public void RotateRight(){
        corps.setRotate(corps.getRotate()-5);
    }
    public void RotateLeft(){
        corps.setRotate(corps.getRotate()+5);
    }
    public double getRotate(){
        return corps.getRotate()-90;
    }

}

package com.jeu.v1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Player extends GraphicObkect{
    private static int coeurs=3;

    public   void setCoeurs(int coeurs) {
        Player.coeurs = coeurs;
    }

    public Player(Zone zone)  {
        Image image=null;
        try {
            image = new Image(new FileInputStream("PhotoJeu/Player12.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        corps=new ImageView(image);
        ((ImageView)corps).setX(0);
        ((ImageView)corps).setY(0);
        double x=zone.getX1()+(zone.getX2()-zone.getX1())*Math.random();
        double y=zone.getY1()+(zone.getY2()-zone.getY1())*Math.random();
        corps.setTranslateX(x);
        corps.setTranslateY(y);

    }

    public int getCoeurs() {
        return coeurs;
    }
}

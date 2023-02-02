package com.jeu.v1;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Zombie extends GraphicObkect{

    public Zombie(Zone zone){
    Image image= null;
        try {
        image = new Image(new FileInputStream("PhotoJeu/zombie_an.gif"));
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

}

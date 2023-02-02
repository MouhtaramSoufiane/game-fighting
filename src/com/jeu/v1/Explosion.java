package com.jeu.v1;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Explosion extends GraphicObkect{
     double x;
     double y;

     Image img;

    {
        try {
            img = new Image(new FileInputStream("PhotoJeu/explotion-explode.gif"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ImageView imgV=new ImageView(img);
     public Explosion(double x,double y){
        this.x=x;
        this.y=y;
        corps=new ImageView(img);
         ((ImageView)corps).setX(x);
         ((ImageView)corps).setY(y);


     }


}


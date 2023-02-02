package com.jeu.v1;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Balle extends GraphicObkect{
    private Point2D direction=new Point2D(0,0);
    public Balle(Arme arme){
        corps=new Circle(0,0,4, Color.RED);
        corps.setTranslateX(arme.getCorps().getTranslateX());
        corps.setTranslateY(arme.getCorps().getTranslateY());
        updatePoint2D(arme.getRotate());
    }
    public void updatePoint2D(double rotation){
        Point2D p;
        double x=Math.cos(Math.toRadians(rotation));
        double y=Math.sin(Math.toRadians(rotation));
        p=new Point2D(x,y);
        direction=p.normalize().multiply(7);
    }
    public void update(){
        corps.setTranslateX(corps.getTranslateX()+direction.getX());
        corps.setTranslateY(corps.getTranslateY()+direction.getY());
    }
}

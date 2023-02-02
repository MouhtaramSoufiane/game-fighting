package com.jeu.v1;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Program extends Application {


    private Pane container = new Pane();
    private HBox hbox=new HBox();

    boolean IsOver=false;
    Line line = new Line(0, 200, 800, 200);
    Zone zone1 = new Zone(0, 0, line.getEndX() - 50, line.getEndY() - 50);
    Zone zone2 = new Zone(line.getStartX(), line.getStartY(), line.getEndX() - 50, 800 - 50);
    Player player = new Player(zone2);
    Media media=new Media(new File("PhotoJeu/expolision_sound.mp3").toURI().toString());
    Media media2=new Media(new File("PhotoJeu/sound_msic.mp3").toURI().toString());
    MediaPlayer mediaPlayer=new MediaPlayer(media2);

    private List<Zombie> zombies = new ArrayList<>();
    private List<Balle> balles = new ArrayList<>();
    Arme arme = new Arme(player);
    AnimationTimer animation = new AnimationTimer() {
        @Override
        public void handle(long now) {
            refreshContent();
        }
    };

    public void refreshContent() {
        for (Balle balle : balles) {

            for (Zombie zombie : zombies) {


                if (balle.touch(zombie)) {
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    container.getChildren().removeAll(balle.getCorps(), zombie.getCorps());
                    double x = zombie.getCorps().getTranslateX();
                    double y = zombie.getCorps().getTranslateY();
                    Explosion expo = new Explosion(x, y);
                    container.getChildren().add(expo.getCorps());
                    PauseTransition delay = new PauseTransition(Duration.seconds(1));
                    delay.setOnFinished(e -> container.getChildren().removeAll(expo.getCorps()));
                    delay.play();
                    if (balle.touch(zombie)) {
                        mediaPlayer.play();
                    }
                    balle.setAlive(false);
                    zombie.setAlive(false);
                }
            }

            balle.update();
        }
        zombies.removeIf(GraphicObkect::getDead);
        balles.removeIf(GraphicObkect::getDead);
        int nb=3;
        
        for(Zombie zombie:zombies){
            if(zombie.getCorps().getTranslateY()>900){
                container.getChildren().removeAll(zombie.getCorps());
                IsOver=true;

            }
            if(IsOver){
                mediaPlayer.play();
                nb-=1;
                if(nb==0){
                    animation.stop();
                    container.getChildren().removeAll(player.getCorps(),arme.getCorps());
                    for(Zombie z:zombies){
                        container.getChildren().removeAll(z.getCorps());
                    }
                    for(Balle b:balles){
                        container.getChildren().removeAll(b.getCorps());
                    }
                    container.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
                    try {
                        ImageView imgv=new ImageView(new Image(new FileInputStream("PhotoJeu/gameOver.png")));
                        imgv.setTranslateY(imgv.getTranslateY()+200);
                        imgv.setTranslateX(imgv.getTranslateX()+250);
                        container.getChildren().add(imgv);

                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

            }


        }

        if (Math.random() < 0.01 ) {
            Zombie z = new Zombie(zone1);
            container.getChildren().add(z.getCorps());
            zombies.add(z);
    }
        for (Zombie z:zombies) {
            TranslateTransition translateTransition=new TranslateTransition();
            translateTransition.setDuration(Duration.millis(100));
            translateTransition.setByY(20);
            translateTransition.setNode(z.getCorps());
            translateTransition.play();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }

    EventHandler<KeyEvent> event = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.X) {
                arme.RotateLeft();
            }
            if (event.getCode() == KeyCode.W) {
                arme.RotateRight();
            }
            if (event.getCode() == KeyCode.SPACE) {
                Balle balle = new Balle(arme);
                container.getChildren().add(balle.getCorps());
                balles.add(balle);

            }
            if (event.getCode() == KeyCode.UP) {
                player.getCorps().setTranslateY(player.getCorps().getTranslateY() - 5);
                arme.attachToPlayer(player);
            }
            if (event.getCode() == KeyCode.DOWN) {
                player.getCorps().setTranslateY(player.getCorps().getTranslateY() + 5);
                arme.attachToPlayer(player);

            }
            if (event.getCode() == KeyCode.LEFT) {
                player.getCorps().setTranslateX(player.getCorps().getTranslateX() - 5);
                arme.attachToPlayer(player);

            }
            if (event.getCode() == KeyCode.RIGHT) {
                player.getCorps().setTranslateX(player.getCorps().getTranslateX() + 5);
                arme.attachToPlayer(player);
            }
        }
    };

    private void createContent() {
        container.getChildren().add(player.getCorps());
        container.getChildren().add(arme.getCorps());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setTitle("Fighting Game");
        container.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));

        Scene scene = new Scene(container);

        primaryStage.setScene(scene);

        animation.start();
        scene.setOnKeyPressed(event);
        createContent();
        primaryStage.show();


    }



}

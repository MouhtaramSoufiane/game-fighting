import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;

public class Main extends Application {
    public static void main(String[] args) {
launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("hello world");
        Media media=new Media(new File("PhotoJeu/expolision_sound.mp3").toURI().toString());
        MediaPlayer mediaPlayer=new MediaPlayer(media);
        mediaPlayer.play();
        StackPane stackPane=new StackPane();
        primaryStage.setScene(new Scene(stackPane,800,600));
        primaryStage.show();
    }
}
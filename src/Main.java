

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        music();
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root, 325, 525));
        primaryStage.show();
    }

    MediaPlayer media;
    public void music(){
        String s="Assemble.mp3";
        Media h=new Media(Paths.get(s).toUri().toString());
        media= new MediaPlayer(h);
        media.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

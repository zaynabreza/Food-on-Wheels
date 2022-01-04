
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.nio.file.Paths;

public class WelcomeController {

    

    @FXML
    private Button cancel;
    public void closeWindow(ActionEvent e){ //code to close the window
        Stage stage= (Stage) cancel.getScene().getWindow();
        stage.close();

    }

    @FXML
    private Button start;
    public void getStarted(ActionEvent e) throws Exception// to sign in screen
    {
        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        Scene current=start.getScene();
        Scene signin=new Scene(root, 325, 525);
        Stage currentStage= (Stage)start.getScene().getWindow();
        currentStage.setScene(signin);
    }
    @FXML private ImageView img;
    @FXML private Button button;

    MediaPlayer media;
    public void music(){
        String s="Assemble.mp3";
        Media h=new Media(Paths.get(s).toUri().toString());
        media= new MediaPlayer(h);
        media.play();
    }

    public void show(ActionEvent e)
    {
        img.setOpacity(1);
        music();
        PauseTransition pause= new PauseTransition(Duration.seconds(11));
        pause.setOnFinished(event -> img.setOpacity(0));
        pause.play();
    }

}

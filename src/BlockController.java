import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;



import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class BlockController {

    private String Email;

    public void Receive(String e)   throws SQLException {
        Email = e;
    }

    @FXML private TextField email;
    @FXML private Button submit;
    @FXML private Label msg;
    public void blockCustomer(ActionEvent e) throws Exception {
        String cus=email.getText();
        Rider r= new Rider();
        r.blockCustomer(cus);

        msg.setText("Blocked");
    }



    @FXML private Button edit;
    public void editRider(ActionEvent e) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditRider.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        EditRiderController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=edit.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)edit.getScene().getWindow();
        currentStage.setScene(search);
    }

    @FXML
    private Button status;
    public void checkStatus(ActionEvent e) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RiderMain.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        RiderMainController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=status.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)status.getScene().getWindow();
        currentStage.setScene(search);
    }



    @FXML
    private Button logout;
    public void LogOut(ActionEvent e)throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        Scene current = logout.getScene();
        Scene Signin = new Scene(root, 325, 525);
        Stage currentStage = (Stage) logout.getScene().getWindow();
        currentStage.setScene(Signin);

    }
}

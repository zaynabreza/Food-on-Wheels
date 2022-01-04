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

public class EditRiderController {

    private String Email;

    public void Receive(String e)   throws SQLException {
        Email = e;
    }
    @FXML
    private TextField num;
    @FXML
    private PasswordField pass;
    @FXML
    private Button submit;
    @FXML
    private Label msg;
    @FXML
    private TextField add;
    public void updateInfo(ActionEvent e) throws Exception
    {
        String Phone = num.getText();
        String Vehicle = add.getText();
        String Password = pass.getText();
        if (( Phone == null && Password == null && Vehicle==null) || (Phone.equals("") && Password.equals("")
                && Vehicle.equals("")) || (Phone == null && Vehicle == null && Password.equals("")) ||
                (Phone == null && Vehicle.equals("") && Password==null) || (Phone.equals("") && Vehicle == null && Password==null)) {
            msg.setText("Please fill at least one");
        }
        else
        {
            if (Password!=null && !Password.equals("")) {
                InputVerification Input = new InputVerification(); //create object of input verification
                String status = Input.Validate_Email_Password(Email, Password, Phone); //check if valid
                if (status == "Valid") {  //details valid and ready to send
                    msg.setText("All details are valid");
                    Rider C = new Rider();
                    C.editRiderInfo(Email,Phone, Password, Vehicle);
                    msg.setText("Details Updated");

                } else {
                    msg.setText("Password is Invalid");
                }
            }
            else
            {
                Rider C = new Rider();
                C.editRiderInfo(Email,Phone, Password, Vehicle);
                msg.setText("Details Updated");
            }
        }
    }

    @FXML private Button status;
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

    @FXML private Button block;
    public void blockCus(ActionEvent e) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Block.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        BlockController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=block.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)block.getScene().getWindow();
        currentStage.setScene(search);
    }
}



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.sql.*;

public class RestaurantSignUpController {
    private String Path;
    @FXML
    private Button signin;
    public void getSignin(ActionEvent e) throws Exception// to sign in screen
    {
        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        Scene current=signin.getScene();
        Scene Signin=new Scene(root, 325, 525);
        Stage currentStage= (Stage)signin.getScene().getWindow();
        currentStage.setScene(Signin);
    }
    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private TextField num;
    @FXML
    private TextField docx;
    @FXML
    private TextField cnic;
    @FXML
    private PasswordField pass;
    @FXML
    private Button submit;
    @FXML
    private Label msg;
    @FXML
    private TextField add;
    @FXML private Label path;
    @FXML private Button choose;
    public void getFile(ActionEvent e)
    {
        FileChooser fileChooser= new FileChooser();
        fileChooser.setTitle("Get Documents");
        Stage stage= (Stage) choose.getScene().getWindow();
        File f=fileChooser.showOpenDialog(stage);

        Path=f.getAbsolutePath();

        path.setText(Path);
    }

    public void enterRestaurantData(ActionEvent e) throws Exception// to sign in screen
    {
        if (Path==null)
        {Path="";}
        String Name= name.getText();
        String Email= email.getText();
        String Phone= num.getText();
        String CNIC=cnic.getText();
        String Password= pass.getText();

        String Address= add.getText();
        if (Name==null || Email==null || Phone==null || Password==null ){
            msg.setText("Please fill all fields");
        }
        else {
            InputVerification Input = new InputVerification(); //create object of input verification
            String status = Input.Validate_Email_Password(Email, Password, Phone); //check if valid
            if (status == "Valid") {
                //call the sign in stage and controller so that manager can sign in
                msg.setText("All fields are valid!");


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManagerSignIn.fxml"));
                Parent root1=(Parent)fxmlLoader.load();

                ManagerSignInController Manager=fxmlLoader.getController();
                Manager.Receive(Name, Email, Password, Path, CNIC, Address);

                Stage stage=new Stage();
                stage.setScene(new Scene(root1, 325, 525));
                stage.show();

            }
            else
            {
                msg.setText(status);
            }
        }

    }

}

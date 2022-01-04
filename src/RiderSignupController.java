

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.*;

public class RiderSignupController {
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
    private TextField fname;
    @FXML
    private TextField num;
    @FXML
    private TextField lname;

    @FXML
    private TextField vehicle;
    @FXML
    private RadioButton male, female;
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
    public void enterRiderData(ActionEvent e) throws Exception// to sign in screen
    {
        String FName= fname.getText();
        String Lname=lname.getText();
        String Email= email.getText();
        String Phone= num.getText();
        String CNIC=cnic.getText();
        String Password= pass.getText();
        String Vehicle= vehicle.getText();
        if (FName==null || Email==null || Phone==null || Password==null ){
            msg.setText("Please fill all fields");
        }
        else {
            InputVerification Input = new InputVerification(); //create object of input verification
            String status = Input.Validate_Email_Password(Email, Password, Phone); //check if valid
            if (status == "Valid") {
                //call the sign in stage and controller so that manager can sign in

                String Gender="";
                if (male.isSelected() && female.isSelected())
                    msg.setText("Please select one gender");
                else {

                    if (male.isSelected())
                        Gender="M";
                    else if (female.isSelected())
                        Gender="F";


                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManagerSignInRiderA.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();

                    ManagerSignInRiderAController Manager = fxmlLoader.getController();
                    Manager.Receive(FName,Lname, Vehicle, Password, Email,CNIC,Gender, Phone);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1, 325, 525));
                    stage.show();
                }

            }
            else
            {
                msg.setText(status);
            }
        }

    }

}

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.sql.*;
public class ManagerSignInRiderAController {

    private String FName;
    private String Lname;
    private String Vehicle;
    private String Password;
    private String Email;
    private String CNIC;
    private String Gender;
    private String Approvedby;
    private String Phone;
    public void Receive(String Fname, String lname, String v, String pass, String email, String cnic, String gen,String ph) {
        FName=Fname;
        Lname=lname;
        Vehicle=v;
        Password=pass;
        Email=email;
        CNIC=cnic;
        Gender=gen;
        Phone=ph;
    }

    @FXML
    private Button submit;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private Label msg;
    public void getApproval(ActionEvent e) throws Exception{ //launch the approval screen

        String ApprovedBy = email.getText();
        String ManagerPass= pass.getText();
        SalesManager M= new SalesManager();
        String status=M.SignInValidation(ApprovedBy,ManagerPass);
        if (status.equals("Success")) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RiderSignupApproval.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            RiderSignUpApprovalController Manager = fxmlLoader.getController();
            Manager.Receive(FName, Lname, Vehicle, Password, Email, CNIC, Gender, ApprovedBy, Phone);

            Scene current = submit.getScene();
            Scene approval = new Scene(root1, 325, 525);
            Stage currentStage = (Stage) submit.getScene().getWindow();
            currentStage.setScene(approval);
        }
        else
        {
            msg.setText(status);
        }


    }
}

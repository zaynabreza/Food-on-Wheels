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
public class ManagerSignIn3Controller {

    private String Name;
    private String Password;
    private String Email;
    private String Address;
    public void Receive(String name, String email, String pass, String Add) {
        Name=name;
        Password=pass;
        Email=email;
        Address=Add;
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

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ApproveREdit.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            ApproveREditController Manager = fxmlLoader.getController();
            System.out.println(Name);
            Manager.Receive(Name, Email, Password, Address);

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

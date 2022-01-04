import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.w3c.dom.Text;

import javax.swing.*;
import java.sql.*;
public class RiderSignUpApprovalController {

    private String FName;
    private String Lname;
    private String Vehicle;
    private String Password;
    private String Email;
    private String CNIC;
    private String Gender;
    private String Approvedby;
    private String Phone;

    public void Receive(String Fname, String lname, String v, String pass, String email, String cnic, String gen, String app, String ph) {
        FName=Fname;
        Lname=lname;
        Vehicle=v;
        Password=pass;
        Email=email;
        CNIC=cnic;
        Gender=gen;
        Approvedby=app;
        Phone=ph;

        msg.setText("For "+Email);
    }

    @FXML
    private RadioButton vehicle;
    @FXML
    private RadioButton cnic;
    @FXML
    private Button submit;
    @FXML
    private Label msg;

    public void enterRiderData(ActionEvent e) throws Exception// to sign in screen
    {
        if( vehicle.isSelected() && cnic.isSelected())
        {
            System.out.println("Email is " + Email);
            msg.setText("Approved");
            SalesManager x= new SalesManager();
            try {
                x.Insert_Rider(FName, Lname, Email, Phone, Vehicle, CNIC, Gender, Password, Approvedby); //send to business layer
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Notifications notificationBuilder=Notifications.create().title("Success").text("You have been registered").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
            notificationBuilder.show();
        }
        else
        {
            msg.setText("Not Approved");
        }
    }



}

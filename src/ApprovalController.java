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
public class ApprovalController {

private String Name;
private String Docx;
private String Password;
private String Email;
private String CNIC;
private String Address;
private String Approvedby;

public void Receive(String name, String email, String pass, String doc, String cnic, String add, String app) {
    Name=name;
    Docx=doc;
    Password=pass;
    Email=email;
    CNIC=cnic;
    Address=add;
    Approvedby=app;

    msg.setText("For "+Email);
}

@FXML
    private RadioButton doc;
@FXML
    private RadioButton cnic;
@FXML
    private Button submit;
@FXML
    private Label msg;

    public void enterRestaurantData(ActionEvent e) throws Exception// to sign in screen
    {
        if( doc.isSelected() && cnic.isSelected())
        {
            System.out.println("Email is " + Email);
            msg.setText("Approved");
            SalesManager x= new SalesManager();
            try {
                x.Create_Restaurant(Name, Email, Password, CNIC, Docx, Approvedby, Address); //send to business layer
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

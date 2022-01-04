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
public class ApproveREditController {

    private String Name;
    private String Email;
    private String Address;
    private String Password;


    public void Receive(String n, String em, String Pass, String Add) {
        Name=n;
        Email=em;
        Address=Add;
        Password=Pass;

        msg.setText("For "+Email);
    }

    @FXML
    private RadioButton approve;

    @FXML
    private Button submit;
    @FXML
    private Label msg;

    public void updateRestaurant(ActionEvent e) throws Exception// to sign in screen
    {
        if( approve.isSelected())
        {
            System.out.println("Email is " + Email);

            SalesManager x= new SalesManager();
            try {
                System.out.println(Name);
                x.editRestaurantDetails(Name, Password, Address); //send to business layer
                msg.setText("Approved");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Notifications notificationBuilder=Notifications.create().title("Success").text("Your information has ben updated").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
            notificationBuilder.show();
        }
        else
        {
            msg.setText("Not Approved");
        }
    }



}

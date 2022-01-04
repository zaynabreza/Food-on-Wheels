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
public class PaymentAuthController {

   private tempOrder Order;

   public void Receive(tempOrder O)
   {
       Order=O;
       msg.setText("Rs."+O.total );
   }


    @FXML
    private RadioButton valid;
    @FXML
    private Button submit;
    @FXML
    private Label msg;

    public void authorize(ActionEvent e) throws Exception// to sign in screen
    {
        Customer C= new Customer();
        //long cardno=
        //get the card no of the  customer
        if(valid.isSelected())
        {
            msg.setText("Payment Succeeded");
            Notifications notificationBuilder=Notifications.create().title("Success").text("Payment Succeeded").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
            notificationBuilder.show();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RestaurantSignIn.fxml"));
            Parent root1=(Parent)fxmlLoader.load();

            RestaurantSignInController a=fxmlLoader.getController();

            a.Receive(Order);

            Scene current=submit.getScene();
            Scene confirm=new Scene(root1, 325, 525);
            Stage currentStage= (Stage)submit.getScene().getWindow();
            currentStage.setScene(confirm);

        }
        else
        {
            msg.setText("Payment Failed");
            Notifications notificationBuilder=Notifications.create().title("Failure").text("Payment Failed").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
            notificationBuilder.show();
        }
    }



}

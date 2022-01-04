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
public class RestaurantSignInController {


   private tempOrder Order;
   public void Receive (tempOrder O)
   {
       Order=O;
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

        String Email = email.getText();
        String expected;
        String RestPass= pass.getText();
        Restaurant R= new Restaurant();
        expected=R.getRestaurantEmailViaName(Order.Restaurant);
        if  (Email.equals(expected)) {
            String status = R.SignInValidation(Email, RestPass);

            if (status.equals("Success")) {


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SubmitOrder.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                SubmitOrderController r = fxmlLoader.getController();
                r.Receive(Order);

                Scene current = submit.getScene();
                Scene order = new Scene(root1, 325, 525);
                Stage currentStage = (Stage) submit.getScene().getWindow();
                currentStage.setScene(order);
            } else {
                msg.setText("Incorrect Password");
            }
        }
        else
        {
            msg.setText("Incorrect Email");
        }


    }
}

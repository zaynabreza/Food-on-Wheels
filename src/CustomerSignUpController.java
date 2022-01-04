

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.*;

public class CustomerSignUpController {
    @FXML
    private Button signin;

    public void getSignin(ActionEvent e) throws Exception// to sign in screen
    {
        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        Scene current = signin.getScene();
        Scene Signin = new Scene(root, 325, 525);
        Stage currentStage = (Stage) signin.getScene().getWindow();
        currentStage.setScene(Signin);
    }

    @FXML
    private TextField email;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField num;
    @FXML
    private TextField dob;
    @FXML
    private TextField card;
    @FXML
    private PasswordField pass;
    @FXML
    private Button submit;
    @FXML
    private Label msg;
    @FXML
    private TextField add;
    @FXML
    private RadioButton notif;
    private int count = 0;

    public void enterCustomerData(ActionEvent e) throws Exception// to sign in screen
    {
        if (card.getText() == null) {
            msg.setText("Please fill all fields");
        }
        else
        {
            String Fname = fname.getText();
            String Lname = lname.getText();
            String Email = email.getText();
            String Phone = num.getText();
            long cardno = Long.parseLong(card.getText());
            String Password = pass.getText();
            Date date = Date.valueOf(dob.getText());
            String Address = add.getText();
            if (count == 0) {


                if (Fname == null || Lname == null || Email == null || Phone == null || Password == null) {
                    msg.setText("Please fill all fields");
                } else {
                    InputVerification Input = new InputVerification(); //create object of input verification
                    String status = Input.Validate_Email_Password(Email, Password, Phone); //check if valid
                    if (status == "Valid") {  //details valid and ready to send
                        count++;
                        msg.setText("All details are valid");
                        notif.setOpacity(1);

                    } else {
                        msg.setText(status);
                    }
                }

            } else if (count == 1) //ask about notifications
            {
                boolean notify = notif.isSelected();
                Customer C= new Customer();
                try {
                    C.addNewCustomer(Fname, Lname, Password, date, Phone, cardno, Email, notify, Address);
                    msg.setText("You have been registered!");
                } catch (SQLException throwables) {
                    msg.setText("error");
                    throwables.printStackTrace();
                }


            }

        }
    }
}

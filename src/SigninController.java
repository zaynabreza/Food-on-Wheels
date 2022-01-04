

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class SigninController {

    private String Email;
    private String Password;

    @FXML
    private Button signup;
    public void launchSignUp(ActionEvent e) throws Exception// to sign up screen
    {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene current=signup.getScene();
        Scene Signup=new Scene(root, 325, 525);
        Stage currentStage= (Stage)signup.getScene().getWindow();
        currentStage.setScene(Signup);
    }


    @FXML
    private Button submit;
    @FXML
    private RadioButton customer;
    @FXML
    private RadioButton restaurant;
    @FXML
    private RadioButton rider;
    @FXML
    private RadioButton manager;
    @FXML
    private Label msg;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    public void launchApplication(ActionEvent e) throws Exception //to the main app
    {
        Email=email.getText();
        Password= pass.getText();
        if ((customer.isSelected() && rider.isSelected()) || (customer.isSelected() && restaurant.isSelected())
        || (rider.isSelected() && restaurant.isSelected()) || (customer.isSelected() && rider.isSelected() &&
                restaurant.isSelected()) && manager.isSelected() )
        { msg.setText("Please choose only one category");}

        else if (!customer.isSelected() && !rider.isSelected() && !restaurant.isSelected())
        {msg.setText("Please choose a category"); }


       else if (Email==null || Password==null || Email.equals("") || Password.equals(""))
        {
            msg.setText("Please enter your email and password");
        }

        if (customer.isSelected()) //customer is signing in
        {
            Customer C= new Customer();
            String status;
            status=C.SignInValidation(Email, Password);
            if (status.equals("Success"))
            {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchRestaurant.fxml"));
                Parent root1=(Parent)fxmlLoader.load();

                SearchRestaurantController a=fxmlLoader.getController();
                a.Receive(Email);

                Scene current=submit.getScene();
                Scene search=new Scene(root1, 325, 525);
                Stage currentStage= (Stage)submit.getScene().getWindow();
                currentStage.setScene(search);
            }
            else
            {
                msg.setText(status);
            }


        }
        else if (restaurant.isSelected())
        {
            Restaurant R= new Restaurant();
            String status;
            status=R.SignInValidation(Email, Password);
            if (status.equals("Success"))
            {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RestaurantA.fxml"));
                Parent root1=(Parent)fxmlLoader.load();

                RestaurantAController a=fxmlLoader.getController();
                a.Receive(Email);

                Scene current=submit.getScene();
                Scene search=new Scene(root1, 325, 525);
                Stage currentStage= (Stage)submit.getScene().getWindow();
                currentStage.setScene(search);
            }
            else
            {
                msg.setText(status);
            }
        }
        else if (rider.isSelected())
        {
            Rider R= new Rider();
            String status;
            status=R.SignInValidation(Email, Password);
            if (status.equals("Success"))
            {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RiderMain.fxml"));
                Parent root1=(Parent)fxmlLoader.load();

                RiderMainController a=fxmlLoader.getController();
                a.Receive(Email);

                Scene current=submit.getScene();
                Scene search=new Scene(root1, 325, 525);
                Stage currentStage= (Stage)submit.getScene().getWindow();
                currentStage.setScene(search);
            }
            else
            {
                msg.setText(status);
            }
        }
        else if (manager.isSelected())
        {
            SalesManager M= new SalesManager();
            String status=M.SignInValidation(Email, Password);
            if (status.equals("Success")) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GenNotification.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                GenNotificationController Manager = fxmlLoader.getController();
                Manager.Receive(Email);

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



}

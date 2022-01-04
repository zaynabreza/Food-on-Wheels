

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class SignUpController {
    @FXML
    private Button customer;
    public void launchCustomerSignUp(ActionEvent e) throws Exception// to Customer sign up screen
    {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerSignUp.fxml"));
        Scene current=customer.getScene();
        Scene CusSignup=new Scene(root, 325, 525);
        Stage currentStage= (Stage)customer.getScene().getWindow();
        currentStage.setScene(CusSignup);
    }
    @FXML
    private Button res;
    public void launchRestaurantSignUp(ActionEvent e) throws Exception// to Restaurant sign up screen
    {
        Parent root = FXMLLoader.load(getClass().getResource("RestaurantSignUp.fxml"));
        Scene current=res.getScene();
        Scene ResSignup=new Scene(root, 325, 525);
        Stage currentStage= (Stage)res.getScene().getWindow();
        currentStage.setScene(ResSignup);
    }
    @FXML
    private Button rider;
    public void launchRiderSignUp(ActionEvent e) throws Exception// to Restaurant sign up screen
    {
        Parent root = FXMLLoader.load(getClass().getResource("RiderSignUp.fxml"));
        Scene current=rider.getScene();
        Scene ResSignup=new Scene(root, 325, 525);
        Stage currentStage= (Stage)rider.getScene().getWindow();
        currentStage.setScene(ResSignup);
    }
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
}

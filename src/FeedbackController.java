import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class FeedbackController {

    String Email;
    String Name;

    public void Receive(String e, String n)
    {
        Email=e;
        Name=n;
    }

    @FXML private Button back;
    public void goBack(ActionEvent e) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        MenuController a=fxmlLoader.getController();
        // System.out.println("Restaurant at point a is " + value);
        a.Receive(Email, Name);

        Scene current=back.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)back.getScene().getWindow();
        currentStage.setScene(search);
    }

    @FXML
    private Button logout;
    public void LogOut(ActionEvent e)throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        Scene current = logout.getScene();
        Scene Signin = new Scene(root, 325, 525);
        Stage currentStage = (Stage) logout.getScene().getWindow();
        currentStage.setScene(Signin);

    }
    @FXML private Button edit;
    public void editInfo(ActionEvent e) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditCustomer.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        EditCustomerController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=edit.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)edit.getScene().getWindow();
        currentStage.setScene(search);
    }

    @FXML private Button orderbutton;
    public void placeOrder(ActionEvent e) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchRestaurant.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        SearchRestaurantController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=orderbutton.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)orderbutton.getScene().getWindow();
        currentStage.setScene(search);
    }
    @FXML private Button status;
    public void checkStatus(ActionEvent e) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderStatus.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        OrderStatusController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=status.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)status.getScene().getWindow();
        currentStage.setScene(search);
    }
    @FXML private Button notif;
    public void viewNotifications(ActionEvent e) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Notifications.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        NotificationsController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=notif.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)notif.getScene().getWindow();
        currentStage.setScene(search);
    }

    @FXML private TextField feedback;
    @FXML private Button submit;
    @FXML private Label msg;

    public void giveFeedback(ActionEvent e) throws SQLException
    {
        String F=feedback.getText();
        if (F.equals("") || F==null)
            msg.setText("Feedback is empty!");
        else {
            Customer C = new Customer();
            C.LeaveReview(F, Name, Email);
            msg.setText("Feedback Sent");
        }


    }

}

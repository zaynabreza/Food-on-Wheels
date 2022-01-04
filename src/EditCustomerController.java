import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditCustomerController {

    private String Email;

    public void Receive(String e)
    {
        Email=e;
    }
    @FXML
    private TextField num;
    @FXML
    private PasswordField pass;
    @FXML
    private Button submit;
    @FXML
    private Label msg;
    @FXML
    private TextField add;
    public void updateInfo(ActionEvent e) throws Exception
    {
        String Phone = num.getText();
        String Address = add.getText();
        String Password = pass.getText();
        if (( Phone == null && Password == null && Address==null) || (Phone.equals("") && Password.equals("")
        && Address.equals("")) || (Phone == null && Address == null && Password.equals("")) ||
                (Phone == null && Address.equals("") && Password==null) || (Phone.equals("") && Address == null && Password==null)) {
            msg.setText("Please fill at least one");
        }
        else
        {
            if (Password!=null && !Password.equals("")) {
                InputVerification Input = new InputVerification(); //create object of input verification
                String status = Input.Validate_Email_Password(Email, Password, Phone); //check if valid
                if (status == "Valid") {  //details valid and ready to send
                    msg.setText("All details are valid");
                    Customer C = new Customer();
                    C.editCustomerInfo(Email, Password, Phone, Address);
                    msg.setText("Details Updated");

                } else {
                    msg.setText("Password is Invalid");
                }
            }
            else
            {
                Customer C = new Customer();
                C.editCustomerInfo(Email, Password, Phone, Address);
                msg.setText("Details Updated");
            }
        }
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
}

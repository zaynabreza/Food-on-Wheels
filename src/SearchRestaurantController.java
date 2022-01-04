import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;



import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
public class SearchRestaurantController implements Initializable {
    private String Email;
    @FXML
    private ComboBox<String> rest;
    private ArrayList<String> Restaurants;
    private ObservableList<String> R;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Restaurant Rest= new Restaurant();

        try {
            Restaurants= Rest.getRestaurantslist();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        R= FXCollections.observableArrayList();
        for (int i=0; i< Restaurants.size(); i++)
        {
            R.add(Restaurants.get(i));
        }

        rest.setItems(R);

    }
    @FXML
    private Button submit;
    @FXML
    private Label msg;
    public void Receive(String email)
    {
        Email=email;
    }
    public void getMenu(ActionEvent e) throws Exception//get the menu of entered restaurant and show to user
    {
        String value;
        value=rest.getValue();


        boolean matches=false;
        for (int i=0; i<R.size(); i++)
        {
            if (value.equals(R.get(i))) {
                matches = true;
            }
        }
        if (matches==false) //if the restaurant entered is invalid
        {
            msg.setText("You entered an Invalid Restaurant.\nPlease enter again or choose\nfrom the list");
        }
        else
        {
            msg.setText("Correct Restaurant Entered Yayy");
            //call the form that shows menu while sending parameters as email and restaurant name.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root1=(Parent)fxmlLoader.load();

            MenuController a=fxmlLoader.getController();
           // System.out.println("Restaurant at point a is " + value);
            a.Receive(Email, value);

            Scene current=submit.getScene();
            Scene search=new Scene(root1, 325, 525);
            Stage currentStage= (Stage)submit.getScene().getWindow();
            currentStage.setScene(search);
        }

    }
    @FXML
    private Button reorder;
    public void callReorder(ActionEvent e) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReOrder.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        ReOrderController a=fxmlLoader.getController();
        // System.out.println("Restaurant at point a is " + value);
        a.Receive(Email);

        Scene current=submit.getScene();
        Scene ReOrder=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)submit.getScene().getWindow();
        currentStage.setScene(ReOrder);
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

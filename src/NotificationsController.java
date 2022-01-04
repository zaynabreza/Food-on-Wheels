import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NotificationsController {

    private String Email;
    private ArrayList<String> Notifications ;
    @FXML
    private TableView<NotifAdapter> table;
    @FXML private Button save;
    @FXML private TableColumn<NotifAdapter, Integer> no;
    @FXML private TableColumn<NotifAdapter, String> n;

    private ObservableList<NotifAdapter> R;


    public void Receive(String e) throws Exception {
        Email = e;
        no.setCellValueFactory(new PropertyValueFactory<>("Num"));
        n.setCellValueFactory(new PropertyValueFactory<>("Message"));



        
        Customer C= new Customer();
        
        Notifications=C.ViewAllNotifications(Email);
        

        R=FXCollections.observableArrayList();

        for (int i=0; i<Notifications.size(); i++)
        {
            NotifAdapter temp= new NotifAdapter(i+1, Notifications.get(i));
            R.add(temp);
        }

        table.setItems(R);

        boolean on=C.getNotifStatus(Email);
        if (on)
        {
            save.setText("Turn Off");
        }
        else
        {
            save.setText("Turn On");
        }

    }
    @FXML
    private Label msg;
    public void changeNotifStatus(ActionEvent e) throws Exception
    {
        Customer C= new Customer();
       // Notification newNotification = new Notification();
        boolean on=C.getNotifStatus(Email);
        if (on)
        {
            C.TurnOffNotification(Email);
            msg.setText("Notifications turned off");
            save.setText("Turn On");
        }
        else
        {
            C.TurnOnNotification(Email);
            msg.setText("Notifications turned on");
            save.setText("Turn Off");
        }

    }

    @FXML
    private Button orderbutton;
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
}

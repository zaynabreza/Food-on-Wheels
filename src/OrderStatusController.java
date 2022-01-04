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
public class OrderStatusController {
    private String Email;
    @FXML
    private ComboBox<String> order;
    private ArrayList<Pending_Orders> Orders ;
    private ObservableList<String> R;
    @FXML
    private Button get;
    @FXML
    private Label msg;
    @FXML
    private Label s1, s2, r1, r2, d1, d2, t1, t2;
    public void Receive(String e) throws SQLException {
        Email=e;
        Order O= new Order();
        Customer C= new Customer();
        Orders=O.getOrderStatus(C.getCustomerID(Email));
        R= FXCollections.observableArrayList();
        for (int i=0; i<Orders.size(); i++)
        {
            R.add(Integer.toString(Orders.get(i).getOrder_Id()));
        }
        order.setItems(R);
    }

    public void getOrder(ActionEvent e) throws Exception//get the menu of entered restaurant and show to user
    {
        Order O= new Order();
        Customer C= new Customer();
        Orders=O.getOrderStatus(C.getCustomerID(Email));

        int value;
        value= Integer.parseInt(order.getValue());


        boolean matches=false;
        for (int i=0; i<R.size(); i++)
        {
            if (value==Integer.parseInt(R.get(i))) {
                matches = true;
            }
        }
        if (!matches) //if the order entered is invalid
        {
            msg.setText("You entered an Invalid OrderNum.\nPlease enter again or choose\nfrom the list");
        }
        else
        {
            msg.setText("");
            s1.setOpacity(1);
            r1.setOpacity(1);
            d1.setOpacity(1);
            t1.setOpacity(1);

            for (int i=0; i<Orders.size(); i++)
            {
                if (value==Orders.get(i).getOrder_Id()) {
                    Restaurant Res= new Restaurant();
                    String RestName= Res.getRestaurantName(Orders.get(i).getRestaurant());

                    s2.setText(Orders.get(i).getStatus());
                    r2.setText(RestName);
                    d2.setText(Orders.get(i).getDate());
                    t2.setText(Double.toString(Orders.get(i).getTotal()));

                }
            }

            //call the form for order confirmation

        }



    }

    public void cancelOrder(ActionEvent e) throws SQLException {
        int value;
        value= Integer.parseInt(order.getValue());


        boolean matches=false;
        for (int i=0; i<R.size(); i++)
        {
            if (value==Integer.parseInt(R.get(i))) {
                matches = true;
            }
        }
        if (!matches) //if the order entered is invalid
        {
            msg.setText("You entered an Invalid OrderNum.\nPlease enter again or choose\nfrom the list");
        }
        else
        {
            Order O = new Order();
            Customer C= new Customer();
            int customerid= C.getCustomerID(Email);
            boolean possible=O.cancelOrder(customerid, value);

            if (possible==false)
            {
                msg.setText("Order cannot be cancelled now");
            }
            else
            {
                msg.setText("Order Cancelled");
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

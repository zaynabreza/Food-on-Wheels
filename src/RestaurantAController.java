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
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;



import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class RestaurantAController {
    @FXML
    private ComboBox<String> order;

    private ObservableList<String> R;
    @FXML
    private Button get;
    @FXML
    private Label msg;
    @FXML
    private Label s1, s2, a1, a2, d1, d2, t1, t2, p1, p2;
    private ArrayList<Order_Details> Orders ;
    private String Email;
    private String Password;

    public void Receive(String e)   throws SQLException
    {
        Email=e;
        Restaurant rest= new Restaurant();
        Orders=rest.getOrderDetails(rest.getRestaurantIDViaEmail(Email));
        R= FXCollections.observableArrayList();
        for (int i=0; i<Orders.size(); i++)
        {
            R.add(Integer.toString(Orders.get(i).OrderID));
        }
        order.setItems(R);



    }
    public void getOrder(ActionEvent e) throws Exception//get the menu of entered restaurant and show to user
    {
        Restaurant rest= new Restaurant();
        Orders=rest.getOrderDetails(rest.getRestaurantIDViaEmail(Email));

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

            a1.setOpacity(1);
            d1.setOpacity(1);
            t1.setOpacity(1);
            p1.setOpacity(1);
            Rider rider= new Rider();

            for (int i=0; i<Orders.size(); i++)
            {
                if (value==Orders.get(i).OrderID) {
                    Order O= new Order();
                    int C=O.getCustID(value);
                    Customer c= new Customer();
                    a2.setText(rider.getCustomerAddress(c.getCustomerEmail(C)));
                    d2.setText(String.valueOf(Orders.get(i).dateOfOrder));
                    t2.setText(Double.toString(Orders.get(i).Total_Charges));
                    p2.setText(rider.getCustomerPhoneNumber(c.getCustomerEmail(C)));

                }
            }
        }

    }

    public void enrouteOrder(ActionEvent e) throws SQLException {
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
            O.updateOrderStatus(value, "En route");


            msg.setText("Status Updated");

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
    @FXML
    private Button menu;
    public void changeMenu(ActionEvent e)throws Exception
    {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RMenu.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        RMenuController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=menu.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)menu.getScene().getWindow();
        currentStage.setScene(search);

    }
    @FXML
    private Button edit;
    public void changeInfo(ActionEvent e)throws Exception
    {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editRestaurant.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        editRestaurantController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=edit.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)edit.getScene().getWindow();
        currentStage.setScene(search);

    }

    @FXML private Button feedback;
    public void getFeedback(ActionEvent e)throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewFeedback.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        ViewFeedbackController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=feedback.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)feedback.getScene().getWindow();
        currentStage.setScene(search);
    }



}

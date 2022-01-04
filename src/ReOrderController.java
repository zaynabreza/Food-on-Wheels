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
public class ReOrderController {
    private String Email;
    @FXML
    private ComboBox<String> order;
    private ArrayList<Order_Line_Items_List> Orders ;
    private ObservableList<String> R;
    @FXML
    private Button submit;
    @FXML
    private Label msg;
    public void Receive(String email) throws SQLException {
        Email=email;
        Order O= new Order();
        Customer C= new Customer();
        Orders=O.getLineItems(C.getCustomerID(Email));

        R= FXCollections.observableArrayList();
        for (int i=0; i< Orders.size(); i++)
        {
            Order_Line_Items_List temp= new Order_Line_Items_List(Orders.get(i).Item, Orders.get(i).Price, Orders.get(i).Quantity, Orders.get(i).Rest_ID);
            temp.num=i+1;

            Orders.set(i, temp);

        }
        for (int i=0; i<Orders.size(); i++)
        {
            R.add(Integer.toString(Orders.get(i).num));
        }

        order.setItems(R);
    }
    public void getOrder(ActionEvent e) throws Exception//get the menu of entered restaurant and show to user
    {
        int value;
        value= Integer.parseInt(order.getValue());


        boolean matches=false;
        for (int i=0; i<R.size(); i++)
        {
            if (value==Integer.parseInt(R.get(i))) {
                matches = true;
            }
        }
        if (!matches) //if the restaurant entered is invalid
        {
            msg.setText("You entered an Invalid OrderNum.\nPlease enter again or choose\nfrom the list");
        }
        else
        {

            msg.setText("Correct Num Entered Yayy");
            for (int i=0; i<Orders.size(); i++)
            {
                if (value==Orders.get(i).num) {
                    Restaurant Res= new Restaurant();
                    String RestName= Res.getRestaurantName(Orders.get(i).Rest_ID);
                    tempOrder sendingOrder= new tempOrder(Email, RestName);




                    /*for (int j=0; j<Orders.get(i).Price.size(); j++)
                    {
                        sendingOrder.I.add(new items(Orders.get(i).Item.get(j), Orders.get(i).Quantity.get(j)));
                        sendingOrder.total+=Orders.get(i).Quantity.get(j) * Orders.get(i).Price.get(j);
                    }*/

                    sendingOrder.Reorder(Orders.get(i).Item, Orders.get(i).Quantity, Orders.get(i).Price);
                    //check availability of menu items
                    ArrayList<Menu_List> Menu;
                    ArrayList<Deals_List> Deals;

                    Menu= Res.getRestaurantMenu(RestName);


                    boolean possible=true;
                    for (int a=0; i<10; i++)
                    {
                        for (int x=0; x<sendingOrder.I.size(); x++)
                        {
                            if (Menu.get(a).Product_Name.equals(sendingOrder.I.get(x).name))
                            {
                                if (!Menu.get(a).Availability)
                                {
                                    possible=false;
                                }
                            }
                        }
                    }

                    if (possible==false)
                    {msg.setText("Sorry, certain items are unavailable");}

                    else {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConfirmOrder.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();

                        ConfirmOrderController a = fxmlLoader.getController();
                        // System.out.println("Restaurant at point a is " + value);
                        a.Receive(sendingOrder);

                        Scene current = submit.getScene();
                        Scene search = new Scene(root1, 325, 525);
                        Stage currentStage = (Stage) submit.getScene().getWindow();
                        currentStage.setScene(search);
                    }



                }
            }

            //call the form for order confirmation

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
    @FXML
    private Button back;
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

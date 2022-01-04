import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import javax.swing.*;

public class MenuController {
    private String Email;
    private String Restaurant;
    private ArrayList<Menu_List> Menu;
    private ArrayList<Deals_List> Deals;
    tempOrder Order;



    @FXML
    private Button D1, D2, M1, M2, M3, M4, M5, M6, M7, M8, M9, M10, feedback;
    @FXML
    private Label DP1, DP2, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, name;
    @FXML
    private Tooltip TD1, TD2, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10;



    public void Receive(String email, String Rest) throws SQLException {
        Email=email;
       // System.out.println("receiving restaurant is " + Rest);
        Restaurant=Rest;
        Restaurant theRestaurant= new Restaurant();

        Order= new tempOrder(Email, Restaurant);

        name.setText(Restaurant);
        Menu= theRestaurant.getRestaurantMenu(Restaurant);
        Deals= theRestaurant.getRestaurantDeals(Restaurant);



        //implementation starts here for deals
    if (Deals.size()<=0)
    {
        D1.setOpacity(0);
        D1.setDisable(true);
        TD1.setOpacity(0);

        D2.setOpacity(0);
        D2.setDisable(true);
        TD2.setOpacity(0);
    }
    else {
        if (Deals.get(0).Description == null) {
            D1.setOpacity(0);
            D1.setDisable(true);
            TD1.setOpacity(0);


        } else {
            D1.setText(Deals.get(0).Description);
            DP1.setText("Rs." + Deals.get(0).Price);
            TD1.setText(Deals.get(0).Description + " Rs" + Deals.get(0).Price);
        }
        if (Deals.get(1).Description == null) {
            D2.setOpacity(0);
            D2.setDisable(true);
            TD2.setOpacity(0);
        } else {
            D2.setText(Deals.get(1).Description);
            DP2.setText("Rs." + Deals.get(1).Price);
            TD2.setText(Deals.get(1).Description + " Rs" + Deals.get(1).Price);
        }
        if (Deals.get(1).Description == null && Deals.get(0).Description == null) {
            DP1.setPrefWidth(200);
            //DP1.setFont(); ->Later set font for the notice
            DP1.setText("No deals at the moment!");
        }
    }
    if (Menu.size()<=0)
    {
        M1.setOpacity(0);
        M1.setDisable(true);
        T1.setOpacity(0);

        M2.setOpacity(0);
        M2.setDisable(true);
        T2.setOpacity(0);

        M3.setOpacity(0);
        M3.setDisable(true);
        T3.setOpacity(0);

        M4.setOpacity(0);
        M4.setDisable(true);
        T4.setOpacity(0);

        M5.setOpacity(0);
        M5.setDisable(true);
        T5.setOpacity(0);

        M6.setOpacity(0);
        M6.setDisable(true);
        T6.setOpacity(0);

        M7.setOpacity(0);
        M7.setDisable(true);
        T7.setOpacity(0);

        M8.setOpacity(0);
        M8.setDisable(true);
        T8.setOpacity(0);

        M9.setOpacity(0);
        M9.setDisable(true);
        T9.setOpacity(0);

        M10.setOpacity(0);
        M10.setDisable(true);
        T10.setOpacity(0);


    }
    else {

        if (Menu.get(0).Product_Name == null || Menu.get(0).Product_Name.equals("") || !Menu.get(0).Availability) {
            M1.setOpacity(0);
            M1.setDisable(true);
            T1.setOpacity(0);
        } else {
            M1.setText(Menu.get(0).Product_Name);
            P1.setText("Rs." + Menu.get(0).Price);
            T1.setText(Menu.get(0).Product_Name + " Rs" + Menu.get(0).Price);
        }

        if (Menu.get(1).Product_Name == null || Menu.get(1).Product_Name.equals("") || !Menu.get(1).Availability) {
            M2.setOpacity(0);
            M2.setDisable(true);
            T2.setOpacity(0);
        } else {
            M2.setText(Menu.get(1).Product_Name);
            P2.setText("Rs." + Menu.get(1).Price);
            T2.setText(Menu.get(1).Product_Name + " Rs" + Menu.get(1).Price);
        }

        if (Menu.get(2).Product_Name == null || Menu.get(2).Product_Name.equals("") || !Menu.get(2).Availability) {
            M3.setOpacity(0);
            M3.setDisable(true);
            T3.setOpacity(0);
        } else {
            M3.setText(Menu.get(2).Product_Name);
            P3.setText("Rs." + Menu.get(2).Price);
            T3.setText(Menu.get(2).Product_Name + " Rs" + Menu.get(2).Price);
        }

        if (Menu.get(3).Product_Name == null || Menu.get(3).Product_Name.equals("") || !Menu.get(3).Availability) {
            M4.setOpacity(0);
            M4.setDisable(true);
            T4.setOpacity(0);
        } else {
            M4.setText(Menu.get(3).Product_Name);
            P4.setText("Rs." + Menu.get(3).Price);
            T4.setText(Menu.get(3).Product_Name + " Rs" + Menu.get(3).Price);
        }

        if (Menu.get(4).Product_Name == null || Menu.get(4).Product_Name.equals("") || !Menu.get(4).Availability) {
            M5.setOpacity(0);
            M5.setDisable(true);
            T5.setOpacity(0);
        } else {
            M5.setText(Menu.get(4).Product_Name);
            P5.setText("Rs." + Menu.get(4).Price);
            T5.setText(Menu.get(4).Product_Name + " Rs" + Menu.get(4).Price);
        }

        if (Menu.get(5).Product_Name == null || Menu.get(5).Product_Name.equals("") || !Menu.get(5).Availability) {
            M6.setOpacity(0);
            M6.setDisable(true);
            T6.setOpacity(0);
        } else {
            M6.setText(Menu.get(5).Product_Name);
            P6.setText("Rs." + Menu.get(5).Price);
            T6.setText(Menu.get(5).Product_Name + " Rs" + Menu.get(5).Price);
        }

        if (Menu.get(6).Product_Name == null || Menu.get(6).Product_Name.equals("") || !Menu.get(6).Availability) {
            M7.setOpacity(0);
            M7.setDisable(true);
            T7.setOpacity(0);
        } else {
            M7.setText(Menu.get(6).Product_Name);
            P7.setText("Rs." + Menu.get(6).Price);
            T7.setText(Menu.get(6).Product_Name + " Rs" + Menu.get(6).Price);
        }

        if (Menu.get(7).Product_Name == null || Menu.get(7).Product_Name.equals("") || !Menu.get(7).Availability) {
            M8.setOpacity(0);
            M8.setDisable(true);
            T8.setOpacity(0);
        } else {
            M8.setText(Menu.get(7).Product_Name);
            P8.setText("Rs." + Menu.get(7).Price);
            T8.setText(Menu.get(7).Product_Name + " Rs" + Menu.get(7).Price);
        }

        if (Menu.get(8).Product_Name == null || Menu.get(8).Product_Name.equals("") || !Menu.get(8).Availability) {
            M9.setOpacity(0);
            M9.setDisable(true);
            T9.setOpacity(0);
        } else {
            M9.setText(Menu.get(8).Product_Name);
            P9.setText("Rs." + Menu.get(8).Price);
            T9.setText(Menu.get(8).Product_Name + " Rs" + Menu.get(8).Price);
        }

        if (Menu.get(9).Product_Name == null || Menu.get(9).Product_Name.equals("") || !Menu.get(9).Availability) {
            M10.setOpacity(0);
            M10.setDisable(true);
            T10.setOpacity(0);
        } else {
            M10.setText(Menu.get(9).Product_Name);
            P10.setText("Rs." + Menu.get(9).Price);
            T10.setText(Menu.get(9).Product_Name + " Rs" + Menu.get(9).Price);
        }
    }
    }

    @FXML private Label added;
    public void addD1ToOrder(ActionEvent e)//add item to order
    {
        String s=Order.Add(Deals.get(0).Description, Deals.get(0).Price);
        if (!s.equals("full"))
        {
            added.setText(s);
            PauseTransition pause= new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> added.setText(""));
            pause.play();
        }


    }
    public void addD2ToOrder(ActionEvent e)//add item to order
    {
        String s=Order.Add(Deals.get(1).Description, Deals.get(1).Price);
        if (!s.equals("full"))
        {
            added.setText(s);
            PauseTransition pause= new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> added.setText(""));
            pause.play();
        }
    }
    public void addM1ToOrder(ActionEvent e)//add item to order
    {
        String s=Order.Add(Menu.get(0).Product_Name, Menu.get(0).Price);
        if (!s.equals("full"))
        {
            added.setText(s);
            PauseTransition pause= new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> added.setText(""));
            pause.play();
        }
    }
    public void addM2ToOrder(ActionEvent e)//add item to order
    {
        String s=Order.Add(Menu.get(1).Product_Name, Menu.get(1).Price);
        if (!s.equals("full"))
        {
            added.setText(s);
            PauseTransition pause= new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> added.setText(""));
            pause.play();
        }
    }
    public void addM3ToOrder(ActionEvent e)//add item to order
    {
        String s=Order.Add(Menu.get(2).Product_Name, Menu.get(2).Price);
        if (!s.equals("full"))
        {
            added.setText(s);
            PauseTransition pause= new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> added.setText(""));
            pause.play();
        }
    }
    public void addM4ToOrder(ActionEvent e)//add item to order
    {
        String s=Order.Add(Menu.get(3).Product_Name, Menu.get(3).Price);
        if (!s.equals("full"))
        {
            added.setText(s);
            PauseTransition pause= new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> added.setText(""));
            pause.play();
        }
    }
    public void addM5ToOrder(ActionEvent e)//add item to order
    {
        String s=Order.Add(Menu.get(4).Product_Name, Menu.get(4).Price);
        if (!s.equals("full"))
        {
            added.setText(s);
            PauseTransition pause= new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> added.setText(""));
            pause.play();
        }
    }
    public void addM6ToOrder(ActionEvent e)//add item to order
    {
        String s=Order.Add(Menu.get(5).Product_Name, Menu.get(5).Price);
        if (!s.equals("full"))
        {
            added.setText(s);
            PauseTransition pause= new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> added.setText(""));
            pause.play();
        }
    }
    public void addM7ToOrder(ActionEvent e)//add item to order
    {
        String s=Order.Add(Menu.get(6).Product_Name, Menu.get(6).Price);
        if (!s.equals("full"))
        {
            added.setText(s);
            PauseTransition pause= new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> added.setText(""));
            pause.play();
        }
    }
    public void addM8ToOrder(ActionEvent e)//add item to order
    {
        String s=Order.Add(Menu.get(7).Product_Name, Menu.get(7).Price);
        if (!s.equals("full"))
        {
            added.setText(s);
            PauseTransition pause= new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> added.setText(""));
            pause.play();
        }
    }
    public void addM9ToOrder(ActionEvent e)//add item to order
    {
        String s=Order.Add(Menu.get(8).Product_Name, Menu.get(8).Price);
        if (!s.equals("full"))
        {
            added.setText(s);
            PauseTransition pause= new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> added.setText(""));
            pause.play();
        }
    }
    public void addM10ToOrder(ActionEvent e)//add item to order
    {
        String s=Order.Add(Menu.get(9).Product_Name, Menu.get(9).Price);
        if (!s.equals("full"))
        {
            added.setText(s);
            PauseTransition pause= new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> added.setText(""));
            pause.play();
        }
    }
    @FXML
    private Button submit;
    public void confirmOrder(ActionEvent e) throws Exception//add item to order
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConfirmOrder.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        ConfirmOrderController a=fxmlLoader.getController();

        a.Receive(Order);

        Scene current=submit.getScene();
        Scene confirm=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)submit.getScene().getWindow();
        currentStage.setScene(confirm);
    }

    public void giveFeedback(ActionEvent E) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Feedback.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        FeedbackController a=fxmlLoader.getController();
        a.Receive(Order.cusEmail, Order.Restaurant);

        Scene current=feedback.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)feedback.getScene().getWindow();
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

    @FXML private Button order;
    @FXML
    private Button back;
    public void placeOrder(ActionEvent e) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchRestaurant.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        SearchRestaurantController a=fxmlLoader.getController();
        a.Receive(Order.cusEmail);

        Scene current=order.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)order.getScene().getWindow();
        currentStage.setScene(search);
    }

    @FXML private Button status;
    public void checkStatus(ActionEvent e) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderStatus.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        OrderStatusController a=fxmlLoader.getController();
        a.Receive(Order.cusEmail);

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
        a.Receive(Order.cusEmail);

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
        a.Receive(Order.cusEmail);

        Scene current=notif.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)notif.getScene().getWindow();
        currentStage.setScene(search);
    }



}
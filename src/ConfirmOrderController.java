import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;



import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ConfirmOrderController {

    int count=0;


    private tempOrder Order;
    @FXML
    private Label i1, i2, i3, i4,i5, q1, q2, q3, q4, q5;
    @FXML
    private RadioButton cod, cc;
    @FXML
    private Label total;
    private boolean applied=false;
    @FXML
    private TextField promo;
    @FXML
    private TextField com;
    @FXML
    private Button submit;
    public ObservableList<items> R;
    public void Receive(tempOrder O) throws SQLException {
        Order=O;
        if (Order.I.size()==1)
        {
            i1.setText(Order.I.get(0).name);
            q1.setText(Integer.toString(Order.I.get(0).quantity));
        }
        else if (Order.I.size()==2)
        {
            i1.setText(Order.I.get(0).name);
            q1.setText(Integer.toString(Order.I.get(0).quantity));
            i2.setText(Order.I.get(1).name);
            q2.setText(Integer.toString(Order.I.get(1).quantity));
        }
        else if (Order.I.size()==3)
        {
            i1.setText(Order.I.get(0).name);
            q1.setText(Integer.toString(Order.I.get(0).quantity));
            i2.setText(Order.I.get(1).name);
            q2.setText(Integer.toString(Order.I.get(1).quantity));
            i3.setText(Order.I.get(2).name);
            q3.setText(Integer.toString(Order.I.get(2).quantity));

        }
        else if (Order.I.size()==4)
        {
            i1.setText(Order.I.get(0).name);
            q1.setText(Integer.toString(Order.I.get(0).quantity));
            i2.setText(Order.I.get(1).name);
            q2.setText(Integer.toString(Order.I.get(1).quantity));
            i3.setText(Order.I.get(2).name);
            q3.setText(Integer.toString(Order.I.get(2).quantity));
            i4.setText(Order.I.get(3).name);
            q4.setText(Integer.toString(Order.I.get(3).quantity));

        }
        else if (Order.I.size()==5)
        {
            i1.setText(Order.I.get(0).name);
            q1.setText(Integer.toString(Order.I.get(0).quantity));
            i2.setText(Order.I.get(1).name);
            q2.setText(Integer.toString(Order.I.get(1).quantity));
            i3.setText(Order.I.get(2).name);
            q3.setText(Integer.toString(Order.I.get(2).quantity));
            i4.setText(Order.I.get(3).name);
            q4.setText(Integer.toString(Order.I.get(3).quantity));
            i5.setText(Order.I.get(4).name);
            q5.setText(Integer.toString(Order.I.get(4).quantity));

        }
        //uptil now Order.total was only price of items now we are also calculating and adding tax
        Order.tax= 0.07*Order.total;
        Order.total=Order.tax+Order.total;

        total.setText("Rs"+Double.toString(Order.total));

        SalesManager S= new SalesManager();
        int b=S.getCustomerBlockStatus(Order.cusEmail);
        if (b==1)
        { submit.setDisable(true);}


    }
    @FXML
    private Button apply;
    public void applyPromo(ActionEvent e) throws SQLException {//this function called when apply promo  pressed
    String tempPromo=promo.getText();

    //call function here to check if valid or not and get discount amount
        Customer C= new Customer();
        double dis=C.getPromoDiscount(Order.cusEmail, tempPromo);

    //if valid save to Order.Promo
        if (dis > 0)
        {
            Order.Promo=tempPromo;
            //apply discount and update Order.total and total Label.
            Order.total= Order.total - (dis/100.0)*Order.total;

            total.setText("Rs"+Double.toString(Order.total));
            applied=true;
        }
        else
        {
            msg.setText("Invalid Promo Code");
        }


    }
    @FXML
    private Label msg;
    public void sendOrder(ActionEvent e) throws Exception{ // this function when confirm pressed
        Order.Comments=com.getText(); //save comments by user
        String tempPromo=promo.getText();
        Customer C= new Customer();
        double dis=C.getPromoDiscount(Order.cusEmail, tempPromo);

        //if valid save to Order.Promo
        if (dis > 0) {
            Order.Promo = tempPromo;
            if (applied==false)
                Order.total = Order.total - (dis / 100.0) * Order.total;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date delivDate = new Date();
        sdf.format(delivDate);
        java.sql.Date sqldelivDate = new java.sql.Date(delivDate.getTime()); //get current date
        Order.date=sqldelivDate;

        if (cod.isSelected() && cc.isSelected())
        {
            msg.setText("Choose one payment option");
        }
        else if (!cod.isSelected() && !cc.isSelected())
        {
            msg.setText("Choose a payment option");
        }
        else if (cod.isSelected())//call Restaurant SignIn
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RestaurantSignIn.fxml"));
            Parent root1=(Parent)fxmlLoader.load();

            RestaurantSignInController Rest=fxmlLoader.getController();
            Rest.Receive(Order);

            Stage stage=new Stage();
            stage.setScene(new Scene(root1, 325, 525));
            stage.show();

            msg.setText("Order Placed!Enjoy!");
        }
        else if (cc.isSelected())//call PaymentAuthorization
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PaymentAuth.fxml"));
            Parent root1=(Parent)fxmlLoader.load();

            PaymentAuthController Pay=fxmlLoader.getController();
            Pay.Receive(Order);

            Stage stage=new Stage();
            stage.setScene(new Scene(root1, 325, 525));
            stage.show();

            msg.setText("Order Placed! Wait for a notification if payment approved or try again!");
        }




    }
    @FXML
    private Button logout;
    @FXML
    private Button back;

    public void LogOut(ActionEvent e)throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        Scene current = logout.getScene();
        Scene Signin = new Scene(root, 325, 525);
        Stage currentStage = (Stage) logout.getScene().getWindow();
        currentStage.setScene(Signin);

    }
    @FXML private Button order;
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

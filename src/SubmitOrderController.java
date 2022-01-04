import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.w3c.dom.Text;

import javax.swing.*;
import java.sql.*;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class SubmitOrderController {

    private tempOrder Order;

    @FXML
    private Label i1, i2, i3, i4,i5, q1, q2, q3, q4, q5;
    @FXML
    private RadioButton cod, cc;
    @FXML
    private Label total;
    @FXML
    private TextField del;
    @FXML
    private TextField rider;
    @FXML
    private Label add;
    @FXML
    private Tooltip tadd;

    public void Receive(tempOrder O) throws SQLException
    {
        Order=O;
        Customer C=new Customer();
        add.setText(C.getCustomerAddress(Order.cusEmail));
        tadd.setText(C.getCustomerAddress(Order.cusEmail));


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



        Order Or= new Order();
        Or.updateCustomerPoints((int)Order.total, C.getCustomerID(Order.cusEmail));
        total.setText("Rs"+Double.toString(Order.total));



    }
    @FXML
    private Label msg;
    public void addDelivery(ActionEvent e){
        if (del.getText()==null || del.getText().equals(""))
        {
            msg.setText("Enter delivery charges first");
        }
        else
        {
            Order.deliverycharges = Double.parseDouble(del.getText());
            Order.total = Order.total + Order.deliverycharges; //update order total
            total.setText("Rs." + Order.total); //show updated total
        }
    }
    public void submitOrder(ActionEvent e) throws SQLException{
        if (rider.getText()==null || rider.getText().equals(""))
        {
            msg.setText("Enter the email of assigned rider");
        }
        else
        {
            Rider r= new Rider();
            int ra=r.getRiderNo(rider.getText());
            if (ra==-1)
            {msg.setText("Wrong rider entered");}
            else {
                Order.Rider = r.getRiderNo(rider.getText());

                Order.status = "Preparing";

                Order toSubmit = new Order();
                toSubmit.insertOrder(Order.Restaurant, Order.cusEmail, Order.Rider, Order.date, Order.total, Order.deliverycharges, Order.tax, Order.Promo, Order.status, Order.Comments);


                if (Order.I.size() == 1) {
                    toSubmit.insertToLineItems(Order.Restaurant, Order.cusEmail, Order.I.get(0).name, "", "", "", "", Order.date, Order.I.get(0).quantity, 0, 0, 0, 0);
                    msg.setText("Order Entered");
                } else if (Order.I.size() == 2) {
                    toSubmit.insertToLineItems(Order.Restaurant, Order.cusEmail, Order.I.get(0).name, Order.I.get(1).name, "", "", "", Order.date, Order.I.get(0).quantity, Order.I.get(1).quantity, 0, 0, 0);
                    msg.setText("Order Entered");
                } else if (Order.I.size() == 3) {
                    toSubmit.insertToLineItems(Order.Restaurant, Order.cusEmail, Order.I.get(0).name, Order.I.get(1).name, Order.I.get(2).name, "", "", Order.date, Order.I.get(0).quantity, Order.I.get(1).quantity, Order.I.get(2).quantity, 0, 0);
                    msg.setText("Order Entered");
                } else if (Order.I.size() == 4) {
                    toSubmit.insertToLineItems(Order.Restaurant, Order.cusEmail, Order.I.get(0).name, Order.I.get(1).name, Order.I.get(2).name, Order.I.get(3).name, "", Order.date, Order.I.get(0).quantity, Order.I.get(1).quantity, Order.I.get(2).quantity, Order.I.get(3).quantity, 0);
                    msg.setText("Order Entered");
                } else if (Order.I.size() == 5) {
                    toSubmit.insertToLineItems(Order.Restaurant, Order.cusEmail, Order.I.get(0).name, Order.I.get(1).name, Order.I.get(2).name, Order.I.get(3).name, Order.I.get(4).name, Order.date, Order.I.get(0).quantity, Order.I.get(1).quantity, Order.I.get(2).quantity, Order.I.get(3).quantity, Order.I.get(4).quantity);
                    msg.setText("Order Entered");
                }
            }


        }

    }

}

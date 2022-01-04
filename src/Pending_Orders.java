import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.SQLException;

public class Pending_Orders {
    SimpleIntegerProperty Order_Id;
    SimpleStringProperty Status;
    SimpleIntegerProperty Restaurant;
    SimpleStringProperty date;
    SimpleDoubleProperty total;



    public Pending_Orders(int o,String s, int R, String d, double t) throws SQLException {
        this.Order_Id=new SimpleIntegerProperty(o);
        this.Status= new SimpleStringProperty(s);
        this.Restaurant= new SimpleIntegerProperty(R);
        this.date= new SimpleStringProperty(d);
        this.total= new SimpleDoubleProperty(t);


    }




    public int getOrder_Id() {
        return Order_Id.get();
    }

    public void setOrder_Id(int order_Id) {
        Order_Id = new SimpleIntegerProperty(order_Id);
    }

    public String getStatus() {
        return Status.get();
    }

    public void setStatus(String status) {
        Status = new SimpleStringProperty(status);
    }

    public int getRestaurant() {
        return Restaurant.get();
    }

    public void setRestaurant(int restaurant) {
        Restaurant = new SimpleIntegerProperty(restaurant);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);
    }

    public double getTotal() {
        return total.get();
    }

    public void setTotal(double total) {
        this.total = new SimpleDoubleProperty(total);
    }
}

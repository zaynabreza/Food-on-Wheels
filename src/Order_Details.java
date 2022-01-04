import java.util.Date;

public class Order_Details {
    int OrderID;
    Double Total_Charges;
    Date dateOfOrder;

    public Order_Details(int anInt, double aDouble, java.sql.Date date) {
        this.OrderID=anInt;
        this.Total_Charges=aDouble;
        this.dateOfOrder=date;
    }


}

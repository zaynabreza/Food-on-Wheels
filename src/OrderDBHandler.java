import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public interface OrderDBHandler {
    void insertOrder(Connection con, int RestaurantID, int CustomerID, int RiderID,Date dateOfOrder,
                     Double Total, Double DeliveryCharges, Double Tax, String PromoCode, String Status, String Comments) throws SQLException;

    void insertToLineItems(Connection con, int RestaurantID, int CustomerID, String Item1, String Item2, String Item3, String Item4, String Item5,
                           Date DateOfDelivery, int qty1, int qty2, int qty3, int qty4, int qty5) throws SQLException;

    ArrayList<Order_Line_Items_List> getLineItems(Connection con, int CustomerID) throws SQLException;

    ArrayList<Pending_Orders> getOrderStatus(Connection con, int CustomerID) throws SQLException;

    boolean cancelOrder(Connection con, int OrderID)throws SQLException;

    void updateCustomerPoints(Connection con, int pointsToAdd, int CustomerID) throws SQLException;

    int getCustID(Connection con, int OrderID) throws SQLException;

}
interface RestaurantAndRiderOrderDBHandler{
    boolean updateOrderStatus(Connection con,String newStatus, int OrderID) throws SQLException;
}
interface RestaurantOrderDBHandler extends RestaurantAndRiderOrderDBHandler{
    ArrayList<Order_Details> getOrderDetailsForRestaurant(Connection con,int RestaurantID) throws SQLException;
}
interface RiderOrderDBHandler extends RestaurantAndRiderOrderDBHandler{
    ArrayList<Order_Details> getOrderDetailsForRider(Connection con,int RiderID) throws SQLException;
}

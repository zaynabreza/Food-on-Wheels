import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;

public interface RestaurantDBHandler {
    int getRestaurantID(Connection con,String Restaurant_Name) throws SQLException;

    String getRestaurantName(Connection con, int Rest_ID ) throws SQLException;
    int getRestaurantIDViaEmail(Connection con, String Email) throws SQLException;
    ArrayList<Menu_List> getRestaurantMenu(Connection con,String Restaurant_Name)throws SQLException;
    ArrayList<Deals_List> getRestaurantDeals(Connection con, String Restaurant_Name) throws SQLException;
    void editRestaurantDetails(Connection con, String Restaurant_Name,String password, String Address) throws SQLException;
    void editRestaurantMenu(Connection con,String Restaurant_Name,String ItemName,Double ItemPrice, Boolean isAvail) throws SQLException;
    void removeFromMenu(Connection con, String Restaurant_Name, String ItemName) throws SQLException; //Note for this have to clear the respective availability and price for the item too
    String addToMenu(Connection con, String Restaurant_Name, String newItem, Double newPrice, Boolean isAvail) throws SQLException;//Note for this find the last available null column and insert this in the next 3 cols
    String addToDeals(Connection con, String Restaurant_Name, String newItem, Double newPrice) throws SQLException;//Note for this find the last available null column and insert this in the next 3 cols
    String getRestaurantPassword(Connection con, String Email) throws  SQLException;
    ArrayList<String> getRestaurantlist(Connection con)throws SQLException;
    public ArrayList<String> ViewReviews(Connection con,String rname) throws SQLException;
    public void OpenCloseRestaurant(Connection con,String rname,Boolean open) throws SQLException;
    public String getRestaurantEmailViaName(Connection con, String Name) throws SQLException;
}
interface ManagerRestaurantDBHandler extends RestaurantDBHandler{
    void Insert_Restaurant(Connection con,String rname, String email, String password, String CNIC, String docx,int ApprovedBy, String Address ) throws SQLException;
}

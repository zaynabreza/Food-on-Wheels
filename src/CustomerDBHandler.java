import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

interface RiderCustomerDBHandler{
    String getCustAddress(Connection con, String Email) throws SQLException;
    String getCustomerPhoneNo(Connection con,String Email) throws SQLException;
    void blockCustomer(Connection con, String Email) throws SQLException;
    String getCustomerEmail(Connection con, int CustID) throws SQLException;
}
public interface CustomerDBHandler {
    void addNewCustomer(Connection con,String fname,String lname,String password, Date dob, String phone_no, long credit_card_no,String Email,Boolean RecvNotif,String Address) throws SQLException;
    void editCustomerInfo(Connection con, String Email,String newPassword, String newPhoneNo, String newAddress) throws SQLException;
    int getCustomerID(Connection con, String Email) throws SQLException;
    String getCustomerEmail(Connection con, int CustID) throws SQLException;
    int getCustomerPoints(Connection con, String Email) throws SQLException;
    String getCustomerPassword(Connection con,String Email) throws SQLException;
    String getCustomerAddress(Connection con, String Email) throws SQLException;
    ArrayList<String> ViewNotifications(Connection con, String email) throws SQLException;
    void TurnNotificationOFF(Connection con,String Email)throws SQLException;
   void TurnNotificationON(Connection con,String Email)throws SQLException;
    ArrayList<Integer> getCustomersWithNotifON(Connection con)throws SQLException;
    public int getPromoDiscount(Connection con,String Email,String code)throws SQLException ;
    public void LeaveReview(Connection con,String review,String Restname,String email) throws SQLException;
}


interface ManagerCustomerDBHandler extends CustomerDBHandler,RiderCustomerDBHandler{
    int getCustomerBlockStatus(Connection con,String Email) throws SQLException;
    void updateCustomerPoints(Connection con,String Email, int NewPoints) throws SQLException;
    void removeCustomer(Connection con, String Email) throws SQLException;

}




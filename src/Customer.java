import java.sql.*;
import java.util.ArrayList;

public class Customer extends Users implements Observer {
    private CustomerDBHandler C1=new CustomerDB();
    private DBConnect DBConnection=DBConnect.getInstance();
    public void addNewCustomer(String fname, String lname, String password, Date dob, String phone_no, long credit_card_no, String email, Boolean RecvNotif, String Address) throws SQLException {
        C1.addNewCustomer(DBConnection.con,fname,lname, password,dob,phone_no,credit_card_no, email,  RecvNotif,  Address);
    }
    public String getCustomerEmail(int CustID) throws SQLException
    {
        return (C1.getCustomerEmail(DBConnection.con,CustID));
    }
    public boolean getNotifStatus(String Email) throws Exception
    {
        Integer Cid=C1.getCustomerID(DBConnection.con, Email);
        ArrayList<Integer> A=C1.getCustomersWithNotifON(DBConnection.con);
        boolean on=false;
        for (int i=0; i<A.size(); i++)
        {
            System.out.println("Customer id is "+Cid);
            System.out.println("Subscriber id is "+ A.get(i));
            if (A.get(i)==Cid)
                on=true;
        }

        return on;

    }
    public void editCustomerInfo(String Email, String newPassword, String newPhoneNo, String newAddress) throws SQLException {
        C1.editCustomerInfo(DBConnection.con,Email,newPassword,newPhoneNo,newAddress);
    }
    public int getCustomerID(String Email) throws SQLException {
        return C1.getCustomerID(DBConnection.con,Email);
    }
    public int getCustomerPoints(String Email) throws SQLException {
        return C1.getCustomerPoints(DBConnection.con,Email);
    }
    public String getCustomerPassword( String Email) throws SQLException {
        return C1.getCustomerPassword(DBConnection.con,Email);
    }
    public String getCustomerAddress(String Email) throws SQLException{
        return C1.getCustomerAddress(DBConnection.con,Email);
    }

    public Customer() throws SQLException {
        System.out.println("New customer");
    }

    @Override
    public String SignInValidation(String Email, String Password) throws SQLException {
        String status;
        CustomerDBHandler C1=new CustomerDB();
        String DBPassword=C1.getCustomerPassword(DBConnection.con,Email);
        if(Password.equals(DBPassword)){
            System.out.println("Sign in successful");
            status="Success";
        }
        else{
            System.out.println("Email or Password Incorrect");
            status="Email or Password Incorrect";
        }
        return status;
    }

    @Override
    public ArrayList<String> ViewAllNotifications(String email) throws SQLException {
        return C1.ViewNotifications(DBConnection.con,email);

    }
    public void TurnOnNotification(String Email) throws SQLException {//  for customer who turned on notification
        Notification notif=new Notification();
        System.out.println("Notifications on ");
        notif.register(this,Email);

    }
    public void TurnOffNotification(String Email) throws SQLException {//  for customer who turned on notification
        Notification notif=new Notification();
        System.out.println("Notifications off :( ");
        notif.unregister(this,Email);

    }
    public Customer(String email) throws SQLException {
        System.out.println("New customer");
    }
    public int getPromoDiscount(String Email,String code) throws SQLException {
        return C1.getPromoDiscount(DBConnection.con,Email,code);
    }
    public void LeaveReview(String review,String Restname,String email) throws SQLException
    {
        C1.LeaveReview(DBConnection.con, review,Restname,email);
    }
}

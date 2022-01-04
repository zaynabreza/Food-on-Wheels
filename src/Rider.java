import java.sql.SQLException;
import java.util.ArrayList;

class Rider extends Users{
    DBConnect DBConnection = null; //New Connection
    {
        try {
            DBConnection = DBConnect.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private RiderDBHandler R1=new RiderDB();
    private RiderCustomerDBHandler C2 = new CustomerDB();
    @Override
    public String SignInValidation(String Email, String Password) throws SQLException {
        String status;
        String DBPassword=R1.getRiderPassword(DBConnection.con,Email);
        if(Password.equals(DBPassword)){
            System.out.println("Sign in successful");
            status="Success";
        }
        else{
            System.out.println("Email or Password Incorrect");
            status="Email or Password incorrect";
        }
        return status;
    }

    public int getRiderNo(String Email)throws SQLException{
        return R1.getRiderID(DBConnection.con,Email);
    }
    public void editRiderInfo(String Email,String ContactNo,String Password,String Vehicle) throws SQLException{
        R1.editRiderInfo(DBConnection.con,Email,ContactNo,Password,Vehicle);
    }
    public String getCustomerEmail(int CustID) throws SQLException
    {
        return (C2.getCustomerEmail(DBConnection.con,CustID));
    }

    public String getCustomerAddress( String Email) throws SQLException {
        return C2.getCustAddress(DBConnection.con,Email);
    }
    public String getCustomerPhoneNumber(String Email) throws SQLException{
        return C2.getCustomerPhoneNo(DBConnection.con,Email);
    }

    public void blockCustomer(String Email) throws Exception{
        C2.blockCustomer(DBConnection.con,Email);
    }

    public ArrayList<Order_Details> getOrderDetails(int RiderID)throws SQLException{
        RiderOrderDBHandler R2= (RiderOrderDBHandler) new OrderDB();
        return R2.getOrderDetailsForRider(DBConnection.con,RiderID);
    }

    public Rider() throws SQLException {
    }
}
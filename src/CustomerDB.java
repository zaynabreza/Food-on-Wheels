import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDB implements ManagerCustomerDBHandler,CustomerDBHandler,RiderCustomerDBHandler {

    @Override
    public void addNewCustomer(Connection con, String fname, String lname,String password, Date dob, String phone_no, long credit_card_no,String email,Boolean RecvNotif,String Address) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Sql Query to insert elements into the Customer table
        String sql = " INSERT INTO `customer` (`FName`,`LName`,`Password`,`DOB`,`Phone Number`,`Credit Card No`,`Block Status`,`Points`,`Email`,`RecvNotif`,`Address`)"
                +" values (?,?,?,?,?,?,?,?,?,?,?)";

        //Making the prepared statements to INSERT into the table
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1,fname);
        preparedStmt.setString(2,lname);
        preparedStmt.setString(3,password);
        preparedStmt.setDate(4,dob);
        preparedStmt.setString(5,phone_no);
        preparedStmt.setLong(6,credit_card_no);
        preparedStmt.setInt(7,0);
        preparedStmt.setInt(8,0);
        preparedStmt.setString(9,email);
        preparedStmt.setBoolean(10,RecvNotif);
        preparedStmt.setString(11,Address);
        preparedStmt.execute(); //execute the insert command
        //con.close();    //close the connection
    }

    @Override
    public String getCustAddress(Connection con, String Email) throws SQLException {
        CustomerDBHandler C = new CustomerDB();
        int CustId=C.getCustomerID(con,Email);
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select `Address` from `customer` where `CustomerID` = ?");
        preparedStmt.setInt(1,CustId);
        ResultSet rs = preparedStmt.executeQuery();
        String Customer_Address="";
        while (rs.next()){      //Get the Customer ID from the Customer table
            Customer_Address=rs.getString(1);
        }
        return Customer_Address;
    }
    @Override
    public String getCustomerEmail(Connection con, int CustID) throws SQLException
    {

        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select `Email` from `customer` where `CustomerID` = ?");
        preparedStmt.setInt(1,CustID);
        ResultSet rs = preparedStmt.executeQuery();
        String email="";
        while (rs.next()){      //Get the Customer ID from the Customer table
            email=rs.getString(1);
        }

        return email;
    }
    @Override
    public String getCustomerPhoneNo(Connection con, String Email) throws SQLException {
        int CustId=getCustomerID(con,Email);
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select `Phone Number` from `customer` where `CustomerID` = ?");
        preparedStmt.setInt(1,CustId);
        ResultSet rs = preparedStmt.executeQuery();
        String PhoneNum="";
        while (rs.next()){      //Get the Customer ID from the Customer table
            PhoneNum=rs.getString(1);
        }
        return PhoneNum;
    }

    @Override
    public void editCustomerInfo(Connection con,String Email, String newPassword, String newPhoneNo, String newAddress) throws SQLException {
        int CustId = getCustomerID(con, Email);
        //Create a new Prepared Statement
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Case: If a new Password was provided
        if(newPassword!=null && !newPassword.equals("")){
            String sql="update `customer` set `Password`='" + newPassword + "' where `CustomerID`=" + Integer.toString(CustId);
            stat.executeUpdate(sql);
        }
        //Case: If a new Phone Number was provided
        if(newPhoneNo!=null && !newPhoneNo.equals("")){
            String sql="update `customer` set `Phone Number`='" + newPhoneNo + "' where `CustomerID`=" + Integer.toString(CustId);
            stat.executeUpdate(sql);
        }
        //Case: If a new Address was provided
        if(newAddress!=null && !newAddress.equals("")){
            String sql="update `customer` set `Address`='" + newAddress + "' where `CustomerID`=" + Integer.toString(CustId);
            stat.executeUpdate(sql);
        }

    }
    @Override
    public int getCustomerID(Connection con, String Email) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select `CustomerID` from `customer` where `Email` = ?");
        preparedStmt.setString(1,Email);
        ResultSet rs = preparedStmt.executeQuery();
        int cust_id=-1;
        while (rs.next()){      //Get the Customer ID from the Customer table
            cust_id=rs.getInt(1);
            System.out.println(cust_id);
        }
        return cust_id;
    }

    @Override
    public int getCustomerBlockStatus(Connection con,String Email) throws SQLException {
        int cust_id=getCustomerID(con,Email); //Fetched the Customer ID using the Email

        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "select `Block Status` from `customer` where `CustomerID` = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,cust_id);
        ResultSet rs = preparedStmt.executeQuery();

        int Block_Status=-1;    //Initializing the Block Status Variable
        while (rs.next()){
            Block_Status=rs.getInt(1); //Get Block Status of the Customer
        }
        return Block_Status;
    }

    @Override
    public int getCustomerPoints(Connection con, String Email) throws SQLException {
        int cust_id=getCustomerID(con,Email); //Fetched the Customer ID using the Email
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "Select `Points` from `customer` where `CustomerID` = ? ";
        //Making the prepared statements to fetch from the table
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,cust_id);
        ResultSet rs = preparedStmt.executeQuery();
        int points=-1;
        while (rs.next()){      //Get the Points from the Customer table
            points=rs.getInt(1);
        }
        return points;
    }

    @Override
    public String getCustomerPassword(Connection con, String Email) throws SQLException {
        int cust_id=getCustomerID(con,Email); //Fetched the Customer ID using the Email

        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "select `Password` from `customer` where `CustomerID` = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,cust_id);
        ResultSet rs = preparedStmt.executeQuery();

        String Password="";
        while (rs.next()){
            Password=rs.getString(1); //Get Block Status of the Customer
        }
        return Password;
    }

    @Override
    public void updateCustomerPoints(Connection con, String Email, int NewPoints) throws SQLException{
        int CustId=getCustomerID(con,Email);
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //UPDATE SQL Query
        String sql = "UPDATE `customer` set `Points`= ? where `CustomerID` = ? ";
        //Making the prepared statements to UPDATE into the table
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,NewPoints);
        preparedStmt.setInt(2,CustId);
        preparedStmt.executeUpdate();
    }

    @Override
    public void removeCustomer(Connection con, String Email) throws SQLException{
        int CustId=getCustomerID(con,Email);
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "DELETE from `customer` where `CustomerID` = ? ";
        //Making the prepared statements to DELETE a row from a table
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,CustId);
        preparedStmt.execute();
    }

    @Override
    public String getCustomerAddress(Connection con, String Email) throws SQLException {
        CustomerDBHandler C = new CustomerDB();
        int CustId=C.getCustomerID(con,Email);
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select `Address` from `customer` where `CustomerID` = ?");
        preparedStmt.setInt(1,CustId);
        ResultSet rs = preparedStmt.executeQuery();
        String Customer_Address="";
        while (rs.next()){      //Get the Customer ID from the Customer table
            Customer_Address=rs.getString(1);
        }
        return Customer_Address;

    }



    @Override
    public void blockCustomer(Connection con, String Email) throws SQLException {
        CustomerDBHandler C = new CustomerDB();
        int CustId=C.getCustomerID(con,Email);
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql ="UPDATE `customer` SET `Block Status` = 1 where `CustomerID` = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,CustId);
        preparedStmt.executeUpdate();
    }



    public void TurnNotificationON(Connection con,String Email) throws SQLException {//  for customer who turned on notification
        int CustId=getCustomerID(con,Email);
        String sql ="UPDATE `customer` SET `RecvNotif` = 1 where `CustomerID` = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,CustId);
        preparedStmt.executeUpdate();


    }
    public void TurnNotificationOFF(Connection con,String Email) throws SQLException {//  for customer who turned on notification
        int CustId=getCustomerID(con,Email);
        String sql ="UPDATE `customer` SET `RecvNotif` = 0 where `CustomerID` = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,CustId);
        preparedStmt.executeUpdate();


    }

    public ArrayList<String> ViewNotifications(Connection con, String email) throws SQLException {
        int CustId=getCustomerID(con,email);
        ArrayList<String> desc = new ArrayList<String>();

        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "select * from `notifications` INNER JOIN `notify_customer` ON notifications.NotifID =notify_customer.NotifID and notify_customer.CustID="+CustId;

        ResultSet rs = stat.executeQuery(sql);

        while (rs.next()) {

            String str = rs.getString("Description");
            desc.add(str);

        }
        return desc;

    }
    public ArrayList<Integer> getCustomersWithNotifON(Connection con) throws SQLException {

        ArrayList<Integer> custIds = new ArrayList<Integer>();

        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "select * from `customer` where `RecvNotif` = 1";
        ResultSet rs = stat.executeQuery(sql);

        while (rs.next()) {

            int id = rs.getInt("CustomerID");
            custIds.add(id);

        }
        return custIds;
    }
    public int getPromoDiscount(Connection con,String Email,String code) throws SQLException {
        int CustId=getCustomerID(con,Email);

        int perc = 0;

        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "select * from `notifications` where `PromoCode`='"+code+"'";

        ResultSet rs = stat.executeQuery(sql);

        while (rs.next()) {
            int p = rs.getInt("Percentage");
            perc=p;

        }
        return perc;

    }
    public void LeaveReview(Connection con,String review,String Restname,String email) throws SQLException {
        int CustId=getCustomerID(con,email);
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sql = "select * from `restaurant` where Restaurant_Name='"+Restname+"'";

        ResultSet rs = stat.executeQuery(sql);
        int RestaurantID = 0;
        while (rs.next()) {

            RestaurantID= rs.getInt("RestaurantID");


        }
        /////////////////////////////

        int max = 0;
        PreparedStatement preparedStmt = con.prepareStatement("select * from `feedback`");
         rs = preparedStmt.executeQuery();
        int sq = -1;
        while (rs.next()) {
            sq = rs.getInt(1);
            if (sq > max) {

                max = sq;
            }


        }
        max++;


        ///////////////////////////
        //Sql Query to insert elements into the Customer table
        sql = " INSERT INTO `feedback` (`FeedbackNo`,`CustomerID`,`RestaurantID`,`FeedbackDescp`)"
                +" values (?,?,?,?)";

        //Making the prepared statements to INSERT into the table
        preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,max);
        preparedStmt.setInt(2,CustId);
        preparedStmt.setInt(3,RestaurantID);
        preparedStmt.setString(4,review);
        preparedStmt.execute(); //execute the insert command

    }

}

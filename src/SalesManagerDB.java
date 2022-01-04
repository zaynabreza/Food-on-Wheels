import java.sql.*;

public class SalesManagerDB {

    public int getSalesManagerId(Connection con, String Email) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select `MgrID` from `salesmanager` where `Email` = ?");
        preparedStmt.setString(1, Email);
        ResultSet rs = preparedStmt.executeQuery();
        int mgr_id = -1;
        while (rs.next()) {      //Get the Restaurant ID from the Restaurant table
            mgr_id = rs.getInt(1);
        }
        return mgr_id;
    }

    public void editSalesManagerInfo(Connection con, String Email, String newPassword, String newPhoneNo) throws SQLException {
        int mgr_id = getSalesManagerId(con, Email);
        Statement stmt = null;
        stmt = (Statement) con.createStatement();
        //Case: Both have to be changed
        if (newPassword != null && newPhoneNo != null) {
            //Sql Query to UPDATE elements in the Customer table
            String sql2 = "UPDATE `salesmanager` set `Password`= ?,`ContactNo`= ? where `MgrID` = ? ";
            //Making the prepared statements to UPDATE into the table
            PreparedStatement preparedStmt2 = con.prepareStatement(sql2);
            preparedStmt2.setString(1, newPassword);
            preparedStmt2.setString(2, newPhoneNo);
            preparedStmt2.setInt(3, mgr_id);
            preparedStmt2.executeUpdate();
        }
        //Case: Only password changes
        else if (newPassword != null && newPhoneNo == null) {
            //Sql Query to UPDATE elements in the Customer table
            String sql2 = "UPDATE `salesmanager` set `Password`= ? where `MgrID` = ? ";
            //Making the prepared statements to UPDATE into the table
            PreparedStatement preparedStmt2 = con.prepareStatement(sql2);
            preparedStmt2.setString(1, newPassword);
            preparedStmt2.setInt(2, mgr_id);
            preparedStmt2.executeUpdate();
        }
        //Case: Update only the Phone number
        else if (newPassword == null && newPhoneNo != null) {
            //Sql Query to UPDATE elements in the Customer table
            String sql2 = "UPDATE `salesmanager` set `ContactNo`= ? where `MgrID` = ? ";
            //Making the prepared statements to UPDATE into the table
            PreparedStatement preparedStmt2 = con.prepareStatement(sql2);
            preparedStmt2.setString(1, newPhoneNo);
            preparedStmt2.setInt(2, mgr_id);
            preparedStmt2.executeUpdate();
        }
    }

    public String getManagerPassword(Connection con, String Email) throws SQLException {
        int mgr_id = getSalesManagerId(con, Email);
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "select `Password` from `salesmanager` where `MgrID` = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, mgr_id);
        ResultSet rs = preparedStmt.executeQuery();

        String Password = "";
        while (rs.next()) {
            Password = rs.getString(1); //Get Block Status of the Customer
        }
        return Password;
    }

    public int storeNotifications(Connection con, String desc, int percentage, String code) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ////////////////////


        int max = 0;
        PreparedStatement preparedStmt = con.prepareStatement("select * from `notifications`");
        ResultSet rs = preparedStmt.executeQuery();
        int sq = -1;
        while (rs.next()) {
            sq = rs.getInt(1);
            if (sq > max) {

                max = sq;
            }


        }
        max++;

        ///////////////////
        //Sql Query to insert elements into the Restaurant table
        String sql = " INSERT INTO `notifications` (`NotifID`,`Description`,`Percentage`,`PromoCode`)" + " values (?,?,?,?)";

        //Making the prepared statements to INSERT into the table
        preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, max);
        preparedStmt.setString(2, desc);
        preparedStmt.setInt(3, percentage);
        preparedStmt.setString(4, code);


        preparedStmt.execute(); //execute the insert command
        return max;

    }

    public void NotifyCustomer(Connection con, int notifid, int custid) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Sql Query to insert elements into the Restaurant table
        String sql = " INSERT INTO `notify_customer` (`NotifID`,`CustID`)" + " values (?,?)";

        //Making the prepared statements to INSERT into the table
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, notifid);
        preparedStmt.setInt(2, custid);


        preparedStmt.execute(); //execute the insert command


    }
}

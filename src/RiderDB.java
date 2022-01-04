import java.sql.*;

public class RiderDB implements RiderDBHandler,ManagerRiderDBHandler{

    @Override
    public int getRiderID(Connection con, String Email) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select `RiderID` from `riders` where `Email` = ?");
        preparedStmt.setString(1,Email);
        ResultSet rs = preparedStmt.executeQuery();
        int rider_id=-1;
        while (rs.next()){      //Get the Restaurant ID from the Restaurant table
            rider_id=rs.getInt(1);
        }
        return rider_id;
    }

    @Override
    public void editRiderInfo(Connection con, String Email, String ContactNo, String Password, String Vehicle) throws SQLException {
        int rider_id=getRiderID(con,Email);
        Statement stmt = null;
        stmt = (Statement) con.createStatement();
        if(Password!=null && !Password.equals("")) {
            String query1 = "update `riders` set `Password`='" + Password + "' where `RiderID`=" + Integer.toString(rider_id);
            stmt.executeUpdate(query1);
        }
        if(ContactNo!=null && !ContactNo.equals("")) {
            String query1 = "update `riders` set `ContactNo`='" + ContactNo + "' where `RiderID`=" + Integer.toString(rider_id);
            stmt.executeUpdate(query1);
        }
        if(Vehicle!=null && !Vehicle.equals("")){
            String query1 = "update `riders` set `Vehicle`='" + Vehicle + "' where `RiderID`=" + Integer.toString(rider_id);
            stmt.executeUpdate(query1);
        }

    }

    @Override
    public String getRiderPassword(Connection con, String Email) throws SQLException {
        int r_id=getRiderID(con,Email);
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "select `Password` from `riders` where `RiderID` = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,r_id);
        ResultSet rs = preparedStmt.executeQuery();

        String Password="";
        while (rs.next()){
            Password=rs.getString(1); //Get Block Status of the Customer
        }
        return Password;
    }


    @Override
    public void Insert_Rider(Connection con, String FName, String LName, String Email, String ContactNo, String Vehicle, String CNIC, String Gender, String Password, int Mgr_id) throws SQLException{
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Sql Query to insert elements into the Restaurant table
        String sql = " INSERT INTO `riders` (`FName`,`LName`,`Email`,`ContactNo`,`Vehicle`,`CNIC`,`Gender`,`Password`,`ApprovedByMgr`)"
                +" values (?,?,?,?,?,?,?,?,?)";

        //Making the prepared statements to INSERT into the table
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1,FName);
        preparedStmt.setString(2,LName);
        preparedStmt.setString(3,Email);
        preparedStmt.setString(4,ContactNo);
        preparedStmt.setString(5,Vehicle);
        preparedStmt.setString(6,CNIC);
        preparedStmt.setString(7,Gender);
        preparedStmt.setString(8,Password);
        preparedStmt.setInt(9,Mgr_id);

        preparedStmt.execute(); //execute the insert command

    }
}

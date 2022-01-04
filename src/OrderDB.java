import javax.sound.sampled.Line;
import java.sql.*;
import java.util.ArrayList;

public class OrderDB implements OrderDBHandler,RestaurantOrderDBHandler,RiderOrderDBHandler{

    @Override
    public void insertOrder(Connection con, int RestaurantID, int CustomerID, int RiderID, Date dateOfOrder, Double Total, Double DeliveryCharges, Double Tax, String PromoCode, String Status, String Comments) throws  SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Sql Query to insert elements into the Customer table
        String sql = " INSERT INTO `orders` (`CustID`,`RestID`,`RidID`,`Total Cost`,`Date`,`Delivery Charges`,`Tax`,`Promo Code`,`Status`,`Comments`)"
                +" values (?,?,?,?,?,?,?,?,?,?)";

        //Making the prepared statements to INSERT into the table
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,CustomerID);
        preparedStmt.setInt(2,RestaurantID);
        preparedStmt.setInt(3,RiderID);
        preparedStmt.setDouble(4,Total);
        preparedStmt.setDate(5,dateOfOrder);
        preparedStmt.setDouble(6,DeliveryCharges);
        preparedStmt.setDouble(7,Tax);
        preparedStmt.setString(8,PromoCode);
        preparedStmt.setString(9,Status);
        preparedStmt.setString(10,Comments);
        preparedStmt.execute(); //execute the insert command
    }

    @Override
    public void insertToLineItems(Connection con, int RestaurantID, int CustomerID, String Item1, String Item2, String Item3,
                                  String Item4, String Item5, Date DateOfDelivery, int qty1, int qty2, int qty3, int qty4,
                                  int qty5) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Sql Query to insert elements into the Customer table
        String sql = " INSERT INTO `lineitems` (`CustID`,`RestID`,`Item1`,`Item2`,`Item3`,`Item4`,`Item5`," +
                "`DateofDelivery`,`Quantity1`,`Quantity2`,`Quantity3`,`Quantity4`,`Quantity5`)"
                +" values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //Making the prepared statements to INSERT into the table
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,CustomerID);
        preparedStmt.setInt(2,RestaurantID);
        preparedStmt.setString(3,Item1);
        preparedStmt.setString(4,Item2);
        preparedStmt.setString(5,Item3);
        preparedStmt.setString(6,Item4);
        preparedStmt.setString(7,Item5);
        preparedStmt.setDate(8,DateOfDelivery);
        preparedStmt.setInt(9,qty1);
        preparedStmt.setInt(10,qty2);
        preparedStmt.setInt(11,qty3);
        preparedStmt.setInt(12,qty4);
        preparedStmt.setInt(13,qty5);
        preparedStmt.execute(); //execute the insert command
    }

    @Override
    public ArrayList<Order_Line_Items_List> getLineItems(Connection con, int CustomerID) throws SQLException {

        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = " Select `Item1`,`Item2`,`Item3`,`Item4`,`Item5`,`Quantity1`,`Quantity2`,`Quantity3`,`Quantity4`,`Quantity5`,`RestID` " +
                "from `lineitems` where `CustID` =?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,CustomerID);
        ResultSet rs = preparedStmt.executeQuery();


        int row=0;
        int MAX_ITEMS=5;
        ArrayList<Order_Line_Items_List> Line = new ArrayList<Order_Line_Items_List>();
        while(rs.next()) {
            // LineItemsList.add(Line);
            ArrayList<String> Item = new ArrayList<String>();
            ArrayList<Integer> Quantity = new ArrayList<Integer>();
            int Rest_ID;
            for(int i=0;i<MAX_ITEMS;i++){
                Item.add(rs.getString(i+1));
                Quantity.add(rs.getInt(i+6));
            }
            Rest_ID=rs.getInt(11); //11th Col will return the Rest_ID
            Line.add(new Order_Line_Items_List(Item,Quantity,Rest_ID));
            row++;
        }

        return Line;
    }
    @Override
    public ArrayList<Pending_Orders> getOrderStatus(Connection con, int CustomerID) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql ="Select `OrderID`,`Status`, `RestID`, `Date`, `Total Cost` from `orders` where `CustID`=?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,CustomerID);
        ResultSet rs = preparedStmt.executeQuery();

        ArrayList<Pending_Orders> P1 = new ArrayList<Pending_Orders>();
        while(rs.next()){
            P1.add(new Pending_Orders(rs.getInt(1), rs.getString(2), (int) rs.getDouble(3), rs.getString(4), rs.getDouble(5)));
        }
        return P1;
    }

    @Override
    public boolean cancelOrder(Connection con, int OrderID) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        String sql ="UPDATE `orders` SET `Status` = 'Cancelled' where `OrderID` = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,OrderID);
        preparedStmt.executeUpdate();
        return true;
    }
    @Override
    public int getCustID(Connection con, int OrderID) throws SQLException{
        Statement stat=null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
        String sql ="Select `CustID` from `orders` where `OrderID` = ?";
        int custid=-1;
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,OrderID);
        ResultSet rs = preparedStmt.executeQuery();
        while(rs.next())
        {
            custid=rs.getInt(1);
        }

        return custid;


    }

    @Override
    public void updateCustomerPoints(Connection con, int pointsToAdd, int CustomerID) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "UPDATE `customer` SET `Points` = `Points` + ? where CustomerID=?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,pointsToAdd);
        preparedStmt.setInt(2,CustomerID);
        preparedStmt.executeUpdate();
    }
	
	@Override
    public boolean updateOrderStatus(Connection con, String Status, int OrderID) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        String sql ="UPDATE `orders` SET `Status` = '" +Status+ "' where `OrderID` = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,OrderID);
        preparedStmt.executeUpdate();
        return true;
    }


    @Override
    public ArrayList<Order_Details> getOrderDetailsForRestaurant(Connection con, int RestaurantID) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql= "Select `OrderID`, `Total Cost`,`Date` from `orders` where `Status` LIKE 'Preparing' and `RestID` =?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,RestaurantID);
        ResultSet rs = preparedStmt.executeQuery();

        ArrayList<Order_Details> Order_detail= new ArrayList<Order_Details>();
        while(rs.next()){
            Order_detail.add(new Order_Details(rs.getInt(1), rs.getDouble(2), rs.getDate(3)));
        }
        return Order_detail;
    }

    @Override
    public ArrayList<Order_Details> getOrderDetailsForRider(Connection con, int RiderID) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql= "Select `OrderID`, `Total Cost`,`Date` from `orders` where `Status` LIKE 'En route' and `RidID` =?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,RiderID);
        ResultSet rs = preparedStmt.executeQuery();

        ArrayList<Order_Details> Order_detail= new ArrayList<Order_Details>();
        while(rs.next()){
            Order_detail.add(new Order_Details(rs.getInt(1), rs.getDouble(2), rs.getDate(3)));
        }
        return Order_detail;
    }
}

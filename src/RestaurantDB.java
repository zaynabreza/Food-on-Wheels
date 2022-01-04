import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

public class RestaurantDB implements RestaurantDBHandler, ManagerRestaurantDBHandler{
    ArrayList<String>Restaurants;

    @Override
    public void Insert_Restaurant(Connection con,String rname, String email, String password, String CNIC, String docx,int ApprovedBy, String Address) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Sql Query to insert elements into the Restaurant table
        String sql = " INSERT INTO `restaurant` (`Restaurant_Name`,`Email`,`Password`,`CNIC`,`Documents`,`ApprovedByMgr`,`Address`,`Open`)"
                +" values (?,?,?,?,?,?,?,?)";

        //Making the prepared statements to INSERT into the table
        boolean open=true;
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1,rname);
        preparedStmt.setString(2,email);
        preparedStmt.setString(3,password);
        preparedStmt.setString(4,CNIC);
        preparedStmt.setString(5,docx);
        preparedStmt.setInt(6,ApprovedBy);
        preparedStmt.setString(7,Address);
        preparedStmt.setBoolean(8,open);

        preparedStmt.execute(); //execute the insert command
        // con.close();    //close the connection
    }

    @Override
    public int getRestaurantID(Connection con, String Restaurant_Name) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select `RestaurantID` from `restaurant` where `Restaurant_Name` = ?");
        preparedStmt.setString(1,Restaurant_Name);
        ResultSet rs = preparedStmt.executeQuery();
        int rest_id=-1;
        while (rs.next()){      //Get the Restaurant ID from the Restaurant table
            rest_id=rs.getInt(1);
            System.out.println(rest_id);
        }
        return rest_id;
    }

    @Override
    public String getRestaurantName(Connection con, int Rest_ID) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select `Restaurant_Name` from `restaurant` where `RestaurantID` = ?");
        preparedStmt.setInt(1,Rest_ID);
        ResultSet rs = preparedStmt.executeQuery();
        String rest_name="";
        while (rs.next()){      //Get the Restaurant ID from the Restaurant table
            rest_name=rs.getString(1);
        }
        return rest_name;
    }

    @Override
    public int getRestaurantIDViaEmail(Connection con, String Email) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select `RestaurantID` from `restaurant` where `Email` = ?");
        preparedStmt.setString(1,Email);
        ResultSet rs = preparedStmt.executeQuery();
        int rest_id=-1;
        while (rs.next()){      //Get the Restaurant ID from the Restaurant table
            rest_id=rs.getInt(1);
            System.out.println(rest_id);
        }
        return rest_id;
    }

    @Override
    public ArrayList<Menu_List> getRestaurantMenu(Connection con,String Restaurant_Name) throws SQLException {

        ArrayList<Menu_List> Menu= new ArrayList<Menu_List>();      //Array List of type Menu
        int rest_id=getRestaurantID(con,Restaurant_Name);           //Get the Restaurant ID using the Restaurant Name
        //Create a new Prepared Statement
        Statement stat2 = null;
        try {
            stat2 = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PreparedStatement preparedStmt2 = con.prepareStatement("select * from `menulist` where `RestID` = ?");
        preparedStmt2.setInt(1,rest_id);
        ResultSet rs2 = preparedStmt2.executeQuery();

        while(rs2.next()){  //Add to the Menu List (Max 10 Items in the DB)
            int a=2; int b=3; int c=4;
            for(int i=0; i<10 ; i++){
                Menu.add(new Menu_List(rs2.getString(a), rs2.getDouble(b),rs2.getBoolean(c)));
                a+=3;b+=3;c+=3;
            }
        }
        return Menu;    //return the Menu fetched from the DB
    }

    @Override
    public ArrayList<Deals_List> getRestaurantDeals(Connection con, String Restaurant_Name) throws SQLException {
        ArrayList<Deals_List> Deals= new ArrayList<Deals_List>();   //Create an Array List of Deals
        int rest_id=getRestaurantID(con,Restaurant_Name);           //Get the Restaurant ID using the Restaurant Name
        //Create a new Prepared Statement
        Statement stat2 = null;
        try {
            stat2 = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PreparedStatement preparedStmt2 = con.prepareStatement("select * from `menulist` where `RestID` = ?");
        preparedStmt2.setInt(1,rest_id);
        ResultSet rs2 = preparedStmt2.executeQuery();

        while(rs2.next()){
            Deals.add(new Deals_List(rs2.getString(32), rs2.getDouble(33)));
            Deals.add(new Deals_List(rs2.getString(34), rs2.getDouble(35)));
        }
        return Deals;
    }

    @Override
    public void editRestaurantDetails(Connection con, String Restaurant_Name, String password, String Address) throws SQLException{
        int rest_id=getRestaurantID(con,Restaurant_Name);
        System.out.println(rest_id);
        System.out.println(password);
        Statement stmt = null;
        stmt = (Statement) con.createStatement();
        if(password!=null && !password.equals("")) {
            String query1 = "update restaurant set Password='" + password + "' where RestaurantID=" + Integer.toString(rest_id);
            stmt.executeUpdate(query1);
            System.out.println("password updated");
        }
        if(Address!=null && !Address.equals("")) {
            String query1 = "update restaurant set Address='" + Address + "' where RestaurantID=" + Integer.toString(rest_id);
            stmt.executeUpdate(query1);
            System.out.println("Address updated");
        }

    }
    public int searchProduct(Connection con, int rest_id, String ItemName) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select * from `menulist` where `RestID` = ?");
        preparedStmt.setInt(1,rest_id);
        ResultSet rs = preparedStmt.executeQuery();
        int count=0;
        while (rs.next()){      //Get the Restaurant ID from the Restaurant table
            String Prod_1=rs.getString(2);
            count++;

            if(Prod_1!=null )
                if(Prod_1.equals(ItemName))
                {
                    return count;
                }
            String Prod_2=rs.getString(5);
            count++;

            if(Prod_2!=null )
                if(Prod_2.equals(ItemName))
                {
                    return count;
                }
            String Prod_3=rs.getString(8);
            count++;

            if(Prod_3!=null )
                if(Prod_3.equals(ItemName))
                {
                    return count;
                }
            String Prod_4=rs.getString(11);
            count++;
            if(Prod_4!=null )
                if(Prod_4.equals(ItemName))
                {
                    return count;
                }
            String Prod_5=rs.getString(14);
            count++;
            if(Prod_5!=null )
                if(Prod_5.equals(ItemName))
                {
                    return count;
                }

            String Prod_6=rs.getString(17);
            count++;
            if(Prod_6!=null )
                if(Prod_6.equals(ItemName))
                {
                    return count;
                }
            String Prod_7=rs.getString(20);
            count++;
            if(Prod_7!=null )
                if(Prod_7.equals(ItemName))
                {
                    return count;
                }
            String Prod_8=rs.getString(23);

            count++;
            if(Prod_8!=null )
                if(Prod_8.equals(ItemName))
                {

                    return count;
                }

            String Prod_9=rs.getString(26);

            count++;
            if(Prod_9!=null )
                if(Prod_9.equals(ItemName))
                {

                    return count;
                }
            String Prod_10=rs.getString(29);
            count++;
            if(Prod_10!=null )
                if(Prod_10.equals(ItemName))
                {
                    return count;
                }


        }

        return -1;
    }
    public int searchDeals(Connection con, int rest_id, String ItemName) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select * from `menulist` where `RestID` = ?");
        preparedStmt.setInt(1,rest_id);
        ResultSet rs = preparedStmt.executeQuery();
        int count=0;
        while (rs.next()) {      //Get the Restaurant ID from the Restaurant table
            String deal_1=rs.getString(32);
            count++;

            if(deal_1!=null )
                if(deal_1.equals(ItemName))
                {
                    return count;
                }

            String deal_2=rs.getString(34);
            count++;

            if(deal_2!=null )
                if(deal_2.equals(ItemName))
                {
                    return count;
                }

        }
        return -1;

    }
    @Override
    public void editRestaurantMenu(Connection con, String Restaurant_Name, String ItemName, Double ItemPrice, Boolean isAvail) throws SQLException {
        int rest_id = getRestaurantID(con, Restaurant_Name);
        int col = searchProduct(con, rest_id, ItemName);
        System.out.println("the col is= "+col);
        if(col>0) {
            Statement stmt = null;
            stmt = (Statement) con.createStatement();


            if (ItemPrice != null) {
                String query1 = "update menulist set " + "Price_" + Integer.toString(col) + "=" + ItemPrice + " where RestID="+Integer.toString(rest_id);
                stmt.executeUpdate(query1);
            }
            int val = (isAvail) ? 1 : 0;

            String query1 = "update menulist set " + "Availability_" + Integer.toString(col) + "=" + val + " where RestID="+Integer.toString(rest_id);
            stmt.executeUpdate(query1);

            System.out.println("Record has been updated!!");
        }
        col = searchDeals(con, rest_id, ItemName);
        if(col>0) {
            Statement stmt = null;
            stmt = (Statement) con.createStatement();


            if (ItemPrice != null) {
                String query1 = "update menulist set " + "Deal_Price_" + Integer.toString(col) + "=" + ItemPrice + " where RestID="+Integer.toString(rest_id);
                stmt.executeUpdate(query1);
            }


            System.out.println("DEAL Record has been updated!!");
        }

    }

    @Override
    public void removeFromMenu(Connection con, String Restaurant_Name, String ItemName) throws SQLException {
        int rest_id = getRestaurantID(con, Restaurant_Name);
        int col = searchProduct(con, rest_id, ItemName);
        System.out.println("the col is= "+col);
        if(col>0) {
            Statement stmt = null;
            stmt = (Statement) con.createStatement();


            String query1 = "update menulist set " + "Prod_"+Integer.toString(col)+"=NULL where RestID="+Integer.toString(rest_id);
            stmt.executeUpdate(query1);
            query1 = "update menulist set " + "Price_" + Integer.toString(col) + "=NULL where RestID="+Integer.toString(rest_id);
            stmt.executeUpdate(query1);



            query1 = "update menulist set " + "Availability_" + Integer.toString(col) + "=0 where RestID="+Integer.toString(rest_id);
            stmt.executeUpdate(query1);

            System.out.println("item deleted from menu!!");
        }
        col = searchDeals(con, rest_id, ItemName);
        if(col>0) {
            Statement stmt = null;
            stmt = (Statement) con.createStatement();


            String query1 = "update menulist set " + "Deal_"+Integer.toString(col)+"=NULL where RestID="+Integer.toString(rest_id);
            stmt.executeUpdate(query1);
            query1 = "update menulist set " + "Deal_Price_" + Integer.toString(col) + "=NULL where RestID="+Integer.toString(rest_id);
            stmt.executeUpdate(query1);


            System.out.println("DEAL deleted!!");
        }

    }
    public int searchNulls(Connection con, int rest_id) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select * from `menulist` where `RestID` = ?");
        preparedStmt.setInt(1,rest_id);
        ResultSet rs = preparedStmt.executeQuery();
        int count=0;
        while (rs.next()){      //Get the Restaurant ID from the Restaurant table
            String Prod_1=rs.getString(2);
            count++;

            if(Prod_1==null )
            {
                return count;
            }
            String Prod_2=rs.getString(5);
            count++;

            if(Prod_2==null )
            {
                return count;
            }
            String Prod_3=rs.getString(8);
            count++;

            if(Prod_3==null )
            {
                return count;
            }
            String Prod_4=rs.getString(11);
            count++;
            if(Prod_4==null )
            {
                return count;
            }
            String Prod_5=rs.getString(14);
            count++;
            if(Prod_5==null )
            {
                return count;
            }

            String Prod_6=rs.getString(17);
            count++;
            if(Prod_6==null )

            {
                return count;
            }
            String Prod_7=rs.getString(20);
            count++;
            if(Prod_7==null )

            {
                return count;
            }
            String Prod_8=rs.getString(23);

            count++;
            if(Prod_8==null )

            {

                return count;
            }

            String Prod_9=rs.getString(26);

            count++;
            if(Prod_9==null )

            {

                return count;
            }
            String Prod_10=rs.getString(29);
            count++;
            if(Prod_10==null )

            {
                return count;
            }


        }

        return -1;
    }
    public int searchNullsDeals(Connection con, int rest_id) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStmt = con.prepareStatement("select * from `menulist` where `RestID` = ?");
        preparedStmt.setInt(1, rest_id);
        ResultSet rs = preparedStmt.executeQuery();
        int count = 0;
        while (rs.next()) {      //Get the Restaurant ID from the Restaurant table
            String Prod_1 = rs.getString(32);
            count++;

            if (Prod_1 == null) {
                return count;
            }
            String Prod_2 = rs.getString(34);
            count++;

            if (Prod_2 == null) {
                return count;
            }
        }
        return -1;
    }
    public String addToDeals(Connection con, String Restaurant_Name, String newItem, Double newPrice) throws SQLException {
        int rest_id = getRestaurantID(con, Restaurant_Name);
        int col = searchNullsDeals(con, rest_id);
        System.out.println("the col is= " + col);
        String status;
        if (col > 0) {
            Statement stmt = null;
            stmt = (Statement) con.createStatement();


            String query1 = "update menulist set " + "Deal_" + Integer.toString(col) + "='"+newItem+ "' where RestID=" + Integer.toString(rest_id);
            stmt.executeUpdate(query1);
            query1 = "update menulist set " + "Deal_Price_" + Integer.toString(col) + "=" + newPrice + " where RestID="+Integer.toString(rest_id);
            stmt.executeUpdate(query1);


            status="Added";
            System.out.println("Deal has been added!!");
        }
        else{
            status="Menu Full";
            System.out.println("no more items can be added");
        }

        return status;
    }

    @Override
    public String getRestaurantPassword(Connection con, String Email) throws SQLException {
        int rest_id=getRestaurantIDViaEmail(con,Email);
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "select `Password` from `restaurant` where `RestaurantID` = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,rest_id);
        ResultSet rs = preparedStmt.executeQuery();

        String Password="";
        while (rs.next()){
            Password=rs.getString(1); //Get Block Status of the Customer
        }
        return Password;
    }

    @Override
    public String addToMenu(Connection con, String Restaurant_Name, String newItem, Double newPrice, Boolean isAvail) throws SQLException {
        int rest_id = getRestaurantID(con, Restaurant_Name);
        int col = searchNulls(con, rest_id);
        System.out.println("the col is= " + col);
        String status;
        if (col > 0) {
            Statement stmt = null;
            stmt = (Statement) con.createStatement();


            String query1 = "update menulist set " + "Prod_" + Integer.toString(col) + "='"+newItem+ "' where RestID=" + Integer.toString(rest_id);
            stmt.executeUpdate(query1);
            query1 = "update menulist set " + "Price_" + Integer.toString(col) + "=" + newPrice + " where RestID="+Integer.toString(rest_id);
            stmt.executeUpdate(query1);

            int val = (isAvail) ? 1 : 0;

            query1 = "update menulist set " + "Availability_" + Integer.toString(col) + "=" + val + " where RestID="+Integer.toString(rest_id);
            stmt.executeUpdate(query1);

            System.out.println("Record has been updated!!");
            status="Added";
        }
        else {
            System.out.println("no more product items can be added");
            status="Menu Full";
        }
        return status;
    }


    public ArrayList<String> getRestaurantlist(Connection con) throws SQLException {
        Restaurants= new ArrayList<String>();

        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "select * from restaurant where Open=1";
        ResultSet rs = stat.executeQuery(sql);

        while (rs.next()){

            String restaurant_name = rs.getString("Restaurant_Name");
            Restaurants.add(restaurant_name);

        }
        return Restaurants;
    }
    public ArrayList<String> ViewReviews(Connection con,String rname) throws SQLException {
        int rest_id=getRestaurantID(con,rname);
        ArrayList<String> desc = new ArrayList<String>();

        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "select * from `feedback` where RestaurantID="+rest_id;

        ResultSet rs = stat.executeQuery(sql);

        while (rs.next()) {

            String str = rs.getString("FeedbackDescp");
            desc.add(str);

        }
        return desc;
    }
    public void OpenCloseRestaurant(Connection con,String rname,Boolean open) throws SQLException
    {
        int rest_id=getRestaurantID(con,rname);
        Statement stmt = null;
        stmt = (Statement) con.createStatement();

            String query1 = "update restaurant set Open="+open+ " where RestaurantID=" + Integer.toString(rest_id);
            stmt.executeUpdate(query1);
            

    }

    @Override
    public String getRestaurantEmailViaName(Connection con, String Name) throws SQLException {
        Statement stat = null;
        try {
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "select `Email` from `restaurant` where Restaurant_Name= '" +Name+ "'";

        ResultSet rs = stat.executeQuery(sql);
        String Email="";
        while (rs.next()) {
            Email= rs.getString("Email");
        }
        return Email;
    }
}


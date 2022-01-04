import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

class Restaurant extends Users{

    DBConnect DBConnection; //New Connection

    {
        try {
            DBConnection = DBConnect.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private RestaurantDBHandler R1=new RestaurantDB();

    public int getRestaurantIDViaEmail(String Email) throws SQLException
    {
        return R1.getRestaurantIDViaEmail(DBConnection.con,Email);
    }
    public String getRestaurantName(int Rest_ID) throws SQLException {
        String name=R1.getRestaurantName(DBConnection.con,Rest_ID) ;
        System.out.println(name);
        return name;
    }
    public int getRestaurantID(String Restaurant_Name) throws SQLException {
        int id= R1.getRestaurantID (DBConnection.con, Restaurant_Name);
        return id;
    }
    public ArrayList<Menu_List> getRestaurantMenu(String Restaurant_Name) throws SQLException {
        ArrayList<Menu_List> mn = R1.getRestaurantMenu(DBConnection.con, Restaurant_Name);
        return mn;
    }
    public ArrayList<Deals_List> getRestaurantDeals(String Restaurant_Name) throws SQLException {

        ArrayList<Deals_List> dl = R1.getRestaurantDeals(DBConnection.con, Restaurant_Name);
        return dl;
    }

    public void editRestaurantMenu(String Restaurant_Name,String ItemName, Double ItemPrice, Boolean isAvail) throws SQLException
    {
        R1.editRestaurantMenu(DBConnection.con,Restaurant_Name,ItemName,ItemPrice,isAvail);

    }
    public void removeFromMenu(String Restaurant_Name,String ItemName) throws SQLException
    {
        R1.removeFromMenu(DBConnection.con,Restaurant_Name,ItemName);

    }
    public String addToDeals(String Restaurant_Name,String newItem, Double newPrice) throws SQLException //this func to add only deals
    {
        return R1.addToDeals(DBConnection.con,Restaurant_Name,newItem,newPrice);

    }
    public String addToMenu(String Restaurant_Name,String newItem, Double newPrice, Boolean isAvail) throws SQLException //this func to add only the products
    {
        return R1.addToMenu(DBConnection.con,Restaurant_Name,newItem,newPrice,isAvail);

    }
    public ArrayList<String> getRestaurantslist() throws SQLException
    {
        ArrayList<String> arr=null;
        arr= R1.getRestaurantlist(DBConnection.con);
        return arr;
    }

    public ArrayList<Order_Details> getOrderDetails(int RestaurantID)throws SQLException{
        RestaurantOrderDBHandler R2 = (RestaurantOrderDBHandler) new OrderDB();
        return R2.getOrderDetailsForRestaurant(DBConnection.con,RestaurantID);
    }

    @Override
    public String SignInValidation(String Email, String Password) throws SQLException {
        String status;
        System.out.println("In restaurant Sign up");
        RestaurantDBHandler R1=new RestaurantDB();
        String DBPassword=R1.getRestaurantPassword(DBConnection.con,Email);
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
    public ArrayList<String> ViewReviews(String rname) throws SQLException {
        return R1.ViewReviews(DBConnection.con,rname);

    }
    public void OpenCloseRestaurant(String rname,Boolean open) throws SQLException {
        R1.OpenCloseRestaurant(DBConnection.con,rname,open);

    }
    public String getRestaurantEmailViaName(String Name) throws SQLException{
        return R1.getRestaurantEmailViaName(DBConnection.con, Name);
    }
}

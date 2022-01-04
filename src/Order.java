import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Order {
    private OrderDBHandler O1=new OrderDB();
    private DBConnect DBConnection=DBConnect.getInstance();

    public Order() throws SQLException {
    }

    public int getCustID(int OrderID) throws SQLException
    {
        return O1.getCustID(DBConnection.con,OrderID);
    }

    public void insertOrder(String Restaurant, String CustomerEmail, int RiderID, Date dateOfOrder, Double Total, Double DeliveryCharges, Double Tax, String PromoCode, String Status, String Comments) throws SQLException {
        Restaurant Res= new Restaurant();
        int RestaurantID= Res.getRestaurantID(Restaurant);
        Customer Cus= new Customer();
        int CustomerID= Cus.getCustomerID(CustomerEmail);
        O1.insertOrder(DBConnection.con,RestaurantID,CustomerID,RiderID,dateOfOrder,Total,DeliveryCharges,Tax,PromoCode,Status,Comments);
    }
    public void insertToLineItems(String Restaurant, String CustomerEmail, String Item1, String Item2, String Item3,
                                  String Item4, String Item5, Date DateOfDelivery, int qty1, int qty2, int qty3, int qty4,
                                  int qty5) throws SQLException {
        Restaurant Res= new Restaurant();
        int RestaurantID= Res.getRestaurantID(Restaurant);
        Customer Cus= new Customer();
        int CustomerID= Cus.getCustomerID(CustomerEmail);
        O1.insertToLineItems(DBConnection.con,RestaurantID,CustomerID, Item1,Item2, Item3,Item4,Item5,DateOfDelivery,qty1,qty2, qty3, qty4,qty5);

    }
    public ArrayList<Order_Line_Items_List> getLineItems(int CustomerID) throws SQLException
    {
        int MAX_ITEMS=5;    //MAX Items that can be added to the LINE (For reorders)
        ArrayList<Order_Line_Items_List> Line = new ArrayList<Order_Line_Items_List>();     //Create an array of Order Line Items
        Line=O1.getLineItems(DBConnection.con,CustomerID);
        for(int i=0;i<Line.size();i++){
            ArrayList<Double> Price =new ArrayList<Double>() ;
            ArrayList<Menu_List> Restaurant_Menu2 = new ArrayList<Menu_List>();
            ArrayList<Deals_List> Restaurant_Deals2= new ArrayList<Deals_List>();
            Restaurant R2= new Restaurant();
            String R_Name=R2.getRestaurantName(Line.get(i).Rest_ID); //Get the name of the Rest via ID
            Restaurant_Menu2=R2.getRestaurantMenu(R_Name);
            Restaurant_Deals2=R2.getRestaurantDeals(R_Name);

            for(int j=0;j<MAX_ITEMS;j++){
                //Business Logic, match the Menu item to the Restaurant and add the price to the Line

                for(Menu_List M1: Restaurant_Menu2){
                    if (M1.Product_Name!=null && Line.get(i).Item.get(j)!=null && M1.Product_Name.equals(Line.get(i).Item.get(j))){
                        Price.add(M1.Price);
                    }
                }
                for (Deals_List M1: Restaurant_Deals2){
                    if (M1.Description!=null && Line.get(i).Item.get(j)!=null && M1.Description.equals(Line.get(i).Item.get(j))){
                        Price.add(M1.Price);
                    }
                }
                Line.get(i).Add_Price(Price);
            }
        }
        return Line;
    }

    public ArrayList<Pending_Orders> getOrderStatus(int CustomerID) throws SQLException{    //pass the CustomerID as a parameter
        return O1.getOrderStatus(DBConnection.con,CustomerID);

    }
    public boolean cancelOrder(int CustomerID, int OrderID) throws SQLException{      //Pass the CustomerID and the OrderID to be cancelled
        ArrayList<Pending_Orders> P1 = new ArrayList<Pending_Orders>();
        P1=O1.getOrderStatus(DBConnection.con,CustomerID);
        for(int i=0;i<P1.size();i++){       //Loop over the entire ArrayList of Pending Orders
            if(P1.get(i).getOrder_Id() == OrderID && P1.get(i).getStatus().equals("Preparing")){  //If the OrderID of PendingOrders matches the OrderID passed
                return O1.cancelOrder(DBConnection.con,OrderID);        //and Status = Preparing, only then allow cancel Order functionality
            }
        }
        return false;
    }

    public boolean updateOrderStatus(int OrderID, String newStatus)throws SQLException{ //Only allowed by Restaurants and Riders
        RestaurantAndRiderOrderDBHandler O2= (RestaurantAndRiderOrderDBHandler) new OrderDB();
        return O2.updateOrderStatus(DBConnection.con,newStatus,OrderID);
    }
	
	public void updateCustomerPoints(int OrderTotalAmount,int CustomerID) throws SQLException{
        OrderTotalAmount*=0.05; //5 % of the Order Amount is added to the Customer Points
        System.out.println("Total points are: " + OrderTotalAmount);
        O1.updateCustomerPoints(DBConnection.con,OrderTotalAmount,CustomerID);
    }
}

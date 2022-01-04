import com.mysql.cj.xdevapi.DbDoc;
import com.sun.javafx.image.impl.ByteIndexed;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class tempMain {
    public static void main(String[] args) throws SQLException {


        /*Functions Related to Restaurant*/
 /*       RestaurantDBHandler R1= new RestaurantDB(); //New DB Handler

        //Insert one created before. NOTE: there are 2 new parameters in insert. Approved by MGR the MGR Id and the Restaurant Address.
        ArrayList<Menu_List> Restaurant_Menu = new ArrayList<Menu_List>();  //Array List to Save the Menu of a single restaurant
        String res_name="Mcdonald"; //Name of the Restaurant to look up
        Restaurant_Menu=R1.getRestaurantMenu(DBConnection.con,res_name); //Function returns the Menu of the Restaurant (Not Including the deals)
        for(int i=0;i<Restaurant_Menu.size();++i){                         //Print Menu details for the specified Restaurant
            System.out.println("Product Name: " + Restaurant_Menu.get(i).Product_Name + " Price: " + Restaurant_Menu.get(i).Price + " Availability: "+ Restaurant_Menu.get(i).Availability);
        }

        //Get the deals available for the restaurant name provided
        ArrayList<Deals_List> Restaurant_Deals= new ArrayList<Deals_List>();    //Array which will contain at MAX 2 deals
        Restaurant_Deals= R1.getRestaurantDeals(DBConnection.con, res_name);
        for(int i=0;i<Restaurant_Deals.size();++i){
            System.out.println("Deal "+(i+1)+": " + Restaurant_Deals.get(i).Description +" Price: " + Restaurant_Deals.get(i).Price);
        }

  */
        /*
        @Hurriya Implement editRestaurantDetails,editRestaurantMenu,removeFromMenu,addToMenu in Restaurant DB Handler
            //Logic for edit Rest Menu : Find the col using the name (10if's), then acc to the one that matches,
            the next 2 cols (check the names they are consistent in the db) update the price and availability
         */
 /*       System.out.println("Restaurant clicked edit menu");
        R1.editRestaurantMenu(DBConnection.con,"Mcdonald","Fires Large",800.0 , true);
        R1.removeFromMenu(DBConnection.con,"Mcdonald","Fries Small");

        Restaurant_Menu=R1.getRestaurantMenu(DBConnection.con,res_name); //Function returns the Menu of the Restaurant (Not Including the deals)
        for(int i=0;i<Restaurant_Menu.size();++i){                         //Print Menu details for the specified Restaurant
            System.out.println("Product Name: " + Restaurant_Menu.get(i).Product_Name + " Price: " + Restaurant_Menu.get(i).Price + " Availability: "+ Restaurant_Menu.get(i).Availability);
        }



        R1.editRestaurantMenu(DBConnection.con,"Mcdonald","Hot Cakes",500.0 , true);
        Restaurant_Menu=R1.getRestaurantMenu(DBConnection.con,res_name); //Function returns the Menu of the Restaurant (Not Including the deals)
        for(int i=0;i<Restaurant_Menu.size();++i){                         //Print Menu details for the specified Restaurant
            System.out.println("Product Name: " + Restaurant_Menu.get(i).Product_Name + " Price: " + Restaurant_Menu.get(i).Price + " Availability: "+ Restaurant_Menu.get(i).Availability);
        }

        R1.addToMenu(DBConnection.con,"Mcdonald","Coffee",200.0 , true);
        Restaurant_Menu=R1.getRestaurantMenu(DBConnection.con,res_name); //Function returns the Menu of the Restaurant (Not Including the deals)
        for(int i=0;i<Restaurant_Menu.size();++i){                         //Print Menu details for the specified Restaurant
            System.out.println("Product Name: " + Restaurant_Menu.get(i).Product_Name + " Price: " + Restaurant_Menu.get(i).Price + " Availability: "+ Restaurant_Menu.get(i).Availability);
        }

        R1.removeFromMenu(DBConnection.con,"Mcdonald","Happy Meal");
        Restaurant_Deals= R1.getRestaurantDeals(DBConnection.con, res_name);
        for(int i=0;i<Restaurant_Deals.size();++i){
            System.out.println("Deal "+(i+1)+": " + Restaurant_Deals.get(i).Description +" Price: " + Restaurant_Deals.get(i).Price);
        }
        R1.addToDeals(DBConnection.con,"Mcdonald","Value meal",340.0);
        Restaurant_Deals= R1.getRestaurantDeals(DBConnection.con, res_name);
        for(int i=0;i<Restaurant_Deals.size();++i){
            System.out.println("Deal "+(i+1)+": " + Restaurant_Deals.get(i).Description +" Price: " + Restaurant_Deals.get(i).Price);
        }

        R1.editRestaurantDetails(DBConnection.con,"Nandos","nanDO13",null);
*/
        /*Functions Related to Customer*/
/*        CustomerDBHandler C1 = new CustomerDB();
        //Insert one was created before
        //NOTE: New parameter added in DB - Recv Notifications!!

        //Get CustId via Email
        String lookupEmail="hnasir2@gmail.com"; //Email required (Take Input from the User)
        int cust_id=C1.getCustomerID(DBConnection.con,lookupEmail);
        System.out.println("CustId recv is: " + cust_id);
        if(cust_id==-1){
            //Customer not found, re-enter the email address
            System.out.println("Re-enter the email Address!!");
        }

        //Edit Customer Information

        //Get the new password/cc no/phoneNo
        //Pass it to this function!!
        String newPassword=null;
        String newPhoneNo="0333-3333333";
        String newAddress=null;

        C1.editCustomerInfo(DBConnection.con, lookupEmail,newPassword, newPhoneNo,newAddress);
*/
        //Interface for the MANAGER!!
        //Get the Block Status of the customer given an email address
       // ManagerCustomerDBHandler C2 = new CustomerDB();
        SalesManager C2=new SalesManager();
        String searchemail="adil617@hotmail.com";
        int blk_status=C2.getCustomerBlockStatus(searchemail);
        System.out.printf("Block Status of Adil is: " + blk_status + "   " );

        //Update the Customer's Points
        searchemail="hnasir2@gmail.com";
        C2.updateCustomerPoints(searchemail,49);
        int points=C2.getCustomerPoints(searchemail);
        System.out.println("Points of Hurriya are: " + points);

        String delEmail="delete@gmail.com";
        C2.removeCustomer(delEmail);


        //Interface for the RIDER!!
       // RiderCustomerDBHandler Rider1 = new CustomerDB();// iska kya krun? ;-;
        Rider Rider1=new Rider();// so i did this here: rider class inhertited customer class
    //    String Cust_Addr=Rider1.getCustomerAddress(searchemail);
    //    System.out.printf("Customer Address is: " + Cust_Addr);


        //**************************************

        //PLACE AN ORDER
        //NOTE: To place an order insert in Order DB and in LineItems DB
    Customer C3=new Customer();
        String email="adil617@hotmail.com";
        Customer C4=new Customer();
        String email2="hnasir2@gmail.com";

        Customer C5=new Customer();
        String email3="rafay@gmail.com";


        C3.TurnOnNotification(email);//customer 1 turns on their notification

        SalesManager salesManager=new SalesManager();
        String msg="TGIF get 15% off on your order!! USE PROMO CODE:FOW015";
        int perc=15;
        String code="FOW015";
        Notification newNotification=new Notification();
        salesManager.GenerateNotification(newNotification,msg,perc,code);//sales manager sends a notification

        C3.ViewAllNotifications(email);//customer 1 views notification

        C3.TurnOffNotification(email);//customer 1 turns off their notification

        msg="Hello 2021!! get 21% off on your order!! USE PROMO CODE:FOW221";
        perc=21;
        code="FOW221";
        salesManager.GenerateNotification(newNotification,msg,perc,code);//sales manager sends another notification

        C3.TurnOnNotification(email);//customer 1 turns on their notification AGAIN

        msg="Eid Mubarak!!! get 30% off on your order!! USE PROMO CODE:FOW330";
        perc=30;
        code="FOW330";
        salesManager.GenerateNotification(newNotification,msg,perc,code);//sales manager sends another notification


        System.out.println("Adil will view his notifications!");
        ArrayList<String>  not1=C3.ViewAllNotifications(email);
        for(int i=0;i<not1.size();++i){
            System.out.println(not1.get(i));
        }
        System.out.println("Hurriya will view her notifications!");
        ArrayList<String>  not2=C4.ViewAllNotifications(email2);
        for(int i=0;i<not2.size();++i){
            System.out.println(not2.get(i));
        }

        System.out.println("Rafay will view his notifications!");
        ArrayList<String>  not3=C5.ViewAllNotifications(email3);
        for(int i=0;i<not3.size();++i){
            System.out.println(not3.get(i));
        }

        C3.LeaveReview("please improve your quality","Nandos",email);
        C5.LeaveReview("Came two hours later. Ridiculous","Mcdonald",email3);

        Restaurant r1 = new Restaurant();

        ArrayList<String>  rev= r1.ViewReviews("Nandos");//Nandos wants to see their reviews
        for(int i=0;i<rev.size();++i){
            System.out.println(rev.get(i));
        }

        r1.OpenCloseRestaurant("Hardees",false);//close restaurant
        ArrayList<String>  list1= r1.getRestaurantslist();//shouldnt be visible in the restaurant list
        for(int i=0;i<list1.size();++i){
            System.out.println(list1.get(i));
        }
        r1.OpenCloseRestaurant("Hardees",true);//open restaurant
      list1= r1.getRestaurantslist();//                     hardees should be visible in list now
        for(int i=0;i<list1.size();++i){
            System.out.println(list1.get(i));
        }
        }
}


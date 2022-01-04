//package com.company;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalesManager extends Users{
    DBConnect DBConnection = null; //New Connection
    {
        try {
            DBConnection = DBConnect.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private SalesManagerDB S1 = new SalesManagerDB();
    private ManagerRiderDBHandler R1 = new RiderDB();
    private ManagerCustomerDBHandler M1= new CustomerDB();
    private ManagerRestaurantDBHandler Manage_Restaurant = new RestaurantDB();


    //To manage information from the Customer Table.
    public int getCustomerBlockStatus(String Email) throws SQLException {
        return M1.getCustomerBlockStatus(DBConnection.con,Email);
    }
    public void updateCustomerPoints( String Email, int NewPoints) throws SQLException{
        M1.updateCustomerPoints(DBConnection.con,Email,NewPoints);

    }
    public void removeCustomer( String Email) throws SQLException {
        M1.removeCustomer(DBConnection.con,Email);
    }
    public int getCustomerPoints(String Email) throws SQLException {
        return M1.getCustomerPoints(DBConnection.con,Email);
    }
    public String getCustomerPassword( String Email) throws SQLException {
        return M1.getCustomerPassword(DBConnection.con,Email);
    }
    public String getCustomerAddress( String Email) throws SQLException {
        return M1.getCustomerAddress(DBConnection.con,Email);
    }
    public void blockCustomer(String Email) throws Exception{
        M1.blockCustomer(DBConnection.con,Email);
    }
    public void addNewCustomer(String fname, String lname, String password, Date dob, String phone_no, long credit_card_no, String email, Boolean RecvNotif, String Address) throws SQLException {
        M1.addNewCustomer(DBConnection.con,fname,lname, password,dob,phone_no,credit_card_no, email,  RecvNotif,  Address);
    }
    public void editCustomerInfo(String Email, String newPassword, String newPhoneNo, String newAddress) throws SQLException {
        M1.editCustomerInfo(DBConnection.con,Email,newPassword,newPhoneNo,newAddress);
    }
    public int getCustomerID(String Email) throws SQLException {
        return M1.getCustomerID(DBConnection.con,Email);
    }


    //To Manage a Restaurant from the Restaurant related Tables
    public void Create_Restaurant(String rname,String email,String password, String CNIC ,String docx, String ApprovedBy,String Address) throws SQLException {
        //Create a New Restaurant
        int managerID=S1.getSalesManagerId(DBConnection.con, ApprovedBy);
        Manage_Restaurant.Insert_Restaurant(DBConnection.con, rname, email, password, CNIC, docx,managerID,Address);
    }

    public String getRestaurantName(int Rest_ID) throws SQLException {
        String name=Manage_Restaurant.getRestaurantName(DBConnection.con,Rest_ID) ;
        return name;
    }
    public int getRestaurantID(String Restaurant_Name) throws SQLException {
        int id= Manage_Restaurant.getRestaurantID (DBConnection.con, Restaurant_Name);
        return id;
    }
    public ArrayList<Menu_List> getRestaurantMenu(String Restaurant_Name) throws SQLException {

        ArrayList<Menu_List> mn = Manage_Restaurant.getRestaurantMenu(DBConnection.con, Restaurant_Name);
        return mn;
    }
    public ArrayList<Deals_List> getRestaurantDeals(String Restaurant_Name) throws SQLException {

        ArrayList<Deals_List> dl = Manage_Restaurant.getRestaurantDeals(DBConnection.con, Restaurant_Name);
        return dl;
    }
    public void editRestaurantDetails(String Restaurant_Name,String password, String Address) throws SQLException
    {
        Manage_Restaurant.editRestaurantDetails (DBConnection.con, Restaurant_Name, password, Address);
    }

    public void editRestaurantMenu(String Restaurant_Name,String ItemName, Double ItemPrice, Boolean isAvail) throws SQLException
    {
        Manage_Restaurant.editRestaurantMenu(DBConnection.con,Restaurant_Name,ItemName,ItemPrice,isAvail);

    }
    public void removeFromMenu(String Restaurant_Name,String ItemName) throws SQLException
    {
        Manage_Restaurant.removeFromMenu(DBConnection.con,Restaurant_Name,ItemName);

    }
    public void addToDeals(String Restaurant_Name,String newItem, Double newPrice) throws SQLException //this func to add only deals
    {
        Manage_Restaurant.addToDeals(DBConnection.con,Restaurant_Name,newItem,newPrice);

    }
    public void addToMenu(String Restaurant_Name,String newItem, Double newPrice, Boolean isAvail) throws SQLException //this func to add only the products
    {
        Manage_Restaurant.addToMenu(DBConnection.con,Restaurant_Name,newItem,newPrice,isAvail);
    }
    public ArrayList<String> getRestaurantslist() throws SQLException
    {
        ArrayList<String> arr=null;
        arr= Manage_Restaurant.getRestaurantlist(DBConnection.con);
        return arr;
    }





    //Manage Rider Information
    public void Insert_Rider(String FName,String LName, String Email, String ContactNo, String Vehicle, String CNIC, String Gender, String Password, String ApprovedBy) throws SQLException{
        int Mgr_id=S1.getSalesManagerId(DBConnection.con, ApprovedBy);
        R1.Insert_Rider(DBConnection.con, FName,LName,Email,ContactNo,Vehicle,CNIC,Gender,Password,Mgr_id);
    }

    //Manage personal Information
    //Validate the email and password provided
    @Override
    public String SignInValidation(String Email, String Password) throws SQLException {
        String status;
        String DBPassword=S1.getManagerPassword(DBConnection.con,Email);
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
    public void editSalesManagerInfo(String Email,String newPassword, String newPhoneNo) throws SQLException{
        S1.editSalesManagerInfo(DBConnection.con, Email,newPassword,newPhoneNo);
    }
    public int getSalesManagerID(String Email)throws SQLException{
        return S1.getSalesManagerId(DBConnection.con, Email);
    }

    public void GenerateNotification(Notification newNotification,String msg,int perc,String code) throws SQLException {
        //manager enters the notification here using UI
        Notification newN = new Notification();
        newN.setNotification(msg,perc,code);


    }



}

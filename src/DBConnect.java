import java.sql.*;
public class DBConnect {    //class used to manipulate CustomerDB

    private static DBConnect DBConnection=null;

    public Connection con =null;
    private DBConnect() throws SQLException {
        try{
            String host = "jdbc:mysql://localhost:3306/fsm";
            String uName = "root";
            String uPass = "1234";
            con = DriverManager.getConnection(host, uName, uPass);
        }
        catch (SQLException err) {
            System.out.println(err.getMessage());
        }


    }
    public static DBConnect getInstance() throws SQLException {
        if(DBConnection==null)
        {
            DBConnection=new DBConnect();
        }
        return DBConnection;
    }


    /*public DBConnect(){    //Function to Establish the Connnection of the Database with Java
        try{
            String host = "jdbc:mysql://localhost:3306/fsm";
            String uName = "root";
            String uPass = "2345";
            con = DriverManager.getConnection(host, uName, uPass);
        }
        catch (SQLException err) {
            System.out.println(err.getMessage());
        }

   } */
}


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Notification implements subj{
    private ArrayList<Observer> observers;
    private String notificationMsg;
    private int perc;
    private String code;
    private String email;
    private SalesManagerDB S1 = new SalesManagerDB();
    private ManagerCustomerDBHandler M1= new CustomerDB();
    DBConnect DBConnection = null; //New Connection
    {
        try {
            DBConnection = DBConnect.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Notification()
    {
        observers = new ArrayList<Observer>();
    }

    public void setNotification(String msg,int perc,String code) throws SQLException {
        this.notificationMsg = msg;
        this.perc = perc;
        this.code= code;
       // this.email = email;
        notifyObserver(msg,perc,code);
    }

    @Override
    public void register(Observer c,String Email) throws SQLException {

        M1.TurnNotificationON(DBConnection.con,Email);
    }

    @Override
    public void unregister(Observer c,String Email) throws SQLException {

        M1.TurnNotificationOFF(DBConnection.con,Email);

    }

    @Override
    public void notifyObserver(String msg,int perc,String code) throws SQLException {
//        for(Observer observer : observers){
//                  observer.update(notificationMsg);
//        }

        ArrayList<Integer>customers=  M1.getCustomersWithNotifON(DBConnection.con);
        int notifid=S1.storeNotifications(DBConnection.con,msg,perc,code);
        for(int i=0;i<customers.size();++i){
            S1.NotifyCustomer(DBConnection.con,notifid,customers.get(i));

        }
    }
}

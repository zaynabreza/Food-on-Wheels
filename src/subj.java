import java.sql.SQLException;

public interface subj {
    public void register(Observer c,String email) throws SQLException;

    public void unregister(Observer c,String email) throws SQLException;

    public void notifyObserver(String msg,int perc,String code) throws SQLException;
}

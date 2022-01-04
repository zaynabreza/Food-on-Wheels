import java.sql.SQLException;
import java.util.ArrayList;

public interface Observer {
    public ArrayList<String> ViewAllNotifications(String email) throws SQLException ;
}

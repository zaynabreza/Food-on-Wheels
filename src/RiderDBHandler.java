import java.sql.Connection;
import java.sql.SQLException;

public interface RiderDBHandler {
    int getRiderID(Connection con, String Email) throws SQLException;
    void editRiderInfo(Connection con,String Email,String ContactNo,String Password,String Vehicle) throws SQLException;
    String getRiderPassword(Connection con,String Email)throws SQLException;
}
interface ManagerRiderDBHandler extends RiderDBHandler{
    void Insert_Rider(Connection con,String FName,String LName, String Email, String ContactNo, String Vehicle, String CNIC, String Gender, String Password, int Mgr_id) throws SQLException;
}

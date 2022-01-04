import java.sql.SQLException;

public abstract class Users {
    public abstract String SignInValidation(String Email, String Password) throws SQLException;

}




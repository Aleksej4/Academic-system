import java.sql.Connection;

public class AdminMenuCreateUser extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        administrator = new Administrator();
        administrator.createUser(con);
    }
}

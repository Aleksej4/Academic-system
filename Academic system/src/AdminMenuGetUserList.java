import java.sql.Connection;

public class AdminMenuGetUserList extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        administrator = new Administrator();
        administrator.userList(con);
    }
}

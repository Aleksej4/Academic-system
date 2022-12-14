import java.sql.Connection;

public class AdminMenuCreateGroup extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        administrator = new Administrator();
        administrator.createGroup(con);
    }
}

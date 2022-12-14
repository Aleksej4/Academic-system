import java.sql.Connection;

public class AdminMenuCreateSubject extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        administrator = new Administrator();
        administrator.createSubject(con);
    }
}

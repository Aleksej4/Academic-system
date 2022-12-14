import java.sql.Connection;

public class AdminMenuDeleteSubject extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        administrator = new Administrator();
        administrator.deleteSubject(con);
    }
}

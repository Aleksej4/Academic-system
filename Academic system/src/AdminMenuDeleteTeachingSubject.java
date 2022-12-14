import java.sql.Connection;

public class AdminMenuDeleteTeachingSubject extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        administrator = new Administrator();
        administrator.deleteTeachingSubject(con);
    }
}

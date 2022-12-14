import java.sql.Connection;

public class AdminMenuCreateTeachingSubject extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        administrator = new Administrator();
        administrator.teachingSubject(con);
    }
}

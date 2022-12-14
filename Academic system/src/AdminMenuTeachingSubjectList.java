import java.sql.Connection;

public class AdminMenuTeachingSubjectList extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        administrator = new Administrator();
        administrator.teachingSubjectList(con);
    }
}

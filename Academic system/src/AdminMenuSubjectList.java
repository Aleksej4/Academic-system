import java.sql.Connection;

public class AdminMenuSubjectList extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        administrator = new Administrator();
        administrator.subjectList(con);
    }
}

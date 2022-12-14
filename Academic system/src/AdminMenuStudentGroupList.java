import java.sql.Connection;

public class AdminMenuStudentGroupList extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        administrator = new Administrator();
        administrator.groupList(con);
    }
}

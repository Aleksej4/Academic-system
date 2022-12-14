import java.sql.Connection;

public class AdminMenuDeleteStudentGroup extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        administrator = new Administrator();
        administrator.deleteStudentGroup(con);
    }
}

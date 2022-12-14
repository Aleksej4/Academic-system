import java.sql.Connection;

public class TeacherMenuCreateGrade extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        teacher = new Teacher();
        teacher.createGrade(con, userID);
    }
}

import java.sql.Connection;

public class TeacherMenuDeleteGrade extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        teacher = new Teacher();
        teacher.deleteGrade(con, userID);
    }
}

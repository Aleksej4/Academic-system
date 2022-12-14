import java.sql.Connection;

public class TeacherMenuChangeGrade extends MenuItem{
    @Override
    public void action(Connection con, int userID) {
        teacher = new Teacher();
        teacher.changeGrade(con, userID);
    }
}

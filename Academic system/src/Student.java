import java.sql.Connection;
import java.sql.SQLException;

public class Student extends userInformation{

    public Student() {
        setUserPosition("Student");
    }

    @Override
    public void loggedIn(Connection con, int userID) {
        StudentGrades studentGrades = new StudentGrades(con, userID, getUserPosition());
    }
}

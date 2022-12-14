import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Teacher extends userInformation{
    TeacherMenu teacherMenu = new TeacherMenu();
    public Teacher() {
        setUserPosition("Teacher");
    }

    @Override
    public void loggedIn(Connection con, int userID) {
        try {
            ResultSet resultSet = con.createStatement().executeQuery("SELECT `First_name`, `Last_name` FROM `teacher` WHERE Teacher_ID = " + userID);
            if (resultSet.next()){
                Menu menu = new Menu("Teacher menu", getUserPosition(), teacherMenu.methods, con, userID, resultSet.getString("First_name") + " " + resultSet.getString("Last_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void createGrade(Connection con, int userID){
        CreateEvaluation createEvaluation = new CreateEvaluation(con, userID);
    }

    protected void changeGrade(Connection con, int userID){
        ChangeGrade changeGrade = new ChangeGrade(con, userID);
    }

    protected void deleteGrade(Connection con, int userID){
        String query = "SELECT A.`Subject evaluation ID`, D.`Name`, A.`Evaluation name`, A.`Evaluation`, B.`First_name`, B.`Last_name` FROM `subject evaluation`A JOIN `student`B ON B.`Student_ID` = A.`Student ID` JOIN `teaching subject`C ON C.`Teaching subject ID` = A.`Teaching subject ID` JOIN `subject`D ON D.`Subject_ID` = C.`Subject ID` WHERE C.`Teacher ID` = " + userID;

        Delete delete = new Delete("evaluation", con, query);
    }
}

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

    public class Administrator extends userInformation {
        private final AdminMenu adminMenu = new AdminMenu();

        public Administrator() {
            setUserPosition("Administrator");
        }

        @Override
        public void loggedIn(Connection con, int userID) {
            try {
                ResultSet resultSet = con.createStatement().executeQuery("SELECT `First name`, `Last name` FROM `administrator` WHERE Administrator_ID = " + userID);
                if (resultSet.next()){
                    Menu menu = new Menu("Administrator menu", getUserPosition(), adminMenu.methods, con, userID, resultSet.getString("First name") + " " + resultSet.getString("Last name"));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        protected void createUser(Connection con) {
            CreateUser createUser = new CreateUser("SELECT `Name` FROM `group`", con);
        }

        protected void userList(Connection con) {
            List list = new List(con, "SELECT A.`Login_ID`, A.`Username`, " +
                    "A.`Password`, B.`User` FROM `login`A JOIN `users`B ON B.`User_ID` = A.`User_ID` WHERE A.`User_ID` != 3");
        }

        protected void groupList(Connection con) {
            List list = new List(con, "SELECT `Group_ID`, `Name` FROM `group` ");
        }

        protected void createGroup(Connection con) {
            CreateGroupOrSubject createGroupOrSubject = new CreateGroupOrSubject("group", con, "SELECT * FROM `group` WHERE Name = ", "INSERT INTO `group`(`Name`) VALUES (?)");
        }

        protected void createSubject(Connection con) {
            CreateGroupOrSubject createGroupOrSubject = new CreateGroupOrSubject("subject", con, "SELECT * FROM `subject` WHERE Name = ", "INSERT INTO `subject`(`Name`) VALUES (?)");
        }

        protected void subjectList(Connection con) {
            List list = new List(con, "SELECT `Subject_ID`, `Name` FROM `subject` ");
        }

        protected void teachingSubject(Connection con) {
            CreateTeachingSubject createTeachingSubject = new CreateTeachingSubject("SELECT * FROM `teacher`","SELECT `Name` FROM `subject`", "SELECT `Name` FROM `group`", con );
        }

        protected void teachingSubjectList(Connection con) {
            List list = new List(con, "SELECT A.`Teaching subject ID`, B.`First_Name`, B.`Last_Name`, C.`Name`, D.`Name` FROM `teaching subject`A JOIN `teacher`B ON B.`Teacher_ID` = A.`Teacher ID` " +
                    "JOIN `subject`C ON C.`Subject_ID` = A.`Subject ID` JOIN `group`D ON D.`Group_ID` = A.`Group ID`");
        }

        protected void deleteSubject(Connection con) {
            Delete delete = new Delete("subject", con, "SELECT `name` FROM `subject`");
        }

        protected void deleteStudentGroup(Connection con) {
            Delete delete = new Delete("student group", con, "SELECT `Name` FROM `group`");
        }

        protected void deleteTeachingSubject(Connection con) {
            String query = "SELECT A.`Teaching subject ID`, B.`First_Name`, B.`Last_Name`, C.`Name`, D.`Name` FROM `teaching subject`A JOIN `teacher`B ON B.`Teacher_ID` = A.`Teacher ID` " +
                    "JOIN `subject`C ON C.`Subject_ID` = A.`Subject ID` JOIN `group`D ON D.`Group_ID` = A.`Group ID`";

            Delete delete = new Delete("teaching subject", con, query);
        }
    }

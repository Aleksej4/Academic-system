import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentGrades extends JFrame{
    private JTextArea Grades;
    private JButton LogOutB;
    private JLabel UsernameL;
    private JLabel UsetypeL;
    private JPanel StudentGrades;
    private JLabel info;
    private helpers helpers = new helpers();

    public StudentGrades(Connection con, int userID, String userType) {
        setContentPane(StudentGrades);
        setSize(550,400);
        setTitle("Student grades");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        user(con, userID, userType);
        grades(con, userID);

        LogOutB.addActionListener(e -> {
            dispose();
            System.exit(0);
        });
    }

    private void user(Connection con, int userID, String userType){
        try {
            ResultSet resultSet = con.createStatement().executeQuery("SELECT `First_name`, `Last_name` FROM `student` WHERE Student_ID = " + userID);
            if (resultSet.next()){
                UsernameL.setText(resultSet.getString("First_name") + " " + resultSet.getString("Last_name"));
            }
            UsetypeL.setText(userType);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void grades(Connection con, int userID){
        info.setText("Subject name | Evaluation name | Grade ");
        try {
            Grades.append(String.valueOf(helpers.showList(con.createStatement(), "SELECT A.`Name`, B.`Evaluation name`, B.`Evaluation` FROM `subject evaluation`B JOIN" +
                    " `teaching subject`C ON C.`Teaching subject ID` = B.`Teaching subject ID` JOIN `subject`A ON A.`Subject_ID` = C.`Subject ID` WHERE B.`Student ID` = " + userID)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

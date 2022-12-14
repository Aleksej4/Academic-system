import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class ChangeGrade extends JFrame{
    private JComboBox<String> EvaluationList;
    private JComboBox<Integer> Grades;
    private JButton UpdateB;
    private JButton CancelB;
    private JPanel CGradeP;
    private final helpers helpers = new helpers();

    public ChangeGrade(Connection con, int userID) {
        setContentPane(CGradeP);
        setSize(550, 400);
        setTitle("Change grade");
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            EvaluationList = helpers.fillList(con.createStatement(), "SELECT A.`Subject evaluation ID`, D.`Name`, A.`Evaluation name`, " +
                    "A.`Evaluation`, B.`First_name`, B.`Last_name` FROM `subject evaluation`A JOIN `student`B ON B.`Student_ID` = A.`Student ID` JOIN `teaching subject`C ON C.`Teaching subject ID` = " +
                    "A.`Teaching subject ID` JOIN `subject`D ON D.`Subject_ID` = C.`Subject ID` WHERE C.`Teacher ID` = " + userID, EvaluationList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i<=10; i++){
            Grades.addItem(i);
        }

        UpdateB.addActionListener(e -> {
            if (EvaluationList.getSelectedItem() == null){
                JOptionPane.showMessageDialog(ChangeGrade.this, "You graded no one");
            }
            else {
                try {
                    con.createStatement().executeUpdate("UPDATE `subject evaluation` SET `Evaluation` = " +
                            Grades.getSelectedItem() + " WHERE `Subject evaluation ID` = " + Integer.parseInt(Objects.requireNonNull(EvaluationList.getSelectedItem()).toString().split(" ")[0]));
                    JOptionPane.showMessageDialog(ChangeGrade.this, "Grade updated successfully");
                    dispose();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        CancelB.addActionListener(e -> dispose());
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class CreateEvaluation extends JFrame{
    private JComboBox<String> subjectList;
    private JComboBox<String> studentList;
    private JComboBox<Integer> grades;
    private JButton GradeB;
    private JButton CancelB;
    private JPanel evaluationP;
    private JTextField EvaluationNameT;
    private final helpers helpers = new helpers();

    public CreateEvaluation(Connection con, int userID) {
        setContentPane(evaluationP);
        setSize(550, 400);
        setTitle("Evaluation");
        setLocationRelativeTo(null);
        setVisible(true);

        for (int i = 1; i<=10; i++){
            grades.addItem(i);
        }

        try {
            subjectList = helpers.fillList(con.createStatement(), "SELECT A.`Teaching subject ID`, C.`Name`, D.`Name` FROM " +
                    "`teaching subject`A JOIN `subject`C ON C.`Subject_ID` = A.`Subject ID` JOIN `group`D ON D.`Group_ID` = A.`Group ID` WHERE A.`Teacher ID` = " + userID, subjectList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        GradeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (subjectList.getSelectedItem() == null){
                    JOptionPane.showMessageDialog(CreateEvaluation.this, "You dont have any subjects");
                }
                else if(studentList.getSelectedItem() == null){
                    JOptionPane.showMessageDialog(CreateEvaluation.this, "Group dont have any students");
                }
                else if(EvaluationNameT.getText().equals("")){
                    JOptionPane.showMessageDialog(CreateEvaluation.this, "Please enter evaluation name");
                }
                else{
                    String query = "INSERT INTO `subject evaluation`(`Evaluation name`, `Evaluation`, `Student ID`, `Teaching subject ID`) VALUES (?,?,?,?)";
                    try {
                        PreparedStatement pstmt = con.prepareStatement(query);
                        pstmt.setString(1, EvaluationNameT.getText());
                        pstmt.setInt(2, (Integer) grades.getSelectedItem());
                        pstmt.setInt(3, Integer.parseInt(Objects.requireNonNull(studentList.getSelectedItem()).toString().split(" ")[0]));
                        pstmt.setInt(4, Integer.parseInt(Objects.requireNonNull(subjectList.getSelectedItem()).toString().split(" ")[0]));
                        pstmt.execute();

                        JOptionPane.showMessageDialog(CreateEvaluation.this, "Student successfully created");
                        dispose();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        subjectList.addActionListener(e -> {
        int ID = Integer.parseInt(Objects.requireNonNull(subjectList.getSelectedItem()).toString().split(" ")[0]);
            try {
                ID = helpers.getID(con.createStatement(), "SELECT `Group ID` FROM `teaching subject` WHERE `Teaching subject ID` = " + ID);
                studentList = helpers.fillList(con.createStatement(), "SELECT `Student_ID`, `First_name`, `Last_name` FROM `student` WHERE `Group_ID` = " + ID, studentList);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        CancelB.addActionListener(e -> dispose());
    }
}

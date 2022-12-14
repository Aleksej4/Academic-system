import javax.swing.*;
import java.sql.*;
import java.util.Objects;

public class CreateTeachingSubject extends JFrame {
    private JComboBox<String> TeacherList;
    private JComboBox<String> SubjectList;
    private JComboBox<String> GroupList;
    private JButton CreateB;
    private JButton CancelB;
    private JPanel CreateTeachingSubject;
    private final helpers helpers = new helpers();

    public CreateTeachingSubject(String queryTeacher, String querySubject, String queryGroup, Connection con) {
        setContentPane(CreateTeachingSubject);
        setSize(550, 400);
        setTitle("Create teaching subject");
        setLocationRelativeTo(null);
        setVisible(true);
        try {
            TeacherList = helpers.fillList(con.createStatement(), queryTeacher, TeacherList);
            SubjectList = helpers.fillList(con.createStatement(), querySubject, SubjectList);
            GroupList = helpers.fillList(con.createStatement(), queryGroup, GroupList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        CreateB.addActionListener(e -> {
            if (Objects.equals(SubjectList.getSelectedItem(), "") || Objects.equals(TeacherList.getSelectedItem(), "") || Objects.equals(GroupList.getSelectedItem(), "")){
                JOptionPane.showMessageDialog(CreateTeachingSubject.this, "One or more lists are empty. Cannot create teaching subject");
            }
            else {
                try {
                    String teacherID = Objects.requireNonNull(TeacherList.getSelectedItem()).toString().split(" ")[0];
                    int groupID = helpers.getID(con.createStatement(), "SELECT `Group_ID` FROM `group` WHERE `Name` = \"" + GroupList.getSelectedItem() + "\"");
                    int subjectID = helpers.getID(con.createStatement(), "SELECT `Subject_ID` FROM `subject` WHERE `Name` = \"" + SubjectList.getSelectedItem() + "\"");

                    if(helpers.isValid(con.createStatement(), "SELECT * FROM `teaching subject` WHERE `Teacher ID` = " + Integer.parseInt(teacherID) + " AND `Subject ID` = " + subjectID + " AND `Group ID` = " + groupID)){
                        JOptionPane.showMessageDialog(CreateTeachingSubject.this, "Teaching subject already exist");
                    }
                    else {
                        try {
                            String query = "INSERT INTO `teaching subject`(`Teacher ID`, `Subject ID`, `Group ID`) VALUES (?,?,?)";
                            PreparedStatement pstmt = con.prepareStatement(query);

                            pstmt.setInt(1, Integer.parseInt(teacherID));
                            pstmt.setInt(2, subjectID);
                            pstmt.setInt(3, groupID);
                            pstmt.execute();

                            JOptionPane.showMessageDialog(CreateTeachingSubject.this, "Teaching subject created");
                            dispose();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        CancelB.addActionListener(e -> dispose());
    }
}

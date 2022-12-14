import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Delete extends JFrame{
    private JComboBox<String> ListJ;
    private JButton DeleteB;
    private JButton CancelB;
    private JLabel textT;
    private JPanel DeleteP;
    private helpers helpers = new helpers();

    public Delete(String deleting, Connection con, String query) {
        setContentPane(DeleteP);
        setSize(550,400);
        setTitle("Delete " + deleting);
        setVisible(true);
        setLocationRelativeTo(null);
        textT.setText(textT.getText() + deleting);
        try {
            ListJ = helpers.fillList(con.createStatement(), query, ListJ);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DeleteB.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Are sure you want to delete item?", "Warning",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String item = ListJ.getSelectedItem().toString();
                switch (deleting) {
                    case "subject":
                        Subject(item, con);
                        break;
                    case "student group":
                        StudentGroup(item, con);
                        break;
                    case "teaching subject":
                        TeachingSubject(item, con);
                        break;
                    case "evaluation":
                        Evaluation(item, con);
                        break;
                    default:
                        return;
                }
                dispose();
            }
        });
        CancelB.addActionListener(e -> dispose());
    }

    private void StudentGroup(String studentGroup, Connection con){
        try {
            int ID = helpers.getID(con.createStatement(), "SELECT `Group_ID` FROM `group` WHERE `Name` = \"" + studentGroup + "\"");

            con.createStatement().executeUpdate("DELETE FROM `subject evaluation` WHERE `subject evaluation`.`Student ID` IN (SELECT `Student_ID` FROM `student` WHERE `Group_ID` = " + ID + ")");
            con.createStatement().executeUpdate("DELETE FROM `teaching subject` WHERE `teaching subject`.`Group ID` IN (SELECT `Group_ID` FROM `group` WHERE `Group_ID` = " + ID + ")");
            con.createStatement().executeUpdate("DELETE FROM `student` WHERE `student`.`Group_ID` IN (SELECT `Group_ID` FROM `group` WHERE `Group_ID` = " + ID + ")");
            con.createStatement().executeUpdate("DELETE FROM `group` WHERE `Group_ID` = " + ID);

            JOptionPane.showMessageDialog(Delete.this, "Student group deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void TeachingSubject(String teachingSubject, Connection con){
        try {
            int ID = Integer.parseInt(teachingSubject.split(" ")[0]);

            con.createStatement().executeUpdate("DELETE FROM `subject evaluation` WHERE `Teaching subject ID`= " + ID);
            con.createStatement().executeUpdate("DELETE FROM `Teaching subject` WHERE `Teaching subject ID` = " + ID);

            JOptionPane.showMessageDialog(Delete.this, "Teaching subject deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void Subject(String subject, Connection con){
        try {
            int ID = helpers.getID(con.createStatement(), "SELECT `Subject_ID` FROM `subject` WHERE `Name` = \"" + subject + "\"");

            con.createStatement().executeUpdate("DELETE FROM `subject evaluation` WHERE `subject evaluation`.`Teaching subject ID` IN (SELECT `Teaching subject ID` FROM `Teaching subject` WHERE `Subject ID` = " + ID + ")");
            con.createStatement().executeUpdate("DELETE FROM `teaching subject` WHERE `teaching subject`.`Subject ID` IN (SELECT `Subject_ID` FROM `subject` WHERE `Subject_ID` = " + ID + ")");
            con.createStatement().executeUpdate("DELETE FROM `subject` WHERE `Subject_ID` = " + ID);

            JOptionPane.showMessageDialog(Delete.this, "Subject deleted");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    private void Evaluation(String evaluation, Connection con){
        if (ListJ.getSelectedItem() == null){
            JOptionPane.showMessageDialog(Delete.this, "You graded no one");
        }
        else {
            try {
                con.createStatement().executeUpdate("DELETE FROM `subject evaluation` WHERE `Subject evaluation ID` = " +
                        Integer.parseInt(evaluation.split(" ")[0]));
                JOptionPane.showMessageDialog(Delete.this, "Grade deleted");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

import javax.swing.*;
import java.sql.*;

public class CreateUser extends JFrame {
    private final helpers helpers = new helpers();
    private JTextField FirstNameField;
    private JTextField LastNameField;
    private JRadioButton StudentRadio;
    private JRadioButton TeacherRadio;
    private JComboBox<String> GroupListB;
    private JButton CreateB;
    private JPanel CreateUser;
    private JButton CancelB;

    public CreateUser(String query, Connection con) {
        setContentPane(CreateUser);
        setTitle("Create user");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            GroupListB = helpers.fillList(con.createStatement(), query, GroupListB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        GroupListB.setEnabled(false);

        ButtonGroup radioGroup = new ButtonGroup();

        radioGroup.add(StudentRadio);
        radioGroup.add(TeacherRadio);

        StudentRadio.addActionListener(e -> GroupListB.setEnabled(true));
        TeacherRadio.addActionListener(e -> GroupListB.setEnabled(false));

        CreateB.addActionListener(e -> {
            if (FirstNameField.getText().equals("") || LastNameField.getText().equals("")){
                JOptionPane.showMessageDialog(CreateUser.this, "Please enter all required information");
            }
            else if (StudentRadio.isSelected()){
                if (GroupListB.getItemCount() == 0){
                    JOptionPane.showMessageDialog(CreateUser.this, "List of groups is empty, you can not create a student");
                }
                else {
                    try {
                        String group = (String) GroupListB.getSelectedItem();
                        int ID = helpers.getID(con.createStatement(), "SELECT `User_ID` FROM `users` WHERE User = \"Student\"");
                        ID = createLogin(con, FirstNameField.getText(), LastNameField.getText(), ID);
                        createStudent(ID, con, FirstNameField.getText(), LastNameField.getText(), group);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(CreateUser.this, "Student created");
                }
            }
            else if (TeacherRadio.isSelected()) {
                try {
                    int ID = helpers.getID(con.createStatement(), "SELECT `User_ID` FROM `users` WHERE User = \"Teacher\"");
                    ID = createLogin(con, FirstNameField.getText(), LastNameField.getText(), ID);
                    createTeacher(ID, con, FirstNameField.getText(), LastNameField.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(CreateUser.this, "Teacher created");
            }

            dispose();
        });
        CancelB.addActionListener(e -> dispose());
    }

    public int createLogin (Connection con, String firstN, String lastN, int ID){
        try {
            String query = "INSERT INTO Login(Username, Password, User_ID)VALUES(?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(query, new String[]{"Login_ID"});
            pstmt.setString(1, firstN.toLowerCase());
            pstmt.setString(2, lastN.toLowerCase());
            pstmt.setInt(3, ID);
            pstmt.execute();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            else
                return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createStudent(int ID, Connection con, String firstN, String lastN, String group){
        try {
            int groupID = helpers.getID(con.createStatement(), "SELECT `Group_ID` FROM `group` WHERE `Name` = \"" + group + "\"");

            String query = "INSERT INTO Student(Student_ID, First_name, Last_name, Group_ID)VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, ID);
            pstmt.setString(2, firstN);
            pstmt.setString(3, lastN);
            pstmt.setInt(4, groupID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTeacher(int ID, Connection con, String firstN, String lastN){
        try {
            String query = "INSERT INTO Teacher(Teacher_ID, First_name, Last_name)VALUES(?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, ID);
            pstmt.setString(2, firstN);
            pstmt.setString(3, lastN);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




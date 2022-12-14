import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateGroupOrSubject extends JFrame {
    private JButton CreateB;
    private JButton CancelB;
    private JTextField NameT;
    private JLabel CreateT;
    private JPanel CreatePanel;
    private final helpers helpers = new helpers();

    public CreateGroupOrSubject(String creating, Connection con, String queryName, String queryCreate) {
        setContentPane(CreatePanel);
        setTitle("Create " + creating);
        setSize(550, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        CreateT.setText(CreateT.getText() + creating);

        CreateB.addActionListener(e -> {
            try {
                if (helpers.isValid(con.createStatement(), queryName + "\"" + NameT.getText() + "\"")){
                    JOptionPane.showMessageDialog(CreateGroupOrSubject.this, "Please select non-existing name");
                }
                else if (NameT.getText().equals("")){
                    JOptionPane.showMessageDialog(CreateGroupOrSubject.this, "Please enter name");
                }
                else {
                    PreparedStatement pstmt = con.prepareStatement(queryCreate);
                    pstmt.setString(1, NameT.getText());
                    pstmt.execute();
                    JOptionPane.showMessageDialog(CreateGroupOrSubject.this, "Successfully created");
                    dispose();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        CancelB.addActionListener(e -> dispose());
    }
}

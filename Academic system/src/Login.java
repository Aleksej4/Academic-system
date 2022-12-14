import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame{
    private JButton logInB;
    private JPanel loginLabel;
    private JPasswordField PasswordJ;
    private JTextField UsernameJ;

    public Login(Connection con) {
        setContentPane(loginLabel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Log in");
        setSize(450, 300);
        setVisible(true);

        logInB.addActionListener(e -> {
            Users users = new Users();
            try {
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from login");

                while (resultSet.next()){
                    if (resultSet.getString("username").equals(UsernameJ.getText().toLowerCase()) && resultSet.getString("password").equals(PasswordJ.getText().toLowerCase())) {
                        users.users.get(resultSet.getString("user_id")).loggedIn(con, resultSet.getInt("Login_ID"));
                        dispose();
                        break;
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}

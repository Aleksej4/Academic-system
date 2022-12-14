import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class List extends JFrame {
    helpers helpers = new helpers();
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public List(Connection con, String query) {
            textArea = new JTextArea(10, 20);
            textArea.setEditable(false);
            showList(con, query);
            scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            setLayout(new FlowLayout());
            setTitle("List");
            add(scrollPane);
            setLocationRelativeTo(null);
            setSize(400,300);
            setVisible(true);

    }
        void showList(Connection con, String query) {
            try {
                    textArea.append(String.valueOf(helpers.showList(con.createStatement(), query)));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}

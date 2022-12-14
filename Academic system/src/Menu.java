import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Set;

public class Menu extends JFrame{
    private JButton SelectB;
    private JComboBox<String> MenuBox;
    private JPanel MenuLabel;
    private JLabel UserJ;
    private JLabel UserTipeJ;
    private JButton LogOutB;

    public Menu(String title, String userTipe, HashMap<String, MenuItem> methods, Connection con, int ID, String fullName) {
        setContentPane(MenuLabel);
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        UserJ.setText(fullName);
        UserTipeJ.setText(userTipe);
        addMenuBox(methods);

        SelectB.addActionListener(e -> {
            System.out.println(MenuBox.getSelectedItem());
            methods.get(MenuBox.getSelectedItem()).action(con, ID);
        });
        LogOutB.addActionListener(e -> {
            dispose();
            System.exit(0);
        });
    }

    private void addMenuBox(HashMap<String, MenuItem> methods){
        Set<String> keys = methods.keySet();
        for(String key: keys){
            MenuBox.addItem(key);
        }
    }
}
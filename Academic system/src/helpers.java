import javax.swing.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class helpers {

    public boolean isValid(Statement statement, String query) {
        try {
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public StringBuilder showList(Statement statement, String query){
        try {
            StringBuilder names = new StringBuilder();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            final int columnCount = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    names.append(resultSet.getString(i)).append(" ");
                }
                names.append("\n");
            }

            return names;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JComboBox<String> fillList(Statement statement, String query, JComboBox<String> list){
        try {
            StringBuilder builder;
            ResultSet resultSet = statement.executeQuery(query);
            list.removeAllItems();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            final int columnCount = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {
                builder = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    builder.append(resultSet.getString(i));
                    builder.append(" ");
                }
                list.addItem(builder.toString());
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getID(Statement statement, String query){
        try {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

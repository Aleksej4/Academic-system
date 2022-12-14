import java.sql.*;

public class Main {
    public static void main(String[] args) {
        final String DB_URL = "jdbc:mysql://localhost:3306/Aleksej";
        final String USER = "root";
        final String PASSWORD = "";

        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Login login = new Login(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
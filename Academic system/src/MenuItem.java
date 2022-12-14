import java.sql.Connection;

public abstract class MenuItem {

    protected Administrator administrator;
    protected Teacher teacher;
    abstract public void action(Connection con, int userID);


}

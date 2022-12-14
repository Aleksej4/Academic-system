import java.util.HashMap;

public class Users {
    protected HashMap <String, userInformation> users;

    public Users(){
        users = new HashMap<>();
        setUsers();
    }
    private void setUsers(){
        Teacher teacher = new Teacher();
        users.put("1", teacher);

        Student student = new Student();
        users.put("2", student);

        Administrator administrator = new Administrator();
        users.put("3", administrator);
    }
}

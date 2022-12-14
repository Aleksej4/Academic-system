import java.util.HashMap;

public class AdminMenu {
    protected HashMap<String, MenuItem> methods;

    public AdminMenu(){
        methods = new HashMap<>();

        AdminMenuCreateUser adminMenuCreateUser = new AdminMenuCreateUser();
        methods.put("Create user", adminMenuCreateUser);
        AdminMenuGetUserList adminMenuGetUserList = new AdminMenuGetUserList();
        methods.put("Users list", adminMenuGetUserList);
        AdminMenuCreateGroup adminMenuCreateGroup = new AdminMenuCreateGroup();
        methods.put("Create student group", adminMenuCreateGroup);
        AdminMenuDeleteStudentGroup adminMenuDeleteStudentGroup = new AdminMenuDeleteStudentGroup();
        methods.put("Delete student group", adminMenuDeleteStudentGroup);
        AdminMenuStudentGroupList adminMenuStudentGroupList = new AdminMenuStudentGroupList();
        methods.put("Student group list", adminMenuStudentGroupList);
        AdminMenuCreateSubject adminMenuCreateSubject = new AdminMenuCreateSubject();
        methods.put("Create subject", adminMenuCreateSubject);
        AdminMenuDeleteSubject adminMenuDeleteSubject = new AdminMenuDeleteSubject();
        methods.put("Delete subject", adminMenuDeleteSubject);
        AdminMenuSubjectList adminMenuSubjectList = new AdminMenuSubjectList();
        methods.put("Subject list", adminMenuSubjectList);
        AdminMenuCreateTeachingSubject adminMenuCreateTeachingSubject = new AdminMenuCreateTeachingSubject();
        methods.put("Create teaching subject", adminMenuCreateTeachingSubject);
        AdminMenuDeleteTeachingSubject adminMenuDeleteTeachingSubject = new AdminMenuDeleteTeachingSubject();
        methods.put("Delete teaching subject", adminMenuDeleteTeachingSubject);
        AdminMenuTeachingSubjectList adminMenuTeachingSubjectList = new AdminMenuTeachingSubjectList();
        methods.put("Teaching subject list", adminMenuTeachingSubjectList);
    }
}

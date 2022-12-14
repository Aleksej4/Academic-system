import java.util.HashMap;

public class TeacherMenu {
    protected HashMap<String, MenuItem> methods;
    public TeacherMenu(){
        methods = new HashMap<>();

        TeacherMenuCreateGrade teacherMenuCreateGrade = new TeacherMenuCreateGrade();
        methods.put("Create grade", teacherMenuCreateGrade);
        TeacherMenuChangeGrade teacherMenuChangeGrade = new TeacherMenuChangeGrade();
        methods.put("Change grade", teacherMenuChangeGrade);
        TeacherMenuDeleteGrade teacherMenuDeleteGrade = new TeacherMenuDeleteGrade();
        methods.put("Delete grade", teacherMenuDeleteGrade);
    }
}

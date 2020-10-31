package JavaClasses;

import java.sql.Connection;
import java.util.LinkedList;

public class TheStudentsList {
    private LinkedList<Student> list;
    DB db;
    //connection
    public TheStudentsList(){
        list = new LinkedList<>();
        db = new DB();
    }
    public LinkedList<Student> getList() {
        return list;
    }

    public void printList(){
        for(int i=0; i<this.list.size(); i++){
            System.out.println(this.list.get(i));
            System.out.println("\n");
        }
    }

    public Student newStudent(String name, String password, TheGreatLib library){
        Student newStudent = new Student(name, password);
        list.add(newStudent);
        newStudent.library = library;
        db.addStudent(name, password);
        return newStudent;
    }


    public void setList(LinkedList<Student> list) {
        this.list = list;
    }
}

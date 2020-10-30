package JavaClasses;

import java.util.LinkedList;

public class Admin extends User implements adminInterface{
    protected TheStudentsList studentList;
    protected TheGreatLib library;

    public Admin(String name, String password){
        super(name, password);
        //if db.query(select * from students) == null{
        //  new list
        // }else{
        //  create list based on query output
        // }
        //if db.query(select * from books) == null{
        //  new library
        // }else{
        //  create list based on query output
        // }
        studentList = new TheStudentsList();
        library = new TheGreatLib();
    }


    public Books newBook(String name, String author, int quantity){
        Books newBook = new Books(name, author, quantity);
        library.getLibrary().add(newBook);
        return newBook;
    }

    public void setLists(TheStudentsList list, TheGreatLib lib) {
        this.library = lib;
        this.studentList = list;
    }

    public Student getStudent(String name) {
        for(int i=0; i<this.studentList.getList().size(); i++){
            if(this.studentList.getList().get(i).getName() == name){
                return this.studentList.getList().get(i);
            }
        }
        return null;
    }

    public Books getBook(String name) {
        for(int i=0; i<this.library.getLibrary().size(); i++){
            if(this.library.getLibrary().get(i).getName() == name){
                return this.library.getLibrary().get(i);
            }
        }
        return null;
    }

    public void removeBook(String bookname, int quantity) {
        for(int i=0; i<this.library.getLibrary().size(); i++){
            if(this.library.getLibrary().get(i).getName() == bookname){
                this.library.getLibrary().remove(i);
                break;
            }
        }
    }

    public void removeStudent(String name) {
        for(int i=0; i<this.studentList.getList().size(); i++){
            if(this.studentList.getList().get(i).getName() == name){
                this.studentList.getList().remove(i);
                break;
            }
        }
    }
}

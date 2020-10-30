package JavaClasses;

import java.util.LinkedList;

public class Student extends User implements studentInterface{
    private LinkedList<Books> borrowedBooks;
    protected TheGreatLib library;

    public Student(TheGreatLib library, String name, String password) {
        super(name, password);
        this.borrowedBooks = new LinkedList<>();
        this.library = library;
    }

    @Override
    public LinkedList<Books> getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public void printBorrowedBooks(){
        for(int i=0; i<this.borrowedBooks.size(); i++){
            System.out.println(this.borrowedBooks.get(i));
            System.out.println("\n");
        }
    }

    public void borrowBook(Books book){
        Books orderbook = new Books(book.getName(), book.getAuthor(), 1);
        library.giveBook(book);
        borrowedBooks.add(orderbook);
    }

    public Books getBook(String name) {
        for(int i=0; i<this.library.getLibrary().size(); i++){
            if(this.library.getLibrary().get(i).getName() == name){
                return this.library.getLibrary().get(i);
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + this.getName() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                '}';
    }

}

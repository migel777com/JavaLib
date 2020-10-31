package JavaClasses;

import java.util.LinkedList;

public class TheGreatLib{
    private LinkedList<Books> library;
    DB db;

    public TheGreatLib(){
        library = new LinkedList<>();
        db = new DB();
    }

    public LinkedList<Books> getLibrary() {
        return library;
    }

    public void giveBook(Books book){
        Books order = new Books(book.getName(), book.getAuthor(), 1);


        if(library.contains(book)){
            int tempindex = library.indexOf(book);
            int quan = library.get(tempindex).getQuantity();
            if(quan >= order.getQuantity()){
                library.get(tempindex).reduceQuantity(1);
            }
            if(library.get(tempindex).getQuantity()==0){
                library.remove(tempindex);
            }
        }
    }

    public void newBook(String name, String author, int quantity){
        Books newBook = new Books(name, author, quantity);
        library.add(newBook);
        db.addBook(name, author, quantity);
    }

    public void printLib(){
        for (Books books : this.library) {
            System.out.println(books);
            System.out.println("\n");
        }
    }

    public void setLibrary(LinkedList<Books> library) {
        this.library = library;
    }
}

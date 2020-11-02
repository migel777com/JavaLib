package JavaClasses;


public class Books {
    private String name;
    private String author;
    private int quantity;
    private String image;
    private String borrowedTime;
    private String returnTime;
    private String username;

    public Books(String name, String author, int quantity) {
        this.name = name;
        this.author = author;
        this.quantity = quantity;
    }

    public Books(String[] bookFields)
    {
        if(bookFields.length == 5)
        {
            //this.id = Integer.parseInt(bookFields[0]);
            this.name = bookFields[1];
            this.author = bookFields[2];
            this.quantity = Integer.parseInt(bookFields[3]);
            this.image = bookFields[4];
        }
    }

    public Books(String[] bookFields, String withdate)
    {
        if(bookFields.length == 8)
        {
            //this.id = Integer.parseInt(bookFields[0]);
            this.name = bookFields[1];
            this.author = bookFields[2];
            //this.quantity = Integer.parseInt(bookFields[3]);
            this.borrowedTime = bookFields[4];
            this.returnTime = bookFields[5];
            this.image = bookFields[6];
            this.username = bookFields[7];
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBorrowedTime() {
        return borrowedTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public String getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void reduceQuantity(int quantity){
        this.quantity = this.getQuantity() - quantity;
    }

    @Override
    public String toString() {
        return "Books{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

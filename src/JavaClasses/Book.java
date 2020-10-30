package JavaClasses;

public class Book {
    private int id;
    private String name;
    private String author;
    private String imageURL;

    public Book(String[] bookFields)
    {
        if(bookFields.length == 4)
        {
            this.id = Integer.parseInt(bookFields[0]);
            this.name = bookFields[1];
            this.author = bookFields[2];
            this.imageURL = bookFields[3];
        }
    }

    public Book(String name, String author, String imageURL)
    {
        this.name = name;
        this.author = author;
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

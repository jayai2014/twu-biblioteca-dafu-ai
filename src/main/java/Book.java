public class Book extends Item {
    private String author;
    private int year;

    public Book(int id, String title, String author, int year) {
        super(id, title);
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title=" + title +
                ", author=" + author +
                ", year=" + year +
                '}';
    }
}

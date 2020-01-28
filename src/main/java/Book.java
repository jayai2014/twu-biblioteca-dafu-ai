/**
 * Represents book data
 */
public class Book {
    /**
     * The identity field
     */
    private int id;

    /**
     * Title of the book
     */
    private String title;

    /**
     * Available stock
     */
    private int stock;

    /**
     * Author of the book
     */
    private String author;

    /**
     * Year the book was published
     */
    private int yearPublished;

    public Book(int id, String title, String author, int yearPublished, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStock() {
        return stock;
    }

    /**
     * Increment book stock by quantity
     * @param quantity quantity to be incremented
     */
    public void incrementStock(int quantity) {
        this.stock += quantity;
    }

    /**
     * Decrement book stock by quantity
     * @param quantity quantity to be decremented
     * @throws InsufficientBookStockException when stock is insufficient
     */
    public void decrementStock(int quantity) throws InsufficientBookStockException {
        if (this.stock - quantity < 0) {
            throw new InsufficientBookStockException(this);
        }

        this.stock -= quantity;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return "id=" + id + "|title=" + title + "|author=" + author
                + "|year=" + yearPublished + "|stock=" + stock;
    }
}

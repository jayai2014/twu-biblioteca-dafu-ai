import java.util.Objects;

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

    public Book(int id, String title, int stock) {
        this.id = id;
        this.title = title;
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

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "id=" + id + ", title=" + title + ", stock=" + stock;
    }
}

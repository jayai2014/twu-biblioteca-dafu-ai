import java.util.List;

public class BookCheckoutsToStringHelper {
    private List<BookCheckout> bookCheckouts;

    public BookCheckoutsToStringHelper(List<BookCheckout> bookCheckouts) {
        this.bookCheckouts = bookCheckouts;
    }

    public String listToString() {
        StringBuilder builder = new StringBuilder();
        for (BookCheckout checkout: bookCheckouts) {
            builder.append(checkout.toString()).append("\n");
        }
        return builder.toString();
    }
}

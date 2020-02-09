public class BookCheckout {
    private User user;
    private Book book;
    private boolean returned = false;

    public BookCheckout(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned() {
        returned = true;
    }

    @Override
    public String toString() {
        return "BookCheckout{" +
                "user=" + user +
                ", book=" + book +
                ", returned=" + returned +
                '}';
    }
}

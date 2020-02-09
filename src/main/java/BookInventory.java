import java.util.*;

class BookInventory extends ItemInventory<Book> {
    protected UserRegistry userRegistry;
    protected Map<User, List<BookCheckout>> userCheckouts = new HashMap<>();

    public BookInventory(UserRegistry userRegistry) {
        this.userRegistry = userRegistry;
    }

    public void loadCheckoutData(Map<User, List<BookCheckout>> userCheckouts) {
        this.userCheckouts = userCheckouts;
    }

    @Override
    void checkoutItem(int itemId) throws InsufficientItemStockException, ItemNotExistException {
        super.checkoutItem(itemId);
        BookCheckout bookCheckOut = new BookCheckout(userRegistry.getCurrentUser(),
                getItemStock(itemId).getItem());

        User user = userRegistry.getCurrentUser();
        if (!userCheckouts.containsKey(user)) {
            userCheckouts.put(user, new ArrayList<>());
        }

        userCheckouts.get(user).add(bookCheckOut);
    }

    void returnBook(int bookId) throws ItemNotExistException {
        getItemStock(bookId).incrementStockQty(1);

        User user = userRegistry.getCurrentUser();
        if (!userCheckouts.containsKey(user)) {
            // treat this case as donating for now
            return;
        }

        // Search through to find the first not-yet-returned book checkout record
        Optional<BookCheckout> searchResult = userCheckouts.get(user).stream()
                .filter(checkout -> checkout.getBook().getId() == bookId
                                && !checkout.isReturned())
                .findFirst();

        if (!searchResult.isPresent()) {
            // treat this case as donating for now
            return;
        }

        searchResult.get().setReturned();
    }

    public List<BookCheckout> getUserCheckouts() {
        List<BookCheckout> allCheckouts = new ArrayList<>();

        for (List<BookCheckout> checkoutList: userCheckouts.values()) {
            allCheckouts.addAll(checkoutList);
        }

        return allCheckouts;
    }
}

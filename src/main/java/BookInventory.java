class BookInventory extends ItemInventory<Book> {
    void checkoutBook(int bookId)
            throws InsufficientItemStockException, ItemNotExistException {
        if (!inv.containsKey(bookId)) {
            throw new ItemNotExistException(bookId);
        }

        inv.get(bookId).decrementStockQty(1);
    }

    void returnBook(int bookId) throws ItemNotExistException {
        if (!inv.containsKey(bookId)) {
            throw new ItemNotExistException(bookId);
        }

        inv.get(bookId).incrementStockQty(1);
    }
}

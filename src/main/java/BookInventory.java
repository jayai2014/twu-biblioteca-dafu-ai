class BookInventory extends ItemInventory<Book> {
    void returnBook(int bookId) throws ItemNotExistException {
        getItemStock(bookId).incrementStockQty(1);
    }
}

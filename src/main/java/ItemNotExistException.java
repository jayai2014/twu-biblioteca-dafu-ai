public class ItemNotExistException extends Exception{
    public ItemNotExistException(int itemId) {
        super("Item does not exist: " + itemId);
    }
}

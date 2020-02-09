import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MenuOption {
    QUIT("0", "Quit", null),
    LOGIN("l", "Login", null),
    LIST_BOOKS("b", "List all books", null),
    CHECKOUT_BOOK("cb", "Checkout a book (e.g. cb1)", UserRole.CUSTOMER),
    RETURN_BOOK("rb", "Return a book (e.g. rb1)", UserRole.CUSTOMER),
    LIST_MOVIES("m", "List all movies", null),
    CHECKOUT_MOVIE("cm", "Checkout a movie (e.g. cm1)", null),
    LIST_BOOK_CHECKOUTS("bc", "List book checkout records", UserRole.LIBRARIAN);

    private String symbol;
    private String description;
    private UserRole userRole;

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    MenuOption(String symbol, String description, UserRole userRole) {
        this.symbol = symbol;
        this.description = description;
        this.userRole = userRole;
    }

    static String getAllOptionsString(BibliotecaApp app) {
        StringBuilder builder = new StringBuilder("Available options:\n");

        // Filter out unavailable options
        UserRole userRole = app.getCurrentUserRole();
        List<MenuOption> allOptions = Arrays.asList(MenuOption.values());
        List<MenuOption> availableOptions = allOptions.stream().filter(
                option -> option.userRole == null || option.userRole == userRole)
                .collect(Collectors.toList());

        for (MenuOption option: availableOptions) {
            builder.append(option.getSymbol())
                    .append(" - ")
                    .append(option.getDescription())
                    .append("\n");
        }

        return builder.toString();
    }
}

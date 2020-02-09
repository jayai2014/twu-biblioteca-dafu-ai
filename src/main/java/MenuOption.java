public enum MenuOption {
    QUIT("0", "Quit"),
    LIST_BOOKS("b", "List all books"),
    CHECKOUT_BOOK("cb", "Checkout a book (e.g. cb1)"),
    RETURN_BOOK("rb", "Return a book (e.g. rb1)"),
    LIST_MOVIES("m", "List all movies"),
    CHECKOUT_MOVIE("cm", "Checkout a movie (e.g. cm1)");

    private String symbol;
    private String description;

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    MenuOption(String symbol, String description) {
        this.symbol = symbol;
        this.description = description;
    }

    static String getAllOptionsString() {
        StringBuilder builder = new StringBuilder("Available options:\n");

        for (MenuOption option: MenuOption.values()) {
            builder.append(option.getSymbol())
                    .append(" - ")
                    .append(option.getDescription())
                    .append("\n");
        }

        return builder.toString();
    }
}

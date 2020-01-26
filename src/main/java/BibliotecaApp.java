public class BibliotecaApp {
    /**
     * Retrieve welcome message
     */
    public static String getWelcomeMessage() {
        return "Welcome to Biblioteca. Your one-stop-shop for " +
                "great book titles in Bangalore!\n";
    }

    public static void main(String[] args) {
        System.out.print(getWelcomeMessage());
    }
}

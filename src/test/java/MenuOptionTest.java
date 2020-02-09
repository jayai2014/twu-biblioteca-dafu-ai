import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MenuOptionTest {
    @Test
    public void testAllOptionStringReturnDifferentItemsForDifferentUserRoles() {
        List<User> users = new ArrayList<>();
        users.add(UserRegistryFactory.CUSTOMER);
        users.add(UserRegistryFactory.LIBRARIAN);
        BibliotecaApp app = new BibliotecaApp();

        // without login
        assertEquals("Available options:\n" +
                        "0 - Quit\n" +
                        "l - Login\n" +
                        "b - List all books\n" +
                        "m - List all movies\n" +
                        "cm - Checkout a movie (e.g. cm1)\n",
                MenuOption.getAllOptionsString(app));

        // with customer role
        app.loadUsersData(users);
        app.processLogin(UserRegistryFactory.CUSTOMER.getUsername(),
                UserRegistryFactory.CUSTOMER.getPassword());

        assertEquals("Available options:\n" +
                "0 - Quit\n" +
                "l - Login\n" +
                "b - List all books\n" +
                "cb - Checkout a book (e.g. cb1)\n" +
                "rb - Return a book (e.g. rb1)\n" +
                "m - List all movies\n" +
                "cm - Checkout a movie (e.g. cm1)\n",
                MenuOption.getAllOptionsString(app));

        // with librarian role
        app.loadUsersData(users);
        app.processLogin(UserRegistryFactory.LIBRARIAN.getUsername(),
                UserRegistryFactory.LIBRARIAN.getPassword());

        assertEquals("Available options:\n" +
                        "0 - Quit\n" +
                        "l - Login\n" +
                        "b - List all books\n" +
                        "m - List all movies\n" +
                        "cm - Checkout a movie (e.g. cm1)\n" +
                        "bc - List book checkout records\n",
                MenuOption.getAllOptionsString(app));
    }
}

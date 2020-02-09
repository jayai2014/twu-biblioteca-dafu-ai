import java.util.ArrayList;
import java.util.List;

public class UserRegistryFactory {
    public static final Librarian LIBRARIAN =
            new Librarian("000-0001", "123", "Libraian Dafu");
    public static final Customer CUSTOMER =
            new Customer("000-0002", "123", "User Dafu");

    public static UserRegistry create() {
        UserRegistry userRegistry = new UserRegistry();

        List<User> users = new ArrayList<>();
        users.add(LIBRARIAN);
        users.add(CUSTOMER);
        userRegistry.loadData(users);

        return userRegistry;
    }
}

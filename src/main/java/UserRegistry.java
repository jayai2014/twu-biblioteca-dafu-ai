import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRegistry {
    private Map<String, User> users = new HashMap<>();
    private User currentUser = null;

    public void loadData(List<User> users) {
        for (User user: users) {
            this.users.put(user.getUsername(), user);
        }
    }

    public void login(String username, String password) throws InvalidCredentialsException {
        if (!users.containsKey(username)) {
            throw new InvalidCredentialsException();
        }

        User user = users.get(username);
        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException();
        }

        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public UserRole getCurrentUserRole() {
        if (currentUser instanceof Customer) {
            return UserRole.CUSTOMER;
        }
        if (currentUser instanceof Librarian) {
            return UserRole.LIBRARIAN;
        }
        return null;
    }
}

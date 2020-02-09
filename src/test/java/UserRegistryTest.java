import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class UserRegistryTest {
    private static class TestUser extends User {
        public TestUser(String username, String password, String name) {
            super(username, password, name);
        }
    }

    @Test
    public void testUserCanLoginWhenCredentialsAreValid() throws InvalidCredentialsException {
        TestUser user = new TestUser("000-0000", "123", "Dafu");
        UserRegistry userRegistry = new UserRegistry();
        List<User> users = new ArrayList<>();
        users.add(user);
        userRegistry.loadData(users);
        userRegistry.login("000-0000", "123");

        assertEquals(user, userRegistry.getCurrentUser());
    }

    @Test(expected = InvalidCredentialsException.class)
    public void testUserCanNotLoginWhenUsernameIsInvalid() throws InvalidCredentialsException {
        TestUser user = new TestUser("000-0000", "123", "Dafu");
        UserRegistry userRegistry = new UserRegistry();
        List<User> users = new ArrayList<>();
        users.add(user);
        userRegistry.loadData(users);
        userRegistry.login("000-0001", "123");
    }

    @Test(expected = InvalidCredentialsException.class)
    public void testUserCanNotLoginWhenPassowrdIsInvalid() throws InvalidCredentialsException {
        TestUser user = new TestUser("000-0000", "123", "Dafu");
        UserRegistry userRegistry = new UserRegistry();
        List<User> users = new ArrayList<>();
        users.add(user);
        userRegistry.loadData(users);
        userRegistry.login("000-0000", "1231");
    }

    @Test
    public void testGetCurrentUserRoleShouldMatchActualInstance() throws InvalidCredentialsException {
        UserRegistry userRegistry = UserRegistryFactory.create();
        userRegistry.login(UserRegistryFactory.CUSTOMER.getUsername(),
                UserRegistryFactory.CUSTOMER.getPassword());
        assertSame(UserRole.CUSTOMER, userRegistry.getCurrentUserRole());

        userRegistry.login(UserRegistryFactory.LIBRARIAN.getUsername(),
                UserRegistryFactory.LIBRARIAN.getPassword());
        assertSame(UserRole.LIBRARIAN, userRegistry.getCurrentUserRole());
    }
}

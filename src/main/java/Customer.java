public class Customer extends User {
    protected String email = null;
    protected String phone = null;

    public Customer(String username, String password, String name) {
        super(username, password, name);
    }

    public Customer(String username, String password, String name, String email, String phone) {
        super(username, password, name);
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return super.toString() + " & Customer{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

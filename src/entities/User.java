public abstract class User {
    private String uuid;
    private String password;

    public User (String email, String password) {
        this.uuid = email;
        this.password = password;
    }
}

package resources;

public class User extends Accounts {
    private String email;
    private String name;
    private Integer userId;

    public User(String login, String password, String email, String name) {
        super(login, password);
        this.email = email;
        this.name = name;
    }

    // musimy dodac tutaj metody dla zwyklego uzytwkownika
}

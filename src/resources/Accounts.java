package resources;

public class Accounts {
    protected String login;
    protected String password;

    public Accounts(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // Metody wspólne dla wszystkich kont
    public boolean authenticate(String inputLogin, String inputPassword) {
        return login.equals(inputLogin) && password.equals(inputPassword);
    }
}

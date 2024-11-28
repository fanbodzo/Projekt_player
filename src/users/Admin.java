package users;

public class Admin extends Accounts {
    private int adminId;

    public Admin(String login, String password, int adminId) {
        super(login, password);
        this.adminId = adminId;
    }


}

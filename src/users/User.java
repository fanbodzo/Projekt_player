package users;

public class User extends Accounts {
    private String email;
    private String name;
    private Integer userId;

    public User(String login, String password, String email, String fullName) {
        super(login, password);
        this.email = email;
        this.name = fullName;
    }

    @Override
    public String getLogin(){
        //w ten sposob zwracamy login dziedziczony z accounts
        return super.getLogin();
    }
    @Override
    public String getPassword(){
        return super.getPassword();
    }

}

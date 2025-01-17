package users;

public class User extends Accounts {
    private String email;
    private String name;
    private String userId;
    private boolean premium;
    //login i password dziedziczymy z accounts

    public User(String userId, String login, String password, String email, String name, boolean premium) {
        super(login, password);
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.premium = premium;
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

    public String getName() {
        return name;
    }
}

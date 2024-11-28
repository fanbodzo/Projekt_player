package users;

public class UserPremium extends User {
    private boolean premiumStatus;
    //subskrypcja pobierana co miesiac, kiedy dasz anuluj to znika
    //private Date premiumExpirationDate;

    public UserPremium(String login, String password, String email, String name) {
        super(login, password, email, name);
        this.premiumStatus = true;
        // trzeba usatwic date kiedy wygasa subskrypcja i kiedy jest odnawiana do wykminienia
    }

}
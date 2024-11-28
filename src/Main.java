import users.User;
import utils.UserLoader;
import gui.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserLoader loader = new UserLoader();
        List<User> users = loader.loadUsersFromFile("data/users.txt");

        // uruchamianie login forma czyli tego gui co zrobilem
        new LoginForm();
    }
}


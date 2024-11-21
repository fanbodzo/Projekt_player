import resources.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserLoader loader = new UserLoader();
        List<User> users = loader.loadUsersFromFile("data/users.txt");

    }
}


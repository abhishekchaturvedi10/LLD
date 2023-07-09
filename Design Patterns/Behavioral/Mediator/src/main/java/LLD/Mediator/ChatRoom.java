package LLD.Mediator;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    List<User> users;

    ChatRoom() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void sendMessage(User usr, String message) {
        for (User user : users) {
            if (user != usr) {
                user.receiveMessage(usr.getName(), message);
            }
        }
    }
}

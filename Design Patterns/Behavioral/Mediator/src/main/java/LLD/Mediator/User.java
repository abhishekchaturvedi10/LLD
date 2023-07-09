package LLD.Mediator;

import java.util.Date;

public class User {
    ChatRoom chatRoom;
    String name;

    User(ChatRoom chatRoom, String name) {
        this.chatRoom = chatRoom;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        System.out.println(name + " sending message: \"" + message + "\"");
        chatRoom.sendMessage(this, message);
    }

    public void receiveMessage(String userName, String message) {
        System.out.println(name + " received message: \"" + message + "\" from " + userName);
    }
}

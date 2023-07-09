package LLD.Mediator;

public class MediatorApplication {

    public static void main(String[] args) {

        ChatRoom mediator = new ChatRoom();

        User user1 = new User(mediator, "Pankaj");
        User user2 = new User(mediator, "Lisa");
        User user3 = new User(mediator, "Saurabh");
        User user4 = new User(mediator, "David");

        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.sendMessage("Hello World!");
    }
}
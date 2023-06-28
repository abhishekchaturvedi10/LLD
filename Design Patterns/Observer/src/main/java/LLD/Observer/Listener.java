package LLD.Observer;

public class Listener implements Observer {

    private final String name;
    private Subject topic;

    Listener(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTopic(Subject topic) {
        this.topic = topic;
        if (topic != null) {
            topic.registerObserver(this);
        }
    }

    public void update(String message) {
        System.out.println("Received message: " + message + " from " + topic.getName() + " by " + name);
    }
}

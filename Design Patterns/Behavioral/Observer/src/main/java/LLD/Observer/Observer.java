package LLD.Observer;

public interface Observer {
    String getName();
    void setTopic(Subject topic);
    void update(String message);
}

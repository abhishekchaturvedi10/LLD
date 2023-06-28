package LLD.Observer;

import java.util.ArrayList;
import java.util.List;

public class Topic implements Subject {

    private final String name;
    private String message;
    private List<Observer> observers = new ArrayList<>();

    Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println(observer.getName() + " registered on " + name);
    }

    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
        observer.setTopic(null);
        System.out.println(observer.getName() + " deregistered from " + name);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            System.out.println("Sending message: " + message + " to " + observer.getName() + " from " + name);
            observer.update(message);
        }
    }

    public void sendMessage(String message) {
        this.message = message;
        notifyObservers();
    }
}

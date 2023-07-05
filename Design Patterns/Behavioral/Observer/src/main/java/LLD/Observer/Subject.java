package LLD.Observer;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    String getName();
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void sendMessage(String message);
}

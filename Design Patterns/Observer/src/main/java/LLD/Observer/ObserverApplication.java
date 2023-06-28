package LLD.Observer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class ObserverApplication {
    public static void main(String[] args) {

        Subject cricketNews = new Topic("Cricket News");

        Observer espn = new Listener("ESPN");
        Observer cricbuzz = new Listener("CricBuzz");
        Observer sportstar = new Listener("Sportstar");

        espn.setTopic(cricketNews);
        cricbuzz.setTopic(cricketNews);
        sportstar.setTopic(cricketNews);

        cricketNews.sendMessage("India won the 2011 cricket world cup!");

        cricketNews.deregisterObserver(cricbuzz);

        cricketNews.sendMessage("CSK won the 2023 IPL!");
    }
}

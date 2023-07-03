package LLD.Singleton;

public class Singleton {
    private static Singleton instance;

    private Singleton() {
        System.out.println("Initialization Singleton Instance!");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    System.out.println("getInstance() invoked for the first time!");
                    instance = new Singleton();
                }
            }
        } else {
            System.out.println("Instance already initialized!");
        }
        return instance;
    }

    public void function() {
        System.out.println("function() invoked!");
    }
}

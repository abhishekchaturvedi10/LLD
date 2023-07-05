package LLD.Singleton;


public class SingletonApplication {

    public static void main(String[] args) {
        System.out.println("----- FIRST CALL -----");
        Singleton.getInstance().function();

        System.out.println("\n----- SECOND CALL -----");
        Singleton.getInstance().function();
    }
}

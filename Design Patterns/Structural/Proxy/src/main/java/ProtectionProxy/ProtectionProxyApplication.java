package ProtectionProxy;

public class ProtectionProxyApplication {

    public static void main(String[] args) {

        Internet internet = new ProxyInternet();

        try {
            internet.connectTo("xyz.com");
            internet.connectTo("abc.com");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

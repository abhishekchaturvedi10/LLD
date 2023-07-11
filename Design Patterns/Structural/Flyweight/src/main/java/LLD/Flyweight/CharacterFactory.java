package LLD.Flyweight;

import java.util.HashMap;

public class CharacterFactory {
    private static HashMap<String, Character> hm = new HashMap<>();

    public static Character getCharacter(String type) {
        Character p = null;
        if (hm.containsKey(type)) {
            p = hm.get(type);
        } else {
            switch (type) {
                case "Terrorist":
                    p = new Terrorist();
                    break;
                case "Soldier":
                    p = new Soldier();
                    break;
                default:
                    System.out.println("Unreachable code!");
            }
            hm.put(type, p);
        }
        return p;
    }
}

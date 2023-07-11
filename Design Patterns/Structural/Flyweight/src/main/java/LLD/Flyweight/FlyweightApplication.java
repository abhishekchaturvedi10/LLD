package LLD.Flyweight;

import java.util.Random;

public class FlyweightApplication {

    private static String[] characterType = {"Terrorist", "Soldier"};
    private static String[] weapons = {"AK-47", "Maverick", "Gut Knife", "Desert Eagle"};

    public static void main(String args[]) {

        for (int i = 0; i < 10; i++) {
            Character p = CharacterFactory.getCharacter(getRandPlayerType());
            p.assignWeapon(getRandWeapon());
            p.description();
        }
    }

    public static String getRandPlayerType() {
        Random r = new Random();
        int randInt = r.nextInt(characterType.length);
        return characterType[randInt];
    }

    public static String getRandWeapon() {
        Random r = new Random();
        int randInt = r.nextInt(weapons.length);
        return weapons[randInt];
    }
}
import java.util.Random;

public class Main {
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic","Medic"};
    public static int[] heroesHealth = {270, 280, 250,150};
    public static int[] heroesDamage = {20, 15, 25,0};
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        round_number++;
        bossDefenceType = changeBossDefence();
        System.out.println("Boss choose: " + bossDefenceType);
        bossHits();
        heroesHit();
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;


        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static String changeBossDefence() {

        int randomIndex = new Random().nextInt(heroesAttackType.length);
        return heroesAttackType[randomIndex];
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
                if (heroesHealth[i] < 0) {
                    heroesHealth[i] = 0;
                }
            }
        }
    }

    public static void heroesHit() {
        int coeff = new Random().nextInt(8) + 2; //2,3,4,5,6,7,8,9
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    bossHealth -= heroesDamage[i] * coeff;
                    System.out.println("Critical damage: ["
                            + coeff + "] = " +
                            heroesDamage[i] * coeff);
                } else {
                    bossHealth -= heroesDamage[i];
                }
                if (bossHealth < 0) {
                    bossHealth = 0;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("________ ROUND [" + round_number + "]");
        System.out.println("Boss health: " + bossHealth
                + " [" + bossDamage + "]");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i] + " [" +
                    heroesDamage[i] + "]");
            System.out.println("Жизнь Медика: " + heroesHealth[i]);
        }
        System.out.println("________________");
    }

    public static void MedicHeal(){
        for (int i = 0; i < heroesHealth.length; i++) {
            int RandomHeal = new Random().nextInt() * 100;
            if (heroesHealth[i] < 0 ) heroesHealth[i] = 0;
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[i] > 0 && !heroesAttackType[i].equals(heroesAttackType[i])){
                heroesHealth[i] = heroesHealth[i] +RandomHeal;
                System.out.println("Медик лечит: " + heroesAttackType[i] + "На: " + RandomHeal);
                break;

            }
        }
    }
}

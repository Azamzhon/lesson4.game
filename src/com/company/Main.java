package com.company;


import java.util.Random;

public class Main {

    public static int[] heroesHealth = {350, 320, 300, 500, 1000, 250, 400, 500};
    public static int[] heroesDamage = {50, 40, 45, 30, 30, 20, 40, 50};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Mental", "Healing", "Physical", "Berserk", "Miss", "Stun"};
    public static int bossHealth = 2000;
    public static int bossDamage = 100;
    public static String bossDefenceType = "";

    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            changeBossDefence();
            round();
        }
    }


    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0
                && heroesHealth[2] <= 0 && heroesHealth[3] <= 0 && heroesHealth[4] <= 0
                && heroesHealth[5] <= 0 && heroesHealth[6] <= 0 && heroesHealth[7] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        bossHit();
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("_______________");
        System.out.println("Boss health: " + bossHealth + " " + bossDefenceType);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Healer health:" + heroesHealth[3]);
        System.out.println("Tank health:" + heroesHealth[4]);
        System.out.println("Nimble health:" + heroesHealth[5]);
        System.out.println("Berserk health:" + heroesHealth[6]);
        System.out.println("Thor health:" + heroesHealth[7]);
        System.out.println("_______________");
    }

    public static void healer() {
        if (heroesHealth[3] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                heroesHealth[i] = heroesHealth[i] + heroesDamage[3];
            }
        } else {
            heroesHealth[3] = 0;
        }
    }
        public static void sShield() {
            for (int i = 0; i < heroesHealth.length; i++) {
                Random r = new Random();
                int randomShield = r.nextInt(50);

                System.out.println("Shield " + " hero " + heroesHealth[i] + " damage");
                System.out.println("____________________");
                if (heroesHealth[4] > 0) {
                    heroesHealth[i] = heroesHealth[i] += randomShield;
                    heroesHealth[4] = heroesHealth[4] - randomShield;
                } else if (heroesHealth[4] <= 0) {
                    System.out.println("---Tank has Died---");

                }
            }
        }
            public static void mMiss () {
                Random r = new Random();
                int randomN = r.nextInt(2);
                if (randomN == 1) {
                    System.out.println("Boss miss Nimble");
                    System.out.println("________________");
                    heroesHealth[5] = heroesHealth[5] + bossDamage;
                }

            }

            public static void bBerserk () {
                Random ra = new Random();
                int randomM = ra.nextInt(50);
                System.out.println("Berserk return Hit " + randomM);
                System.out.println("___________________");
                bossHealth = bossHealth - (heroesDamage[6] + randomM);

            }
            public static void sStun() {
                Random ra = new Random();
                int randomM = ra.nextInt(3);
                if (randomM == 1) {
                    System.out.println("Thor stunned the Boss");
                    System.out.println("_____________________");
                    bossDamage = 0;

                } else {
                    bossDamage = 50;
                }
            }

            public static void heroesHit() {
                for (int i = 0; i < heroesDamage.length; i++) {
                    if (bossHealth > 0 && heroesHealth[i] > 0) {
                        if (bossDefenceType.equals(heroesAttackType[i])) {
                            Random r = new Random();
                            int coef = r.nextInt(8) + 2; // 2,3,4,5,6,7,8,9
                            if (bossHealth - heroesDamage[i] * coef < 0) {
                                bossHealth = 0;
                            } else {
                                bossHealth = bossHealth - heroesDamage[i] * coef;
                            }
                            System.out.println(heroesAttackType[i] +
                                    " critically hit " + heroesDamage[i] * coef);
                        } else {
                            if (bossHealth - heroesDamage[i] < 0) {
                                bossHealth = 0;
                            } else {
                                bossHealth = bossHealth - heroesDamage[i];
                            }
                        }
                    }
                }
            }

            public static void bossHit() {
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[i] > 0 && bossHealth > 0) {
                        if (heroesHealth[i] - bossDamage < 0) {
                            heroesHealth[i] = 0;
                        } else {
                            heroesHealth[i] = heroesHealth[i] - bossDamage;
                        }
                    }
                }
            }
}
package ua.com.aleev.island.util;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    private Randomizer() {
    }

    public static int random(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }

    public static double random(double from, double to) {
        return ThreadLocalRandom.current().nextDouble(from, to);
    }


    public static boolean get(int probably) {
        return random(0, 100) < probably;
    }
}

package java;

/**
 * 002_Cook_your_lasagna
 * https://exercism.org/tracks/java/exercises/lasagna
 */
public class CookYourLasagna {
    public class Lasagna {
        // TODO: define the 'expectedMinutesInOven()' method

        // TODO: define the 'remainingMinutesInOven()' method

        // TODO: define the 'preparationTimeInMinutes()' method

        // TODO: define the 'totalTimeInMinutes()' method
    }

    public class LasagnaSoltion {
        public static int expectedMinutesInOven() {
            return 40;
        }

        public static int remainingMinutesInOven(int minutes) {
            return LasagnaSoltion.expectedMinutesInOven() - minutes;
        }

        public static int preparationTimeInMinutes(int layers) {
            return layers * 2;
        }

        public static int totalTimeInMinutes(int layers, int minutes) {
            return preparationTimeInMinutes(layers) + minutes;
        }
    }
}

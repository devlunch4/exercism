package java;

/**
 * 003_Annalyn's_Infiltration
 * https://exercism.org/tracks/java/exercises/annalyns-infiltration
 */
public class Annalyns_Infiltration {
    class AnnalynsInfiltration {
        public static boolean canFastAttack(boolean knightIsAwake) {
            throw new UnsupportedOperationException(
                    "Please implement the (static) AnnalynsInfiltration.canFastAttack() method");
        }

        public static boolean canSpy(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake) {
            throw new UnsupportedOperationException(
                    "Please implement the (static) AnnalynsInfiltration.canSpy() method");
        }

        public static boolean canSignalPrisoner(boolean archerIsAwake, boolean prisonerIsAwake) {
            throw new UnsupportedOperationException(
                    "Please implement the (static) AnnalynsInfiltration.canSignalPrisoner() method");
        }

        public static boolean canFreePrisoner(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake,
                boolean petDogIsPresent) {
            throw new UnsupportedOperationException(
                    "Please implement the (static) AnnalynsInfiltration.canFreePrisoner() method");
        }
    }

    class AnnalynsInfiltrationSolution {
        class AnnalynsInfiltration {
            public static boolean canFastAttack(boolean knightIsAwake) {
                return !knightIsAwake;
            }

            public static boolean canSpy(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake) {
                return knightIsAwake || archerIsAwake || prisonerIsAwake;
            }

            public static boolean canSignalPrisoner(boolean archerIsAwake, boolean prisonerIsAwake) {
                return !archerIsAwake && prisonerIsAwake;
            }

            public static boolean canFreePrisoner(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake,
                    boolean petDogIsPresent) {
                return (prisonerIsAwake && !knightIsAwake && !archerIsAwake) || (!archerIsAwake && petDogIsPresent);
            }
        }

    }

}

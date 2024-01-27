package java;

/**
 * 004_Cars, Assemble!
 * https://exercism.org/tracks/java/exercises/cars-assemble
 */
public class Cars_Assemble {
    public class CarsAssemble {

        public double productionRatePerHour(int speed) {
            throw new UnsupportedOperationException("Please implement the CarsAssemble.productionRateperHour() method");
        }

        public int workingItemsPerMinute(int speed) {
            throw new UnsupportedOperationException("Please implement the CarsAssemble.workingItemsPerMinute() method");
        }
    }

    public class CarsAssembleSolution {
        private final int numberInHour = 221;

        private double success(int speed) {
            if (1 <= speed && speed <= 4) {
                return 1.0;
            } else if (5 <= speed && speed <= 8) {
                return 0.9;
            } else if (9 == speed) {
                return 0.8;
            } else if (10 == speed) {
                return 0.77;
            }
            return speed;
        }

        public double productionRatePerHour(int speed) {
            return numberInHour * speed * success(speed);
        }

        public int workingItemsPerMinute(int speed) {
            return (int) (productionRatePerHour(speed) / 60);
        }
    }
}

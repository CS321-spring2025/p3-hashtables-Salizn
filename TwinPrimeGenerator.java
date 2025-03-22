public class TwinPrimeGenerator {

    /**
     * Finds the first and lowest twin prime in a range
     *
     * @param min
     * @param max
     * @returns lowest twin prime in a range
     */
    public static int generateTwinPrime(int min, int max) {
        for (int i = min; i <= max; i++) {
            // Check if i and its twin are primes
            if (checkPrime(i) && checkPrime(i-2)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Did not find twin primes the range.");
    }

    /**
     * Checks if a number is prime
     *
     * @param number
     * @returns true if number is prime, false if not
     */
    private static boolean checkPrime(int number) {
        //1 is not prime
        if (number < 2) {
            return false;
        }

        //Check for 2 or 3, easy prime numbers
        if (number == 2 || number == 3) {
            return true;
        }

        //Remove even numbers (not prime) and numbers divisible by 3 (also not prime)
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        //Check from 5 to square root of number
        int i = 5;
        while (i * i <= number) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }

            //Increment by 6
            i += 6;
        }

        return true;
    }

    //Smoke test
    public static void main(String[] args) {
        System.out.println("Twin Prime: " + generateTwinPrime(95500, 96000));
    }
}

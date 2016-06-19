import java.util.stream.IntStream;

/**
 * Created by mik on 19/06/16.
 */
class Primes {

    static boolean isPrime(int n) {

        if (n < 2) return false;
        if (n == 2) return true;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }

        return true;
    }

    static boolean isPrime2(int n) {

        return n == 2 || n > 2 && IntStream
                .rangeClosed(2, (int) Math.sqrt(n))
                .filter(i -> n % i == 0).count() == 0;

    }

}

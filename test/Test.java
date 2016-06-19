import org.junit.Assert;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * Created by mik on 19/06/16.
 */

@RunWith(Theories.class)
public class Test {

    @DataPoints("primes")
    public static final List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 47);

    @DataPoints("non primes")
    public static final List<Integer> nonPrimes = Arrays.asList(0, 1, 4, 6, 8, 9, 10, 15, 21);

    private static Predicate<Integer> f1 = Primes::isPrime;
    private static Predicate<Integer> f2 = Primes::isPrime2;
    private static Predicate<Integer> f3 = Primes::isPrime2;


    @Theory
    public void testIsPrime(@FromDataPoints("primes") int n) {

        Assert.assertTrue(f1.test(n));
        Assert.assertTrue(f2.test(n));

    }

    @Theory
    public void testNotPrime(@FromDataPoints("non primes") int n) {

        Assert.assertFalse(f1.test(n));
        Assert.assertFalse(f2.test(n));

    }

    @org.junit.Test
    public void checkIfSame() {
        PrimeSieve sieve = PrimeSieve.getInstance();

        int maxValue = 1000000;
        sieve.initSieve(maxValue);

        IntStream
                .range(0, maxValue)
                .forEach(n -> Assert.assertEquals(Primes.isPrime(n), sieve.isPrime(n)));
    }

    @org.junit.Test
    public void timeTest() {

        PrimeSieve sieve = PrimeSieve.getInstance();

        int maxValue = 100000000;

        long start = System.nanoTime();
        sieve.initSieve(maxValue, true);
        long end = System.nanoTime();
        long diff = end - start;

        System.out.println("Generated sieve of size "
                + maxValue +
                " in: "
                + TimeUnit.MILLISECONDS.convert(diff, TimeUnit.NANOSECONDS)
                + " ms ("
                + diff
                + ") ns");

    }


}

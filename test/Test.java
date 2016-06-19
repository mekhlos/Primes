import org.junit.Assert;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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
    public void prime2Test() {
//        IntStream.range(0, 20).forEach(x -> System.out.println(x + " - " + f2.test(x)));


        PrimeSieve sieve = new PrimeSieve(100);

        sieve.printSieve();
    }


}

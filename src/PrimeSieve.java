/**
 * Created by mik on 19/06/16.
 */
class PrimeSieve {

//    private static PrimeSieve primeSieve;

    private final int INT_SIZE = 31;
    private final int limit;
    private int[] sieve;

    PrimeSieve(int limit) {

        this.limit = limit;
        int numOfBins = this.limit / INT_SIZE + 1;
        this.sieve = new int[numOfBins];

        initialiseSieve();

    }

    private void initialiseSieve() {
        for (int n = 2; n <= Math.sqrt(limit); n++) {

            // index to the array element
            int binIndex = n / INT_SIZE;

            // index to bit in the bin
            int bitIndex = n % INT_SIZE;

            // if bit is zero in bin at index
            if ((~sieve[binIndex] & (1 << bitIndex)) != 0) {
                // found a prime
                for (int multipleOfN = n * n; multipleOfN < limit; multipleOfN += n) {

                    binIndex = multipleOfN / INT_SIZE;
                    bitIndex = multipleOfN % INT_SIZE;

                    sieve[binIndex] |= (1 << bitIndex);

                }
            }

            sieve[0] |= 3;

        }
    }

    boolean isPrime(int n) {
        int binIndex = n / INT_SIZE;
        int bitIndex = n % INT_SIZE;

        return ((~sieve[binIndex] & (1 << bitIndex)) != 0);
    }


    void printSieve() {
        for (int i = 0; i < limit; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    @Override
    public String toString() {
        return "";
    }


}

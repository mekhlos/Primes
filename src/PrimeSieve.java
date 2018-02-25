/**
 * Created by mik on 19/06/16.
 */
class PrimeSieve {

    private static PrimeSieve primeSieve;

    private final int INT_SIZE = 32;
    private int limit;
    private int[] sieve;


    private PrimeSieve() {
    }

    static PrimeSieve getInstance() {
        if (primeSieve == null) {
            primeSieve = new PrimeSieve();
        }

        return primeSieve;
    }


    void initSieve(int maxValue) {
        initSieve(maxValue, false);
    }

    /* creating new sieve if the given max value is greater
    than the value the sieve is currently generated with */
    void initSieve(int maxValue, boolean forceNew) {

        if (maxValue > this.limit || forceNew) {
            this.limit = maxValue;
            int numOfBins = this.limit / INT_SIZE + 1;
            this.sieve = new int[numOfBins];

            generateSieve();
        }

    }

    private void generateSieve() {
        for (int n = 2; n <= Math.sqrt(limit); n++) {

            // index to the array element
            int binIndex = n / INT_SIZE;

            // index to bit in the bin
            int bitIndex = n % INT_SIZE;

            // if bit is zero in bin at index
            if (isZeroInSieve(binIndex, bitIndex)) {
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

    private boolean isZeroInSieve(int binIndex, int bitIndex) {
        return (~sieve[binIndex] & (1 << bitIndex)) != 0;
    }

    boolean isPrime(int n) {
        int binIndex = n / INT_SIZE;
        int bitIndex = n % INT_SIZE;

        return isZeroInSieve(binIndex, bitIndex);
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
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < limit; i++) {
            if (isPrime(i)) {
                sb.append(i).append("\n");
            }
        }

        return sb.toString();
    }


}

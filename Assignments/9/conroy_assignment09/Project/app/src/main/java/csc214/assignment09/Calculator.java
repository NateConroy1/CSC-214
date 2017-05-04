package csc214.assignment09;

/**
 * Created by Nate on 4/25/17.
 */

public class Calculator {

    // brute-force prime algorithm
    public static long calculateLargestPrimeNumber(long n) {
        long max = 2;
        for(long i = 2; i <= n; i ++) {
            if(isPrime(i)) {
                if(i > max) {
                    max = i;
                }
            }
        }
        return max;
    }

    // simple square root calculation
    public static long calculateSquareRoot(long n) {
        return (long) Math.sqrt(n);
    }

    // helper method that checks if number is prime (inefficiently)
    private static boolean isPrime(long n) {
        for(int i = 2; 2 * i < n; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

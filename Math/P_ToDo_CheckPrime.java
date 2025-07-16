package Math;

public class P_ToDo_CheckPrime {
    public boolean isPrime(int n) {
        if(n<=1) return false;
        // Instead of checking til n, we can check till √n because a larger factor of n must be a multiple of a smaller factor that has been already checked. 
        for(int i=2; i<=Math.sqrt(n); i++) {
            if(n%i==0) return false;
        }
        return true;
    }

    //Write a java program to check if any number given as input is the sum of 2 prime numbers.
    public boolean isTwoPrimeSum(int num) {
        // The smallest sum of two primes is 2 + 2 = 4
        if(num<=2) return false;
        // the loop should only go up to num / 2 because for any pair (i, num - i) where i > num / 2, num - i will have already been checked in a previous iteration.
        for(int i=2; i<=num/2; i++) {
            if(isPrime(i) && isPrime(num-i)) {
                return true;
            }
        }
        return false;
    }
}

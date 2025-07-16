/*
    204. Count primes
    Given an integer n, return the number of prime numbers that are strictly *less than* n.

    Example 1:
        Input: n = 10
        Output: 4
        Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

    Example 2:
        Input: n = 0
        Output: 0

    Example 3:
        Input: n = 1
        Output: 0
    
    Constraints:
        0 <= n <= 5 * 10^6
 */

package Math;

public class P204_Medium {
    /*
     * Solution:  Sieve of Eratosthenes
     *      We can start with the smallest prime number, 2, and mark all of its multiples up to 'n' as non-primes. Then we repeat the same process for the next available number in the array that is not marked as composite and so on.
     *      We have a nested-loop structure. Now the question is, what are the bounds on these two loops? The outer loop will start a 2 and go up to Sqrt(n). This is because by that point we will have considered all of the possible multiples
     *         of all the prime numbers below n. 
     *      Let's define the boundaries of the inner loop. We will invariantly pick the next available prime number (a number/ index not yet marked in the array as a composite) before entering the inner loop. 
     *          Say the index we picked from the outer loop is i, then the inner loop will start at i*i and increase by increments of i until it surpasses n. In short, we iterate over every multiple of i between i and n.
     *      The reason why start at i*i instead of 2*i is that all of the previous multiples would already have been covered by previous primes. 
     * time complexity: O(sqrt(n)*loglogn + n)
     *      +n is from calculating the answer after the main algorithm.
     *      sqrt(n) comes from the outer loop. 
     * space complexity: O(n)
     */
    public int countPrimes(int n) {
        // less than n, so n==2 is also 0.
        if(n<=2) return 0;

        boolean[] nums = new boolean[n];
        nums[0] = true;
        nums[1] = true;
        for(int i=2; i<=Math.sqrt(n); i++) {
            if(nums[i] == false) {
                for(int j=i*i; j<n; j+=i) {
                    nums[j] = true;
                }
            }
        }

        int count = 0;
        for(int i=2; i<n; i++) {
            if(nums[i] == false) {
                count++;
            }
        }
        return count;
    }
}

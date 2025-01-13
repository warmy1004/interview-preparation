/*
    263. Ugly number
    An ugly number is a positive integer which does not have a prime factor other than 2, 3, and 5.
    Given an integer n, return true if n is an ugly number.

    Example 1:
        Input: n = 6
        Output: true
        Explanation: 6 = 2 × 3

    Example 2:
        Input: n = 1
        Output: true
        Explanation: 1 has no prime factors.

    Example 3:
        Input: n = 14
        Output: false
        Explanation: 14 is not ugly since it includes the prime factor 7.
    
    Constraints:
        -2^31 <= n <= 2^31 - 1
 */
package Math;

public class P263_Easy {
    /*
     * Solution
     * time complexity: O(logn)
     * space complexity: O(1)
     */
    public boolean isUgly(int n) {
        if(n<=0) return false;
        for(int prime : new int[] {2,3,5}) {
            n = divideNum(n, prime);
        }
        return n==1;
    }

    int divideNum(int n, int prime) {
        while(n%prime==0) {
            n = n/prime;
        }
        return n;
    }

    public boolean isUgly(int n) {
        if(n<=0) return false;
        if(n==1) return true;
        while(n%2==0) {
            n/=2;
        }
        while(n%3==0) {
            n/=3;
        }
        while(n%5==0) {
            n/=5;
        }
        return n==1;
    }
}

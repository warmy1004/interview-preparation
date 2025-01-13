/*
    279. perfect squares
    Given an integer n, return the least number of perfect square numbers that sum to n.
    A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

    Example 1:
        Input: n = 12
        Output: 3
        Explanation: 12 = 4 + 4 + 4.

    Example 2:
        Input: n = 13
        Output: 2
        Explanation: 13 = 4 + 9.
    
    Constraints:
        1 <= n <= 10^4
 */

package DP;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P279_Medium {
    /*
     * Solution: DP
     * time complexity: O(n*sqrt(n))
     * space complexity: O(n)
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int num = 1;
        while(num*num<=n) {
            int prime = num*num;
            for(int i=prime; i<=n; i++) {
                dp[i] = Math.min(dp[i-prime]+1, dp[i]);
            }
            num++;
        }
        return dp[n];
    }

    public int numSquares(int n) {
        Set<Integer> primes = new HashSet<>();
        for(int i=1; i<=Math.sqrt(n); i++) {
            primes.add(i*i);
        }
        int[] dp = new int[n+1];
        Arrays.fill(dp, n+2);
        dp[0] = 0;

        for(int prime: primes) {
            for(int i=1; i<=n; i++) {
                if(i==prime) dp[i]=1;
                if(i>prime) dp[i] = Math.min(dp[i], dp[i-prime]+1);
            } 
        }
        return dp[n];
    }
    
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        //pre-cacluate the square numbers
        int max_square_idx = (int)Math.sqrt(n)+1;
        int[] squares = new int[max_square_idx];
        for(int i=1; i<max_square_idx; i++) {
            squares[i] = i*i;
        }

        for(int i=1; i<=n; i++) {
            for(int prime = 1; prime<max_square_idx; prime++) {
                if(i < squares[prime]) break;
                dp[i] = Math.min(dp[i], dp[i-squares[prime]]+1);
            }
        }
        return dp[n];
    }
}

/*
    322. coin change
    You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
    Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
    You may assume that you have an infinite number of each kind of coin.

    Example 1:
        Input: coins = [1,2,5], amount = 11
        Output: 3
        Explanation: 11 = 5 + 5 + 1

    Example 2:
        Input: coins = [2], amount = 3
        Output: -1

    Example 3:
        Input: coins = [1], amount = 0
        Output: 0
    
    Constraints:
        1 <= coins.length <= 12
        1 <= coins[i] <= 2^31 - 1
        0 <= amount <= 10^4
 */
package DP;

/*
 * Similar Questions:
 *      139. word break (Medium)
 *      279. perfect square (Medium)
 *      518. Coin Chnage 2 (Medium)
 */
import java.util.Arrays;

public class P322_Must_ToDo_Medium {
    /*
     * Solution: bottom up dp
     * time complexity: O(n*S), where S is the amount and n is denomination count
     * space complexity: O(S)
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for(int i=1; i<=amount; i++) {
            for(int coin: coins) {
                if(coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);
                }
            }
        }
        return dp[amount] == amount+1 ? -1 : dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for(int coin: coins) {
            for(int i=coin; i<=amount; i++) {
                dp[i] = Math.min(dp[i], dp[i-coin]+1);
            }
        }
        return dp[amount] == amount+1 ? -1 : dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;
        if(coins.length == 0) return -1;
        int[] dp = new int[amount+1];
        for(int i=0; i<amount+1; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for(int coin: coins) {
            // iterate from coin to the target amount, because less value than coin still doesn't build up with the coins
            for(int i=coin; i<=amount; i++) {
                if(dp[i-coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /*
     * Solution: top down dp
     * time complexity: O(n*S), where S is the amount and n is denomination count
     * space complexity: O(S)
     */
    public int coinChange(int[] coins, int amount) {
        if(amount < 1) return 0;
        return solve(coins, amount, new int[amount]);
    }

    int solve(int[] coins, int remains, int[] dp) {
        if(remains < 0) return -1;
        if(remains == 0) return 0;
        if(dp[remains-1] != 0) return dp[remains-1];
        int min = Integer.MAX_VALUE;
        for(int coin: coins) {
            int result = solve(coins, remains-coin, dp);
            if(result >=0 ) {
                min = Math.min(min, result+1);
            }
        }
        dp[remains-1] = min == Integer.MAX_VALUE ? -1 : min;
        return dp[remains-1];
    }

    public int coinChange(int[] coins, int amount) {
        int answer = solve(coins, amount);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    int solve(int[] coins, int amount) {
        if(amount == 0) return 0;
        if(amount < 0) return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for(int coin: coins) {
            int answer = solve(coins, amount -coin);
            if(answer!=Integer.MAX_VALUE) {
                min = Math.min(min, answer+1);
            }
        }
        return min;
    }
}

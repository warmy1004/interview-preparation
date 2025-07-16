/*
    123. Best time to buy and sell stock 3
    You are given an array prices where prices[i] is the price of a given stock on the ith day.
    Find the maximum profit you can achieve. You may complete at most two transactions.
    Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

    Example 1:
        Input: prices = [3,3,5,0,0,3,1,4]
        Output: 6
        Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
        Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

    Example 2:
        Input: prices = [1,2,3,4,5]
        Output: 4
        Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
        Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.

    Example 3:
        Input: prices = [7,6,4,3,1]
        Output: 0
        Explanation: In this case, no transaction is done, i.e. max profit = 0.
    
    Constraints:
        1 <= prices.length <= 10^5
        0 <= prices[i] <= 10^5
 */
package DP;

public class P123_ToDo_Hard {
    /*
     * Solution: bidirectional DP with two arrays
     * 
     * Approach:
     *      one array keeping the results of sequence from left to right
     *          - holding the maximum profits that one can gain from doing one single transaction on the left subsequence of prices from the index zero to i
     *      the other array keeping the results of sequence from right to left
     *          - holding the maximum profits that one can gain from doing one single transaction on the right subsequence of the prices from the index i up to N-1
     * 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 1) return 0;
        int leftMin = prices[0];
        int rightMax = prices[n-1];

        int[] leftProfits = new int[n];
        int[] rightProfits = new int[n+1];

        for(int i=1; i<n; i++) {
            leftProfits[i] = Math.max(leftProfits[i-1], prices[i]-leftMin);
            leftMin = Math.min(leftMin, prices[i]);

            int rIdx = n-i-1;
            rightProfits[rIdx] = Math.max(rightProfits[rIdx+1], rightMax-prices[rIdx]);
            rightMax = Math.max(rightMax, prices[rIdx]);
        }

        int profit = 0;
        for(int i=0; i<n; i++) {
            profit = Math.max(profit, leftProfits[i]+rightProfits[i+1]);
        }
        return profit;
    }

    /*
     * Solution: one-pass simulation
     * 
     * Approach:
     *  we can consider the problem as a game, and we as agent could make at most two transactions in order to gain the maximum points (profits) from the game.
     *  The two transactions be decomposed into 4 actions: 
     *      buy of transaction 1, sell of transaction 1, buy of transaction 2, sell of transaction 2
     *  To solve the game, we simply run a simulation along the sequence of prices, at each time step, we calculate the potential outcomes for each of our actions.
     *  At the end of the simulation, the outcome of the final action 'sell of transaction 2' would be the desired output of the problem.
     *      t1_cost: the minimal cost of buying the stock in transaction 1. The minimal cost to acquire a stock would be the minimal price value that we have seen so far at each step.
     *      t1_profit: the maximal profit of selling the stock in transaction 1. Actually, at the end of the iteration, this value would be the answer for the 'Best time to buy and sell stock 1'.
     *      t2_cost: the minimal cost of buying the stock in transaction 2, while taking into account the profit gained from the previous transaction 1.
     *              One can consider this as the cost of reinvestment. Similar with t1_cost, we try to find the lowest price so far, which in addition would be partially compensated by the profits gained from the first transaction.
     *      t2_profit: the maximal profit of selling the stock in transaction 2. 
     *              With the help of t2_cost as we prepared so far, we would find out the maximal profits with at most two transactions at each step.
     * 
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int maxProfit(int[] prices) {
        int t1_cost = Integer.MAX_VALUE, t2_cost = Integer.MAX_VALUE;
        int t1_profit = 0, t2_profit = 0;
        for(int price: prices) {
            // the maximum profit if only one transaction is allowed
            t1_cost = Math.min(t1_cost, price);
            t1_profit = Math.max(t1_profit, price - t1_cost);

            // reinvest the gained profit in the second transactions
            t2_cost = Math.min(t2_cost, price-t1_profit);
            t2_profit = Math.max(t2_profit, price-t2_cost);
        }
        return t2_profit;
    }

    /*
     * Approach:
     *  first assume that we have no money.
     *  buy1 means that we have to borrow money from others, we want to borrow less so that we have to make our balance as max as we can (because this is negative)
     *  sell1 means we decide to sell the stock, after selling it we have price[i] money and we have to give back the money we owed, so we have prices[i]-|buy1| = prices[i]+buy1, we want to make this max.
     *  buy2 means we want to buy another stock, we already have sell1 money, so after buying stock2 we have buy2 = sell1 - prices[i] moeny left, we want more money left, so we make it max.
     *  sell2 means we want to sell stock2, we can have prices[i] money after selling it, and we have buy2 money left before, so sell2 = buy2 + prices[i], we make this max.
     *  So, sell2 is the most money we can have.
     */
    public int maxProfit(int[] prices) {
        int sell1 = 0, sell2 = 0, buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        for(int price: prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, price + buy1);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2+price);
        }
        return sell2;
    }

    /*
     * Solution: DP
     */
    public int maxProfit(int[] prices) {
        if(prices.length == 1) return 0;
        int k=2; // kth transactions
        int[][] dp = new int[k+1][prices.length];
        for(int i=1; i<k; i++) {
            int maxDiff = -prices[0];
            for(int j=1; j<prices.length; j++) {
                maxDiff = Math.max(maxDiff, dp[i-1][j-1]-prices[j-1]);
                dp[i][j] = Math.max(dp[i][j-1], prices[j]+maxDiff);
            }
        }
        return dp[k][prices.length-1];
    }
}

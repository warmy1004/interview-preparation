/*
    122. Best Time to Buy and Sell Stock II

    You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
    On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
    Find and return the maximum profit you can achieve.

    Example 1:
        Input: prices = [7,1,5,3,6,4]
        Output: 7
        Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
        Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3. Total profit is 4 + 3 = 7.

    Example 2:
        Input: prices = [1,2,3,4,5]
        Output: 4
        Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4. Total profit is 4.

    Example 3:
        Input: prices = [7,6,4,3,1]
        Output: 0
        Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
    
    Constraints:
        1 <= prices.length <= 3 * 10^4
        0 <= prices[i] <= 10^4
 */

package Array_String;

public class Top150_P122_ToDo_Medium {
    /*
        Solution 1: Simple one pass
        Approach: The sum of each difference between indexes are same as the difference of the highest and the lowest indexes. 
    */
    public int maxProfit(int[] prices) {
        int profit=0;
        for(int i=1; i<prices.length; i++) {
            if(prices[i-1] < prices[i])
                profit+= prices[i]-prices[i-1];
        }
        return profit;
    }

    /*
     * Solution 2: Peak Valley approach
     */
    // public int maxProfit(int[] prices) {
    //     int profit=0;
    //     int i =0;
    //     int low = prices[0], high = prices[0];

    //     while(i<prices.length-1) {
    //         while( i<prices.length-1 && prices[i]>=prices[i+1]) i++;
    //         low = prices[i];
    //         while(i<prices.length-1 && prices[i]<= prices[i+1]) i++;
    //         high = prices[i];
    //         profit += high-low;
    //     }
    //     return profit;
    // }

    /*
     * Solution 3: DP
     * time compelxity: O(n)
     * space complexity: O(n) because of dp[]
     */
    // public int maxProfit(int[] prices) {
    //     int n = prices.length;
    //     int dp[] = new int[n];
    //     dp[0] = 0;
    //     for(int i=1; i<n; i++) {
    //         dp[i] = dp[i-1]+ Math.max(0, prices[i]-prices[i-1]);
    //     }
    //     return dp[n-1];
    // }

    /*
     * Solution 4: DP variation - to make O(1) space complexity because it just needs dp[n-1]
     */
    // public int maxProfit(int[] prices) {
    //     int n = prices.length;
    //     int profit = 0;
    //     for(int i=1; i<n; i++) {
    //         profit += Math.max(0, prices[i]-prices[i-1]);
    //     }
    //     return profit;
    // }

}

/*
 * Except Soution 3
 * time complexity: O(n)
 * space complexity: O(1)
 */
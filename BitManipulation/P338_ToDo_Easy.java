/*
    338. Counting Bits

    Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.

    Example 1:
        Input: n = 2
        Output: [0,1,1]
        Explanation:
            0 --> 0
            1 --> 1
            2 --> 10

    Example 2:
        Input: n = 5
        Output: [0,1,1,2,1,2]
        Explanation:
            0 --> 0
            1 --> 1
            2 --> 10
            3 --> 11
            4 --> 100
            5 --> 101
    
    Constraints:
    0 <= n <= 1 
    
    Follow up:
        It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
        Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
 */
package BitManipulation;

public class P338_ToDo_Easy {
    /*
     * Solution 1: Pop Count
     *      a follow-up of the Number of 1 Bits (P191), where we need to count the bits for an unsigned integer. The number is often called pop count or Hamming weight. 
     * 
     * time complexity: O(nlogN)
     * space complexity: O(1)
     */
    public int[] countBits(int n) {
        int[] answer = new int[n+1];
        for(int i= 0; i<=n; i++) {
            answer[i] = popCount(i);
        }
        return answer;
    }

    int popCount(int i) {
        int count = 0;
        while(i!=0) {
            i&=i-1;
            count++;
        }
        return count;
    }

    /*
     * Solution 2: DP + least significant bit
     *      the relation between x and x'= x/2 --> we can see that x' is differ than x by one bit, because x' can be considered as the result of removing the least significant bit of x.
     *      Thus, we have the following transition function of pop count P(x):
     *          p(x) = p(x/2) + (x mod 2)
     * 
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int[] countBits_DP(int n) {
        int[] answer = new int[n+1];
        for (int i = 0; i<=n; i++) {
            // x/2 is x>>1
            // x%2 is x&1
            answer[i] = answer[i>>2] + (i&1);
        }
        return answer;
    }

    /*
     * Solution 3: DP + Last Set Bit
     *      Last set bit is the rightmost set bit. Setting that bit to zero with the bit trick, x &= x-1, leads to the following transition function:
     *          p(x) = p( x&(x-1))+1
     * 
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int[] countBits_DP2(int n) {
        int[] answer = new int[n+1];
        for(int i=1; i<=n; i++) {
            answer[i] = answer[i&(i-1)]+1;
        }
        return answer;
    }
}

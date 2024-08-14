/*
    55. Jump Game

    You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
    Return true if you can reach the last index, or false otherwise.

    Example 1:
        Input: nums = [2,3,1,1,4]
        Output: true
        Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

    Example 2:
        Input: nums = [3,2,1,0,4]
        Output: false
        Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.

    Constraints:
        1 <= nums.length <= 10^4
        0 <= nums[i] <= 10^5
 */

package Array_String;

enum Status {
    GOOD, BAD, UNKNOWN
}

public class Top150_P55_ToDo_DP_Medium {
    /*
     * Solution 1 : Top-down DP with memoization
     * 
     * Top-down DP can be thought of as optimized backtracking. It relies on the observation that once we determine that a certain index is good/bad, this result will never change. 
     * This means that we can store the result and not need to recompute it every time. 
     * Therefore, for each position in the array, we remember whether the index is good or bad, which is memoization technique. 
     * 
     * time complexity: O(n^2)
     * space complexity: O(n)
     */
    
    // Status[] memo;
    // public boolean canJump(int[] nums) {
    //     memo = new Status[nums.length];
    //     for (int i=0; i<nums.length; i++) {
    //         memo[i] = Status.UNKNOWN;
    //     }
    //     // last index always reaches itself.
    //     memo[nums.length-1] = Status.GOOD;
    //     return checkJump(0, nums);
    // }

    // boolean checkJump(int pos, int[] nums) {
    //     if(memo[pos]!=Status.UNKNOWN) return memo[pos] == Status.GOOD ? true : false;
        
    //     // find the furthest index which it can jump
    //     int furtherJump = Math.min(pos + nums[pos], nums.length-1);

    //     // one quick optimizaation - check from right to left instead of checking from left to right. 
    //     // the theoritical worst case performance is the same, but in practice for silly examples, the code might run faster.
    //     // Intuitively, this means we always try to make the biggest jump such that we reach the end asap
    //     for(int nextPos = furtherJump; nextPos> pos; nextPos--) {
    //         if(checkJump(nextPos, nums)) {
    //             memo[pos] = Status.GOOD;
    //             return true;
    //         }
    //     }
    //     memo[pos] = Status.BAD;
    //     return false;
    // }

    /*
     * Solution 2: Bottom UP DP.
     * Top-down to Bottom-up conversion is done by eliminating recursion. 
     * In practice, this achieves better performance as we no longer have the method stack overhead and might even benefit from some caching.
     * More importantly, this step opens up possibilities for future optimization. The recursion is usually eliminated by trying to reverse the order of the steps from the top-down approach.
     * 
     * The observation to make here is that we only ever jump to the right. This means that if we start from the right of the array, every time we will query a position to our right,
     * that position has already be determined as being GOOD or BAD. This means we don't need to recurse anymore, as we will always hit the memo table
     * 
     * time complexity: O(n^2)
     * space complexity: O(1)
     */
    // public boolean canJump(int[] nums) {
    //     Status[] memo = new Status[nums.length];
    //     for(int i=0; i<nums.length; i++) {
    //         memo[i] = Status.UNKNOWN;
    //     }
    //     memo[nums.length-1] = Status.GOOD;

    //     for(int pos=nums.length-2; pos>=0; pos--) {
    //         int furtherJump = Math.min(nums.length-1, pos+nums[pos]);
    //         for(int nextpos = furtherJump; nextpos>pos; nextpos-- ) {
    //             if(memo[nextpos] == Status.GOOD) {
    //                 memo[nextpos] = Status.GOOD;
    //                 break;
    //             }
    //         }
    //     }
    //     return memo[0] == Status.GOOD;
    // }

    /*
     * Solution 3 - Greedy
     * 
     * From a given position, when we try to see if we can jump to a Good position, we only ever use one - the first one. In other words, the left-most one. 
     * if we keep track of this left-most GOOD position as a separate veriable, we can avoid searching for it in the array. Not only that, but we can stop using the array altogether.
     * 
     * Iterating right-to-left, for each position we check if there is a potential jump that reaches a GOOD index. If we can reach a GOOD index, then our position is itself GOOD.
     * Also, this new GOOD position will be the new leftmost GOOD index. Iteration coninues until the beginning of the array. If first position is GOOD, 
     * then we can reach the last index from the first position.
     * 
     * time complexity: O(n)
     * space complexity: O(1)
     */
    // public boolean canJump(int[] nums) {
    //     int lastpos = nums.length-1;
    //     for(int pos = nums.length-1; pos>=0; pos--) {
    //         if(pos+nums[pos] >= lastpos) {
    //             lastpos = pos;
    //         }
    //     }
    //     return lastpos == 0;
    // }

    /*
     * Solution 4
     * Approach: at each step, we keep track of the furthest jump index. The nature of the problem (eg. maximal jumps where you can hit a range of targets 
     * instead of singular jumps where you can only hit one target) is that for an index to be reachable, each of the previous indices have to be reachable. 
     * 
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public boolean canJump(int[] nums) {
        int furtherJump = 0;
        for(int pos=0; pos<nums.length; pos++) {
            if (pos> furtherJump) return false;
            furtherJump = Math.max(furtherJump, pos+nums[pos] );
        }
        return true;
    }
}

/*
    45. Jump Game II

    You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
    Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
        - 0 <= j <= nums[i] and
        - i + j < n
    Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

    Example 1:
        Input: nums = [2,3,1,1,4]
        Output: 2
        Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

    Example 2:
        Input: nums = [2,3,0,1,4]
        Output: 2
    
    Constraints:
        1 <= nums.length <= 10^4
        0 <= nums[i] <= 1000
        It's guaranteed that you can reach nums[n - 1].
 */

package Array_String;

public class Top150_P45_ToDo_DP_BFS_Medium {

    /*
     * Solution 1
     * Note:
     *      The only way there are 0 jump is if you start on the last index, which means that length of nums is 1.
     *      So in the same manner, if you're in the first iteration, then there will be at least 1 jump.
     *      
     *      The problem asks for shortest path and it's natural to think of BFS. Nodes are array elements. For an element, neighbors are the elements that are within its max jump length.
     *      Given nodes in current level, we generate all nodes in the next level. Nodes are traversed level by level.
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int jump(int[] nums) {
        int count = 0;
        int farJump = 0; // the furthest reachable index from one of indices in the current position's moveable range
        int maxEnd = 0; // the maximum index of the jump range from the current position

        for(int i=0; i<nums.length-1; i++) {
            // update the furthest reachable index of this jump
            farJump = Math.max(farJump, i+nums[i]);
            
            if(farJump >= nums.length-1) {
                count++;
                break;
            }

            // when we visit all items on the current moveable range
            // move on to the next moveable range
            if(maxEnd == i) {
                maxEnd = farJump;
                count++;
            }
        }
        return count;
    }

    /*
     * Solution 2 - Top-down DP
     * 
     * time complexity: O(n^2)
     * space complexity: O(1)
     */
    // public int jump(int[] nums) {
    //     int memo[] = new int[nums.length];
    //     Arrays.fill(memo, Integer.MAX_VALUE);
    //     memo[0]=0;
    //     for(int i=0; i<nums.length; i++) {
    //         for(int j=1; j<=nums[i]; j++) {
    //             if(i+j < nums.length) {
    //                 memo[i+j] = Math.min(memo[i+j], memo[i]+1);
    //             }
    //         }
    //     }
    //     return memo[nums.length-1];
    // }
}
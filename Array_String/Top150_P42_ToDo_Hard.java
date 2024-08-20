/*
    42. Trapping Rain Water
    Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

    Example 1:
        Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
        Output: 6
        Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

    Example 2:
        Input: height = [4,2,0,3,2,5]
        Output: 9
    
    Constraints:
        n == height.length
        1 <= n <= 2 * 10^4
        0 <= height[i] <= 10^5
 */

package Array_String;

import java.util.Stack;

public class Top150_P42_ToDo_Hard {
    /*
     * Solution 1: DP
     * time complexity: O(n)
     * space complexity: O(n)
     * 
     * Algorithm:
     *      find maximum height of bar from the left end upto an index i in the array left_array - leftmost height at each index i
     *      find maximum height of bar from the right end upto an index i in the array right_array - rightmost height at each index i
     *      calcuate water based on left_array and right_array - sum of min(left_array[i], right_array[i]) - height[i] between i=1 and i=n-2; because index 0 and index n-1 can't trap any water
     */
    // public int trap(int[] height) {
    //     int n = height.length;
    //     int[] left_height = new int[n];
    //     int[] right_height = new int[n];
    //     int total_tank = 0;

    //     left_height[0] = height[0];
    //     for(int i=1; i<n; i++) {
    //         left_height[i] = Math.max(left_height[i-1], height[i]);
    //     }

    //     right_height[0] = height[n-1];
    //     for(int i=n-2; i>=0; i--) {
    //         right_height[i] = Math.max(right_height[i+1], height[i]);
    //     }

    //     for(int i=1; i<n-1; i++) {
    //         total_tank += Math.min(left_height[i], right_height[i]) - height[i];
    //     }

    //     return total_tank;
    // }

    /*
     * Solution 2: Using Stack
     * time complexity: O(n)
     * space complexity: O(n)
     */
    // public int trap(int[] height) {
    //     int total_tank = 0;
    //     Stack<Integer> stack = new Stack<>();
    //     for(int right=0; right <height.length; i++) {
    //         // find the highest right height
    //         while(!stack.isEmpty() && height[stack.peek()]<height[right]) {
    //             int middle = stack.pop();
    //             if(stack.isEmpty()) break;
    //             int left = stack.peek();
    //             int water_height = Math.min(height[left], height[right]) - height[middle];
    //             int water_width = right - left -1;
    //             total_tank += (water_height*water_width);
    //         }
    //         stack.push(right);
    //     }
    // }

    /*
     * Solution 3: Using 2 pointers
     * time complexity: O(n)
     * space complecity: O(1)
     */
    public int trap(int[] height) {
        int n = height.length;
        int leftmost = 0, rightmost = 0;
        int left = 0, right = n-1;
        int total_tank = 0;
        while(left<right) {
            if(height[left] <= height[right]) {
                leftmost = Math.max(leftmost, height[left]);
                total_tank += leftmost - height[left];
                left++;
            } else {
                rightmost = Math.max(rightmost, height[right]);
                total_tank += rightmost - height[right];
                right--;
            }
        }
        return total_tank;
    }

    /*
     * Solution 4: Using 2 pointers with a different approach
     */
    // public int trap(int[] height) {
    //     int left = 0, right = height.length-1;
    //     int leftmost = Integer.MIN_VALUE, rightmost = Integer.MIN_VALUE;
    //     int total_tank=0;

    //     while(left < right) {
    //         leftmost = Math.max(leftmost, height[left]);
    //         rightmost = Math.max(rightmost, height[right]);

    //         total_tank += leftmost < rightmost ? leftmost - height[left++] : rightmost - height[right--];
    //     }
    //     return total_tank;
    // }
}

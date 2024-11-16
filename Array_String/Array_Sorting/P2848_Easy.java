/*
    2848. Points that intersect with cars
    You are given a 0-indexed 2D integer array nums representing the coordinates of the cars parking on a number line. For any index i, nums[i] = [starti, endi] where starti is the starting point of the ith car and endi is the ending point of the ith car.
    Return the number of integer points on the line that are covered with any part of a car.

    Example 1:
        Input: nums = [[3,6],[1,5],[4,7]]
        Output: 7
        Explanation: All the points from 1 to 7 intersect at least one car, therefore the answer would be 7.

    Example 2:
        Input: nums = [[1,3],[5,8]]
        Output: 7
        Explanation: Points intersecting at least one car are 1, 2, 3, 5, 6, 7, 8. There are a total of 7 points, therefore the answer would be 7.
    
    Constraints:
        1 <= nums.length <= 100
        nums[i].length == 2
        1 <= starti <= endi <= 100
 */
package Array_String.Array_Sorting;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P2848_Easy {

    /*
     * Solution: sort
     * time complexity: O(nlogn)
     * space complexity: O(1)
     */
    public int numberOfPoints(List<List<Integer>> nums) {
        Collections.sort(nums, (a,b)-> Integer.compare(a.get(0), b.get(0)));
        List<Integer> prev = nums.get(0);
        int num = 0;
        for(int i=1; i<nums.size(); i++) {
            List<Integer> curr = nums.get(i);
            if(prev.get(1)>=curr.get(0)) {
                prev.set(1, Math.max(prev.get(1), curr.get(1)));
            } else {
                num += prev.get(1)-prev.get(0)+1;
                prev = curr;
            }
        }
        num+= prev.get(1)-prev.get(0)+1;
        return num;
    }

    /*
     * Solution: Set
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int numberOfPoints(List<List<Integer>> nums) {
        Set<Integer> set = new HashSet<>();
        for(List<Integer> pair: nums) {
            for(int left = pair.get(0); left<=pair.get(1); left++) {
                set.add(left);
            }
        }
        return set.size();
    }
}

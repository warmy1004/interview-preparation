/*
    452. Minimum number of arrows to burst balloons
    There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
    Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
    Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

    Example 1:
        Input: points = [[10,16],[2,8],[1,6],[7,12]]
        Output: 2
        Explanation: The balloons can be burst by 2 arrows:
        - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
        - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].

    Example 2:
        Input: points = [[1,2],[3,4],[5,6],[7,8]]
        Output: 4
        Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.

    Example 3:
        Input: points = [[1,2],[2,3],[3,4],[4,5]]
        Output: 2
        Explanation: The balloons can be burst by 2 arrows:
        - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
        - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].

    Constraints:
        1 <= points.length <= 10^5
        points[i].length == 2
        -2^31 <= xstart < xend <= 2^31 - 1
 */
package Array_String.Array_Sorting;

import java.util.Arrays;

public class P452_Must_ToDo_Medium {
    /*
     * Solution: Greedy + sort based on end
     *      this is easier to understand than sort based on start
     * time complexity: O(nlogn)
     * space complexity: O(1)
     * 
     */
    public int findMinArrowShots(int[][] points) {
        /*
            sorting by start doesn't guarantee the optimal placement of arrows because:
                - If you place the arrow based on the start of an interval, you might miss balloons that end before the current arrow's position
                - This can lead to shooting unnecessary extra arrows.
            Sorting by End ensures that you minimize the number of arrows required by covering the largest number of overlapping balloons with each arrow. 
        */
        Arrays.sort(points, (a,b) -> Integer.compare(a[1], b[1]));

        int arrow = 1;
        int prevEnd = points[0][1];
        for(int[] point: points) {
            // not overlap, so increase arrow, and change the prev to the curr
            // arrow 하나로 처리하려면, 겹치는 부분이 있어야만 함. 이 때 기준이, 제일 작은 end 값 기준
            if(prevEnd < point[0]) {
                arrow++;
                prevEnd = point[1];
            }
        }
        return arrow;
    }

    /*
     * Solution: Greedy + sort based on start
     * time complexity: O(nlogn)
     * space complexity: O(logn) 
     *      in java, the Arrays.sort() is implemented as a variant of quicksort algorithm whose space complexity is O(logn)
     * 
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a,b)->Integer.compare(a[0], b[0]));
        int[] prev = points[0];
        int count = 1;
        for(int i=1; i<points.length; i++) {
            // Checking the overlapped range. Only overlapped area is needed for one arrow
            if(prev[1] >= points[i][0]) {
                prev[0] = Math.max(prev[0], points[i][0]);
                prev[1] = Math.min(prev[1], points[i][1]);
            } else {
                count++;
                prev = points[i];
            }
        }
        return count;
    }
}

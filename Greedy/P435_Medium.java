/*
    435. Non-overlapping intervals
    Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
    Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.

    Example 1:
        Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
        Output: 1
        Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

    Example 2:
        Input: intervals = [[1,2],[1,2],[1,2]]
        Output: 2
        Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.

    Example 3:
        Input: intervals = [[1,2],[2,3]]
        Output: 0
        Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
    
    Constraints:
        1 <= intervals.length <= 10^5
        intervals[i].length == 2
        -5 * 10^4 <= starti < endi <= 5 * 10^4
 */
package Greedy;

import java.util.Arrays;

public class P435_Medium {
    /*
     * Solution: Greedy
     * 
     * Approach:
     *      최대 겹치지 않는 구간의 개수를 구한 뒤, 전체 구간에서 그 개수를 빼는 방식으로 해결 가능
     *      최대 겹치지 않는 구간 찾기:
     *          서로 겹치지 않는 구간을 최대한 많이 유지하려면, 종료시간이 빠른 구간을 선택해야한다. 즉, 구간을 end 기준으로 정렬하고 겹치는 경우 구간을 제거한다. 대표적인 greedy 문제
     * 
     * time complexity: O(nlogn)
     * space complexity: O(logn) because of Arrays.sort() which is implemented using a variant of QuickSort algorithm, that has a space complexity of O(logn)
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> Integer.compare(a[1], b[1]));
        int nonOverlap = 1;
        int prev = 0;
        for(int i=1; i<intervals.length; i++) {
            if(intervals[prev][1] <= intervals[i][0]) {
                nonOverlap ++;
                prev = i;
            }
        }
        return intervals.length-nonOverlap;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> Integer.compare(a[1], b[1]));
        int count = 0;
        int prev = 0;
        for(int i=1; i<intervals.length; i++) {
            if(intervals[prev][1]> intervals[i][0]) {
                count++;
            } else {
                prev = i;
            }
        }
        return count;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> Integer.compare(a[1], b[1]));
        int prevEnd = intervals[0][1];
        int count = 0;
        for(int i=1; i<intervals.length; i++) {

            // if the current interval overlaps with the previous one, remove the interval
            if(prevEnd > intervals[i][0]) {
                count++;
            } else {
                // otherwise, update the prevEnd time to the current interval's end
                prevEnd = intervals[i][1];
            }
        }
        return count;
    }
}

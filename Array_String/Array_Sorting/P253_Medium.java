/*
    253. Meeting Rooms 2
    Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

    Example 1:
        Input: intervals = [[0,30],[5,10],[15,20]]
        Output: 2

    Example 2:
        Input: intervals = [[7,10],[2,4]]
        Output: 1

    Constraints:
        1 <= intervals.length <= 10^4
        0 <= starti < endi <= 10^6
 */

 /*
  * Purpose: find the maximum number of overlapping intervals
  *     start 배열과 end 배열을 따로 정렬한 뒤, '각각의 구간이 시작되는 시점'과 '끝나는 시점'을 비교하며 겹치는 구간의 수를 추적한다. 이를 통해 '최대' 겹치는 구간을 구할 수 있다.
  * Similar question
  *     2406. Divide intervals into minimum number of groups - medium
  */
package Array_String.Array_Sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

public class P253_Medium {
    /*
     * Solution: Sort + MinHeap
     * time complexity: O(nlogn)
     * space complexity: O(n)
     */
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> Integer.compare(a[0], b[0]));
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        for(int[] pair: intervals) {
            if(!heap.isEmpty() && heap.peek()[1] <= pair[0]) {
                heap.remove();
            }
            heap.add(pair);
        }
        return heap.size();
    }

    /*
     * Solution: array + sort
     * time complexity: O(nlogn)
     * space complexity: O(n)
     */
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for(int i=0; i<n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int result = 0, count = 0;
        int i=0, j=0;
        while(i<n) {
            // 새로운 구간이 시작되므로, 겹치는 구간 수를 증가시키고 최대 겹치는 구간 수를 갱신한다.
            if(starts[i] < ends[j]) {
                i++;
                count++;
            } else {
                // 이전 구간이 끝났으므로 현재 겹치는 구간의 수를 감소시킨다.
                j++;
                count--;
            }
            result = Math.max(count, result);
        }
        return result;
    }

    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for(int i=0; i<n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int count = 0;
        int j=0;
        for(int i=0; i<n; i++){
            if(starts[i] < ends[j]) {
                count++;
            } else {
                j++;
            }
        }
        return count;
    }

    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for(int i=0; i<n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int j=0;
        for(int i=0; i<n; i++){
            if(starts[i] >= ends[j]) {
                j++;
            }
        }
        return n-j;
    }
}

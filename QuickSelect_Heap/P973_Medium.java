/*
    973. K cloest points to origin
    Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
    The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
    You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

    Example 1:
        Input: points = [[1,3],[-2,2]], k = 1
        Output: [[-2,2]]
        Explanation:
        The distance between (1, 3) and the origin is sqrt(10).
        The distance between (-2, 2) and the origin is sqrt(8).
        Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
        We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].

    Example 2:
        Input: points = [[3,3],[5,-1],[-2,4]], k = 2
        Output: [[3,3],[-2,4]]
        Explanation: The answer [[-2,4],[3,3]] would also be accepted.
    
    Constraints:
        1 <= k <= points.length <= 10^4
        -10^4 <= xi, yi <= 10^4
 */
package QuickSelect_Heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class P973_Medium {
    /*
     * Solution: Heap
     * time complexity: O(nlogk)
     * space complexity: O(k)
     */
    public int[][] kClosest(int[][] points, int k) {
        Map<Integer, Double> map = new HashMap<>();
        for(int i=0; i<points.length; i++) {
            map.put(i, Math.pow(points[i][0], 2)+Math.pow(points[i][1], 2));
        }

        Queue<Integer> closestIndex = new PriorityQueue<>((a,b)-> (int) (map.get(a)-map.get(b)));
        for(int idx: map.keySet()) {
            closestIndex.add(idx);
        }

        int[][] result = new int[k][2];
        for(int i=0; i<k; i++) {
            result[i] = points[closestIndex.remove()];
        }
        return result;
    }

    /*
     * Solution: sort with custom comparator
     * time complexity: O(nlogn)
     * space complexity: O(logN) to O(n) for the extra space required by the sorting process
     */
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (a, b)-> getPower(a)-getPower(b));
        return Arrays.copyOf(points, k);

    }

    int getPower(int[] point) {
        return point[0]*point[0] + point[1]*point[1];
    }

    /*
     * Solution: quick select
     * time complexity: O(n)
     * space complexity: O(n) or O(logn) because of recursion
     */
    public int[][] kClosest(int[][] points, int k) {
        return quickselect(points, 0, points.length-1, k);
    }

    int[][] quickselect(int[][] points, int start, int end, int k) {
        int pivot = partition(points, start, end);

        //앞에서 푼 문제들과는 다르게, 이건 smallest/cloest 값을 찾는 것이기 때문에, 0-based 배열에서 0번째 인덱스부터 비교하게 된다. 
        // 따라서, k가 아닌, k-1과 비교해야 k번째까지의 가까운 인덱스를 구할 수 있다.
        if(pivot==k-1) {
            return Arrays.copyOf(points, k);
        } else if(pivot < k-1) {
            return quickselect(points, pivot+1, end, k);
        } else {
            return quickselect(points, start, pivot-1, k);
        }      
    }

    int partition(int[][] points, int start, int end) {
        int pivot = start;
        for(int i=start; i<end; i++) {
            if(getPowered(points[i]) < getPowered(points[end])) {
                swapIndex(points, i, pivot);
                pivot++;
            }
        }
        swapIndex(points, pivot, end);
        return pivot;
    }

    int getPowered(int[] point) {
        return point[0]*point[0] + point[1]*point[1];
    }

    void swapIndex(int[][] points, int a, int b) {
        int[] temp = points[a];
        points[a] = points[b];
        points[b] = temp;
    }
    
    /*
     * Solution: quick select without recursion
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int[][] kClosest(int[][] points, int k) {
        return quickselect(points, k);
    }

    int[][] quickselect(int[][] points, int k) {
        int left = 0, right = points.length-1;
        int pivot = points.length;
        while(pivot!=k) {
            pivot = partition(points, left, right);
            if(pivot < k) {
                left = pivot;
            } else {
                right = pivot -1;
            }
        }
        return Arrays.copyOf(points, k);
    }

    int partition(int[][] points, int left, int right) {
        int[] pivot = choosePivot(points, left, right);
        int pivotDist = calculateDist(pivot);
        while(left<right) {
            if(calculateDist(points[left]) >= pivotDist) {
                int[] temp = points[left];
                points[left] = points[right];
                points[right] = temp;
                right--;
            } else {
                left++;
            }
        }
        if(calculateDist(points[left]) < pivotDist) {
            left++;
        }
        return left;
    }

    private int[] choosePivot(int[][] points, int left, int right) {
        // Choose a pivot element of the array
        return points[left + (right - left) / 2];
    }

    int calculateDist (int[] point) {
        return point[0]*point[0] + point[1]*point[1];
    }

}

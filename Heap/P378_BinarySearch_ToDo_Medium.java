/*
    378. Kth smallest element in a sorted matrix
    Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
    Note that it is the kth smallest element in the sorted order, not the kth distinct element.
    You must find a solution with a memory complexity better than O(n2).

    Example 1:
    Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
    Output: 13
    Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13

    Example 2:
    Input: matrix = [[-5]], k = 1
    Output: -5
    
    Constraints:
        n == matrix.length == matrix[i].length
        1 <= n <= 300
        -109 <= matrix[i][j] <= 10^9
        All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
        1 <= k <= n2
    
    Follow up:
        Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
        Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.
 */
package Heap;

import java.util.PriorityQueue;

public class P378_BinarySearch_ToDo_Medium {
    /*
     * Solution: heap
     * time complexity: O(n^2)
     * space complexity: O(k)
     */
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b)-> b-a);
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                heap.add(matrix[i][j]);
                if(heap.size()>k) heap.remove();
            }
        }
        return heap.poll();

    }

    /*
     * Solution: binary search
     * time complexity: O(N*log(max-min))
     *      For a one-dimensional binary search over an array with N elements, the complexity comes out to be O(logN)
     *      For our secnario, we are kind of defining our binary search space in terms of the minimum and the maximum numbers in the array. 
     *          Going by this idea, the complexity for our binary search should be O(log(max-min)) where max is the maximum element in the array and likewise, Min is the mimimum elemtn. 
     *      However, we update our search space after each iteration. So, even if the maimum element is super large as compared to the remaining elements in the matrix, we will bring down the search space considerably in the next iterations. 
     *          But, going pruely by the extremes for our search space, the complexity of our binary search in search of Kth smallest element will be O(log(max-min))
     *      in each iteration of our binary search approach, we iterate over the matrix trying to determine the size of the left-half as explained before. That takes O(n)
     *      Thus, the overall time complexity is O(n*log(max-min))
     * space complexity: O(1)
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0], high = matrix[n-1][n-1];
        while(low<high) {
            // TLE prevention...?
            int mid = low + (high-low)/2;
            int[] smallLargePair = {low, high};
            int count = getCount(matrix, mid, smallLargePair);

            if(count==k) return smallLargePair[0];
            else if(count < k) low = smallLargePair[1]; // search higher
            else high = smallLargePair[0]; // search lower
        }
        return low;
    }

    int getCount(int[][] matrix, int target, int[] smallLargePair) {
        int n = matrix.length;
        int i=n-1, j=0;
        int count = 0;
        while(i>=0 && j<n) {
            if(matrix[i][j] > target) {
                // as matrix[i][j] is bigger than the mid, let's keep track of the smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[i][j]);
                i--;
            } else {
                // as matrix[i][j] is less than or equal to the mid, let's keep track of the biggest number less than or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[i][j]);
                count+=i+1;
                j++;
            }
        }
        return count;
    }

    /*
     * Solution: binary search v2
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0], high = matrix[n-1][n-1];
        while(low<high) {
            int mid = low + (high-low)/2;
            int count = getCount(matrix, mid);
            if(count < k) low = mid+1; 
            else high = mid; 
        }
        return low;
    }

    int getCount(int[][] matrix, int mid) {
        int n = matrix.length;
        int count = 0;
        int i=0, j = n-1;
        while(i<n && j>=0) {
            if(matrix[i][j] > mid) {
                j--;
            } else {
                count += j+1;
                i++;
            }
        }
        return count;
    }
}

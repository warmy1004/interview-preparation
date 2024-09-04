/*
    1539. Kth Missing Positive Number
    Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
    Return the kth positive integer that is missing from this array.

    Example 1:
        Input: arr = [2,3,4,7,11], k = 5
        Output: 9
        Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.

    Example 2:
        Input: arr = [1,2,3,4], k = 2
        Output: 6
        Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
    
    Constraints:
        1 <= arr.length <= 1000 
        1 <= arr[i] <= 1000
        1 <= k <= 1000
        arr[i] < arr[j] for 1 <= i < j <= arr.length
 
    Follow up: Could you solve this problem in less than O(n) complexity?
 */
package BinarySearch;

public class P1539_ToDo_Easy {
    /*
     * Solution: Binary Search
     * time complexity: O(logn)
     * space complexity: O(1)
     */
    public int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length-1;
        while(left<=right) {
            int mid = left + (right-left)/2;
            // if number of positive integers which are missing before arr[mid] is less than k, continue to search on the right
            // arr[mid]-mid-1 ==> the number of missing integers before the mid index in arr. 
            // for example, array without missing integers = [1,2,3,4,5], acutual input array = [2,3,4,7,11]
            // ----> before index 3, value 7, there are 7-4 = 3 missing integers
            if(arr[mid]- (mid+1) < k) {
                left = mid +1;
            } else {
                right = mid -1;
            }
        }

        // At the end of while loop, left = right+1, and the kth missing is in-between arr[right] and arr[left].
        // the number of integers missing before arr[right] is arr[right]-right-1 
        // if there is no missing element in the given arr, then arr[right]+k will be the final answer. 
        // ---> so, the number to return is arr[right]+k - (arr[right]-right-1) = k + right + 1 = k + left
        return left + k;
    }
    
}

/*
    4. Median of Two sorted arrays
    Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
    The overall run time complexity should be O(log (m+n)).

    Example 1:
        Input: nums1 = [1,3], nums2 = [2]
        Output: 2.00000
        Explanation: merged array = [1,2,3] and median is 2.

    Example 2:
        Input: nums1 = [1,2], nums2 = [3,4]
        Output: 2.50000
        Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

    Constraints:
        nums1.length == m
        nums2.length == n
        0 <= m <= 1000
        0 <= n <= 1000
        1 <= m + n <= 2000
        -10^6 <= nums1[i], nums2[i] <= 10^6
 */
package BinarySearch;

public class P4_Hard {
    /*
     * Solution: Sort with two pointers
     * time complexity: O(n+m)
     * space complexity: O(n+m)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[] merge = new int[n+m];

        int i=0, j=0, k=0;
        while(i<n && j<m) {
            if(nums1[i] < nums2[j]) {
                merge[k] = nums1[i++];
            } else {
                merge[k] = nums2[j++];
            }
            k++;
        }
        while(i<n) {
            merge[k++] = nums1[i++];
        }
        while(j<m) {
            merge[k++] = nums2[j++];
        }

        if( (n+m)%2 == 1) {
            return merge[(n+m)/2];
        } else {
            return ((double)merge[(n+m)/2] + (double)merge[(n+m)/2-1])/2;
        }
    }

    /*
     * Solution: binary search
     * time complexity: O(log(n+m))
     * space complexity: O(1)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1= nums1.length;
        int n2= nums2.length;

        if(n1==0) {
            return n2%2 == 0? (nums2[n2/2-1]+nums2[n2/2])/2.0 : nums2[n2/2];
        }
        if(n2==0) {
            return n1%2 == 0? (nums1[n1/2-1]+nums1[n1/2])/2.0 : nums1[n1/2];
        }
        if(n1>n2) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int low = 0, high = n1;
        while(low<=high) {
            int i = (low+high)/2;
            int j = (n1+n2+1)/2-i;

            int max1 = i==0 ? Integer.MIN_VALUE: nums1[i-1];
            int min1 = i==n1 ? Integer.MAX_VALUE: nums1[i];

            int max2 = j==0 ? Integer.MIN_VALUE: nums2[j-1];
            int min2 = j==n2 ? Integer.MAX_VALUE: nums2[j];

            if(max1<=min2 && max2<= min1) {
                if( (n1+n2)%2 == 0) {
                    return (Math.max(max1,max2) + Math.min(min1, min2))/2.0;
                } else {
                    return Math.max(max1, max2);
                }
            } else if(max1>min2) {
                // This occurs when the partition in nums1 is too far to the right, 
                // meaning that the maximum value from nums1 on the left side is greater than the minimum value from nums2 on the right side.
                // That is, we need to look for a smaller partition in nums1.
                // nums1 = [5, 10, 15], nums2 = [1,2,3,4]
                high = i-1;
            } else {
                // we need to increase i (move right in nums1) to try to iclude larger elements from nums1 in the left partition. nums1 = [1,2,3] , nums2 = [4,5]
                // this is used when the maximum value on the left side of the partition in nums2 is greater than the minimum value on the right side of the partition nums1. 
                // it indicates we need a larger partition in nums1.
                low = i+1;
            }
        }
        return -1;
    }
}

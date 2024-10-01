/*
    1395. Count Number of Teams
    There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
    You have to form a team of 3 soldiers amongst them under the following rules:
    Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
    A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
    Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).

    Example 1:
        Input: rating = [2,5,3,4,1]
        Output: 3
        Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1). 

    Example 2:
        Input: rating = [2,1,3]
        Output: 0
        Explanation: We can't form any team given the conditions.

    Example 3:
        Input: rating = [1,2,3,4]
        Output: 4
    
    Constraints:
        n == rating.length
        3 <= n <= 1000
        1 <= rating[i] <= 10^5
        All the integers in rating are unique.
 */
package DP;

public class P1395_Medium {
    /*
     * Solution
     * time complexity: O(n^2)
     * space complexity: O(1)
     */
    public int numTeams(int[] rating) {
        int n = rating.length;
        int units = 0;

        for (int mid=0; mid<n; mid++) {
            int smaller_left = 0;
            int larger_right = 0;

            for(int left = mid-1; left>=0; left--) {
                if (rating[left]<rating[mid]) {
                    smaller_left++;
                }
            }

            for(int right = mid+1; right<n; right++) {
                if(rating[mid]<rating[right]){
                    larger_right++;
                }
            }
            units+= smaller_left*larger_right;

            int larger_left = mid - smaller_left;
            int smaller_right = n - mid-1 - larger_right;
            units+= larger_left * smaller_right;
        }
        return units;
    }
}

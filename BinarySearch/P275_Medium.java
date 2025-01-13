/*
    275. H-index 2
    Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper and citations is sorted in ascending order, return the researcher's h-index.
    According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the given researcher has published at least h papers that have each been cited at least h times.
    You must write an algorithm that runs in logarithmic time.

    Example 1:
        Input: citations = [0,1,3,5,6]
        Output: 3
        Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had received 0, 1, 3, 5, 6 citations respectively.
        Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.

    Example 2:
        Input: citations = [1,2,100]
        Output: 2
    
    Constraints:
        n == citations.length
        1 <= n <= 10^5
        0 <= citations[i] <= 1000
        citations is sorted in ascending order.
 */
package BinarySearch;

/*
 * Per the definition of H-index, we need to find the first paper at index i, where citation number c = citation[i] is greater than or equal to n-i, i.e. c>=n-i.
 * Since all papers after paper i are cited at least c times, there are n-i papers (including paper i) that are cited at least c times. 
 * In other words, the H-index is n-i
 * 
 * n-i means the number of papers has at least citations[i] citations
 */
public class P275_Medium {
    /*
     * Linear search
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int idx = 0;
        for(int c: citations) {
            if(c >= n-idx) {
                return n-idx;
            } else {
                idx++;
            }
        }
        return 0;
    }

    public int hIndex(int[] citations) {
        int n = citations.length;
        int max = 0;
        for(int i=0; i<n; i++) {
            if(citations[i] >= n - i) {
                max = Math.max(max, n-i);
            }
        }
        return max;
    }

    /*
     * binary search
     * time complexity: O(logn)
     * space complexity: O(1)
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n-1;
        while(left<=right) {
            int mid = (left+right)/2;
            if(citations[mid] == n-mid) {
                return n-mid;
            } else if(citations[mid] < n-mid) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return 0;
    }

    public int hIndex(int[] citations) {
        int low = 0, high = citations.length;
        while(low<high) {
            int mid = (low+high)/2;
            if(citations[mid] >= citations.length-mid) {
                high = mid;
            } else {
                low = mid+1;
            }
        }
        return citations.length-low;
    }

    /*
     * Solution: couting sort
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n+1];
        for(int c: citations) {
            papers[Math.min(c, n)]++;
        }
        int total = 0;
        for(int i=n; n>=0; i--) {
            total += papers[i];
            if(total>=i) {
                return i;
            }
        }
        return 0;
    }
}

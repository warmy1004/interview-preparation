/*
    274. H-Index

    Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return the researcher's h-index.
    According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the given researcher has published at least h papers that have each been cited at least h times.

    Example 1:
        Input: citations = [3,0,6,1,5]
        Output: 3
        Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
        Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.

    Example 2:
        Input: citations = [1,3,1]
        Output: 1
    
    Constraints:
        n == citations.length
        1 <= n <= 5000
        0 <= citations[i] <= 1000
 */


package Array_String;

/*
 * H-index can't be greater than the count of papers (the given array's length)
 * Goal: find max value of V such taht V of the papers have >= V number of citations.
 */
public class Top150_P274_ToDo_HIndex_Medium {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n+1];

        // couting papers for each citation number
        // Any citation larger than n can be replaced by n and the h-index will not change after the replacement
        for(int c: citations) {
            papers[Math.min(n, c)]++;
        }

        // whenever the total count exceeds the index of the papers, meaning that we have the index number of papers that have reference greater than or qual to the index.
        // which will be our h-index result. (array[i]>=i)

        // if citation[i] > i, then papers 0 to i all have at least i+1 citations. 
        // Thus, to find h-index, we search for the largest i (let's call it i') such that citations[i]>i and therefore the h-index is i'+1
        int totalpaper = 0;
        for(int i=n; i>=0; i--) {
            totalpaper += papers[i]; // i번 cited된 논문들의 수를 합산. 예) i=5, 5번 인용된 논문은 papers[5] + papers[6] + ... + papers[n] 이다. 그래서 뒤에서부터 더해주는 것
            // it basically takes the first occurrence of the situation where number of citations is greater than the number of papers
            // as soon as the paper number falls below the cumulative sum of citations, we get the h-index
            if(totalpaper >= i) return i; 
        }
        return 0;
    }
}

/*
 * time complexity: O(n)
 * space complexity: O(n)
 * 
 * Further thoughts:
 * Q: Is it possible to have multiple h-values?
 * A: No. The dashed line y=x (number of papers line y = number of citations line x) crosses the histogram once and only once, because the sorted bar are monotonic.
 * it can also be proven from the definition of the h-index.
 */
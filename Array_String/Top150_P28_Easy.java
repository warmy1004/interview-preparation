/*
    28. Find the Index of the First Occurence in a String
    Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

    Example 1:
        Input: haystack = "sadbutsad", needle = "sad"
        Output: 0
        Explanation: "sad" occurs at index 0 and 6.
        The first occurrence is at index 0, so we return 0.

    Example 2:
        Input: haystack = "leetcode", needle = "leeto"
        Output: -1
        Explanation: "leeto" did not occur in "leetcode", so we return -1.

    Constraints:
        1 <= haystack.length, needle.length <= 104
        haystack and needle consist of only lowercase English characters.
 */

package Array_String;

public class Top150_P28_Easy {
    /*
     * Solution 1: using built-in String method
     * time complexity: O(n*m), where n and m are the length of the serach string and pattern respectively
     * space complexity: O(1)
     */
    // public int strStr(String haystack, String needle) {
    //     return haystack.indexOf(needle);
    // }

    /* 
     * Solution: Sliding window version 1
     * time complexity: O(n*m)
     * space complexity: O(1)
     */
    public int strStr(String haystack, String needle) {
        int m = needle.length();
        int n = haystack.length();
        if(m>n) return -1;

        for(int i=0; i<n-m; i++) {
            if(haystack.substring(i, i+n).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    /* 
     * Solution 3: Sliding window version 2
     * time complexity: O(n*m)
     * space complexity: O(1)
     */
    // public int strStr(String haystack, String needle) {
    //     int m = needle.length();
    //     int n = haystack.length();
    //     if(m>n) return -1;

    //     for(int i=0; i<n-m; i++) {
    //         for(int j =0; j<m; j++) {
    //             if(needle.charAt(j) != haystack.charAt(i+j)) break;
    //             if(j == m-1) return i;
    //         }
    //     }
    //     return -1;
    // }

    
}

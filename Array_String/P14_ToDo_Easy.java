/*
    14. Longest Common Prefix
    Write a function to find the longest common prefix string amongst an array of strings.
    If there is no common prefix, return an empty string "".

    Example 1:
        Input: strs = ["flower","flow","flight"]
        Output: "fl"

    Example 2:
        Input: strs = ["dog","racecar","car"]
        Output: ""
        Explanation: There is no common prefix among the input strings.
    
    Constraints:
        1 <= strs.length <= 200
        0 <= strs[i].length <= 200
        strs[i] consists of only lowercase English letters.
 */

package Array_String;

public class P14_ToDo_Easy {
    /*
     * Solution 1: Vertical scanning
     * time complexity: O(S), where S is the sume of all characters in all strings. In the worst case, there will be n euqal strings with length m and the algorithm performs S=mn character comparisons.
     * space complexity: O(1)
     */
    // public String longestCommonPrefix(String[] strs) {
    //     if(strs.length == 0) return "";
        
    //     //set the target as the first string
    //     for(int i=0; i<strs[0].length(); i++) {
    //         char c = strs[0].charAt(i);
    //         for(int j=0; j<strs.length; j++) {
    //             if(i == strs[j].length() || strs[j].charAt(i) != c) {
    //                 return strs[0].substring(0, i);
    //             }
    //         }
    //     }
    //     return strs[0];
    // }

    /*
     * Solution 2: Horizontal scanning
     * time complexity: O(S)
     * space complexity: O(1)
     */
    // public String longestCommonPrefix(String[] strs) {
    //     if(strs.length == 0) return "";

    //     String prefix = strs[0];
    //     for(int i=1; i<strs.length; i++) {
    //         // indexOf() : returns the position of the first occurence of specified character(s) in a string
    //         while(strs[i].indexOf(prefix)!=0) {
    //             prefix = prefix.substring(0, prefix.length()-1);
    //             if(prefix.isEmpty()) return "";
    //         }
    //     }
    //     return prefix;
    // }

    /*
     * Solution 3: Divide and conquer
     * 
     * Intuition:
     *  The idea of the algorithm comes from the associative property of LCP operation. We nothis that:
     *  LCP(S1 ... Sn) = LCP( (S1 ... Sk), LCP(Sk+1 ... Sn))
     *  where LCP(S1 ... Sn) is the longest common prefix in set of strings [S1 ... Sn], 1<k<n
     * 
     * In the worst case, we have n equal strings with length m
     * time complexity: O(S), where S is the number of all characters in the array, S=m*n
     *      Time complexity is 2*T(n/2)+O(m). Therefore, time complexity is O(S).
     *      In the best case, this algorithm performs O(minLen*n), where minLen is the shortest string of the array
     * Space complexity: O(m*logn), there is a memory overhead since we store recursive calls in the execution stack. There are logn recursive calls, each store needs m space to store the result.
     */
    // public String longestCommonPrefix(String[] strs) {
    //     if(strs.length == 0) return "";
    //     return lcp(strs, 0, strs.length-1);
    // }

    // String lcp(String[] strs, int left, int right) {
    //     if(left==right) return strs[left];
    //     else {
    //         int mid = (left+right)/2;
    //         String l_str = lcp(strs, left, mid);
    //         String r_str = lcp(strs, mid+1, right);
    //         return checkCommon(l_str, r_str);
    //     }
    // }

    // String checkCommon(String left, String right) {
    //     int min_len = Math.min(left.length(), right.length());
    //     for(int i=0; i<min_len; i++) {
    //         if(left.charAt(i)!=right.charAt(i)) {
    //             return left.substring(0, i);
    //         }
    //     }
    //     return left.substring(0, min_len);
    // }

    /*
     * Solution 4: Binary search
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        int min_len = Integer.MAX_VALUE;
        for(String s : strs) min_len = Math.min(min_len, s.length());

        int low =1;
        int high=min_len;
        // low == high: to check one character, i.e. low=high=1
        while(low<=high) {
            int mid = (low+high)/2;
            if(isCommon(strs, mid)) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return strs[0].substring(0, (low+high)/2);
    }

    boolean isCommon(String[] strs, int mid) {
        String str = strs[0].substring(0, mid);
        for(int i=1; i<strs.length; i++) {
            if(!strs[i].startsWith(str)) {
                return false;
            }
        }
        return true;
    }
}

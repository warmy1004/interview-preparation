/*
    131. Palindrome Partitioning
    Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

    Example 1:
        Input: s = "aab"
        Output: [["a","a","b"],["aa","b"]]

    Example 2:
        Input: s = "a"
        Output: [["a"]]

    Constraints:
        1 <= s.length <= 16
        s contains only lowercase English letters.
 */
package Palindrome;

import java.util.ArrayList;

public class P131_Backtracking_ToDo_Medium {
    /*
     * Solution: DFS backtracking
     * time complexity: O(N*2^N)
     *      the worse case time complexity is when all the possible substrings are palindrome. Hence, there could be 2^N possible substrings in the worst case. 
     *      For each substring, it takes O(N) time to generate the substring and determine if it is a palindrome or not. This gives us a time complexity of O(N*2^N)
     * space complexity: O(N), this space will be used to store the recursion stack.
     */
    public List<List<String>> partition(String s) {
        List<List<String>> answer = new ArrayList<>();
        dfs(s, 0, new ArrayList<String>(), answer);
        return answer;
    }

    void dfs(String s, int start, List<String> curr, List<List<String>> answer) {
        if(start >= s.length()) answer.add(new ArrayList<String>(curr));
        for(int end = start; end < s.length(); end++) {
            if( isPalindrome(s, start, end)) {
                curr.add(s.substring(start, end+1));
                dfs(s, end+1, curr, answer);
                curr.remove(curr.size() -1);
            }
        }
    }

    boolean isPalindrome(String s, int i, int j) {
        while(i<=j) {
            if(s.charAt(i++)!= s.charAt(j--)) return false;
        }
        return true;
    }

    /*
     * Solution: Backtracking with DP table
     * time complexity: O(N*2^N)
     *      in the worst case, there couldd be 2^N possible substrings and it will take O(N) to generate each substring as in the above approach.
     *      However, we are eliminating one additional iteration to check if the substring is a palindrome or not.
     * space complexity: O(N^2), because of dp
     */
    public List<List<String>> partition(String s) {
        List<List<String>> answer = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        dfs(s, 0, dp, new ArrayList<String>(), answer);
        return answer;
    }

    void dfs(String s, int start, boolean[][] dp, List<String> curr, List<List<String>> answer) {
        if(start>=s.length()) answer.add(new ArrayList<>(curr));
        for(int end=start; end < s.length(); end++) {
            if(s.charAt(start) == s.charAt(end) && (end-start<=2 || dp[start+1][end-1])) {
                dp[start][end] = true;
                curr.add(s.substring(start, end+1));
                dfs(s, end+1, dp, curr, answer);
                curr.remove(curr.size()-1);
            }
        }
    }
}

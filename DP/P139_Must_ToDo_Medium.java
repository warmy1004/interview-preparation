/*
    139. word break
    Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
    Note that the same word in the dictionary may be reused multiple times in the segmentation.

    Example 1:
        Input: s = "leetcode", wordDict = ["leet","code"]
        Output: true
        Explanation: Return true because "leetcode" can be segmented as "leet code".

    Example 2:
        Input: s = "applepenapple", wordDict = ["apple","pen"]
        Output: true
        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
        Note that you are allowed to reuse a dictionary word.

    Example 3:
        Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
        Output: false

    Constraints:
        1 <= s.length <= 300
        1 <= wordDict.length <= 1000
        1 <= wordDict[i].length <= 20
        s and wordDict[i] consist of only lowercase English letters.
        All the strings of wordDict are unique.
 */
package DP;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class P139_Must_ToDo_Medium {
    /*
     * Solution: Breadth-First Search
     * time complexity: O(n^2*m)
     *      There are O(n) nodes. because of endChecked, we never visit a node more than once. At each node, we iterate over the noes in front of the current node, of which there are O(n).
     *      For each node end, we create a substring, which also costs O(n).
     *      Threrfore, handling a node costs O(n^2), so the BFS could cost up to O(n^3). 
     * space complexity:
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] endChecked = new boolean[s.length()+1];
        Queue<Integer> startQueue = new ArrayDeque<>();
        startQueue.add(0);

        while(!startQueue.isEmpty()) {
            int start = startQueue.remove();
            if(start == s.length()) return true;
            for(int end = start+1; end<=s.length(); end++) {
                if(endChecked[end]) continue;
                if(wordDict.contains(s.substring(start, end))) {
                    endChecked[end] = true;
                    startQueue.add(end);
                }
            }
        }
        return false;
    }

    /*
     * time complexity: O(n^2) optimized by using set to reduce a search time with contains()
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] endChecked = new boolean[s.length()+1];
        Queue<Integer> startQueue = new ArrayDeque<>();
        startQueue.add(0);

        while(!startQueue.isEmpty()) {
            int start = startQueue.remove();
            if(start == s.length()) return true;
            for(int end = start+1; end<=s.length(); end++) {
                if(endChecked[end]) continue;
                if(set.contains(s.substring(start, end))) {
                    endChecked[end] = true;
                    startQueue.add(end);
                }
            }
        }
        return false;
    }

    /*
     * Solution: DP
     * time complexity:
     * space complexity:
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n =s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i=1; i<=n; i++) {
            for(int j = 0; j<i; j++) {
                if(dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for(int i=0; i<s.length(); i++) {
            for(String word: wordDict) {
                int m = word.length();
                if(i<m-1) continue;
                else {
                    if(i == m-1 || dp[i-m]) {
                        if(s.substring(i-m+1, i+1).equals(word)) {
                            dp[i] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[s.length()-1];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int n =s.length();
        boolean[] dp = new boolean[n];
        for(int i=0; i<n; i++) {
            for(String word: wordDict) {
                int m = word.length();
                if(i<m-1) continue;
                if(i==m-1 || dp[i-m]) {
                    if(s.substring(i-m+1, i+1).equals(word)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[n-1];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] =true;
        int maxlen = 0;
        for(String str: wordDict) {
            maxlen = Math.max(maxlen, str.length());
        }

        for(int i=1; i<=n; i++) {
            for(int j=i-1; j>=Math.max(i-maxlen-1, 0); j--) {
                if(dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}

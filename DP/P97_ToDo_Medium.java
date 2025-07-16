/*
    97. Interleaving String
    Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
    An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively, such that:
        s = s1 + s2 + ... + sn
        t = t1 + t2 + ... + tm
        |n - m| <= 1
    The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
    Note: a + b is the concatenation of strings a and b.

    Example 1:
        Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
        Output: true
        Explanation: One way to obtain s3 is:
        Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
        Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
        Since s3 can be obtained by interleaving s1 and s2, we return true.

    Example 2:
        Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
        Output: false
        Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.

    Example 3:
        Input: s1 = "", s2 = "", s3 = ""
        Output: true
    
    Constraints:
        0 <= s1.length, s2.length <= 100
        0 <= s3.length <= 200
        s1, s2, and s3 consist of lowercase English letters.
    
    Follow up: Could you solve it using only O(s2.length) additional memory space?
 */
package DP;

public class P97_ToDo_Medium {
    /*
     * Solution: 2D DP- bottom up
     * time complexity: O(nm)
     * space complexity: O(nm)
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length();
        if(n+m != s3.length()) return false;
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for(int i=1; i<=n; i++) {
            if(dp[i-1][0] && s1.charAt(i-1)==s3.charAt(i-1)) {
                dp[i][0] = true;
            }
        }

        for(int j=1; j<=m; j++) {
            if(dp[0][j-1] && s2.charAt(j-1)==s3.charAt(j-1)) {
                dp[0][j] = true;
            }
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(dp[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1) || dp[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1)) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[n][m];
    }

    /*
     * Solution: 1D DP- bottom up
     * time complexity: O(nm)
     * space complexity: O(n)
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if(m+n != s3.length()) return false;
        boolean[] dp = new boolean[n+1];
        dp[0] = true;

        for(int j=1; j<=n; j++) {
            dp[j] = dp[j-1] && s2.charAt(j-1)==s3.charAt(j-1);
        }

        for(int i=1; i<=m; i++) {
            dp[0] = dp[0] && s1.charAt(i-1) == s3.charAt(i-1);
            for(int j=1; j<=n; j++) {
                dp[j] = dp[j] && s1.charAt(i-1) == s3.charAt(i+j-1) || dp[j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
            }
        }
        return dp[n];
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if(m+n != s3.length()) return false;
        boolean[] dp = new boolean[n+1];
        
        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++) {
                if(i==0 && j==0) dp[j] = true;
                else if(i==0) dp[j] = dp[j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
                else if(j==0) dp[j] = dp[j] && s1.charAt(i-1) == s3.charAt(i+j-1);
                else dp[j] = dp[j] && s1.charAt(i-1) == s3.charAt(i+j-1) || dp[j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
            }
        }
        return dp[n];
    }
}


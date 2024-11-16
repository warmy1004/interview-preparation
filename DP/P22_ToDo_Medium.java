/*
    22. Generate Parentheses
    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    Example 1:
        Input: n = 3
        Output: ["((()))","(()())","(())()","()(())","()()()"]

    Example 2:
        Input: n = 1
        Output: ["()"]
    
    Constraints:
        1 <= n <= 8
 */

package DP;

import java.util.ArrayList;
import java.util.List;

public class P22_ToDo_Medium {
    /*
     * Solution: DP
     * time complexity: O(4^n/(n*sqrt(n))) 
     *      big O of four to the n over n times the square root of n
     *      big O of four to power of n divided by n times the square root of n
     * space complexity: O(n), by recursive call stack, which is 2n.
     */
    public List<String> generateParenthesis(int n) {
        List<String> dp = new ArrayList<>();
        backtracking(dp, 0, 0, new StringBuilder(), n);
        return dp;
    }

    void backtracking(List<String> dp, int open, int close, StringBuilder str, int n) {
        if(str.length() == 2*n) {
            dp.add(str.toString());
            return;
        }
        if(open<n) {
            str.append("(");
            backtracking(dp, open+1, close, str, n);
            str.deleteCharAt(str.length()-1);
        }
        if(close<open) {
            str.append(")");
            backtracking(dp, open, close+1, str, n);
            str.deleteCharAt(str.length()-1);
        }
    }

    /*
     * Other version
     */
    public List<String> generateParenthesis(int n) {
        List<String> dp = new ArrayList<>();
        recursive(dp, 0, 0, "", n);
        return dp;
    }
    public void recursive(List<String> list, int open, int close, String str, int n){
        
        if(str.length() == n*2){
            list.add(str);
            return;
        }
        
        if(open < n)
            recursive(list,  open+1, close, str+"(", n);
        if(close < open)
            recursive(list,  open, close+1, str+")", n);
    }
}

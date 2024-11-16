/*
    1963. Minimum number of swaps to make the string balanced
    You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.
    A string is called balanced if and only if:
        It is the empty string, or
        It can be written as AB, where both A and B are balanced strings, or
        It can be written as [C], where C is a balanced string.
        You may swap the brackets at any two indices any number of times.
    Return the minimum number of swaps to make s balanced.

    Example 1:
        Input: s = "][]["
        Output: 1
        Explanation: You can make the string balanced by swapping index 0 with index 3.
        The resulting string is "[[]]".

    Example 2:
        Input: s = "]]][[["
        Output: 2
        Explanation: You can do the following to make the string balanced:
        - Swap index 0 with index 4. s = "[]][][".
        - Swap index 1 with index 5. s = "[[][]]".
        The resulting string is "[[][]]".

    Example 3:
        Input: s = "[]"
        Output: 0
        Explanation: The string is already balanced.
    
    Constraints:
        n == s.length
        2 <= n <= 10^6
        n is even.
        s[i] is either '[' or ']'.
        The number of opening brackets '[' equals n / 2, and the number of closing brackets ']' equals n / 2.
 */
package Array_String;

public class P1963_Medium {
    /*
     * Solution: Greedy 
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int minSwaps(String s) {
        int open = 0, close = 0;
        int swapcount = 0;
        for(char ch: s.toCharArray()) {
            if(ch =='[') {
                open++;
            } else {
                close++;
            }

            if(close>open) {
                swapcount ++;
                open++;
                close--;
            }
        }
        return swapcount;
    }

    public int minSwaps(String s) {
        int balance = 0, swapcount = 0;
        for(char ch: s.toCharArray()) {
            if(ch=='[') balance++;
            else balance--;
            if (balance < 0) {
                balance = 1;
                swapcount++;
            }
        }
        return swapcount;
    }

    public int minSwaps(String s) {
        int close = 0;
        int open = 0;
        for(char ch: s.toCharArray()) {
            if(ch=='[') {
                open++;
            } else {
                if(open == 0) {
                    close++;
                } else {
                    open--;
                }
            }
        }
        return (close+1)/2;
    }

    public int minSwaps(String s) {
        Stack<Character> stack = new Stack<>();
        int unbalanced = 0;
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);

            if(ch=='[') stack.push(ch);
            else {
                if(!stack.isEmpty()) stack.pop();
                else unbalanced++;
            }
        }

        // 1 swap removes 2 mismatches, but in case of odd mismatch, one more extra swap is needed
        return (unbalanced+1)/2;
    }

    public int minSwaps(String s) {
        int unbalanced = 0;
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);

            if(ch=='[') unbalanced++;
            else {
                if(unbalanced > 0) {
                    // if there's already an unmatched opening bracket, we can balance it by reducing the counter becasue this closing bracket has found a match
                    unbalanced--;
                }
                // if unbalanced == 0, which means no matched [, we do nothing, because we can't balance a ] without a prior [
            }
        }

        // unbalanced will show how many opening brackets remain unmatched. 
        // 1 swap removes 2 mismatches, but in case of odd mismatch, one more extra swap is needed
        return (unbalanced+1)/2;
    }
}

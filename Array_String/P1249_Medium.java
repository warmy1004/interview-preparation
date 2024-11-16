/*
    1249. Minimum Remove to make valid parentheses

    Given a string s of '(' , ')' and lowercase English characters.
    Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
    Formally, a parentheses string is valid if and only if:
        It is the empty string, contains only lowercase characters, or
        It can be written as AB (A concatenated with B), where A and B are valid strings, or
        It can be written as (A), where A is a valid string.
    

    Example 1:
        Input: s = "lee(t(c)o)de)"
        Output: "lee(t(c)o)de"
        Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

    Example 2:
        Input: s = "a)b(c)d"
        Output: "ab(c)d"

    Example 3:
        Input: s = "))(("
        Output: ""
        Explanation: An empty string is also valid.
    

    Constraints:
        1 <= s.length <= 10^5
        s[i] is either '(' , ')', or lowercase English letter.
 */

package Array_String;

import java.util.Stack;
import javax.naming.spi.DirStateFactory;

public class P1249_Medium {
    /*
     * Solution: Stack 1
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public String minRemoveToMakeValid(String s) {
        int open = 0;
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                open++;
                if(open>0) {
                    stack.push(ch);
                }
            } else if(ch == ')') {
                if(open > 0) {
                    open --;
                    stack.push(ch);
                }
            } else {
                stack.push(ch);
            }
        }
        StringBuilder str = new StringBuilder();
        int close = 0;
        while(!stack.isEmpty()) {
            char ch = stack.pop();
            if(ch == ')') {
                close++;
                if(close > 0) {
                    str.append(ch);
                }
            } else if(ch == '(') {
                if(close > 0) {
                    close--;
                    str.append(ch);
                }
            } else {
                str.append(ch);
            }
        }
        return str.reverse().toString();
    }

    /*
     * Solution: stack 2
     * 
     */
    public String minRemoveToMakeValid(String s) {
        int open = 0, close = 0;
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                open++;
            } else if (ch ==')') {
                close++;
            }

            if(open < close) {
                close--;
                continue;
            } else {
                stack.push(ch);
            }
        }

        StringBuilder str = new StringBuilder();
        while(!stack.isEmpty()) {
            char ch = stack.pop();
            if(open>close && ch =='(') {
                open--;
            } else {
                str.append(ch);
            }
        }
        return str.reverse().toString();
    }

    /*
     * Solution: with stack 3
     */
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                stack.push(i);
            } else if(ch==')') {
                if(!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }

        StringBuilder str = new StringBuilder();
        for(int i=s.length()-1; i>=0; i--) {
            if(!stack.isEmpty() && stack.peek() == i) {
                stack.pop();
                continue;
            } else {
                str.append(s.charAt(i));
            }
        }
        return str.reverse().toString();
    }

    /*
     * Solution: without stack 1
     * time complexity:
     * space complexity:
     */
    public String minRemoveToMakeValid(String s) {
        int open = 0; 
        int close = 0;
        StringBuilder str = new StringBuilder();
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                open++;
            } else if(ch==')') {
                close++;
            }

            if(close>open) {
                close--;
            } else {
                str.append(ch);
            }
        }

        open = 0;
        close = 0;
        StringBuilder result = new StringBuilder();
        for(int i=str.length()-1; i>=0; i--) {
            char ch = str.charAt(i);
            if(ch == '(') open++;
            else if(ch ==')') close++;
            if(open>close) {
                open--;
            } else {
                result.append(ch);
            }
        }
        return result.reverse().toString();
    }

    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int open = 0;
        int balance = 0;
        // remove all invalid ")"
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                open++;
                balance++;
            } else if(ch ==')') {
                if (balance==0) continue;
                else balance--;
            }
            sb.append(ch);
        }

        // remove the right most "("
        StringBuilder result = new StringBuilder();
        int diff = open - balance;
        for(int i=0; i<sb.length(); i++) {
            char ch = sb.charAt(i);
            if(ch == '(') {
                diff --;
                if(diff < 0) continue;
            }
            result.append(ch);
        }
        return result.toString();
    }

    public String minRemoveToMakeValid(String s) {
        char[] arr = s.toCharArray();
        int open = 0;
        for(int i=0; i<s.length(); i++) {
            if(arr[i] == '(') {
                open++;
            } else if(arr[i] == ')') {
                if(open==0) {
                    arr[i] = '#';
                } else {
                    open--;
                }
            }
        }

        for(int i=s.length()-1; i>=0; i--) {
            if(open> 0 && arr[i] == '(') {
                arr[i] = '#';
                open--;
            }
        }

        StringBuilder result = new StringBuilder();
        for(int i=0; i<s.length(); i++) {
            if(arr[i]!='#') {
                result.append(arr[i]);
            }
        }

        return result.toString();
    }
}

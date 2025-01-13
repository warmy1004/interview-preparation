/*
    224. Basic Calculator
    Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
    Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

    Example 1:
        Input: s = "1 + 1"
        Output: 2

    Example 2:
        Input: s = " 2-1 + 2 "
        Output: 3

    Example 3:
        Input: s = "(1+(4+5+2)-3)+(6+8)"
        Output: 23
    
    Constraints:
        1 <= s.length <= 3 * 10^5
        s consists of digits, '+', '-', '(', ')', and ' '.
        s represents a valid expression.
        '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
        '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
        There will be no two consecutive operators in the input.
        Every number and running calculation will fit in a signed 32-bit integer.
 */
package Math;

import java.util.Stack;

public class P224_ToDo_Hard {
    /*
     * Solution: Using Stack
     *      Approach: using a stack to store the total number which is currently calculated by and sign before calculating the nested parenthesis
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char prevOp = '+';

        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch)) {
                num = num*10 + (ch-'0');
            } else if(ch == '(') {
                int j = i+1;
                int brace = 1;
                while(j<s.length()) {
                    if(s.charAt(j) == '(') brace++;
                    if(s.charAt(j) == ')') brace--;
                    if(brace == 0) break;
                    j++;
                }
                num = calculate(s.substring(i+1, j));
                i=j;
            }

            if(ch == '+' || ch == '-' || i==s.length()-1) {
                if(prevOp == '+') {
                    stack.push(num);
                } else if(prevOp == '-') {
                    stack.push(-num);
                }
                prevOp = ch;
                num = 0;
            }
        }

        int result = 0;
        while(!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int sign = 1;
        int num = 0;
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);

            if(Character.isDigit(ch)) {
                num = num*10 + (ch-'0');
            } else if(ch=='+') {
                result += sign*num;
                sign = 1;
                num = 0;
            } else if(ch=='-') {
                result += sign*num;
                sign = -1;
                num = 0;
            } else if(ch =='(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if(ch == ')') {
                result += num*sign;
                num = 0;
                result *= stack.pop();
                result += stack.pop();
            }
        }
        return result + num*sign;
    }

    /*
     * Solution: Without Stack
     * time complexity: O(n)
     * space complexity: O(n)
     */
    int idx = 0;
    public int calculate(String s) {
        return dfs(s);
    }

    int dfs(String s) {
        int result = 0, num = 0, sign = 1;
        while(idx<s.length()){
            char ch = s.charAt(idx++);
            if(Character.isDigit(ch)) {
                num = num*10 + (ch-'0');
            } else if(ch =='+' || ch=='-') {
                result += num * sign;
                sign = ch=='+'? 1: -1;
                num = 0;
            } else if (ch=='(') {
                num = dfs(s);
            } else if(ch==')') {
                return result += num*sign;
            }
        }
        result += num*sign;
        return result;
    }

    int i = 0;
    public int calculate(String s) {
        int sum = 0, num = 0;
        char prevOp = '+';
        while(i<s.length()) {
            char ch = s.charAt(i++);
            if(ch == '(') {
                num = calculate(s);
            } else if(ch == ')') {
                break;
            } else if( Character.isDigit(ch)) {
                num = num * 10 + (ch-'0');
            } else if (ch != ' ') {
                sum = runOp(prevOp, sum, num);
                num = 0;
                prevOp = ch;
            }
        }
        return runOp(prevOp, sum, num);
    }
    private int runOp(char op, int prev, int curr) {
        if(op == '+') {
            return prev + curr;
        } else {
            return prev - curr;
        }
    }
}

/*
    227. Basic calculator 2
    Given a string s which represents an expression, evaluate this expression and return its value. 
    The integer division should truncate toward zero.
    You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
    Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

    Example 1:
        Input: s = "3+2*2"
        Output: 7

    Example 2:
        Input: s = " 3/2 "
        Output: 1

    Example 3:
        Input: s = " 3+5 / 2 "
        Output: 5
    
    Constraints:
        1 <= s.length <= 3 * 10^5
        s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
        s represents a valid expression.
        All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
        The answer is guaranteed to fit in a 32-bit integer.
 */
package Math;

import java.util.Stack;

public class P227_ToDo_Medium {
    /*
     * Solution: using Stack
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char op = '+';
        for(int i=0; i<s.length();i++) {
            char curr = s.charAt(i);
            if(Character.isDigit(curr)) {
                num = num*10 + (curr-'0');
            }

            if(!Character.isDigit(curr)&& !Character.isWhitespace(curr) || i==s.length()-1) {
                if(op=='+') stack.push(num);
                else if(op=='-') stack.push(-num);
                else if(op =='*') stack.push(stack.pop()*num);
                else if(op == '/') stack.push(stack.pop()/num);

                op = curr;
                num=0;
            }
        }
        int answer = 0;
        while(!stack.isEmpty()) {
            answer += stack.pop();
        }
        return answer;
    }

    /*
     * Solution: Optimized one without Stack
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int calculate(String s) {
        char op = '+';
        int answer = 0, lastNum = 0, currNum = 0;

        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);

            if(Character.isDigit(ch)) {
                currNum = currNum*10 + (ch-'0');
            }

            if(!Character.isDigit(ch)&&!Character.isWhitespace(ch) || i==s.length()-1) {
                if(op == '+') {
                    answer += lastNum;
                    lastNum = currNum;
                } else if(op == '-') {
                    answer += lastNum;
                    lastNum = -currNum;
                } else if(op == '*') {
                    lastNum *= currNum;
                } else if(op == '/') {
                    lastNum /= currNum;
                }
                op = ch;
                currNum=0;
            }
        }
        answer += lastNum;
        return answer;
    }
}

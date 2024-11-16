/*
    772. Basic caclulator 3
    Implement a basic calculator to evaluate a simple expression string.
    The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.
    You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
    Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

    Example 1:
        Input: s = "1+1"
        Output: 2

    Example 2:
        Input: s = "6-4/2"
        Output: 4

    Example 3:
        Input: s = "2*(5+5*2)/3+(6/2+8)"
        Output: 21

    Constraints:
        1 <= s <= 10^4
        s consists of digits, '+', '-', '*', '/', '(', and ')'.
        s is a valid expression.
 */
package Math;

public class P772_Hard {
    /*
     * Solution: Stack
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0; 
        int prevOp = '+';
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);

            if(Character.isDigit(ch)) {
                num = num*10 + (ch-'0');
            } else if (ch == '(') {
                int j = i+1;
                int brace = 1;
                while(j<s.length()) {
                    if(s.charAt(j)=='(') brace++;
                    if(s.charAt(j)==')') brace--;
                    if(brace==0) break;
                    j++;
                }
                num = calculate(s.substring(i+1, j));
                i=j;
            }

            if(!Character.isDigit(ch) && !Character.isWhitespace(ch) || i==s.length()-1){
                if(prevOp == '+') {
                    stack.push(num);
                } else if(prevOp == '-') {
                    stack.push(-num);
                } else if(prevOp == '*') {
                    stack.push(stack.pop()*num);
                } else if(prevOp == '/') {
                    stack.push(stack.pop()/num);
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

    /*
     * Solution: without stack
     * time complexity: O(n)
     * space complexity: O(1)
     */
    int i = 0;
    public int calculate(String s) {
        int num = 0; 
        int last = 0;
        int total = 0;
        char prevOp = '+';

        while(i<s.length()) {
            char ch = s.charAt(i++);
            if(Character.isDigit(ch)) {
                num = num *10+ (ch-'0');
            } else if(ch == '(') {
                num = calculate(s);
            } else if(ch ==')') {
                break;
            } else if (ch != ' ') {
                last = cal(prevOp, last, num);
                if(ch == '+' || ch == '-') {
                    total += last;
                    last = 0;
                } 
                prevOp = ch;
                num = 0;
            }
        }
        return total + cal(prevOp, last, num);
    }

    int cal(char op, int last, int curr) {
        if(op=='+') return last + curr;
        else if(op=='-') return last-curr;
        else if(op=='*') return last*curr;
        else return last/curr;
    }
}

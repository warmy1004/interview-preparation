/*
    258. Add digits
    Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.

    Example 1:
        Input: num = 38
        Output: 2
        Explanation: The process is
        38 --> 3 + 8 --> 11
        11 --> 1 + 1 --> 2 
        Since 2 has only one digit, return it.

    Example 2:
        Input: num = 0
        Output: 0
    
    Constraints:
        0 <= num <= 2^31 - 1
    
    Follow up: Could you do it without any loop/recursion in O(1) runtime?
 */
package LinkedList;

public class P258_Easy {
    /*
     * Solution: Math
     * time complexity: O(logn)
     * space complexity: O(1)
     */
    public int addDigits(int num) {
        while(num>9) {
            num = getNum(num);
        }
        return num;
    }
    int getNum(int num) {
        int sum = 0;
        while(num!=0) {
            sum+= num%10;
            num /= 10;
        }
        return sum;
    }

    public int addDigits(int num) {
        int sum = 0;
        while(num>0) {
            sum += num%10;
            num = num/10;
            if(num==0 && sum>9) {
                num=sum;
                sum=0;
            }
        }
        return sum;
    }

    /*
     * Solution: Math with constant time
     * time complexity: O(1)
     * space complexity: O(1)
     */
    public int addDigits(int num) {
        if(num == 0) return 0;
        if(num%9==0) return 9;
        return num%9;
    }

    public int addDigits(int num) {
        return num == 0 ? 0: 1+(num-1) %9;
    }
}

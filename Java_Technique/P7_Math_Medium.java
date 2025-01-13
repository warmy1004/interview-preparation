/*
    7. Reverse Integer
    Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
    Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

    Example 1:
        Input: x = 123
        Output: 321

    Example 2:
        Input: x = -123
        Output: -321

    Example 3:
        Input: x = 120
        Output: 21

    Constraints:
        -2^31 <= x <= 2^31 - 1
 */

public class P7_Math_Medium {
    /*
     * Solution
     * time complexity: O(logx)
     * space complexity: O(1)
     */
    public int reverse(int x) {
        int result = 0;
        while(x!=0) {
            int mod = x % 10;
            if(result > 0 && result > (Integer.MAX_VALUE-mod)/10) {
                return 0;
            }
            if(result < 0 && result < (Integer.MIN_VALUE-mod)/10) {
                return 0;
            }
            result = result * 10 + mod;
            x /= 10;
        }
        return result;
    }

    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            /*
             * The expression rev == Integer.MAX_VALUE / 10 && pop > 7 is a common check used to prevent integer overflow when reversing an integer.
             *      rev == Integer.MAX_VALUE / 10: This checks if rev is at its maximum safe value (214748364).
             *      pop > 7: This checks if the next digit being added will cause an overflow. 
    *                        Since 2147483640 is the highest value rev can be before adding pop, any pop value greater than 7 will cause overflow.
             */
            if (
                rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)
            ) 
                return 0;
            if (
                rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)
            ) 
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}

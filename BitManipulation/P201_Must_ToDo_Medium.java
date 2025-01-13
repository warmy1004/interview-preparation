/*
    201. Bitwise and of numbers range
    Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.
    Example 1:
        Input: left = 5, right = 7
        Output: 4

    Example 2:
        Input: left = 0, right = 0
        Output: 0

    Example 3:
        Input: left = 1, right = 2147483647
        Output: 0
    
    Constraints:
        0 <= left <= right <= 2^31 - 1
 */
package BitManipulation;

/*
 * After the AND operation on all the numbers, the remaining part of bit strings is the common prefix of all these bit strings. 
 * The final result asked by the problem consists of this common prefix of a bit string as its left part, with the rest of the bits as zero. 
 * More specifiaclly, the common prefix of all these bit strings is also the common prefix between the starting and ending numbers of the specified range. 
 * As a result, we then can reformulate the problem as 'given two integer numbers, we are asked to find the common prefix of their binary strings'.
 * 
 * One of the solutions would be to a resort to the bit shift operation. 
 * Another solution is 'brian kernighan's algorithm' which is applied to turn off the rightmost bit of one in a number.
 */
public class P201_Must_ToDo_Medium {
    /*
     * Solution: bit shift
     * 
     * Approach:
     *  The idea is that we shift both numbers to the right, until the numbers become equal, i.e. the numbers are reduced into their common prefix.
     *  Then, we append zeros to the common prefix in order to obtain the desired result by shifting the common prefix to the left. 
     * 
     * time complexity: O(1)
     * space complexity: O(1)
     */
    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        //find the common 1bits - common prefix
        // reducing both numbers into their common prefix, by doing right shift iteratively.
        while(left<right) {
            left >>=1;
            right >>=1;
            shift++;
        }
        return left << shift;
    }

    /*
     * Solution: Brian kernighan's algorithm
     * 
     * When we do an AND bit operation between number and number-1, the rightmost bit of one in the original number would be turned off (from 1 to 0).
     * Based ont this trick, we could apply it to figure out the common prefix of two-bit strings
     */
    public int rangeBitwiseAnd(int left, int right) {
        while(left<right) {
            right &= (right-1);
        }
        return right;
    }
}

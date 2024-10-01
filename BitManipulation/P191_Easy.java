/*
    191. Number of 1 Bits
    Write a function that takes the binary representation of a positive integer and returns the number of set bits it has (also known as the Hamming weight).

    Example 1:
        Input: n = 11
        Output: 3
        Explanation:
        The input binary string 1011 has a total of three set bits.

    Example 2:
        Input: n = 128
        Output: 1
        Explanation:
        The input binary string 10000000 has a total of one set bit.

    Example 3:
        Input: n = 2147483645
        Output: 30
        Explanation:
        The input binary string 1111111111111111111111111111101 has a total of thirty set bits.

    Constraints:
        1 <= n <= 2^31 - 1
 */
package BitManipulation;

public class P191_Easy {
    /*
     * Solution: Bit Manipulation
     * time complexity: O(1), the run time depends on the number of 1-bits in n. In the worse case, all bits in n are 1-bits. In case of a 32 bit integer, the run time is O(1)
     * space complexity: O(1)
     */
    public int hammingWeight(int n) {
        int count = 0;
        while(n!=0) {
            // In the binary representation, the least significant 1-bit (the most right 1-bit) in n always corresponds to a 0-bit in n-1.
            // Therefore, AND-ing the two numbers n and n-1 always flips the least significant 1-bit in n to 0, and keep all other bits the same.
            n &= (n-1);
            count++;
        }
        return count;
    }
}

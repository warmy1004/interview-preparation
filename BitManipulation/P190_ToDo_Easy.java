/*
    190. Reverse bits
    Reverse bits of a given 32 bits unsigned integer.
    Note:
        Note that in some languages, such as Java, there is no unsigned integer type. In this case, both input and output will be given as a signed integer type. They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
        In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above, the input represents the signed integer -3 and the output represents the signed integer -1073741825.
    
    Example 1:
        Input: n = 00000010100101000001111010011100
        Output:    964176192 (00111001011110000010100101000000)
        Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.

    Example 2:
        Input: n = 11111111111111111111111111111101
        Output:   3221225471 (10111111111111111111111111111111)
        Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10111111111111111111111111111111.
    
    Constraints:
        The input must be a binary string of length 32
    
    Follow up: If this function is called many times, how would you optimize it?
 */
package BitManipulation;

public class P190_Easy {
    // you need treat n as an unsigned value

    /*
     * Solution: Bit by Bit
     * 
     * Approach:
     *      To retrieve the right-most bit in an integer n, one could either apply the modulo operation (n%2) OR the bit AND operation(n&1)
     *      To combine the results of reversed bits (2^a, 2^b), one could either use the addition operation ( 2^a + 2^b) or again the bit OR operation (2^a | 2^b)
     *  
     *      The key idea is that for a bit that is situated at the index i, after the reversion, its position should be 31-i (the index starts from zero) 
     * 
     * time complexity: O(1)
     * space complexity: O(1)
     */
    public int reverseBits(int n) {
        int result = 0, power = 31;

        // there is no more bits of one left, we terminate the iteration
        while(n!=0) {
            // To retrieve the right-most bit of an integer, applying the bit AND operation -> n&1
            // then, for each bit, we reverse it to the correct position, (n&1) << power. THen, we accumulate this reversed bit to the final result.
            result += (n&1) << power;

            // iterate from right to left
            // >>> unsigned right shift (부호 비트를 고려하지 않아서 음수 정수에서도 안전), >> signed right shift (부호비트를 유지하면서 오른쪽으로 이동)
            // >> : arithmetic right shift, signed right shift -> 부호 비트를 유지하면서 오른쪽으로 이동, 음수의 경우 왼쪽에 1을 채워 넣는다. 
            // >>> : logical right shift, unsigned right shift -> 부호와 상관없이 0을 왼쪽에 채워 넣는다. 음수의 경우, 부호 비트 무시하고 비트를 단순 이동한다. 
            // >>를 사용하면, 음수의 경우 왼쪽에 1이 채워지기 때문에, n!=0 조건이 계속 유지된다. 즉, n이 절대 0이 되지 않기 때문에 무한 루프가 발생해서 TLE가 발생한다.
            // >>>를 사용하면, 부호를 무시하고 0을 채워넣기 때문에, 음수라도 오른쪽 이동을 반복하면 결국 n=0이 되어 루프가 끝난다. 
            // 항상 >>>를 사용하면, 음수와 양수 모두를 안전하게 처리할 수 있기 때문에, 테스트 케이스에서 음수가 포함될 가능성이 있다면 >>>가 필수이다.
            n = n>>>1;
            power--;
        }
        return result;
    }

    public int reverseBits(int n) {
        int result = 0;
        for(int i=0; i<32; i++) {
            // result 비트를 왼쪽으로 이동하며, 새로운 비트(n의 right-most bit) 를 추가한다. 누적적으로 결과를 생성한다.
            result = (result<<1) | (n&1);
            n = n>>>1;
        }
        return result;
    }

    public int reverseBits(int n) {
        int result = 0;
        for(int i=0; i<32; i++) {
            // result 비트를 왼쪽으로 이동하며, 새로운 비트(n의 right-most bit) 를 추가한다. 누적적으로 결과를 생성한다.
            result = (result<<1);
            result += (n&1);
            n = n>>>1;
        }
        return result;
    }

    public int reverseBits(int n) {
        int result = 0;
        for(int i=0; i<32; i++) {
            // it's same as result << 1
            result *= 2;
            result += n&1;
            n >>>= 1;
        }
        return result;
    }

    /*
     * Solution: mask and shift
     * time complexity: O(1)
     * space complexity: O(1)
     */
    public int reverseBits(int n) {
        // in java, when we need to do right shift, we should use not arithmetic shift(>>) but logical shift(>>>) to shift the last significant bit in addition to other bits
        // we break the original 32-bit into 2 blocks of 16 bits, and switch them. 2 byte swap
        n = (n >>> 16) | (n << 16);
        // break the 16-bits block into 2 blocks of 8 bits. Similarly, switch the position of the 8 bits blocks. 1 byte swap
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        // continue to break the blocks into smaller blocks, until we reach the level with the block of 1 bit. 4 bit swap
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        // 2 bit swap
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        // 1bit swap
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }

}

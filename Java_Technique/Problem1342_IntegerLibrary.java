/* 
    1342. Number of Steps to Reduce a Number to Zero
    Given an integer num, return the number of steps to reduce it to zero.
    In one step, if the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.

    Example 1:
        Input: num = 14
        Output: 6
        Explanation: 
        Step 1) 14 is even; divide by 2 and obtain 7. 
        Step 2) 7 is odd; subtract 1 and obtain 6.
        Step 3) 6 is even; divide by 2 and obtain 3. 
        Step 4) 3 is odd; subtract 1 and obtain 2. 
        Step 5) 2 is even; divide by 2 and obtain 1. 
        Step 6) 1 is odd; subtract 1 and obtain 0.

    Example 2:
        Input: num = 8
        Output: 4
        Explanation: 
        Step 1) 8 is even; divide by 2 and obtain 4. 
        Step 2) 4 is even; divide by 2 and obtain 2. 
        Step 3) 2 is even; divide by 2 and obtain 1. 
        Step 4) 1 is odd; subtract 1 and obtain 0.

    Example 3:
        Input: num = 123
        Output: 12
    
    Constraints: 0 <= num <= 10^6 
*/

public class Problem1342_IntegerLibrary {
    public int numberOfSteps(int num) {
        return Integer.toBinaryString(num).length()-1 + Integer.bitCount(num);

        /*
            int count = 0;
            while(num!=0) {
                if(num%2==0) {
                    num/=2;
                } else {
                    num--;
                }
                count++;
            }
            return count;
            // time complexity: O(logN), because it is halved at every step.
            // space complexity: O(1)
         */
    }
}

/*
 * Integer.toBinaryString(): returns a string representation of the integer argument as an unsigned integer in base 2. 
 *      Input: 10
 *      Output: 1010
 * Integer.toBinaryString(num).length() - 1 => this is equal to the greatest value of x, satisfying 2^x <= num. 
 * So, this number represents the number of times num must be "divided by two".
 * 
 * Integer.bitCount(): returns the count of set bits (1's) in a positive number. For negative numbers it returns count of set bits in it's two's complement form. 
 *                     This function is sometimes referred to as the population count.
 *      Input: 10
 *      Output: 2
 * So, this number represents the number of times we would encounter a "subtract one" operation before dividing in the iterative approach.
 */
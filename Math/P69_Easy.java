/*
    69. Sqrt(x)
    Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.
    You must not use any built-in exponent function or operator.
    For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.

    Example 1:
        Input: x = 4
        Output: 2
        Explanation: The square root of 4 is 2, so we return 2.

    Example 2:
        Input: x = 8
        Output: 2
        Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
    
    Constraints:
        0 <= x <= 2^31 - 1
 */
package Math;

public class P69_Easy {
    /*
     * Solution: binary search
     * time complexity: O(logn)
     * space complexity: O(1)
     */
    public int mySqrt(int x) {
        if(x<=1) return x;
        int left = 0, right = x;
        while(left<=right) {
            int mid = (left+right)/2;
            // because of x's range
            long num = (long)mid*mid;
            if(num == x) return mid;
            else if(num<x) left = mid+1;
            else right = mid-1;
        }
        return right;
    }

    /*
     * Solution: recursion + bit shifts
     * time complexity: O(logn)
     * space complexity: O(1)
     */
    public int mySqrt(int x) {
        if(x<2) return x;
        /*
         * x<<y means x*2^y
         * x>>y means x/(2^y)
         */
        int left = mySqrt(x>>2) <<1;
        int right = left+1;
        return (long) right*right > x? left: right;
    }


}

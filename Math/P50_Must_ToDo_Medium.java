/*
    50. Pow(x,n)
    Implement pow(x, n), which calculates x raised to the power n (i.e., xn).

    Example 1:
        Input: x = 2.00000, n = 10
        Output: 1024.00000

    Example 2:
        Input: x = 2.10000, n = 3
        Output: 9.26100

    Example 3:
        Input: x = 2.00000, n = -2
        Output: 0.25000
        Explanation: 2-2 = 1/22 = 1/4 = 0.25
    

    Constraints:
    -100.0 < x < 100.0
    -2^31 <= n <= 2^31-1
    n is an integer.
    Either x is not zero or n > 0.
    -10^4 <= xn <= 10^4
 */
package Math;

public class P50_Must_ToDo_Medium {
    /*
     * Solution: binary exponentiation (recursive)
     * 
     * Approach: 
     *      Binary exponentiation, also known as exponentiation by squaring, is a technique for efficeintly computing the power of a number. 
     *      By repeatedly squaring x and halving n, we can quickly compute x^n using a logarithmic number of multiplications.
     *      The basic idea here is to use the fact that x^n can be expressed as:
     *          - (x^2)^(n/2) if n is even
     *          - x*(x^2)^((n-1)/2) if n is odd (we separate out one x, then n-1 will become even)
     * 
     * time complexity: O(logn)
     * space complexiyt: O(logn)
     */
    public double myPow(double x, int n) {
        return binaryExpo(x, (long)n);
    }

    double binaryExpo(double x, long n) {
        if(n==0) return 1;
        if(n<0) return 1.0/binaryExpo(x, -1*n);
        if(n%2==1) return x*binaryExpo(x*x, (n-1)/2);
        else return binaryExpo(x*x, n/2);
    }

    /*
     * Solution: another version
     */
    public double myPow(double x, int n) {
        if(n==0) return 1;
        if(n<0) {
            // prevent integer overflow
            return 1/x * myPow(1/x, -1*(n+1));
        }
        return n%2==0 ? myPow(x*x, n/2) : x*myPow(x*x, (n-1)/2);
    }

    /*
     * 
     */
    public double myPow(double x, int n) {
    }
}

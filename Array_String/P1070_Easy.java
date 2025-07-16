/*
    1070. Greatest Common Divisor of Strings
    For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).
    Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.

    Example 1:
        Input: str1 = "ABCABC", str2 = "ABC"
        Output: "ABC"

    Example 2:
        Input: str1 = "ABABAB", str2 = "ABAB"
        Output: "AB"

    Example 3:
        Input: str1 = "LEET", str2 = "CODE"
        Output: ""
    
    Constraints:
        1 <= str1.length, str2.length <= 1000
        str1 and str2 consist of English uppercase letters.
 */
public class P1070_Easy {
    /*
     * Solution: brute forth
     * time complexity: O(min(n1, n2)* (m+n)) 
     *      We checked every prefix string base of the shorter string among str1 and str2, and verify if both strings are made by multiples of base.
     *      There are up to min(m,n) prefix strings to verify and each check involves iterating over the two input strings to check if the current base is the GCD string, 
     *      which costs O(m+n). Therefore, the overall time complexity is O(min(m,n) * (m+n))
     * space complexity: O(1) or O(min(n1, n2)) to keep a copy of base in each iteration
     */
    public String gcdOfStrings(String str1, String str2) {
        int n1 = str1.length(), n2 = str2.length();
        for(int i=Math.min(n1, n2); i>0; i--) {
            if(validGcd(str1, str2, i)) {
                return str1.substring(0, i);
            }
        }
        return "";
    }

    private boolean validGcd(String str1, String str2, int len) {
        int n1 = str1.length(), n2= str2.length();
        if(n1%len !=0 || n2%len!=0) {
            return false;
        } else {
            String base = str1.substring(0, len);
            return str1.replace(base, "").isEmpty() && str2.replace(base, "").isEmpty();
        }
    }

    /*
     * Solution: recursive
     * time complexity: O(n+m)
     * space complexity: O(n+m)
     */
    public String gcdOfStrings(String str1, String str2) {
        if(str2.isEmpty()) return str1;
        if(str2.length()>str1.length()) return gcdOfStrings(str2, str1);
        if(str1.startsWith(str2)) return gcdOfStrings(str1.substring(str2.length()), str2);
        return "";
    }

    /*
     * Solution: greatest common divisor
     * 
     * time complexity: O(n+m)
     * space complexity: O(n+m)
     */
    public String gcdOfStrings(String str1, String str2) {
        /*
         * Suppose there exists a divisible string base, we can write str1 and str2 in the form of multiples of base.
         * Since both strings contain multiple base, their concatenation must be consistent, regardless of the order (str1+str2 == str2+str1)
         */
        if(!(str1+str2).equals(str2+str1)) return "";

        // if strings are equal than return the substring from 0 to gcd of n1 and n2
        // get the GCD of the two lengths
        int gcdLen = gcd(str1.length(), str2.length());
        return str1.substring(0, gcdLen);
    }

    private int gcd(int x, int y) {
        if(y==0) return x;
        else return gcd(y, x%y);
    }
}

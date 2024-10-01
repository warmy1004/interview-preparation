/*
    2384. Largest Palindromic Number
    You are given a string num consisting of digits only.
    Return the largest palindromic integer (in the form of a string) that can be formed using digits taken from num. It should not contain leading zeroes.

    Notes:
        You do not need to use all the digits of num, but you must use at least one digit.
        The digits can be reordered.
        
    Example 1:
        Input: num = "444947137"
        Output: "7449447"
        Explanation: 
        Use the digits "4449477" from "444947137" to form the palindromic integer "7449447".
        It can be shown that "7449447" is the largest palindromic integer that can be formed.

    Example 2:
        Input: num = "00009"
        Output: "9"
        Explanation: 
        It can be shown that "9" is the largest palindromic integer that can be formed.
        Note that the integer returned should not contain leading zeroes.

    Constraints:
        1 <= num.length <= 10^5
        num consists of digits.
 */

/*
 * Similar Questions
 *      409. Longest Palindrome
 */
package Palindrome;

public class P2384_Medium {
    /*
     * Solution:
     * time complexity:
     * space complexity:
     */
    public String largestPalindromic(String num) {
        int[] freq = new int[10];
        for(char ch: num.toCharArray()) {
            freq[ch-'0']++;
        }
        if (freq[0] == num.length()) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        int mid = -1;
        for(int i=9; i>=0; i--) {
            if(sb.length() == 0 && i==0) continue;
            if(mid==-1 && freq[i]%2==1) {
                mid = i;
            }
            while(freq[i]>1) {
                sb.append(i);
                freq[i]-=2;
            }
        }
        return mid==-1? sb.toString()+sb.reverse().toString() : sb.toString() + mid + sb.reverse().toString();
    }
}

/*
 * Python
 */
class Solution:
    def largestPalindromic(self, num: str) -> str:
        count = Counter(num)
        answer = mid = ''

        for d in sorted(count.keys(), reverse=True):
            // count[d] & 1 : check odd or even. odd will return 1, even will return 0. So, mid will be d when count[d] is odd and mid will '' when count[d] is even
            mid = max(mid, d * (count[d]&1)) 
            // print d on the left side as much as count[d]'s half
            answer += d * (count[d]//2)

        // removing '0' so prevent 0-start    
        answer = answer.lstrip('0')
        return answer+mid+answer[::-1] or '0'

/*
    2575. Find the divisibility array of a string
    You are given a 0-indexed string word of length n consisting of digits, and a positive integer m.
    The divisibility array div of word is an integer array of length n such that:
        div[i] = 1 if the numeric value of word[0,...,i] is divisible by m, or
        div[i] = 0 otherwise.
    Return the divisibility array of word.

    Example 1:
        Input: word = "998244353", m = 3
        Output: [1,1,0,0,0,1,1,0,0]
        Explanation: There are only 4 prefixes that are divisible by 3: "9", "99", "998244", and "9982443".

    Example 2:
        Input: word = "1010", m = 10
        Output: [0,1,0,1]
        Explanation: There are only 2 prefixes that are divisible by 10: "10", and "1010".
    
    Constraints:
        1 <= n <= 10^5
        word.length == n
        word consists of digits from 0 to 9
        1 <= m <= 10^9
 */
package twoPointer.subarraySum_prefixSum;

public class P2575_Medium {
    /*
     * Solution
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int[] divisibilityArray(String word, int m) {
        int[] answer = new int[word.length()];
        long number = 0;
        for(int i=0; i<word.length(); i++) {
            int digit = word.charAt(i)-'0';
            number = (number * 10 + digit) % m;
            if(number == 0) {
                answer[i]=1;
            }

            // Forming a number from each digit of word is long, so it will overflow and it causes forming a wrong number. 
            // So, instead of forming a new number from each digit, we need to keep track of the remainder from the last number. 
            // this is acutally the same way to calculate remainder
        }
        return answer;
    }
}

/*
    125. Valid Palindrome
    A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
    Given a string s, return true if it is a palindrome, or false otherwise.

    Example 1:
        Input: s = "A man, a plan, a canal: Panama"
        Output: true
        Explanation: "amanaplanacanalpanama" is a palindrome.

    Example 2:
        Input: s = "race a car"
        Output: false
        Explanation: "raceacar" is not a palindrome.

    Example 3:
        Input: s = " "
        Output: true
        Explanation: s is an empty string "" after removing non-alphanumeric characters.
        Since an empty string reads the same forward and backward, it is a palindrome.
    
    Constraints:
        1 <= s.length <= 2 * 10^5
        s consists only of printable ASCII characters.
 */
package Array_String;

public class P125_Easy {
    /*
     * Solution: reverse String 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public boolean isPalindrome_reverse1(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray()) {
            if(Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString().equals(sb.reverse().toString());
    }

    public boolean isPalindrome_reverse2(String s) {
        StringBuilder sb = new StringBuilder;
        s.chars().filter(c -> Character.isLetterOrDigit(c)).mapToObj(c -> Character.toLowerCase((char)c)).forEach(sb:: append);
        return sb.toString().equals(sb.reverse().toString());
    }

    public boolean isPalindrome_reverse3(String s) {
        String str = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed;)
    }

    /*
     * Solution: two pointer
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length()-1;
        while(i<j) {
            while(i<j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while(i<j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            if(Character.toLowerCase(s.charAt(i))!= Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}


/*
 * Python solution
 */
class Solution:
    def isPalindrome(self, s: str) -> bool:
        s1 = filter(lambda a: a.isalnum(), s)
        lowered = map(lambda a: a.lower(), s1)
        sb = list(lowered)
        rev = sb[::-1]
        return sb == rev

class Solution:
    def isPalindrome(self, s: str) -> bool:
        s = [ch.lower() for ch in s if ch.isalnum()]
        return s == s[::-1]

/*
 * With ~ operator:
 *      Operator ~ is the bitwise Not operator. It performs logical negation on a given number by flipping all of its bits: ~x == -x-1, ~0==-1, ~1=-2 etc.
 */
class Solution:
    def isPalindrome(self, s: str) -> bool:
        s = [c.lower() for c in s if c.isalnum()]
        return all(s[i] == s[~i] for i in range(len(s)//2))
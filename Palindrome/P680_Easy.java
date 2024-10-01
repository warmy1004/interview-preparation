/*
    680. Valid Palindrome 2

    Given a string s, return true if the s can be palindrome after deleting at most one character from it.

    Example 1:
        Input: s = "aba"
        Output: true

    Example 2:
        Input: s = "abca"
        Output: true
        Explanation: You could delete the character 'c'.

    Example 3:
        Input: s = "abc"
        Output: false
    
    Constraints:
        1 <= s.length <= 10^5
        s consists of lowercase English letters.
 */
package Palindrome;

public class P680_Easy {
    /*
     * Solution: Two pointers
     * time complexity: O(n)
     * space complextiy: O(1)
     */
    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length()-1;

        while(i<=j) {
            if(s.charAt(i)!= s.charAt(j)) {
                String s1 = s.substring(i, j);
                String s2 = s.substring(i+1, j+1);
                return isPalindrome(s1) || isPalindrome(s2);
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    boolean isPalindrome(String s) {
        int i=0;
        int j = s.length()-1;
        while(i<=j) {
            if(s.charAt(i++)!= s.charAt(j--)) return false;
        }
        return true;
    }

    /*
     * Solution: Two pointer 2
     * time complexity: O(n)
     * space complextiy: O(1)
     */
    public boolean validPalindrome2(String s) {
        return isPalindrome(s, 0, s.length()-1, false);
    }

    boolean isPalindrome(String s, int i, int j, boolean deleted) {
        while(i<=j) {
            if(s.charAt(i)!=s.charAt(j)) {
                if(deleted) {
                    return false;
                }
                return isPalindrome(s, i+1, j, true) || isPalindrome(s, i, j-1, true);
            }
            i++;
            j--;
        }
        return true;
    }
}

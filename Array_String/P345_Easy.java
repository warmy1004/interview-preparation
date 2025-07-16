/*
    345. Reverse vowels of a String
    Given a string s, reverse only all the vowels in the string and return it.
    The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

    Example 1:
        Input: s = "IceCreAm"
        Output: "AceCreIm"
        Explanation:
        The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".

    Example 2:
        Input: s = "leetcode"
        Output: "leotcede"

    Constraints:
        1 <= s.length <= 3 * 10^5
        s consist of printable ASCII characters.
 */
package Array_String;

public class P345_Easy {
    /*
     * Solution: two pointers
     * time complexity:O(n)
     * space complexity: O(n)
     */
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int i=0, j=arr.length-1;
        while(i<j) {
            while(i<s.length() && !isVowel(arr[i])) {
                i++;
            }
            while(j>=0 && !isVowel(arr[j])) {
                j--;
            }

            if(i<j) {
                char temp = arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                i++;
                j--;
            }
        }
        return new String(arr);
    }

    private boolean isVowel(char ch) {
        return ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u' || ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U';
    }
}

/*
 * JavaScript
 */
var reverseVowels = function(s) {
    let vowels = 'aeiouAEIOU';
    let i=0, j=s.length-1;
    const arr = s.split('');
    while(i<j) {
        while(i<j && !vowels.includes(arr[i])) {
            i++;
        }
        while(i<j && !vowels.includes(arr[j])) {
            j--;
        }

        [arr[i], arr[j]] = [arr[j], arr[i]];
        i++;
        j--;
    }
    return arr.join('');
};

/*
 * Python
 */
class Solution:
    def reverseVowels(self, s: str) -> str:
        arr = list(s)
        i , j = 0, len(s)-1
        vowels = "aeiouAEIOU"
        while i<j:
            while i<j and arr[i] not in vowels:
                i+=1
            while i<j and arr[j] not in vowels:
                j-=1
            arr[i], arr[j] = arr[j], arr[i]
            i+=1
            j-=1
        return ''.join(arr)
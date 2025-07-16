/*
    151. Reverse Words in a string
    Given an input string s, reverse the order of the words.
    A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
    Return a string of the words in reverse order concatenated by a single space.
    Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

    Example 1:
        Input: s = "the sky is blue"
        Output: "blue is sky the"

    Example 2:
        Input: s = "  hello world  "
        Output: "world hello"
        Explanation: Your reversed string should not contain leading or trailing spaces.

    Example 3:
        Input: s = "a good   example"
        Output: "example good a"
        Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
    
    Constraints:
        1 <= s.length <= 10^4
        s contains English letters (upper-case and lower-case), digits, and spaces ' '.
        There is at least one word in s.
 
    Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 */
package twoPointer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P151_Medium {
    /*
     * Solution: Using built-in function
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public String reverseWords(String s) {
        String[] splitted = s.trim().split("\\s+");
        String answer = "";
        for(int i=splitted.length-1; i>0; i--) {
            answer += splitted[i]+" ";
        }
        return answer+splitted[0];
    }

    public String reverseWords(String s) {
        String[] splitted = s.trim().split("\\s+");
        StringBuilder answer = new StringBuilder();
        for (int i = splitted.length - 1; i > 0; i--) {
            answer.append(splitted[i]);
            answer.append(" ");
        }
        return answer.append(splitted[0]).toString();
    }

    public String reverseWords(String s) {
        String[] arr = s.trim().split(" ");
        StringBuilder str = new StringBuilder();
        for(int i=arr.length-1; i>0; i--) {
            if(!arr[i].equals("")) {
                str.append(arr[i]);
                str.append(" ");
            }
        }
        return str.append(arr[0]).toString();
    }

    /*
     * Solution: without built-in function
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public String reverseWords(String s) {
        int n = s.length();
        List<String> words = new ArrayList<>();
        int i=0;
        while(i<n) {
            while(i<n && s.charAt(i)==' ') i++;
            if(i==n) break;
            int start = i;

            while(i<n && s.charAt(i)!=' ') i++;
            int end = i;
            words.add(s.substring(start, end));
        }

        String reversed = "";
        for(int j = words.size()-1; j>0; j--) {
            reversed += words.get(j) + " ";
        }
        return reversed+words.get(0);
    }

    /*
     * Solution: Stack
     * time complexity:
     * space complexity:
     */
    public String reverseWords(String s) {
        Stack<String> stack = new Stack<>();
        for(String str: s.trim().split(" ")) {
            if(!str.isEmpty()) {
                stack.push(str);
            }
        }

        StringBuilder str = new StringBuilder();
        while(!stack.isEmpty()) {
            str.append(stack.pop());
            str.append(" ");
        }
        return str.toString().trim();
    }
}

/*
 * JavaScript
 */
var reverseWords = function(s) {
    let words = s.trim().split(/\s+/).reverse();
    return words.join(" ");
}

var reverseWords = function(s) {
    let words = s.split(' ');
    let arr = [];
    for(let i=words.length-1; i>=0; i--) {
        if(words[i]) {
            arr.push(words[i]);
        }
    }
    return arr.join(" ");
}

/*
 * Python
 */
class Solution:
    def reverseWords(self, s: str) -> str:
        return " ".join(reversed(s.split()))

class Solution:
    def reverseWords(self, s:str) -> str:
        words = s.split()
        res = []
        for i in range(len(words)-1, -1, -1):
            res.append(words[i])
            if i!=0:
                res.append(" ")
        return "".join(res)

class Solution:
    def reverseWords(self, s:str) -> str:
        words = s.split()
        i, j = 0, len(words)-1
        while i<j:
            words[i], words[j] = words[j], words[i]
            i+=1
            j-=1
        return " ".join(words)

class Solution:
    def reverseWords(self, s:str) -> str:
        words = s.split()
        reversed_words = words[::-1]
        return " ".join(reversed_words)

class Solution:
    def reverseWords(self, s:str) -> str:
        words = s.split()
        reversed_words = reversed(words)
        return " ".join(reversed_words)
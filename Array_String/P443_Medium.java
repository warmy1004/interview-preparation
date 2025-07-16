/*
    443. String Compression
    Given an array of characters chars, compress it using the following algorithm:
    Begin with an empty string s. For each group of consecutive repeating characters in chars:
        If the group's length is 1, append the character to s.
        Otherwise, append the character followed by the group's length.
    The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
    After you are done modifying the input array, return the new length of the array.
    You must write an algorithm that uses only constant extra space.

    Example 1:
        Input: chars = ["a","a","b","b","c","c","c"]
        Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
        Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".

    Example 2:
        Input: chars = ["a"]
        Output: Return 1, and the first character of the input array should be: ["a"]
        Explanation: The only group is "a", which remains uncompressed since it's a single character.

    Example 3:
        Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
        Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
        Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".

    Constraints:
        1 <= chars.length <= 2000
        chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
 */

package Array_String;

public class P443_Medium {
    /*
     * Solution: two pointers
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int compress(char[] chars) {
        int i=0, result = 0;
        while(i<chars.length) {
            int value = 1;
            while(i+value < chars.length && chars[i] == chars[i+value]) {
                value++;
            }
            chars[result++] = chars[i];
            if(value>1) {
                for(char ch: String.valueOf(value).toCharArray()) {
                    chars[result++] = ch;
                }
            }
            i+= value;
        }
        return result;
    }
}


/*
 * Python
 */
class Solution:
    def compress(self, chars: List[str]) -> int:
        i, result = 0, 0
        while i<len(chars):
            value = 1
            while i+value < len(chars) and chars[i] == chars[i+value]:
                value+=1
            chars[result] = chars[i]
            result+=1
            i+= value
            if value > 1:
                numbers = str(value)
                chars[result: result+len(numbers)] = list(numbers)
                result+=len(numbers)
        return result

class Solution:
    def compress(self, chars: List[str]) -> int:
        i, result = 0, 0
        while i<len(chars):
            ch = chars[i]
            count = 0
            while i<len(chars) and ch == chars[i]:
                count+=1
                i+=1
            chars[result] = ch
            result+=1
            if count > 1:
                for c in str(count):
                    chars[result] = c
                    result+=1
        return result

/*
 * JavaScript
 */
var compress = function(chars) {
    let i = 0, result = 0;
    while(i<chars.length) {
        let count = 0;
        let ch = chars[i];
        while(i<chars.length && chars[i] == ch) {
            i++;
            count++;
        }
        chars[result++] = ch;
        if(count>1) {
            for(let c of count.toString()) {
                chars[result++] = c;
            }
        }
    }
    return result;
};
/*
    68. Text Justification
    Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
    You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
    Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
    For the last line of text, it should be left-justified, and no extra space is inserted between words.

    Note:
    A word is defined as a character sequence consisting of non-space characters only.
    Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
    The input array words contains at least one word.
    
    Example 1:
        Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
        Output:
        [
        "This    is    an",
        "example  of text",
        "justification.  "
        ]

    Example 2:
        Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
        Output:
        [
        "What   must   be",
        "acknowledgment  ",
        "shall be        "
        ]
        Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
        Note that the second line is also left-justified because it contains only one word.

    Example 3:
        Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
        Output:
        [
        "Science  is  what we",
        "understand      well",
        "enough to explain to",
        "a  computer.  Art is",
        "everything  else  we",
        "do                  "
        ]

    Constraints:
        1 <= words.length <= 300
        1 <= words[i].length <= 20
        words[i] consists of only English letters and symbols.
        1 <= maxWidth <= 100
        words[i].length <= maxWidth
 */
package Array_String;

import java.util.ArrayList;
import java.util.List;

public class Top150_P68_ToDo_Hard {
    /* 
     * Solution : Modulo based approach
     * Unlike most problems on LeetCode, this is one that can be solved by just doing exactly what the problem statement is telling us to do.
     * You don't need any data structures or algorithmic tricks to solve this problem. The point of this problem is to test your ability to quickly write clean code while navigating edge cases. 
     * 
     * time complexity: O(n*k), when n be words.length, k be the average length of a word, and m be maxWidth
     * space complexity: O(m)
     * 
     * Runtime beats 59.09%
     * Memory beats 21.73%
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while( i< words.length) {
            List<StringBuilder> target = getTargets(words, maxWidth, i);
            i+= target.size();
            result.add(formatText(target, words, maxWidth, i));
        }
        return result;
    }

    List<StringBuilder> getTargets(String[] words, int maxWidth, int start) {
        List<StringBuilder> targets = new ArrayList<>();
        int count = 0;
        while(start < words.length && count+words[start].length()<=maxWidth) {
            targets.add(new StringBuilder(words[start]));
            count+= words[start].length() + 1;
            start++;
        }
        return targets;
    }

    String formatText(List<StringBuilder> target, String[] words, int maxWidth, int curloc) {
        int count = 0;
        for(int i= 0; i<target.size()-1; i++) {
            target.get(i).append(" ");
            count += target.get(i).length();
        }
        int extraSpaces = maxWidth - count - target.getLast().length();
        if(target.size()==1 || curloc== words.length) {
            target.getLast().append(" ".repeat(extraSpaces));
        } else {
            int t_index = 0;
            while(extraSpaces > 0) {
                if(t_index<target.size()-1) {
                    target.get(t_index).append(" ");
                    extraSpaces--;
                    t_index++;
                } else {
                    t_index = 0;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for(StringBuilder sb : target) {
            result.append(sb);
        }
        return result.toString();
    }
}

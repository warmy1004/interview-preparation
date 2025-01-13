/*
    17. letter combinations of a phone number
    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
    A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

    Example 1:
        Input: digits = "23"
        Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

    Example 2:
        Input: digits = ""
        Output: []

    Example 3:
        Input: digits = "2"
        Output: ["a","b","c"]
    
    Constraints:
        0 <= digits.length <= 4
        digits[i] is a digit in the range ['2', '9'].
 */
package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P17_Medium {
    /*
     * Solution: backtracking
     * time complexity: O(4^n*n) where N is the length of digits. 
     *      Note that 4 in this expression is referring to the maximum value length in the hashmap and not to the length of the input.
     *      The worst-case is where the input consists of only 7s and 9s. In that case, we have to explore 4 additional paths for every extra digit. Then, for each combination, it costs up to N to build the combination.
     *      This problem can be generalized to a scenario where numbers correspond with up to M digits, in which case the time complexity would be O(M^N*N). 
     * space complexity: O(n)
     */
    String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> answer = new ArrayList<>();
        if(digits.isEmpty()) return answer;
        backtrack(digits, 0, "", answer);
        return answer;
    }

    void backtrack(String digits, int idx, String str, List<String> answer) {
        if(idx == digits.length()) {
            answer.add(str);
            return;
        }
        for(char ch: letters[digits.charAt(idx)-'0'].toCharArray()) {
            str+= ch;
            backtrack(digits, idx+1, str, answer);
            str.substring(0, str.length()-1);
        }
    }

    /*
     * Solution: version 2
     */
    public List<String> letterCombinations(String digits) {
        List<String> answer = new ArrayList<>();
        if(digits.isEmpty()) return answer;
        Map<Character, List<Character>> map = buildMap();
        backtrack(digits, 0, new StringBuilder(), answer, map);
        return answer;
    }

    void backtrack(String digits, int idx, StringBuilder sb, List<String> answer, Map<Character, List<Character>> map) {
        if(idx == digits.length()) {
            answer.add(sb.toString());
            return;
        }

        for(Character ch: map.get(digits.charAt(idx))) {
            sb.append(ch);
            backtrack(digits, idx+1, sb, answer, map);
            sb.deleteCharAt(idx);
        }
    }

    Map<Character, List<Character>> buildMap() {
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', Arrays.asList('a','b','c'));
        map.put('3', Arrays.asList('d','e','f'));        
        map.put('4', Arrays.asList('g','h','i'));
        map.put('5', Arrays.asList('j','k','l'));
        map.put('6', Arrays.asList('m','n','o'));
        map.put('7', Arrays.asList('p','q','r','s'));
        map.put('8', Arrays.asList('t','u','v'));
        map.put('9', Arrays.asList('w','x','y','z'));
        return map;
    }
}

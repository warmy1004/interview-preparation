/*     
    383. Ransom Note
    Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
    Each letter in magazine can only be used once in ransomNote.

    Example 1:
        Input: ransomNote = "a", magazine = "b"
        Output: false

    Example 2:
        Input: ransomNote = "aa", magazine = "ab"
        Output: false

    Example 3:
        Input: ransomNote = "aa", magazine = "aab"
        Output: true
    
    Constraints:
        1 <= ransomNote.length, magazine.length <= 105
        ransomNote and magazine consist of lowercase English letters. 
*/

import java.util.HashMap;

public class Problem383_HashMap {
    // public boolean canConstruct(String ransomNote, String magazine) {
    //     if(ransomNote.length() > magazine.length()) return false;

    //     HashMap<Character, Integer> map = new HashMap<>();
    //     for(char mg : magazine.toCharArray()) {
    //         map.put(mg, map.getOrDefault(mg,0)+1);
    //     }

    //     for(char rn: ransomNote.toCharArray()) {
    //         if(!map.containsKey(rn) || map.get(rn)==0) return false;
    //         else map.put(rn, map.get(rn)-1);
    //     }
    //     return true;
    // }

    public boolean canConstruct(String ransomNote, String magazine) {
        // This solution (2ms) is faster than the above hashMap one (10ms)
        int[] alphabet = new int[26];
        for(char c: magazine.toCharArray()) {
            alphabet[c-'a']++; // if you subtract any char with 'a', it gives you index of that alphabet
        }
        for(char c: ransomNote.toCharArray()) {
            if(--alphabet[c-'a']<0) return false; // we decrement the counter of the character from its own index and check the value
        }
        return true;
    }
}

/*
 * time complexity: O(n)
 * space complexity: O(1), this is nver more than 26
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    1657. Determine if two strings are close
    Two strings are considered close if you can attain one from the other using the following operations:
        Operation 1: Swap any two existing characters.
            For example, abcde -> aecdb
        Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
            For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
    You can use the operations on either string as many times as necessary.
    Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.

    Example 1:
        Input: word1 = "abc", word2 = "bca"
        Output: true
        Explanation: You can attain word2 from word1 in 2 operations.
        Apply Operation 1: "abc" -> "acb"
        Apply Operation 1: "acb" -> "bca"

    Example 2:
        Input: word1 = "a", word2 = "aa"
        Output: false
        Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
    
    Example 3:
        Input: word1 = "cabbba", word2 = "abbccc"
        Output: true
        Explanation: You can attain word2 from word1 in 3 operations.
        Apply Operation 1: "cabbba" -> "caabbb"
        Apply Operation 2: "caabbb" -> "baaccc"
        Apply Operation 2: "baaccc" -> "abbccc"
    
    Constraints:
        1 <= word1.length, word2.length <= 10^5
        word1 and word2 contain only lowercase English letters.
 */

public class P1657_ToDo_Hash_Medium {
    /*
     * Solution: hash map + list
     * time complexity: O(n)
     *      we iterate over each word to build the hashmap which would take O(n) times. 
     *      Further, we sort the character keys and frequency values of each hashmap. 
     *      The maixmum size of hashmap would be 26, as we store each character a-z only once. 
     *      In the worst case, all the sort operations would take O(26log26) time to sort those frequency values.
     * space complexity: O(1)
     *      As the maximum size of each hashmap would be 26, we are using constant extra space.
     */
    public boolean closeStrings(String word1, String word2) {
        if(word1.length()!=word2.length()) return false;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for(char ch: word1.toCharArray()) {
            map1.put(ch, map1.getOrDefault(ch, 0)+1);
        }
        for(char ch: word2.toCharArray()) {
            map2.put(ch, map2.getOrDefault(ch, 0)+1);
        }
        if(!map1.keySet().equals(map2.keySet())) return false;
        List<Integer> list1 = new ArrayList<>(map1.values());
        List<Integer> list2 = new ArrayList<>(map2.values());
        Collections.sort(list1);
        Collections.sort(list2);
        return list1.equals(list2);
    }

    /*
     * Solution: frequency array
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public boolean closeStrings(String word1, String word2) {
        if(word1.length()!=word2.length()) return false;
        int arr1[] = new int[26];
        int arr2[] = new int[26];
        int key1 = 0;
        int key2= 0;
        for(char ch: word1.toCharArray()) {
            arr1[ch-'a']++;
            key1 = key1 | (1<<(ch-'a'));
        }
        for(char ch: word2.toCharArray()) {
            arr2[ch-'a']++;
            key2 = key2 | (1<<(ch-'a'));
        }
        if(key1!=key2) return false;
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for(int i=0; i<26; i++) {
            if(arr1[i]!=arr2[i]) return false;
        }
        return true;
    }

    public boolean closeStrings(String word1, String word2) {
        if(word1.length() != word2.length()) return false;
        int arr1[] = new int[26];
        int arr2[] = new int[26];
        for(char ch: word1.toCharArray()) {
            arr1[ch-'a']++;
        }
        for(char ch: word2.toCharArray()) {
            arr2[ch-'a']++;
        }
        for(int i=0; i<26; i++) {
            if( (arr1[i]>0 && arr2[i]==0) || (arr1[i]==0 && arr2[i]>0)) return false;
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    public boolean closeStrings(String word1, String word2) {
        if(word1.length() != word2.length()) return false;
        int arr1[] = new int[26];
        int arr2[] = new int[26];
        for(char ch: word1.toCharArray()) {
            arr1[ch-'a']++;
        }
        for(char ch: word2.toCharArray()) {
            arr2[ch-'a']++;
        }
        for(int i=0; i<26; i++) {
            if( (arr1[i]>0 && arr2[i]==0) || (arr1[i]==0 && arr2[i]>0)) return false;
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for(int i=25; i>=0; i--) {
            if(arr1[i]!= arr2[i]) return false;
            if(arr1[i]==0) break;
        }
        return true;
    }
}

/*
 * Javascript
 */
/**
 * @param {string} word1
 * @param {string} word2
 * @return {boolean}
 */
var closeStrings = function(word1, word2) {
    let arr1 = new Array(26).fill(0);
    let arr2 = new Array(26).fill(0);
    for(let ch of word1) {
        arr1[ch.charCodeAt(0)-'a'.charCodeAt(0)]++;
    }
    for(et ch of word2) {
        arr2[ch.charCodeAt(0)-'a'.charCodeAt(0)]++;
    }
    for(let i=0; i<26; i++) {
        if(arr1[i]>0&&arr2[i]==0 || arr1[i]==0&&arr2[i]>0) return false;
    }
    arr1.sort((a,b)=>a-b);
    arr2.sort((a,b)=>a-b);
    for(let i=0; i<26; i++) {
        if(arr1[i]!=arr2[i]) return false;
    }
    return true;
};

var closeStrings = function(word1, word2) {
}

/*
 * Python
 */
class Solution:
    def closeStrings(self, word1:str, word2:str) -> bool:
        arr1 = [0]*26
        arr2 = [0]*26
        for ch in word1:
            arr1[ord(ch)-ord('a')] +=1
        for ch in word2:
            arr2[ord(ch)-ord('a')]+=1
        for i in range(26):
            if arr1[i]==0 and arr2[i]>0 or arr1[i]>0 and arr2[i]==0:
                return False
        arr1.sort()
        arr2.sort()
        for i in range(26):
            if arr1[i]!=arr2[i]:
                return False
        return True

class Solution:
    def closeStrings(self, word1:str, word2:str) -> bool:
    map1 = Counter(word1)
    map2 = Counter(word2)

    if set(map1.keys()) != set(map2.keys()):
        return False
    sorted_map1 = sorted(map1.values())
    sorted_map2 = sorted(map2.values())
    return sorted_map1 == sorted_map2

from collections import Counter
class Solution:
    def closeStrings(self, word1:str, word2:str) -> bool:
        return set(word1)==set(word2) and Counter(Counter(word1).values()) == Counter(Counter(word2).values())

class Solution:
    def closeStrings(self, word1:str, word2:str) -> bool:
        return sorted(Counter(word1).values()) == sorted(Counter(word2).values()) and set(Counter(word1).keys()) == set(Counter(word2).keys()) 
    
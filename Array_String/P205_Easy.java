/*
    205. Isomorphic Strings
    Given two strings s and t, determine if they are isomorphic.
    Two strings s and t are isomorphic if the characters in s can be replaced to get t.
    All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

    Example 1:
        Input: s = "egg", t = "add"
        Output: true
        Explanation:
            The strings s and t can be made identical by:
            Mapping 'e' to 'a'.
            Mapping 'g' to 'd'.

    Example 2:
        Input: s = "foo", t = "bar"
        Output: false
        Explanation:
            The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.

    Example 3:
        Input: s = "paper", t = "title"
        Output: true

    Constraints:
        1 <= s.length <= 5 * 10^4
        t.length == s.length
        s and t consist of any valid ascii character.
 */
package Array_String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P205_Easy {
    /*
     * Solution: transformation with hashmap
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public boolean isIsomorphic(String s, String t) {
        return transformString(s).equals(transformString(t));
    }

    String transformString(String str) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder transformed = new StringBuilder();
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if(!map.containsKey(ch)) {
                map.put(ch, i);
            }
            transformed.append(map.get(ch));
            transformed.append(",");
        }
        return transformed.toString();
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            char s1 = s.charAt(i);
            char t1 = t.charAt(i);

            if(map.containsKey(s1) && map.get(s1)!=t1) return false;
            if(!map.containsKey(s1) && map.containsValue(t1)) return false;
            map.put(s1, t1);
        }
        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> smap = new HashMap<>();
        Map<Character, Integer> tmap = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            if(!smap.containsKey(s.charAt(i))) {
                smap.put(s.charAt(i), i);
            }
            if(!tmap.containsKey(t.charAt(i))) {
                tmap.put(t.charAt(i), i);
            }
            if(smap.get(s.charAt(i))!= tmap.get(t.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /*
     * Solution: using array
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public boolean isIsomorphic(String s, String t) {
        int[] mapS = new int[256];
        int[] mapT = new int[256];
        for(int i=0; i<s.length(); i++) {
            if(mapS[s.charAt(i)] != mapT[t.charAt(i)]) {
                return false;
            }
            mapS[s.charAt(i)] = i+1;
            mapT[t.charAt(i)] = i+1;
        }
        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        int[] mapS = new int[256];
        Arrays.fill(mapS, -1);
        int[] mapT = new int[256];
        Arrays.fill(mapT, -1);

        for(int i=0; i<s.length(); i++) {
            char s1 = s.charAt(i);
            char t1 = t.charAt(i);

            if(mapS[s1] == -1 && mapT[t1] == -1) {
                mapS[s1] = t1;
                mapT[t1] = s1;
            } else if(mapS[s1]!=t1 || mapT[t1]!=s1) {
                return false;
            }
        }
        return true;
    }
}

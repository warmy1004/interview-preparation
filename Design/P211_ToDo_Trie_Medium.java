/*
    211. Design add and search words data structure
    Design a data structure that supports adding new words and finding if a string matches any previously added string.
    Implement the WordDictionary class:
        WordDictionary() Initializes the object.
        void addWord(word) Adds word to the data structure, it can be matched later.
        bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
    
    Example:
    Input
        ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
        [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
    Output
        [null,null,null,null,false,true,true,true]
    Explanation
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        wordDictionary.search("pad"); // return False
        wordDictionary.search("bad"); // return True
        wordDictionary.search(".ad"); // return True
        wordDictionary.search("b.."); // return True
    
    Constraints:
        1 <= word.length <= 25
        word in addWord consists of lowercase English letters.
        word in search consist of '.' or lowercase English letters.
        There will be at most 2 dots in word for search queries.
        At most 104 calls will be made to addWord and search.
 */
package Design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P211_ToDo_Trie_Medium {
    /*
     * Solution: With trie
     * time complexity: O(m) for the well-defined words without dots, where M is the key length, and N is a number of keys, and O(n*26^m) for the undefined words.
     *      That corresponds to the worst-case situation of searching an undefined word which is one character longer than all inserted keys.
     * space complexity: O(1)
     */
    Node root;

    public WordDictionary() {
        root = new Node();
    }
    
    public void addWord(String word) {
        Node curr = root;
        for(char ch: word.toCharArray()) {
            if(curr.children[ch-'a'] == null) {
                curr.children[ch-'a'] = new Node();
            }
            curr = curr.children[ch-'a'];
        } 
        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }

    private boolean searchHelper(String word, int start, Node curr) {
        for(int i=start; i<word.length(); i++) {
            char ch = word.charAt(i);
            if(ch == '.') {
                for(int child = 0; child < curr.children.length; child++) {
                    if(curr.children[child] != null && searchHelper(word, i+1, curr.children[child])) {
                        return true;
                    }
                }
                return false;
            } else {
                if(curr.children[ch-'a'] == null) return false;
                curr = curr.children[ch-'a'];
            }
        }
        return curr.isEnd;
    }

    /*
     * Solution: with map
     * 
     * This solution will pass all test cases and formally has O(mn) time complexity for the search, where M is the length of the word to find, and n is the number of words.
     * Although this solution is not efficient for the most important practical use cases:
     *      Finding all keys with a common prefix
     *      enumerating a dataset of strings in lexicographical order
     *      scaling for the large datasets. Once the hash table increases in size, there are a lot of hash collisions and the search time complexity could degrade to O(n^2*m) where n is the number of the inserted keys
     * Trie could use less space compared to hashmap when storing many keys with the same prefix. In this case, using trie has only O(n*m) time complexity. 
     * 
     * time complexity: O(nm)
     * space complexity: O(n)
     */
    Map<Integer, Set<String>> map;
    public WordDictionary() {
        map = new HashMap<>();
    }

    public void addWord(String word) {
        int len = word.length();
        if(!map.containsKey(len)) {
            map.put(len, new HashSet<>());
        }
        map.get(len).add(word);
    }
    
    public boolean search(String word) {
        int len = word.length();
        if(map.containsKey(len)) {
            for(String str: map.get(len)) {
                int idx = 0;
                while(idx<len && (str.charAt(idx) == word.charAt(idx) || word.charAt(idx) =='.')) {
                    idx++;
                }
                if(idx == len) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Node {
    boolean isEnd;
    Node[] children;
    public Node() {
        isEnd = false;
        children = new Node[26];
    }
}
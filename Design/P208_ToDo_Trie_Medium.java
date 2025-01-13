/*
    208. Implement Trie(prefix tree)
    A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
    Implement the Trie class:
        Trie() Initializes the trie object.
        void insert(String word) Inserts the string word into the trie.
        boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
        boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
    
    Example 1:
    Input
        ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
        [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
    Output
        [null, null, true, false, true, null, true]
    Explanation
        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple");   // return True
        trie.search("app");     // return False
        trie.startsWith("app"); // return True
        trie.insert("app");
        trie.search("app");     // return True
    
    Constraints:
        1 <= word.length, prefix.length <= 2000
        word and prefix consist only of lowercase English letters.
        At most 3 * 10^4 calls in total will be made to insert, search, and startsWith.
 */
package Design;

/*
 * Trie : a tree based data structure for an efficient string search
 */

public class P208_ToDo_Trie_Medium {
    /*
     * Solution: Node
     * time complexity: O(m)
     * space complexity: O(1)
     */
    Node root;
    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
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
        Node curr = root;
        for(char ch: word.toCharArray()) {
            if(curr.children[ch-'a'] == null) return false;
            curr = curr.children[ch-'a'];
        }
        return curr.isEnd == true;
    }
    
    public boolean startsWith(String prefix) {
        Node curr = root;
        for(char ch: prefix.toCharArray()) {
            if(curr.children[ch-'a'] == null) return false;
            curr = curr.children[ch-'a'];
        }
        return true;
    }
}

class Node {
    Node[] children;
    boolean isEnd;
    public Node() {
        children = new Node[26];
        isEnd = false;
    }
}
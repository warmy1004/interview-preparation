/*
    212. word search 2
    Given an m x n board of characters and a list of strings words, return all words on the board.
    Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

    Example 1:
        Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
        Output: ["eat","oath"]

    Example 2:
        Input: board = [["a","b"],["c","d"]], words = ["abcb"]
        Output: []
    
    Constraints:
        m == board.length
        n == board[i].length
        1 <= m, n <= 12
        board[i][j] is a lowercase English letter.
        1 <= words.length <= 3 * 10^4
        1 <= words[i].length <= 10
        words[i] consists of lowercase English letters.
        All the strings of words are unique.
 */
package Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P212_Must_ToDo_Trie_Hard {
    /*
     * Solution: backtracking with hashmap
     * 
     * time complexity: 
     * space complexity:
     */
    Map<String, Integer> map = new HashMap<>();
    Set<String> set = new HashSet<>();
    int maxLen = 1;
    public List<String> findWords(char[][] board, String[] words) {
        for(String word: words) {
            map.put(word, 1);
            maxLen = Math.max(word.length(), maxLen);
        }

        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                backtrack(board, i, j, "", visited);
            }
        }
        return new ArrayList<>(set);
    }

    void backtrack(char[][] board, int i, int j, String str, boolean[][] visited) {
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || visited[i][j] == true || str.length() >= maxLen) return;
        visited[i][j] = true;
        str+=board[i][j];
        if(map.containsKey(str)) {
            set.add(str);
        }
        backtrack(board, i-1, j, str, visited);
        backtrack(board, i+1, j, str, visited);
        backtrack(board, i, j-1, str, visited);
        backtrack(board, i, j+1, str, visited);
        visited[i][j] = false;
        str = str.substring(0, str.length()-1);
    }


    /*
     * Solution: backtracking with Trie
     * 
     * time complexity: O(M(4*3^(L-1))) where M is the number of cells in the board and L is the maximum length of words
     * space complexity: O(n)
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> list = new ArrayList<>();
        TrieNode root = buildTrie(words);

        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                backtrack(board, i, j, root, list);
            }
        }
        return list;
    } 

    void backtrack(char[][] board, int i, int j, TrieNode root, List<String> list) {
        if(i<0 || i>=board.length || j<0 || j>=board[0].length) return;
        char ch = board[i][j];
        if(ch=='#' || root.neighbors[ch-'a'] == null) return;
        root = root.neighbors[ch-'a'];
        if(root.word != null) {
            list.add(root.word);
            root.word = null; // prevent a duplicate
        }
        board[i][j] = '#';
        backtrack(board, i-1, j, root, list);
        backtrack(board, i+1, j, root, list);
        backtrack(board, i, j-1, root, list);
        backtrack(board, i, j+1, root, list);
        board[i][j] = ch;
    }

    TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for(String word: words) {
            TrieNode curr = root;
            for(char ch: word.toCharArray()) {
                if(curr.neighbors[ch-'a'] == null) {
                    curr.neighbors[ch-'a'] = new TrieNode();
                }
                curr = curr.neighbors[ch-'a'];
            }
            curr.word = word;
        }
        return root;
    }
}

class TrieNode{
    TrieNode[] neighbors = new TrieNode[26];
    String word = null;
}

/*
    127. Word Ladder
    A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

        Every adjacent pair of words differs by a single letter.
        Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
        sk == endWord
    Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

    Example 1:
        Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
        Output: 5
        Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

    Example 2:
        Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
        Output: 0
        Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
    
    Constraints:
        1 <= beginWord.length <= 10
        endWord.length == beginWord.length
        1 <= wordList.length <= 5000
        wordList[i].length == beginWord.length
        beginWord, endWord, and wordList[i] consist of lowercase English letters.
        beginWord != endWord
        All the words in wordList are unique.
 */
package BFS_DFS;

import java.lang.runtime.TemplateRuntime;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class P127_Must_ToDo_Hard {
    /*
     * Solution: BFS
     * time complexity: O(n*m), where n is the number of words and m is the length of each word
     * space complexity: O(n*m)
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;

        // to make the search fast, so TLE can be prevented
        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        int step = 0;
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            step++;
            for(int i=0; i<levelSize; i++) {
                String curr = queue.remove();
                if(curr.equals(endWord)) return step;
                for(int ci = 0; ci<curr.length(); ci++) {
                    char[] arr = curr.toCharArray();
                    for(char ch = 'a'; ch<='z'; ch++) {
                        arr[ci] = ch;
                        String str = new String(arr);
                        if(wordSet.contains(str)) {
                            wordSet.remove(str);
                            queue.add(str);
                        }
                    }
                }
            }
        }
        return 0;
    }

    /*
     * Solution: using two sets for beginWord and endWord
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        Set<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);
        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);

        int len = 1;
        int strLen = beginWord.length();
        Set<String> visited = new HashSet<>();
        while(!beginSet.isEmpty() && !endSet.isEmpty()) {
            if(beginSet.size() > endSet.size()) {
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }

            Set<String> combined = new HashSet<>();
            for(String str: beginSet) {
                
                for(int i=0; i<strLen; i++) {
                    char[] arr = str.toCharArray();
                    for(char ch='a'; ch<='z'; ch++) {
                        arr[i] = ch;
                        String nxt = String.valueOf(arr);
                        if(endSet.contains(nxt)) return len+1;
                        if(!visited.contains(nxt) && wordList.contains(nxt)) {
                            visited.add(nxt);
                            combined.add(nxt);
                        }
                    }
                }
            }
            beginSet = combined;
            len++;
        }
        return 0;
    }
}

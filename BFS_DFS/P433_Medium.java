/*
    433. Minimum generic mutation
    A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
    Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.
        For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
    There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
    Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.
    Note that the starting point is assumed to be valid, so it might not be included in the bank.

    Example 1:
        Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
        Output: 1

    Example 2:
        Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
        Output: 2
        
    Constraints:
        0 <= bank.length <= 10
        startGene.length == endGene.length == bank[i].length == 8
        startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
 */
package BFS_DFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class P433_Medium {
    /*
     * Solution: BFS
     * time complexity: O(B), where B=bank.length
     * space complexity: O(1), because the problem limits the input explicitly, we technically use constant space
     */
    public int minMutation(String startGene, String endGene, String[] bank) {
        List<String> bankList = Arrays.asList(bank);
        char[] candidates = new char[]{'A', 'C', 'G', 'T'};
        Queue<String> queue = new ArrayDeque<>();
        queue.add(startGene);
        Set<String> checked = new HashSet<>();
        checked.add(startGene);
        int move = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                String curr = queue.remove();
                if(curr.equals(endGene)) {
                    return move;
                }
                for(char ch: candidates) {
                    for(int idx=0; idx<8; idx++) {
                        String temp = curr.substring(0, idx) + ch + curr.substring(idx+1);
                        if(bankList.contains(temp) && !checked.contains(temp)) {
                            checked.add(temp);
                            queue.add(temp);
                        }
                    }
                }
            }
            move++;
        }
        return -1;
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        List<String> bankList = Arrays.asList(bank);
        char[] choices = {'A', 'C', 'G', 'T'};

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.add(startGene);
        int move = 0;
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            for(int i=0; i<levelSize; i++) {
                String curr = queue.remove();
                if(curr.equals(endGene)) return move;
                char[] currChar = curr.toCharArray();
                for(int idx=0; idx<8; idx++) {
                    char oldChar = currChar[idx];
                    for(int ch = 0; ch<4; ch++) {
                        char newChar = choices[ch];
                        if(oldChar!=newChar) {
                            currChar[idx] = newChar;
                            String next = String.valueOf(currChar);
                            if(!visited.contains(next) && bankList.contains(next)) {
                                visited.add(next);
                                queue.add(next);
                            }
                        }
                    }
                    currChar[idx] = oldChar;
                }
            }
            move++;
        }
        return -1;
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        Queue<String> queue = new ArrayDeque<>();
        queue.add(startGene);
        int move = 1;
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            for(int i=0; i<levelSize; i++) {
                String currGene = queue.remove();
                Iterator<String> iter = bankSet.iterator();
                while(iter.hasNext()) {
                    String b = iter.next();
                    int diff = 0;
                    for(int j=0; j<8 && diff<2; j++) {
                        if(currGene.charAt(j)!=b.charAt(j)) {
                            diff++;
                        }
                    }
                    if(diff==1) {
                        if(b.equals(endGene)) return move;
                        queue.add(b);
                        iter.remove();
                    }
                }
            }
            move++;
        }
        return -1;
    }

    /*
     * Solution: DFS + backtrap
     * time complexity: O(B), where B=bank.length
     * space complexity: O(1), because the problem limits the input explicitly, we technically use constant space
     */
    int minMove = Integer.MAX_VALUE;
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> visited = new HashSet<>();
        dfs(startGene, endGene, visited, bank, 0);
        if(minMove == Integer.MAX_VALUE) return -1;
        return minMove;
    }

    void dfs(String startGene, String endGene, Set<String> visited, String[] bank, int currMove) {
        if(startGene.equals(endGene)) {
            minMove = Math.min(minMove, currMove);
            return ;
        }

        for(int i=0; i<8; i++) {
            if(!visited.contains(startGene) && diff(startGene, bank[i])==1) {
                visited.add(startGene);
                dfs(bank[i], endGene, visited, bank, currMove+1);
                visited.remove(startGene);
            }
        }
    }

    int diff(String s, String b) {
        int count = 0;
        for(int i=0; i<8; i++) {
            if(s.charAt(i)!=b.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}

/*
    314. Binary Tree Vertical Order Traversal

    Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
    If two nodes are in the same row and column, the order should be from left to right.

    Example 1:
        Input: root = [3,9,20,null,null,15,7]
        Output: [[9],[3,15],[20],[7]]

    Example 2:
        Input: root = [3,9,8,4,0,1,7]
        Output: [[4],[9],[3,0,1],[8],[7]]

    Example 3:
        Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
        Output: [[4],[9,5],[3,0,1],[8,2],[7]]
    
    Constraints:
        The number of nodes in the tree is in the range [0, 100].
        -100 <= Node.val <= 100
 */

package BFS_DFS_BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class P314_ToDo_Medium {
    /*
     * Solution: BFS without Sorting
     * time complexity: O(n), where N is the number of nodes in the tree
     * space complexity: O(n)
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();

        int col = 0;
        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(root, col));

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        int minCol = Integer.MAX_VALUE, maxCol = Integer.MIN_VALUE;
        
        while(root!=null || !queue.isEmpty()) {
            Pair<TreeNode, Integer> p = queue.poll();
            root = p.getKey();
            col = p.getValue();

            if(root!=null) {
                if(!map.containsKey(col)) {
                    map.put(col, new ArrayList<>());
                }
                map.get(col).add(root.val);
                
                minCol = Math.min(minCol, col);
                maxCol = Math.max(maxCol, col);
                queue.offer(new Pair<>(root.left, col-1));
                queue.offer(new Pair<>(root.right, col+1));
            }
        }

        for(int i=minCol; i<=maxCol; i++) {
            answer.add(map.get(i));
        }
        return answer;
    }

    /*
     * Solution: BFS with Sorting
     * time complexity: O(NlogN), where N is the number of nodes in the tree
     *      The first part of the algorithm, which is BFS traversal, it takes O(n) since we traversed each node once and only once.
     *      The second part of the algorithm, which is sorting the obtained hash table by its keys, it takes O(nlogn) in the worst case, where the binary tree is extremely imblanced.
     * space complexity: O(n)
     */
    public List<List<Integer>> verticalOrder_sorting(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();

        int col = 0;
        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(root, col));

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        while(root!=null || !queue.isEmpty()) {
            Pair<TreeNode, Integer> p = queue.poll();
            root = p.getKey();
            col = p.getValue();

            if(root!=null) {
                if(!map.containsKey(col)) {
                    map.put(col, new ArrayList<>());
                }
                map.get(col).add(root.val);
              
                queue.offer(new Pair<>(root.left, col-1));
                queue.offer(new Pair<>(root.right, col+1));
            }
        }

        List<Integer> sortedKeys = new ArrayList<>(map.keySet());
        Collections.sort(sortedKeys);
        for(int i: sortedKeys) {
            answer.add(map.get(i));
        }
        return answer;
    }
}


// Python >>>

// from collections import defaultdict

// class Solution:
//     def verticalOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
//         if root is None:
//             return []
//         map = defaultdict(list)
//         queue = deque([(root, 0)]) 
         // queue = [(root,0)]

//         while q:
//             root, col = map.popleft()
//             if root:
//                 map[col].append(root.val)
//                 queue.append((root.left, col -1))
//                 queue.append((root.right, col+1))

//         mincol = min(map.keys())
//         maxcol = max(map.keys())
//         return [map[x] for x in range(mincol, maxcol+1)]
/*
    1161. Maximum level sum of a binary tree
    Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
    Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

    Example 1:
        Input: root = [1,7,0,7,-8,null,null]
        Output: 2
        Explanation: 
        Level 1 sum = 1.
        Level 2 sum = 7 + 0 = 7.
        Level 3 sum = 7 + -8 = -1.
        So we return the level with the maximum sum which is level 2.

    Example 2:
        Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
        Output: 2
    
    Constraints:
        The number of nodes in the tree is in the range [1, 10^4].
        -10^5 <= Node.val <= 10^5
 */
package BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

public class P1161_Medium {
    /*
     * Solution: DFS using hashMap
     * time complexity: O(n)
     * space complexity: O(n); dfs traversal is recursive and would take some space to store the tack calls.
     *          The maximum number of active stack calls at a time would be the tree's height, which in the worst case would be O(n when the tree is a straight line,)
     */
    public int maxLevelSum(TreeNode root) {
        Map<Integer, Integer> sum = new HashMap<>();
        dfs(root, 1, sum);
        int size = sum.size();
        int max = sum.get(size);
        int answer = size;
        while(--size>0) {
            if(max <= sum.get(size)) {
                answer = size;
                max = sum.get(size);
            }
        }
        return answer;
    }

    void dfs(TreeNode node, int lv, Map<Integer, Integer> sum) {
        sum.put(lv, sum.getOrDefault(lv,0 )+node.val);
        if(node.left!=null) dfs(node.left, lv+1, sum);
        if(node.right!=null) dfs(node.right, lv+1, sum);
    }

    /*
     * Solution: DFS using list
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int maxLevelSum(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs1(root, list, 0);
        return 1+ IntStream.range(0, list.size()).reduce(0, (a,b)-> list.get(a)<list.get(b)? b: a);
        /*
            int maxLv = 0;
            for(int i=0;i<list.size(); i++) {
                if(list.get(maxLv) < list.get(i)) {
                    maxLv = i
                }
            }
            return maxLv+1;
         */
    }

    void dfs1(TreeNode node, List<Integer> list, int lv) {
        if(list.size() == lv) list.add(node.val);
        else list.set(lv, list.get(lv)+node.val);
        if(node.left!=null) dfs1(node.left, list, lv+1);
        if(node.right!=null) dfs1(node.right, list, lv+1);
    }

    /*
     * Solution: DFS using array
     * time complexity: O(n)
     * space complexity: O(n)
     */
    int depth;
    int[] arr;
    public int maxLevelSum(TreeNode root) {
        arr = new int[10001];
        depth = 1;
        dfs2(root, 1);
        int result = 1, sum = root.val;
        for(int i=2; i<=depth; i++) {
            if(arr[i]>sum) {
                result=i;
                sum = arr[i];
            }
        }
        return result;
    }
    private void dfs2(TreeNode node, int level) {
        if(level > depth) depth = level;
        arr[level]+=node.val;
        if(node.left!=null) dfs2(node.left, level+1);
        if(node.right!=null) dfs2(node.right, level+1);
    }

    /*
     * Solution: iterative
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int maxLevelSum(TreeNode root) {
        if(root == null) return null;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int maxLevel = 1;
        int maxSum = Integer.MIN_VALUE;
        int level = 1;
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            int sum = 0;
            for(int i=0; i<levelSize; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
            if(sum>maxSum) {
                maxLevel = level;
                maxSum = sum;
            }
            level++;
        }
        return maxLevel;
    }

}

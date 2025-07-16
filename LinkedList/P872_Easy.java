
import java.util.ArrayList;
import java.util.List;

/*
    872. Leaf-similar trees
    Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
    For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
    Two binary trees are considered leaf-similar if their leaf value sequence is the same.
    Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

    Example 1:
        Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
        Output: true

    Example 2:
        Input: root1 = [1,2,3], root2 = [1,3,2]
        Output: false

    Constraints:
        The number of nodes in each tree will be in the range [1, 200].
        Both of the given trees will have values in the range [0, 200].
 */
public class P872_Easy {
    /*
     * Solution: recursive
     * time complexity: O(n+m)
     * space complexity: O(n+m)
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> r1 = new ArrayList<>();
        List<Integer> r2 = new ArrayList<>();
        dfs(root1, r1);
        dfs(root2, r2);
        return r1.equals(r2);
    }

    void dfs(TreeNode root, List<Integer> list) {
        if(root == null) return;
        if(root.left == null && root.right == null) list.add(root.val);
        dfs(root.left, list);
        dfs(root.right, list);
    }
}

/*
 * JavaScript
 */
var leafSimilar = function(root1, root2) {
    const recursive = function(root, list) {
        if(!root) return;
    if(!root.left && !root.right) list.push(root.val);
    recursive(root.left, list);
    recursive(root.right, list);
    };
    const r1 = [];
    const r2 = [];
    recursive(root1, r1);
    recursive(root2, r2);
    return JSON.stringify(r1) === JSON.stringify(r2);
};

/*
 * Python
 */
class Solution:
    def leafSimilar(self, root1: Optional[TreeNode], root2: Optional[TreeNode]) -> bool:
        def dfs(root):
            if root:
                if not root.left and not root.right:
                    yield root.val
                yield from dfs(root.left)
                yield from dfs(root.right)
        return list(dfs(root1)) == list(dfs(root2))

class Solution:
    def leafSimilar(self, root1: Optional[TreeNode], root2: Optional[TreeNode]) -> bool:
        def dfs(root, result):
            if not root:
                return
            if not root.left and not root.right:
                result.append(root.val)
            dfs(root.left, result)
            dfs(root.right, result)
        r1 = []
        r2 = []
        dfs(root.left, r1)
        dfs(root.right, r2)
        return r1 == r2
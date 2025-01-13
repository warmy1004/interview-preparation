/*
    427. Construct Quad tree
    Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.
    Return the root of the Quad-Tree representing grid.
    A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
        val: True if the node represents a grid of 1's or False if the node represents a grid of 0's. Notice that you can assign the val to True or False when isLeaf is False, and both are accepted in the answer.
        isLeaf: True if the node is a leaf node on the tree or False if the node has four children.
        class Node {
            public boolean val;
            public boolean isLeaf;
            public Node topLeft;
            public Node topRight;
            public Node bottomLeft;
            public Node bottomRight;
        }
    We can construct a Quad-Tree from a two-dimensional area using the following steps:
        If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
        If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
        Recurse for each of the children with the proper sub-grid.
    If you want to know more about the Quad-Tree, you can refer to the wiki.

    Quad-Tree format:
        You don't need to read this section for solving the problem. This is only if you want to understand the output format here. The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.
        It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].
        If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.

    Example 1:
        Input: grid = [[0,1],[1,0]]
        Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
        Explanation: The explanation of this example is shown below:
        Notice that 0 represents False and 1 represents True in the photo representing the Quad-Tree.

    Example 2:
        Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
        Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
        Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
        The topLeft, bottomLeft and bottomRight each has the same value.
        The topRight have different values so we divide it into 4 sub-grids where each has the same value.
        Explanation is shown in the photo below:

    Constraints:
        n == grid.length == grid[i].length
        n == 2x where 0 <= x <= 6
 */
package DivideConquer;

/*
 * Overview:
 *  We have a square matrix of size N*N of 0's and 1's. We need to convert this matrix to a quad tree with nodes having two attributes, val and isLeaf:
 *      1. if the whole matrix has the same value (0 or 1), then isLeaf would be true and val would be the same as the matrix value, and we can return.
 *      2. Otherwise, it's not a leaf node, so isLeaf would be false, and val will not matter. This node will have four child nodes.
 *      3. divide the current matrix into four equally sized square matrices and recurse the same process to each.
 *  Note that N would always be in the form of 2^x (x>=0) and hence at any point in the above process, it would be possible to divide the the matrix into four equal parts.
 *  We can see in the problem explanation that the matrix is repeatedly divided into 4 subparts again and again.
 *  It suggests we use a recursive approach to divide the current matrix into four equal parts and then again repeat the same division on smaller parts recursively. 
 */

public class P427_ToDo_Medium {
    /*
     * Solution: recursion
     * time complexity: O(N^2*logn)
     * space complexity: O(logn)
     */
    public Node construct(int[][] grid) {
        return solve(grid, 0, 0, grid.length);
    }
    Node solve(int[][] grid, int x, int y, int len) {
        if(isAllSameValue(grid, x, y, len)) {
            return new Node(grid[x][y]==1, true);
        } else {
            Node root = new Node(false, false);
            int halfLen = len/2;
            root.topLeft = solve(grid, x, y, halfLen);
            root.topRight = solve(grid, x, y+halfLen, halfLen);
            root.bottomLeft = solve(grid, x+halfLen, y, halfLen);
            root.bottomRight = solve(grid, x+halfLen, y+halfLen, halfLen);
            return root;
        }
    }

    boolean isAllSameValue(int[][] grid, int x, int y, int len) {
        for(int i=x; i<x+len; i++) {
            for(int j=y; j<y+len; j++) {
                if(grid[i][j] != grid[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Solution: optimized recursion
     * time complexity: O(N^2*logn)
     * space complexity: O(logn)
     */
    public Node construct(int[][] grid) {
        return solve(grid, 0, 0, grid.length);
    }

    Node solve(int[][] grid, int x, int y, int len) {
        if(len == 1) {
            return new Node(grid[x][y]==1, true);
        }
        Node topLeft = solve(grid, x, y, len/2);
        Node topRight = solve(grid, x, y+len/2, len/2);
        Node bottomLeft = solve(grid, x+len/2, y, len/2);
        Node bottomRight = solve(grid, x+len/2, y+len/2, len/2);
        if(topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf 
            && topLeft.val == topRight.val && topLeft.val == bottomLeft.val && topRight.val==bottomRight.val) {
                return new Node(topLeft.val, true);
            }
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }
}

// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
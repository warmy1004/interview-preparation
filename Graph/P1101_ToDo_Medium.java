/*
    1101. The earliest moment when everyone become friends
    There are n people in a social group labeled from 0 to n - 1. You are given an array logs where logs[i] = [timestampi, xi, yi] indicates that xi and yi will be friends at the time timestampi.
    Friendship is symmetric. That means if a is friends with b, then b is friends with a. Also, person a is acquainted with a person b if a is friends with b, or a is a friend of someone acquainted with b.
    Return the **earliest** time for which every person became acquainted with every other person. If there is no such earliest time, return -1.

    Example 1:
        Input: logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], n = 6
        Output: 20190301
        Explanation: 
        The first event occurs at timestamp = 20190101, and after 0 and 1 become friends, we have the following friendship groups [0,1], [2], [3], [4], [5].
        The second event occurs at timestamp = 20190104, and after 3 and 4 become friends, we have the following friendship groups [0,1], [2], [3,4], [5].
        The third event occurs at timestamp = 20190107, and after 2 and 3 become friends, we have the following friendship groups [0,1], [2,3,4], [5].
        The fourth event occurs at timestamp = 20190211, and after 1 and 5 become friends, we have the following friendship groups [0,1,5], [2,3,4].
        The fifth event occurs at timestamp = 20190224, and as 2 and 4 are already friends, nothing happens.
        The sixth event occurs at timestamp = 20190301, and after 0 and 3 become friends, we all become friends.

    Example 2:
        Input: logs = [[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]], n = 4
        Output: 3
        Explanation: At timestamp = 3, all the persons (i.e., 0, 1, 2, and 3) become friends.
    
    Constraints:
        2 <= n <= 100
        1 <= logs.length <= 10^4
        logs[i].length == 3
        0 <= timestampi <= 10^9
        0 <= xi, yi <= n - 1
        xi != yi
        All the values timestampi are unique.
        All the pairs (xi, yi) occur at most one time in the input.
 */
package Graph;

public class P1101_ToDo_Medium {
    /*
     * Solution: Union Find
     * 
     * time complexity: O(N + MlogM+ Mα(N)), N be the number of poople and M be the number of logs
     *      sorting takes O(MlogM), initializing the arrays in UnionFind takes O(N)
     *      then, iterate through the sorted logs. At each iteration, we invoke the union(a,b). the amortized time complexity is O(Mα(N))
     * space complexity: O(N+logM)
     *      in Java, the Arrays.sort() is implemented as a variant of quicksort algorithm whose space complexity is O(logM)
     */
    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, new Comparator<int[]>() {
            @Override
            public int compare(int[] log1, int[] log2) {
                Integer n1 = new Integer(log1[0]);
                Integer n2 = new Integer(log2[0]);
                return n1.compareTo(n2);
            }
        });

        int count =0;
        UnionFind uf = new UnionFind(n);
        for(int[] edge: edges) {
            int x = edge[1];
            int y = edge[2];
            count += uf.union(x,y);
            if(count==n-1) {
                return edge[0];
            }
        }
        return -1;
    }
}

class UnionFind {
    int[] root;
    int[] rank;

    public UnionFind(int n) {
        root = new int[n];
        for(int i=0; i<n; i++) {
            root[i] = i;
        }
        rank = new int[n];
    }

    int find(int x) {
        if(root[x]!=x) {
            root[x] = find(root[x]);
        }
        return root[x];
    }

    int union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if(rx== ry) {
            return 0;
        } else {
            if(rank[rx]<rank[ry]) {
                root[rx] = ry;
            } else if(rank[rx]>rank[ry]){
                root[ry]= rx;
            } else {
                root[ry] = rx;
                rank[rx]++;
            }
        }
        return 1;
    }
}



/*
 * Python Solution 1
 */
class Solution:
    def earliestAcq(self, logs: List[List[int]], n: int) -> int:
        logs.sort(key=lambda x: x[0])
        uf = UnionFind(n)

        count = 0
        for answer, x, y in logs:
            if uf.union(x,y):
                count+=1
            if count == n-1:
                return answer
        return -1


class UnionFind:
    def __init__(self, size):
        self.root = [i for i in range(size)]
        self.rank = [0]*size

    def find(self, x):
        if self.root[x]!=x:
            self.root[x] = self.find(self.root[x])
        return self.root[x]
    
    def union(self, x, y):
        rx = self.find(x)
        ry = self.find(y)

        if rx == ry:
            return False
        else:
            if self.rank[rx]<self.rank[ry]:
                self.root[rx] = ry
            elif self.rank[rx]>self.rank[ry]:
                self.root[ry]=rx
            else:
                self.root[ry]=rx
                self.rank[rx]+=1
        return True

/*
 * Python Solution 2
 */
class Solution:
    def earliestAcq(self, logs: List[List[int]], n: int) -> int:
        logs.sort(key=lambda x: x[0])

        def dfs(x: int):
            self.visited.add(x)
            for i in adj[x]:
                if i not in self.visited:
                    dfs(i)


        adj = collections.defaultdict(list)
       
        for a,b,c in logs:
            self.visited = set()
            adj[b].append(c)
            adj[c].append(b)
            dfs(b)
            if len(self.visited)==n:
                return a
        return -1
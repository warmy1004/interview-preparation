# BFS (Breadth-First Search) 
* vertex-baed technique for finding the shortest path in a graph and in a tree
    a traversaol approach in which we first walk through all nodes on the same level before moving on to the next level
* using a Queue data structure that follows FIFO
* more suitable for searching vertices closer to the given sourcee
* applications of BFS:
    * Shortest path and Minimum Spanning Tree for unweighted graph
        - In an unweighted graph, the shortest path is the path with the least number of edges. With Breadth First, we always reach a vertex from a given source using the minimum number of edges. 
        - Also, in the case of unweighted graphs, any spanning tree is Minimum Spanning Tree and we can use either Depth or Breadth first traversal for finding a spanning tree.
    * Minimum Spanning Tree for weighted graphs
        - The condition is that the weight should be non-negative and the same for each pair of vertices.
    * Peer-to-Peer Networks
        - In P2P networks like BitTorrent, BFS is used to find all neighbor nodes.
    * Crawlers in Search Engines
        - Crawlers build an index using Breadth First. The idea is to start from the source page and follow all links from the source and keep doing the same. 
        - DFS can also be used for Crawlers, but the adventage of BFS is, the depth or levels of the built tree can be limited.
    * Social Networking Websites
        - we can find people with a given distance 'k' from a person using BFS til 'k' levels.
    * GPS Navigation systems
        - used to find all neighboring locations
    * Broadcasting in network
    * In Garbage Collection
    * Cycle detection in undirected graph 
        - DFS can be also used
    * Ford-Fulkerson algorithm
        - we can use BFS or DFS to find the maximum flow. BFS is preferred as it reduces the worst-case time complextity to O(VE^2)
    * To test if a graph is Bipartite
        - DFS can be also used
    * Path finding
        - DFS can be also used
    * Finding all nodes within one connected component
        - DFS can be also used
    * AI
        - used in travering a game tree to find the best move
    * Network Security
    * Connected component in an undirected graph
    * Topological sorting in a directed acyclic graph (DAG)
    * Image processing
    * Recommender sysgtems
    * Others: Many algorithms like Prim's Minimum Spanning Tree and Dijkstra's Single Source Shortest Path use structure similar to BFS

## Graph algorithm
    1. Initialization: Enqueue the starting node into a queue and mark it as visited
    2. Exploration: While the queue is not empty:
        * Dequeue a node from the queue and visit it (e.g., print its value)
        * For each unvisited neighbor of the dequeued node:
            * Enqueue the neighbor into the queue
            * Mark the neighbor as visited
    3. Termination: Repeat step 2 until the queue is empty
 
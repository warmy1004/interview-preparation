/*
    146. LRU cache
    Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
    Implement the LRUCache class:
        LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
        int get(int key) Return the value of the key if the key exists, otherwise return -1.
        void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
    The functions get and put must each run in O(1) average time complexity.

    Example 1:
    Input
        ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
        [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
    Output
        [null, null, null, 1, null, -1, null, -1, 3, 4]
    Explanation
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    
    Constraints:
        1 <= capacity <= 3000
        0 <= key <= 10^4
        0 <= value <= 10^5
        At most 2 * 10^5 calls will be made to get and put.

 */
package Design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class ListNode {
    int key;
    int value;
    ListNode next;
    ListNode prev;
    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class P146_ToDo_Medium {
    /*
     * Solution: Doubly Linked List with hash map
     */
    private int capacity;
    Map<Integer, ListNode> map;
    ListNode head;
    ListNode tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;

        ListNode node = map.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            ListNode old = map.get(key);
            remove(old);
        }
        ListNode node = new ListNode(key, value);
        map.put(key, node);
        add(node);
        if(map.size()>capacity) {
            ListNode delete = head.next;
            remove(delete);
            map.remove(delete.key);
        }
    }

    public void add(ListNode node) {
        ListNode prevEnd = tail.prev;
        prevEnd.next = node;
        node.prev = prevEnd;
        node.next = tail;
        tail.prev = node;
    }

    public void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /*
     * Solution: hashmap + deque
     * Note: it takes time so long
     */
    private int capacity;
    private Map<Integer,Integer> map;
    private Deque<Integer> deque;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        deque = new ArrayDeque<>();
    }

    public int get(int key) {
        int value = map.getOrDefault(key, -1);
        if(value!=-1) {
            deque.remove(key);
            deque.addLast(key);
        }
        return value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            deque.remove(key);
            map.remove(key);
        }
        deque.addLast(key);
        map.put(key, value);
        if(deque.size() > this.capacity) {
            map.remove(deque.pollFirst());
        }
    }
}

import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=460 lang=java
 *
 * [460] LFU 缓存
 *
 * https://leetcode.cn/problems/lfu-cache/description/
 *
 * algorithms
 * Hard (44.38%)
 * Likes:    609
 * Dislikes: 0
 * Total Accepted:    57.1K
 * Total Submissions: 128.6K
 * Testcase Example:  '["LFUCache","put","put","get","put","get","get","put","get","get","get"]\n' +
  '[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]'
 *
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * 
 * 实现 LFUCache 类：
 * 
 * 
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
 * void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量
 * capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用
 * 的键。
 * 
 * 
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 * 
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 * 
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * 
 * 
 * 
 * 示例：
 * 
 * 
 * 输入：
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get",
 * "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * 输出：
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 * 
 * 解释：
 * // cnt(x) = 键 x 的使用计数
 * // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // 返回 1
 * ⁠                // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
 * ⁠                // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // 返回 -1（未找到）
 * lfu.get(3);      // 返回 3
 * ⁠                // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
 * ⁠                // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // 返回 -1（未找到）
 * lfu.get(3);      // 返回 3
 * ⁠                // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // 返回 4
 * ⁠                // cache=[3,4], cnt(4)=2, cnt(3)=3
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= capacity <= 10^4
 * 0 <= key <= 10^5
 * 0 <= value <= 10^9
 * 最多调用 2 * 10^5 次 get 和 put 方法
 * 
 * 
 */

// @lc code=start
class LFUCache {
    private final int capacity;
    private int size;
    private final Map<Integer, LinkedNode> cache;
    private final DeLinkedList first, last;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>(capacity);
        this.first = new DeLinkedList();
        this.last = new DeLinkedList();
        this.first.next = this.last;
        this.last.pre = this.first;
    }

    public int get(int key) {
        LinkedNode ln = this.cache.get(key);
        if (ln == null)
            return -1;

        increaseFreq(ln);
        return ln.value;
    }

    public void put(int key, int value) {
        if (this.capacity == 0)
            return;
        LinkedNode ln = this.cache.get(key);
        if (ln != null) {
            ln.value = value;
            increaseFreq(ln);
        } else {
            DeLinkedList dll = this.last.pre;

            if (this.size == this.capacity) {
                cache.remove(dll.tail.pre.key);
                dll.removeNode(dll.tail.pre);
                size--;

                if (dll.head.next == dll.tail) {
                    reclaimDeLinkedList(dll);
                    dll = this.last.pre;
                }
            }

            ln = new LinkedNode(key, value);
            cache.put(key, ln);
            if (dll.frequency != 1) {
                insertDeLinkedList(new DeLinkedList(1), dll);
                dll = this.last.pre;
            }

            dll.addNode(ln);
            size++;
        }
    }

    private void increaseFreq(LinkedNode ln) {
        DeLinkedList dll = ln.dll, preDll = dll.pre;
        int frequency = dll.frequency + 1;
        dll.removeNode(ln);
        if (dll.head.next == dll.tail)
            reclaimDeLinkedList(dll);

        if (preDll.frequency != frequency) {
            dll = new DeLinkedList(frequency);
            insertDeLinkedList(dll, preDll);
        } else
            dll = preDll;

        dll.addNode(ln);
    }

    private void reclaimDeLinkedList(DeLinkedList dll) {
        dll.pre.next = dll.next;
        dll.next.pre = dll.pre;
    }

    private void insertDeLinkedList(DeLinkedList dll, DeLinkedList preDll) {
        dll.next = preDll.next;
        preDll.next.pre = dll;
        preDll.next = dll;
        dll.pre = preDll;
    }
}

class LinkedNode {
    int key, value;
    LinkedNode pre, next;
    DeLinkedList dll;

    public LinkedNode() {
    };

    public LinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class DeLinkedList {
    int frequency;
    LinkedNode head, tail;
    DeLinkedList pre, next;

    public DeLinkedList() {
        this(0);
    }

    public DeLinkedList(int frequency) {
        this.frequency = frequency;
        this.head = new LinkedNode();
        this.tail = new LinkedNode();
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    public void removeNode(LinkedNode ln) {
        ln.pre.next = ln.next;
        ln.next.pre = ln.pre;
    }

    public void addNode(LinkedNode ln) {
        ln.next = this.head.next;
        this.head.next.pre = ln;
        ln.pre = this.head;
        this.head.next = ln;
        ln.dll = this;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

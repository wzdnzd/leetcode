/*
 * @lc app=leetcode.cn id=1206 lang=java
 *
 * [1206] 设计跳表
 *
 * https://leetcode.cn/problems/design-skiplist/description/
 *
 * algorithms
 * Hard (67.45%)
 * Likes:    301
 * Dislikes: 0
 * Total Accepted:    36.3K
 * Total Submissions: 53.6K
 * Testcase Example:  '["Skiplist","add","add","add","search","add","search","erase","erase","search"]\n' +
  '[[],[1],[2],[3],[0],[4],[1],[0],[1],[1]]'
 *
 * 不使用任何库函数，设计一个 跳表 。
 * 
 * 跳表 是在 O(log(n))
 * 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
 * 
 * 例如，一个跳表包含 [30, 40, 50, 60, 70, 90] ，然后增加 80、45 到跳表中，以下图的方式操作：
 * 
 * 
 * 
 * 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是
 * O(log(n))，空间复杂度是 O(n)。
 * 
 * 了解更多 : https://oi-wiki.org/ds/skiplist/
 * 
 * 在本题中，你的设计应该要包含这些函数：
 * 
 * 
 * bool search(int target) : 返回target是否存在于跳表中。
 * void add(int num): 插入一个元素到跳表。
 * bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num
 * ，删除其中任意一个即可。
 * 
 * 
 * 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入
 * ["Skiplist", "add", "add", "add", "search", "add", "search", "erase",
 * "erase", "search"]
 * [[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
 * 输出
 * [null, null, null, null, false, null, true, false, true, false]
 * 
 * 解释
 * Skiplist skiplist = new Skiplist();
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0);   // 返回 false
 * skiplist.add(4);
 * skiplist.search(1);   // 返回 true
 * skiplist.erase(0);    // 返回 false，0 不在跳表中
 * skiplist.erase(1);    // 返回 true
 * skiplist.search(1);   // 返回 false，1 已被擦除
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 0 <= num, target <= 2 * 10^4
 * 调用search, add,  erase操作次数不大于 5 * 10^4 
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.Random;

class Skiplist {
    static final int MAX_LEVEL = 32;
    static final double P_FACTOR = 0.25;

    private SkiplistNode head;
    private int level;
    private Random random;

    public Skiplist() {
        this.head = new SkiplistNode(-1, MAX_LEVEL);
        this.level = 0;
        this.random = new Random();
    }

    public boolean search(int target) {
        SkiplistNode current = this.head;
        for (int i = level - 1; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].val < target)
                current = current.forward[i];
        }

        current = current.forward[0];
        if (current != null && current.val == target)
            return true;

        return false;
    }

    public void add(int num) {
        SkiplistNode[] updateNodes = new SkiplistNode[MAX_LEVEL];
        Arrays.fill(updateNodes, head);
        SkiplistNode current = this.head;

        for (int i = level - 1; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].val < num)
                current = current.forward[i];

            updateNodes[i] = current;
        }

        int k = randomLevel();
        level = Math.max(level, k);
        SkiplistNode newNode = new SkiplistNode(num, k);

        for (int i = 0; i < k; i++) {
            newNode.forward[i] = updateNodes[i].forward[i];
            updateNodes[i].forward[i] = newNode;
        }
    }

    public boolean erase(int num) {
        SkiplistNode[] updateNodes = new SkiplistNode[MAX_LEVEL];
        SkiplistNode current = this.head;

        for (int i = level - 1; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].val < num)
                current = current.forward[i];

            updateNodes[i] = current;
        }

        current = current.forward[0];
        if (current == null || current.val != num)
            return false;

        for (int i = 0; i < level; i++) {
            if (updateNodes[i].forward[i] != current)
                break;

            updateNodes[i].forward[i] = current.forward[i];
        }

        while (level > 1 && head.forward[level - 1] == null)
            level--;

        return true;
    }

    private int randomLevel() {
        int level = 1;
        while (random.nextDouble() < P_FACTOR && level < MAX_LEVEL)
            level++;

        return level;
    }
}

class SkiplistNode {
    int val;
    SkiplistNode[] forward;

    public SkiplistNode(int val, int maxLevel) {
        this.val = val;
        this.forward = new SkiplistNode[maxLevel];
    }
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
// @lc code=end

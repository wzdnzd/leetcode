/*
 * @lc app=leetcode.cn id=2336 lang=java
 *
 * [2336] 无限集中的最小数字
 *
 * https://leetcode.cn/problems/smallest-number-in-infinite-set/description/
 *
 * algorithms
 * Medium (69.40%)
 * Likes:    45
 * Dislikes: 0
 * Total Accepted:    15.9K
 * Total Submissions: 22.7K
 * Testcase Example:  '["SmallestInfiniteSet","addBack","popSmallest","popSmallest","popSmallest","addBack","popSmallest","popSmallest","popSmallest"]\n' +
  '[[],[2],[],[],[],[1],[],[],[]]'
 *
 * 现有一个包含所有正整数的集合 [1, 2, 3, 4, 5, ...] 。
 * 
 * 实现 SmallestInfiniteSet 类：
 * 
 * 
 * SmallestInfiniteSet() 初始化 SmallestInfiniteSet 对象以包含 所有 正整数。
 * int popSmallest() 移除 并返回该无限集中的最小整数。
 * void addBack(int num) 如果正整数 num 不 存在于无限集中，则将一个 num 添加 到该无限集中。
 * 
 * 
 * 
 * 
 * 示例：
 * 
 * 输入
 * ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest",
 * "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
 * [[], [2], [], [], [], [1], [], [], []]
 * 输出
 * [null, null, 1, 2, 3, null, 1, 4, 5]
 * 
 * 解释
 * SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
 * smallestInfiniteSet.addBack(2);    // 2 已经在集合中，所以不做任何变更。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 2 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 3 ，并将其从集合中移除。
 * smallestInfiniteSet.addBack(1);    // 将 1 添加到该集合中。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 在上一步中被添加到集合中，
 * ⁠                                  // 且 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 4 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 5 ，并将其从集合中移除。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= num <= 1000
 * 最多调用 popSmallest 和 addBack 方法 共计 1000 次
 * 
 * 
 */

// @lc code=start

import java.util.TreeSet;

class SmallestInfiniteSet {
    // 可用优先级队列 PriorityQueue 代替
    private TreeSet<Integer> set;

    private int smallest;

    public SmallestInfiniteSet() {
        this.set = new TreeSet<>();
        this.smallest = 1;
    }

    public int popSmallest() {
        if (!set.isEmpty())
            return set.pollFirst();

        return smallest++;
    }

    public void addBack(int num) {
        if (num < smallest)
            set.add(num);
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
// @lc code=end

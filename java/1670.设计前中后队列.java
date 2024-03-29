/*
 * @lc app=leetcode.cn id=1670 lang=java
 *
 * [1670] 设计前中后队列
 *
 * https://leetcode.cn/problems/design-front-middle-back-queue/description/
 *
 * algorithms
 * Medium (52.05%)
 * Likes:    42
 * Dislikes: 0
 * Total Accepted:    9.7K
 * Total Submissions: 17.8K
 * Testcase Example:  '["FrontMiddleBackQueue","pushFront","pushBack","pushMiddle","pushMiddle","popFront","popMiddle","popMiddle","popBack","popFront"]\n' +
  '[[],[1],[2],[3],[4],[],[],[],[],[]]'
 *
 * 请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
 * 
 * 请你完成 FrontMiddleBack 类：
 * 
 * 
 * FrontMiddleBack() 初始化队列。
 * void pushFront(int val) 将 val 添加到队列的 最前面 。
 * void pushMiddle(int val) 将 val 添加到队列的 正中间 。
 * void pushBack(int val) 将 val 添加到队里的 最后面 。
 * int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * 
 * 
 * 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说：
 * 
 * 
 * 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
 * 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：
 * ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle",
 * "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
 * [[], [1], [2], [3], [4], [], [], [], [], []]
 * 输出：
 * [null, null, null, null, null, 1, 3, 4, 2, -1]
 * 
 * 解释：
 * FrontMiddleBackQueue q = new FrontMiddleBackQueue();
 * q.pushFront(1);   // [1]
 * q.pushBack(2);    // [1, 2]
 * q.pushMiddle(3);  // [1, 3, 2]
 * q.pushMiddle(4);  // [1, 4, 3, 2]
 * q.popFront();     // 返回 1 -> [4, 3, 2]
 * q.popMiddle();    // 返回 3 -> [4, 2]
 * q.popMiddle();    // 返回 4 -> [2]
 * q.popBack();      // 返回 2 -> []
 * q.popFront();     // 返回 -1 -> [] （队列为空）
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 最多调用 1000 次 pushFront， pushMiddle， pushBack， popFront， popMiddle 和 popBack
 * 。
 * 
 * 
 */

// @lc code=start

import java.util.Deque;
import java.util.LinkedList;

class FrontMiddleBackQueue {
    private Deque<Integer> front;
    private Deque<Integer> back;

    public FrontMiddleBackQueue() {
        this.front = new LinkedList<>();
        this.back = new LinkedList<>();
    }

    public void pushFront(int val) {
        this.front.addFirst(val);

        if (this.front.size() > this.back.size() + 1)
            this.back.addFirst(this.front.pollLast());
    }

    public void pushMiddle(int val) {
        if (this.front.size() > this.back.size())
            this.back.addFirst(this.front.pollLast());

        this.front.addLast(val);
    }

    public void pushBack(int val) {
        this.back.addLast(val);

        if (this.back.size() > this.front.size())
            this.front.addLast(this.back.pollFirst());
    }

    public int popFront() {
        if (this.front.isEmpty())
            return -1;

        int val = this.front.pollFirst();

        if (this.front.size() < this.back.size())
            this.front.addLast(this.back.pollFirst());

        return val;
    }

    public int popMiddle() {
        if (this.front.isEmpty())
            return -1;

        int val = this.front.pollLast();

        if (this.front.size() < this.back.size())
            this.front.addLast(this.back.pollFirst());

        return val;
    }

    public int popBack() {
        if (this.front.isEmpty())
            return -1;

        int val = 0;
        if (this.back.isEmpty())
            val = this.front.pollLast();
        else {
            val = this.back.pollLast();
            if (this.front.size() > this.back.size() + 1)
                this.back.addFirst(this.front.pollLast());
        }

        return val;
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */
// @lc code=end

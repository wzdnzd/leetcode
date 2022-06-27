import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=155 lang=java
 *
 * [155] 最小栈
 *
 * https://leetcode.cn/problems/min-stack/description/
 *
 * algorithms
 * Easy (58.26%)
 * Likes:    1334
 * Dislikes: 0
 * Total Accepted:    392.9K
 * Total Submissions: 674.4K
 * Testcase Example:  '["MinStack","push","push","push","getMin","pop","top","getMin"]\n' +
  '[[],[-2],[0],[-3],[],[],[],[]]'
 *
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 
 * 实现 MinStack 类:
 * 
 * 
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 * 
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * -2^31 <= val <= 2^31 - 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push, pop, top, and getMin最多被调用 3 * 10^4 次
 * 
 * 
 */

// @lc code=start
class MinStack {
    private final List<Integer> datas;
    private final List<Integer> minStack;

    public MinStack() {
        datas = new ArrayList<>();
        minStack = new ArrayList<>();
    }

    public void push(int val) {
        datas.add(val);
        if (minStack.isEmpty() || val <= minStack.get(minStack.size() - 1)) {
            minStack.add(val);
        }
    }

    public void pop() {
        if (datas.isEmpty()) {
            throw new RuntimeException("cannot pop because stack is empty");
        }

        int num = datas.remove(datas.size() - 1);
        int index = minStack.size() - 1;
        if (num == minStack.get(index)) {
            minStack.remove(index);
        }
    }

    public int top() {
        if (datas.size() == 0) {
            throw new RuntimeException("stack is empty");
        }

        return datas.get(datas.size() - 1);
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("stack is empty");
        }

        return minStack.get(minStack.size() - 1);
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
// @lc code=end

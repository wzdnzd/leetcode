import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=295 lang=java
 *
 * [295] 数据流的中位数
 *
 * https://leetcode.cn/problems/find-median-from-data-stream/description/
 *
 * algorithms
 * Hard (52.79%)
 * Likes:    706
 * Dislikes: 0
 * Total Accepted:    86.2K
 * Total Submissions: 163.2K
 * Testcase Example:  '["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]\n' +
  '[[],[1],[2],[],[3],[]]'
 *
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 
 * 例如，
 * 
 * [2,3,4] 的中位数是 3
 * 
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 
 * 设计一个支持以下两种操作的数据结构：
 * 
 * 
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 
 * 
 * 示例：
 * 
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3) 
 * findMedian() -> 2
 * 
 * 进阶:
 * 
 * 
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 
 * 
 */

// @lc code=start
class MedianFinder {
    private final PriorityQueue<Integer> l;
    private final PriorityQueue<Integer> r;

    public MedianFinder() {
        l = new PriorityQueue<>((x, y) -> y - x);
        r = new PriorityQueue<>((x, y) -> x - y);
    }

    public void addNum(int num) {
        int m = l.size();
        int n = r.size();

        if (m == n) {
            if (r.isEmpty() || num <= r.peek())
                l.add(num);

            else {
                l.add(r.poll());
                r.add(num);
            }
        } else {
            if (l.peek() <= num)
                r.add(num);
            else {
                r.add(l.poll());
                l.add(num);
            }
        }
    }

    public double findMedian() {
        if (l.size() == r.size())
            return (l.peek() + r.peek()) / 2.0;

        return l.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
// @lc code=end

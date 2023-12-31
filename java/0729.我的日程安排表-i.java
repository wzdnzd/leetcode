/*
 * @lc app=leetcode.cn id=729 lang=java
 *
 * [729] 我的日程安排表 I
 *
 * https://leetcode.cn/problems/my-calendar-i/description/
 *
 * algorithms
 * Medium (58.32%)
 * Likes:    275
 * Dislikes: 0
 * Total Accepted:    54.4K
 * Total Submissions: 93.3K
 * Testcase Example:  '["MyCalendar","book","book","book"]\n[[],[10,20],[15,25],[20,30]]'
 *
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 * 
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 * 
 * 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x <
 * end 。
 * 
 * 实现 MyCalendar 类：
 * 
 * 
 * MyCalendar() 初始化日历对象。
 * boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。否则，返回
 * false 并且不要将该日程安排添加到日历中。
 * 
 * 
 * 
 * 
 * 示例：
 * 
 * 
 * 输入：
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * 输出：
 * [null, true, false, true]
 * 
 * 解释：
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False ，这个日程安排不能添加到日历中，因为时间 15
 * 已经被另一个日程安排预订了。
 * myCalendar.book(20, 30); // return True ，这个日程安排可以添加到日历中，因为第一个日程安排预订的每个时间都小于
 * 20 ，且不包含时间 20 。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= start < end <= 10^9
 * 每个测试用例，调用 book 方法的次数最多不超过 1000 次。
 * 
 * 
 */

// @lc code=start

import java.util.TreeSet;

class MyCalendar {
    private TreeSet<int[]> booked;

    public MyCalendar() {
        this.booked = new TreeSet<>((a, b) -> a[0] - b[0]);
    }

    public boolean book(int start, int end) {
        if (booked.isEmpty()) {
            booked.add(new int[] { start, end });
            return true;
        }

        int[] pair = { end, 0 };
        int[] plan = booked.ceiling(pair);

        if (plan == booked.first() || booked.lower(pair)[1] <= start) {
            booked.add(new int[] { start, end });
            return true;
        }

        return false;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
// @lc code=end

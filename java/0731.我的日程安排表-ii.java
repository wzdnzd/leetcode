/*
 * @lc app=leetcode.cn id=731 lang=java
 *
 * [731] 我的日程安排表 II
 *
 * https://leetcode.cn/problems/my-calendar-ii/description/
 *
 * algorithms
 * Medium (62.83%)
 * Likes:    248
 * Dislikes: 0
 * Total Accepted:    32.4K
 * Total Submissions: 51.3K
 * Testcase Example:  '["MyCalendarTwo","book","book","book","book","book","book"]\n' +
  '[[],[10,20],[50,60],[10,40],[5,15],[5,10],[25,55]]'
 *
 * 实现一个程序来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * 
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生 三重预订。
 * 
 * 事件能够用一对整数 startTime 和 endTime 表示，在一个半开区间的时间 [startTime, endTime) 上预定。实数 x
 * 的范围为  startTime <= x < endTime。
 * 
 * 实现 MyCalendarTwo 类：
 * 
 * 
 * MyCalendarTwo() 初始化日历对象。
 * boolean book(int startTime, int endTime) 如果可以将日程安排成功添加到日历中而不会导致三重预订，返回
 * true。否则，返回 false 并且不要将该日程安排添加到日历中。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：
 * ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * 输出：
 * [null, true, true, true, false, true, true]
 * 
 * 解释：
 * MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
 * myCalendarTwo.book(10, 20); // 返回 True，能够预定该日程。
 * myCalendarTwo.book(50, 60); // 返回 True，能够预定该日程。
 * myCalendarTwo.book(10, 40); // 返回 True，该日程能够被重复预定。
 * myCalendarTwo.book(5, 15);  // 返回 False，该日程导致了三重预定，所以不能预定。
 * myCalendarTwo.book(5, 10); // 返回 True，能够预定该日程，因为它不使用已经双重预订的时间 10。
 * myCalendarTwo.book(25, 55); // 返回 True，能够预定该日程，因为时间段 [25, 40)
 * 将被第三个日程重复预定，时间段 [40, 50) 将被单独预定，而时间段 [50, 55) 将被第二个日程重复预定。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= start < end <= 10^9
 * 最多调用 book 1000 次。
 * 
 * 
 */

// @lc code=start

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class MyCalendarTwo {
    private TreeMap<Integer, Integer> diffs;

    public MyCalendarTwo() {
        diffs = new TreeMap<Integer, Integer>();
    }

    public boolean book(int start, int end) {
        int books = 0;

        diffs.put(start, diffs.getOrDefault(start, 0) + 1);
        diffs.put(end, diffs.getOrDefault(end, 0) - 1);
        Set<Map.Entry<Integer, Integer>> entries = diffs.entrySet();

        for (Map.Entry<Integer, Integer> entry : entries) {
            books += entry.getValue();

            if (books > 2) {
                diffs.put(start, diffs.getOrDefault(start, 0) - 1);
                diffs.put(end, diffs.getOrDefault(end, 0) + 1);

                return false;
            }
        }

        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(startTime,endTime);
 */
// @lc code=end

/*
 * @lc app=leetcode.cn id=1353 lang=java
 *
 * [1353] 最多可以参加的会议数目
 *
 * https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended/description/
 *
 * algorithms
 * Medium (30.86%)
 * Likes:    305
 * Dislikes: 0
 * Total Accepted:    24.5K
 * Total Submissions: 78.1K
 * Testcase Example:  '[[1,2],[2,3],[3,4]]'
 *
 * 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于
 * endDayi 。
 * 
 * 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。在任意一天 d 中只能参加一场会议。
 * 
 * 请你返回你可以参加的 最大 会议数目。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：events = [[1,2],[2,3],[3,4]]
 * 输出：3
 * 解释：你可以参加所有的三个会议。
 * 安排会议的一种方案如上图。
 * 第 1 天参加第一个会议。
 * 第 2 天参加第二个会议。
 * 第 3 天参加第三个会议。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：events= [[1,2],[2,3],[3,4],[1,2]]
 * 输出：4
 * 
 * 
 * 
 * 
 * 提示：​​​​​​
 * 
 * 
 * 1 <= events.length <= 10^5
 * events[i].length == 2
 * 1 <= startDayi <= endDayi <= 10^5
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int maxEvents(int[][] events) {
        int count = 0, maxDay = 0;

        for (int[] event : events)
            maxDay = Math.max(maxDay, event[1]);

        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int length = events.length;
        for (int index = 0, day = 0; day <= maxDay; day++) {
            while (!pq.isEmpty() && pq.peek() < day)
                pq.poll();


            while (index < length && events[index][0] <= day) {
                pq.offer(events[index][1]);
                index++;
            }

            if (!pq.isEmpty()) {
                pq.poll();
                count++;
            }
        }

        return count;
    }
}
// @lc code=end


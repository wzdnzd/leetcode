/*
 * @lc app=leetcode.cn id=2224 lang=java
 *
 * [2224] 转化时间需要的最少操作数
 *
 * https://leetcode.cn/problems/minimum-number-of-operations-to-convert-time/description/
 *
 * algorithms
 * Easy (69.44%)
 * Likes:    12
 * Dislikes: 0
 * Total Accepted:    13.1K
 * Total Submissions: 18.8K
 * Testcase Example:  '"02:30"\n"04:35"'
 *
 * 给你两个字符串 current 和 correct ，表示两个 24 小时制时间 。
 * 
 * 24 小时制时间 按 "HH:MM" 进行格式化，其中 HH 在 00 和 23 之间，而 MM 在 00 和 59 之间。最早的 24 小时制时间为
 * 00:00 ，最晚的是 23:59 。
 * 
 * 在一步操作中，你可以将 current 这个时间增加 1、5、15 或 60 分钟。你可以执行这一操作 任意 次数。
 * 
 * 返回将 current 转化为 correct 需要的 最少操作数 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：current = "02:30", correct = "04:35"
 * 输出：3
 * 解释：
 * 可以按下述 3 步操作将 current 转换为 correct ：
 * - 为 current 加 60 分钟，current 变为 "03:30" 。
 * - 为 current 加 60 分钟，current 变为 "04:30" 。 
 * - 为 current 加 5 分钟，current 变为 "04:35" 。
 * 可以证明，无法用少于 3 步操作将 current 转化为 correct 。
 * 
 * 示例 2：
 * 
 * 输入：current = "11:00", correct = "11:01"
 * 输出：1
 * 解释：只需要为 current 加一分钟，所以最小操作数是 1 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * current 和 correct 都符合 "HH:MM" 格式
 * current <= correct
 * 
 * 
 */

// @lc code=start
class Solution {
    public int convertTime(String current, String correct) {
        int h1 = Integer.valueOf(current.substring(0, 2));
        int m1 = Integer.valueOf(current.substring(3, 5));

        int h2 = Integer.valueOf(correct.substring(0, 2));
        int m2 = Integer.valueOf(correct.substring(3, 5));

        int total = (h2 - h1) * 60 + (m2 - m1);
        return total / 60 + total % 60 / 15 + total % 60 % 15 / 5 + total % 60 % 15 % 5;
    }
}
// @lc code=end

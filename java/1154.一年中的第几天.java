/*
 * @lc app=leetcode.cn id=1154 lang=java
 *
 * [1154] 一年中的第几天
 *
 * https://leetcode.cn/problems/day-of-the-year/description/
 *
 * algorithms
 * Easy (62.12%)
 * Likes:    117
 * Dislikes: 0
 * Total Accepted:    58.2K
 * Total Submissions: 93.8K
 * Testcase Example:  '"2019-01-09"'
 *
 * 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。返回该日期是当年的第几天。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：date = "2019-01-09"
 * 输出：9
 * 解释：给定日期是2019年的第九天。
 * 
 * 示例 2：
 * 
 * 
 * 输入：date = "2019-02-10"
 * 输出：41
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * date.length == 10
 * date[4] == date[7] == '-'，其他的 date[i] 都是数字
 * date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日
 * 
 * 
 */

// @lc code=start

class Solution {
    public int dayOfYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));

        int[] amount = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        // 闰年
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
            ++amount[1];

        int ans = 0;
        for (int i = 0; i < month - 1; ++i)
            ans += amount[i];

        return ans + day;
    }
}
// @lc code=end

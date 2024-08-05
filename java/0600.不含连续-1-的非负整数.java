/*
 * @lc app=leetcode.cn id=600 lang=java
 *
 * [600] 不含连续1的非负整数
 *
 * https://leetcode.cn/problems/non-negative-integers-without-consecutive-ones/description/
 *
 * algorithms
 * Hard (50.29%)
 * Likes:    360
 * Dislikes: 0
 * Total Accepted:    29K
 * Total Submissions: 57.3K
 * Testcase Example:  '5'
 *
 * 给定一个正整数 n ，请你统计在 [0, n] 范围的非负整数中，有多少个整数的二进制表示中不存在 连续的 1 。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入: n = 5
 * 输出: 5
 * 解释: 
 * 下面列出范围在 [0, 5] 的非负整数与其对应的二进制表示：
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * 其中，只有整数 3 违反规则（有两个连续的 1 ），其他 5 个满足规则。
 * 
 * 示例 2:
 * 
 * 
 * 输入: n = 1
 * 输出: 2
 * 
 * 
 * 示例 3:
 * 
 * 
 * 输入: n = 2
 * 输出: 3
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 1 <= n <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findIntegers(int n) {
        int[] dp = new int[31];
        dp[0] = dp[1] = 1;

        for (int i = 2; i < 31; ++i)
            dp[i] = dp[i - 1] + dp[i - 2];

        int prev = 0, count = 0;
        for (int i = 29; i >= 0; i--) {
            int curr = 1 << i;

            if ((n & curr) != 0) {
                count += dp[i + 1];

                if (prev == 1)
                    break;

                prev = 1;
            } else
                prev = 0;

            if (i == 0)
                ++count;
        }

        return count;
    }
}
// @lc code=end

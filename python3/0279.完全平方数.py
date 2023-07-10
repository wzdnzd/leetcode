#
# @lc app=leetcode.cn id=279 lang=python3
#
# [279] 完全平方数
#
# https://leetcode.cn/problems/perfect-squares/description/
#
# algorithms
# Medium (65.77%)
# Likes:    1742
# Dislikes: 0
# Total Accepted:    413.4K
# Total Submissions: 624.6K
# Testcase Example:  '12'
#
# 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
#
# 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11
# 不是。
#
#
#
# 示例 1：
#
#
# 输入：n = 12
# 输出：3
# 解释：12 = 4 + 4 + 4
#
# 示例 2：
#
#
# 输入：n = 13
# 输出：2
# 解释：13 = 4 + 9
#
#
# 提示：
#
#
# 1 <= n <= 10^4
#
#
#


# @lc code=start
class Solution:
    def numSquares(self, n: int) -> int:
        dp = [float("inf")] * (n + 1)
        dp[0] = 0

        for i in range(1, n + 1):
            for j in range(1, int(i**0.5) + 1):
                dp[i] = min(dp[i], dp[i - j * j] + 1)

        return dp[n]


# @lc code=end

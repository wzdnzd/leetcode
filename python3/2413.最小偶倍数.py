#
# @lc app=leetcode.cn id=2413 lang=python3
#
# [2413] 最小偶倍数
#
# https://leetcode.cn/problems/smallest-even-multiple/description/
#
# algorithms
# Easy (87.28%)
# Likes:    65
# Dislikes: 0
# Total Accepted:    44.7K
# Total Submissions: 50.8K
# Testcase Example:  '5'
#
# 给你一个正整数 n ，返回 2 和 n 的最小公倍数（正整数）。
#
#
# 示例 1：
#
# 输入：n = 5
# 输出：10
# 解释：5 和 2 的最小公倍数是 10 。
#
#
# 示例 2：
#
# 输入：n = 6
# 输出：6
# 解释：6 和 2 的最小公倍数是 6 。注意数字会是它自身的倍数。
#
#
#
#
# 提示：
#
#
# 1 <= n <= 150
#
#
#


# @lc code=start
class Solution:
    def smallestEvenMultiple(self, n: int) -> int:
        return n if n % 2 == 0 else n * 2


# @lc code=end

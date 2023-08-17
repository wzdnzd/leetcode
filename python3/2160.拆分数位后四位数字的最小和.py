#
# @lc app=leetcode.cn id=2160 lang=python3
#
# [2160] 拆分数位后四位数字的最小和
#
# https://leetcode.cn/problems/minimum-sum-of-four-digit-number-after-splitting-digits/description/
#
# algorithms
# Easy (83.89%)
# Likes:    28
# Dislikes: 0
# Total Accepted:    14.8K
# Total Submissions: 17.6K
# Testcase Example:  '2932'
#
# 给你一个四位 正 整数 num 。请你使用 num 中的 数位 ，将 num 拆成两个新的整数 new1 和 new2 。new1 和 new2 中可以有
# 前导 0 ，且 num 中 所有 数位都必须使用。
# 
# 
# 比方说，给你 num = 2932 ，你拥有的数位包括：两个 2 ，一个 9 和一个 3 。一些可能的 [new1, new2] 数对为 [22,
# 93]，[23, 92]，[223, 9] 和 [2, 329] 。
# 
# 
# 请你返回可以得到的 new1 和 new2 的 最小 和。
# 
# 
# 
# 示例 1：
# 
# 输入：num = 2932
# 输出：52
# 解释：可行的 [new1, new2] 数对为 [29, 23] ，[223, 9] 等等。
# 最小和为数对 [29, 23] 的和：29 + 23 = 52 。
# 
# 
# 示例 2：
# 
# 输入：num = 4009
# 输出：13
# 解释：可行的 [new1, new2] 数对为 [0, 49] ，[490, 0] 等等。
# 最小和为数对 [4, 9] 的和：4 + 9 = 13 。
# 
# 
# 
# 
# 提示：
# 
# 
# 1000 <= num <= 9999
# 
# 
#

# @lc code=start
class Solution:
    def minimumSum(self, num: int) -> int:
        digits = []
        while num != 0:
            digits.append(num % 10)
            num //= 10
        
        digits.sort()
        return 10 * (digits[0] + digits[1]) + digits[2] + digits[3]

# @lc code=end


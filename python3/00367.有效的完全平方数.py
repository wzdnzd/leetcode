#
# @lc app=leetcode.cn id=367 lang=python3
#
# [367] 有效的完全平方数
#
# https://leetcode-cn.com/problems/valid-perfect-square/description/
#
# algorithms
# Easy (39.45%)
# Total Accepted:    8.2K
# Total Submissions: 20.3K
# Testcase Example:  '16'
#
# 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
#
# 说明：不要使用任何内置的库函数，如  sqrt。
#
# 示例 1：
#
# 输入：16
# 输出：True
#
# 示例 2：
#
# 输入：14
# 输出：False
#
#
#


class Solution:
    def isPerfectSquare(self, num: int) -> bool:
        # return num ** 0.5 == int(num ** 0.5)

        # n ^ 2 = 1 + 3 + 5 + ... + (2n - 1)
        i, result = 1, 1
        while result < num:
            i += 2
            result += i

        return True if result == num else False

#
# @lc app=leetcode.cn id=371 lang=python3
#
# [371] 两整数之和
#
# https://leetcode-cn.com/problems/sum-of-two-integers/description/
#
# algorithms
# Easy (58.16%)
# Total Accepted:    9.1K
# Total Submissions: 17.1K
# Testcase Example:  '1\n2'
#
# 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
#
# 示例 1:
#
# 输入: a = 1, b = 2
# 输出: 3
#
#
# 示例 2:
#
# 输入: a = -2, b = 3
# 输出: 1
#
#


class Solution:
    def getSum(self, a: int, b: int) -> int:
        MAX = 0x7fffffff
        mask = 0xFFFFFFFF
        while b != 0:
            a, b = (a ^ b) & mask, ((a & b) << 1)
        return a if a <= MAX else ~(a ^ mask)

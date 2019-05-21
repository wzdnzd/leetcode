#
# @lc app=leetcode.cn id=29 lang=python3
#
# [29] 两数相除
#
# https://leetcode-cn.com/problems/divide-two-integers/description/
#
# algorithms
# Medium (17.26%)
# Likes:    125
# Dislikes: 0
# Total Accepted:    12.7K
# Total Submissions: 69.8K
# Testcase Example:  '10\n3'
#
# 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
#
# 返回被除数 dividend 除以除数 divisor 得到的商。
#
# 示例 1:
#
# 输入: dividend = 10, divisor = 3
# 输出: 3
#
# 示例 2:
#
# 输入: dividend = 7, divisor = -3
# 输出: -2
#
# 说明:
#
#
# 被除数和除数均为 32 位有符号整数。
# 除数不为 0。
# 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
#
#
#


class Solution:
    def divide(self, dividend: int, divisor: int) -> int:
        positive = (dividend > 0) is (divisor > 0)
        dividend, divisor = abs(dividend), abs(divisor)
        result = 0
        while dividend >= divisor:
            tmp, cnt = divisor, 1
            while dividend >= tmp:
                cnt <<= 1
                tmp <<= 1
            dividend -= tmp >> 1
            result += cnt >> 1
        result = result if positive else -result
        return min(max(-(2**31), result), 2**31-1)

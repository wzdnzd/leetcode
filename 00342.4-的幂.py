#
# @lc app=leetcode.cn id=342 lang=python3
#
# [342] 4的幂
#
# https://leetcode-cn.com/problems/power-of-four/description/
#
# algorithms
# Easy (44.03%)
# Total Accepted:    6.7K
# Total Submissions: 14.9K
# Testcase Example:  '16'
#
# 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
#
# 示例 1:
#
# 输入: 16
# 输出: true
#
#
# 示例 2:
#
# 输入: 5
# 输出: false
#
# 进阶：
# 你能不使用循环或者递归来完成本题吗？
#
#


class Solution:
    def isPowerOfFour(self, num: int) -> bool:
        # while num > 1:
        #     if num % 4 != 0:
        #         break
        #     num /= 4

        # return num == 1

        # https://leetcode-cn.com/problems/power-of-four/comments/7075

        # check(is or not) a power of 2
        if num < 0 or num & (num - 1):
            return False

        # check 1 on odd bits
        return (num & 0x55555555) > 0

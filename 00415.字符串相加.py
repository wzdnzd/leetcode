#
# @lc app=leetcode.cn id=415 lang=python
#
# [415] 字符串相加
#
# https://leetcode-cn.com/problems/add-strings/description/
#
# algorithms
# Easy (42.80%)
# Likes:    68
# Dislikes: 0
# Total Accepted:    7.3K
# Total Submissions: 16.2K
# Testcase Example:  '"0"\n"0"'
#
# 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
#
# 注意：
#
#
# num1 和num2 的长度都小于 5100.
# num1 和num2 都只包含数字 0-9.
# num1 和num2 都不包含任何前导零。
# 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
#
#
#


class Solution(object):
    def addStrings(self, num1, num2):
        """
        :type num1: str
        :type num2: str
        :rtype: str
        """
        result = ''
        num1, num2 = num1[::-1], num2[::-1]
        if len(num1) < len(num2):
            num1 = num1 + (len(num2)-len(num1)) * '0'
        if len(num1) > len(num2):
            num2 = num2 + (len(num1)-len(num2)) * '0'

        flag = 0
        for i in range(len(num1)):
            num = int(num1[i]) + int(num2[i]) + flag
            mod = num % 10
            flag = num // 10
            result += str(mod)

        return result[::-1] if flag ==0 else (result + str(flag))[::-1]

#
# @lc app=leetcode.cn id=58 lang=python3
#
# [58] 最后一个单词的长度
#
# https://leetcode-cn.com/problems/length-of-last-word/description/
#
# algorithms
# Easy (28.52%)
# Total Accepted:    17K
# Total Submissions: 59.3K
# Testcase Example:  '"Hello World"'
#
# 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
#
# 如果不存在最后一个单词，请返回 0 。
#
# 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
#
# 示例:
#
# 输入: "Hello World"
# 输出: 5
#
#
#


class Solution:
    def lengthOfLastWord(self, s: 'str') -> 'int':
        # return 0 if len(s.strip()) == 0 else len(s.split()[-1])
        s, count = s.strip(), 0
        if len(s) == 0:
            return 0

        while count < len(s) and s[-count-1] != ' ':
            count += 1
        return count

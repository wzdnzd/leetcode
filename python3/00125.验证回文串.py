#
# @lc app=leetcode.cn id=125 lang=python3
#
# [125] 验证回文串
#
# https://leetcode-cn.com/problems/valid-palindrome/description/
#
# algorithms
# Easy (37.67%)
# Total Accepted:    30.4K
# Total Submissions: 78.3K
# Testcase Example:  '"A man, a plan, a canal: Panama"'
#
# 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
#
# 说明：本题中，我们将空字符串定义为有效的回文串。
#
# 示例 1:
#
# 输入: "A man, a plan, a canal: Panama"
# 输出: true
#
#
# 示例 2:
#
# 输入: "race a car"
# 输出: false
#
#
#


class Solution:
    def isPalindrome(self, s: str) -> bool:
        text = ''
        for c in s.lower():
            asc = ord(c)
            if 97 <= asc <= 122 or 48 <= asc <= 57:
                text += c

        return text == text[::-1]

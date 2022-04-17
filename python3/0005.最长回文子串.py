#
# @lc app=leetcode.cn id=5 lang=python3
#
# [5] 最长回文子串
#
# https://leetcode-cn.com/problems/longest-palindromic-substring/description/
#
# algorithms
# Medium (24.11%)
# Likes:    873
# Dislikes: 0
# Total Accepted:    59.1K
# Total Submissions: 234.9K
# Testcase Example:  '"babad"'
#
# 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
#
# 示例 1：
#
# 输入: "babad"
# 输出: "bab"
# 注意: "aba" 也是一个有效答案。
#
#
# 示例 2：
#
# 输入: "cbbd"
# 输出: "bb"
#
#
#


class Solution:
    def longestPalindrome(self, s: str) -> str:
        # 扩散法
        def diffuse(s, l, r):
            while l >= 0 and r < len(s) and s[l] == s[r]:
                l -= 1
                r += 1
            return s[l + 1 : r]

        result = ""
        for i in range(len(s)):
            # odd case, like "aba"
            substr = diffuse(s, i, i)
            if len(substr) > len(result):
                result = substr
            # even case, like "abba"
            substr = diffuse(s, i, i + 1)
            if len(substr) > len(result):
                result = substr

        return result

#
# @lc app=leetcode.cn id=409 lang=python
#
# [409] 最长回文串
#
# https://leetcode-cn.com/problems/longest-palindrome/description/
#
# algorithms
# Easy (45.66%)
# Likes:    51
# Dislikes: 0
# Total Accepted:    5.5K
# Total Submissions: 11.6K
# Testcase Example:  '"abccccdd"'
#
# 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
#
# 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
#
# 注意:
# 假设字符串的长度不会超过 1010。
#
# 示例 1:
#
#
# 输入:
# "abccccdd"
#
# 输出:
# 7
#
# 解释:
# 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
#
#
#


class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: int
        """
        bitmap = [0] * 52
        for c in s:
            asc = ord(c)
            if asc < 97:
                bitmap[asc - 65] = bitmap[asc - 65] + 1
            else:
                bitmap[asc - 97 + 26] = bitmap[asc - 97 + 26] + 1

        flag, count = 0, 0
        for num in bitmap:
            if num % 2 == 0:
                count += num
            else:
                count += num - 1
                flag = 1

        return count + flag

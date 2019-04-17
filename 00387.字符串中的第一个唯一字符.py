#
# @lc app=leetcode.cn id=387 lang=python
#
# [387] 字符串中的第一个唯一字符
#
# https://leetcode-cn.com/problems/first-unique-character-in-a-string/description/
#
# algorithms
# Easy (35.36%)
# Total Accepted:    26.7K
# Total Submissions: 71.6K
# Testcase Example:  '"leetcode"'
#
# 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
#
# 案例:
#
#
# s = "leetcode"
# 返回 0.
#
# s = "loveleetcode",
# 返回 2.
#
#
#
#
# 注意事项：您可以假定该字符串只包含小写字母。
#
#


class Solution(object):
    def firstUniqChar(self, s):
        """
        :type s: str
        :rtype: int
        """
        record = {}
        for c in s:
            if c not in record.keys():
                record[c] = 1
            else:
                record[c] = record[c] + 1

        for i in range(len(s)):
            if s[i] in record.keys() and record[s[i]] == 1:
                return i

        return -1

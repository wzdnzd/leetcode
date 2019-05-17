#
# @lc app=leetcode.cn id=10 lang=python3
#
# [10] 正则表达式匹配
#
# https://leetcode-cn.com/problems/regular-expression-matching/description/
#
# algorithms
# Hard (20.85%)
# Likes:    455
# Dislikes: 0
# Total Accepted:    15.4K
# Total Submissions: 67.7K
# Testcase Example:  '"aa"\n"a"'
#
# 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
#
# '.' 匹配任意单个字符。
# '*' 匹配零个或多个前面的元素。
#
#
# 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
#
# 说明:
#
#
# s 可能为空，且只包含从 a-z 的小写字母。
# p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
#
#
# 示例 1:
#
# 输入:
# s = "aa"
# p = "a"
# 输出: false
# 解释: "a" 无法匹配 "aa" 整个字符串。
#
#
# 示例 2:
#
# 输入:
# s = "aa"
# p = "a*"
# 输出: true
# 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
#
#
# 示例 3:
#
# 输入:
# s = "ab"
# p = ".*"
# 输出: true
# 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
#
#
# 示例 4:
#
# 输入:
# s = "aab"
# p = "c*a*b"
# 输出: true
# 解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
#
#
# 示例 5:
#
# 输入:
# s = "mississippi"
# p = "mis*is*p*."
# 输出: false
#
#


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        dp = [[False] * (len(s) + 1) for _ in range(len(p) + 1)]
        dp[0][0] = True
        for i in range(1, len(p)):
            dp[i + 1][0] = dp[i - 1][0] and p[i] == '*'
        for i in range(len(p)):
            for j in range(len(s)):
                if p[i] == '*':
                    dp[i + 1][j + 1] = dp[i - 1][j + 1] or dp[i][j + 1]
                    if p[i - 1] == s[j] or p[i - 1] == '.':
                        dp[i + 1][j + 1] |= dp[i + 1][j]
                else:
                    dp[i + 1][j +
                              1] = dp[i][j] and (p[i] == s[j] or p[i] == '.')
        return dp[-1][-1]

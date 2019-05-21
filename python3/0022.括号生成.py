#
# @lc app=leetcode.cn id=22 lang=python3
#
# [22] 括号生成
#
# https://leetcode-cn.com/problems/generate-parentheses/description/
#
# algorithms
# Medium (67.93%)
# Likes:    377
# Dislikes: 0
# Total Accepted:    23.1K
# Total Submissions: 32.9K
# Testcase Example:  '3'
#
# 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
#
# 例如，给出 n = 3，生成结果为：
#
# [
# ⁠ "((()))",
# ⁠ "(()())",
# ⁠ "(())()",
# ⁠ "()(())",
# ⁠ "()()()"
# ]
#
#
#


class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        result = []
        if n <= 0:
            return []

        def singleStr(s, left, right, n):
            if left == n and right == n:
                result.append(s)
            if left < n:
                singleStr(s + '(', left + 1, right, n)

            if right < left:
                singleStr(s + ')', left, right + 1, n)

        singleStr('', 0, 0, n)

        return result

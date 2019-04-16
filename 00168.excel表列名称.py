#
# @lc app=leetcode.cn id=168 lang=python3
#
# [168] Excel表列名称
#
# https://leetcode-cn.com/problems/excel-sheet-column-title/description/
#
# algorithms
# Easy (29.72%)
# Total Accepted:    6.2K
# Total Submissions: 20.1K
# Testcase Example:  '1'
#
# 给定一个正整数，返回它在 Excel 表中相对应的列名称。
#
# 例如，
#
# ⁠   1 -> A
# ⁠   2 -> B
# ⁠   3 -> C
# ⁠   ...
# ⁠   26 -> Z
# ⁠   27 -> AA
# ⁠   28 -> AB
# ⁠   ...
#
#
# 示例 1:
#
# 输入: 1
# 输出: "A"
#
#
# 示例 2:
#
# 输入: 28
# 输出: "AB"
#
#
# 示例 3:
#
# 输入: 701
# 输出: "ZY"
#
#
#


class Solution:
    def convertToTitle(self, n: int) -> str:
        if n <= 0:
            return ''

        result = ''

        while n > 0:
            result += chr((n-1) % 26 + 65)
            n = (n-1) // 26
        return result[::-1]

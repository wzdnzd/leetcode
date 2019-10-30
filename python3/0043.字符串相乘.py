#
# @lc app=leetcode.cn id=43 lang=python3
#
# [43] 字符串相乘
#
# https://leetcode-cn.com/problems/multiply-strings/description/
#
# algorithms
# Medium (39.93%)
# Likes:    227
# Dislikes: 0
# Total Accepted:    31.5K
# Total Submissions: 77.1K
# Testcase Example:  '"2"\n"3"'
#
# 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
#
# 示例 1:
#
# 输入: num1 = "2", num2 = "3"
# 输出: "6"
#
# 示例 2:
#
# 输入: num1 = "123", num2 = "456"
# 输出: "56088"
#
# 说明：
#
#
# num1 和 num2 的长度小于110。
# num1 和 num2 只包含数字 0-9。
# num1 和 num2 均不以零开头，除非是数字 0 本身。
# 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
#
#
#

# @lc code=start


class Solution:
    def multiply(self, num1: str, num2: str) -> str:
        result = 0
        for i, n1 in enumerate(num1[::-1]):
            n1 = ord(n1) - ord('0')
            for j, n2 in enumerate(num2[::-1]):
                n2 = ord(n2) - ord('0')
                result += n2 * n1 * (10 ** (i + j))
        return str(result)
# @lc code=end

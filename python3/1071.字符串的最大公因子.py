#
# @lc app=leetcode.cn id=1071 lang=python3
#
# [1071] 字符串的最大公因子
#
# https://leetcode.cn/problems/greatest-common-divisor-of-strings/description/
#
# algorithms
# Easy (59.32%)
# Likes:    463
# Dislikes: 0
# Total Accepted:    101K
# Total Submissions: 170.2K
# Testcase Example:  '"ABCABC"\n"ABC"'
#
# 对于字符串 s 和 t，只有在 s = t + t + t + ... + t + t（t 自身连接 1 次或多次）时，我们才认定 “t 能除尽 s”。
#
# 给定两个字符串 str1 和 str2 。返回 最长字符串 x，要求满足 x 能除尽 str1 且 x 能除尽 str2 。
#
#
#
# 示例 1：
#
#
# 输入：str1 = "ABCABC", str2 = "ABC"
# 输出："ABC"
#
#
# 示例 2：
#
#
# 输入：str1 = "ABABAB", str2 = "ABAB"
# 输出："AB"
#
#
# 示例 3：
#
#
# 输入：str1 = "LEET", str2 = "CODE"
# 输出：""
#
#
#
#
# 提示：
#
#
# 1 <= str1.length, str2.length <= 1000
# str1 和 str2 由大写英文字母组成
#
#
#


# @lc code=start
class Solution:
    def gcdOfStrings(self, str1: str, str2: str) -> str:
        m, n = len(str1), len(str2)
        if m > n:
            return self.gcdOfStrings(str2, str1)

        for i in range(m, 0, -1):
            if m % i != 0 or n % i != 0:
                continue

            if self._is_repeated(str1, str1, i) and self._is_repeated(str2, str1, i):
                return str1[:i]

        return ""

    def _is_repeated(self, str1: str, str2: str, k: int) -> bool:
        for i in range(len(str1)):
            if str1[i] != str2[i % k]:
                return False

        return True


# @lc code=end

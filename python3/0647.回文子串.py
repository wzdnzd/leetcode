#
# @lc app=leetcode.cn id=647 lang=python3
#
# [647] 回文子串
#
# https://leetcode.cn/problems/palindromic-substrings/description/
#
# algorithms
# Medium (66.78%)
# Likes:    1187
# Dislikes: 0
# Total Accepted:    284.7K
# Total Submissions: 425.8K
# Testcase Example:  '"abc"'
#
# 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
#
# 回文字符串 是正着读和倒过来读一样的字符串。
#
# 子字符串 是字符串中的由连续字符组成的一个序列。
#
# 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
#
#
#
# 示例 1：
#
#
# 输入：s = "abc"
# 输出：3
# 解释：三个回文子串: "a", "b", "c"
#
#
# 示例 2：
#
#
# 输入：s = "aaa"
# 输出：6
# 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
#
#
#
# 提示：
#
#
# 1 <= s.length <= 1000
# s 由小写英文字母组成
#
#
#


# @lc code=start
class Solution:
    def countSubstrings(self, s: str) -> int:
        ans = 0
        for i in range(len(s)):
            ans += self.extend(s, i, i)
            ans += self.extend(s, i, i + 1)

        return ans

    def extend(self, s: str, i: int, j: int) -> int:
        n, count = len(s), 0
        while i >= 0 and j < n and s[i] == s[j]:
            i -= 1
            j += 1
            count += 1

        return count


# @lc code=end

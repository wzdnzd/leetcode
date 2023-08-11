#
# @lc app=leetcode.cn id=1941 lang=python3
#
# [1941] 检查是否所有字符出现次数相同
#
# https://leetcode.cn/problems/check-if-all-characters-have-equal-number-of-occurrences/description/
#
# algorithms
# Easy (73.53%)
# Likes:    19
# Dislikes: 0
# Total Accepted:    13.1K
# Total Submissions: 17.8K
# Testcase Example:  '"abacbc"'
#
# 给你一个字符串 s ，如果 s 是一个 好 字符串，请你返回 true ，否则请返回 false 。
#
# 如果 s 中出现过的 所有 字符的出现次数 相同 ，那么我们称字符串 s 是 好 字符串。
#
#
#
# 示例 1：
#
# 输入：s = "abacbc"
# 输出：true
# 解释：s 中出现过的字符为 'a'，'b' 和 'c' 。s 中所有字符均出现 2 次。
#
#
# 示例 2：
#
# 输入：s = "aaabb"
# 输出：false
# 解释：s 中出现过的字符为 'a' 和 'b' 。
# 'a' 出现了 3 次，'b' 出现了 2 次，两者出现次数不同。
#
#
#
#
# 提示：
#
#
# 1 <= s.length <= 1000
# s 只包含小写英文字母。
#
#
#


# @lc code=start
class Solution:
    def areOccurrencesEqual(self, s: str) -> bool:
        counter = {}
        for c in s:
            counter[c] = counter.get(c, 0) + 1

        return len(set(counter.values())) == 1


# @lc code=end

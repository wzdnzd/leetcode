#
# @lc app=leetcode.cn id=30 lang=python3
#
# [30] 串联所有单词的子串
#
# https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/description/
#
# algorithms
# Hard (23.92%)
# Likes:    85
# Dislikes: 0
# Total Accepted:    7K
# Total Submissions: 26.8K
# Testcase Example:  '"barfoothefoobarman"\n["foo","bar"]'
#
# 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
#
# 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
#
#
#
# 示例 1：
#
# 输入：
# ⁠ s = "barfoothefoobarman",
# ⁠ words = ["foo","bar"]
# 输出：[0,9]
# 解释：
# 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
# 输出的顺序不重要, [9,0] 也是有效答案。
#
#
# 示例 2：
#
# 输入：
# ⁠ s = "wordgoodgoodgoodbestword",
# ⁠ words = ["word","good","best","word"]
# 输出：[]
#
#
#


class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        if len(words) == 0 or len(s) < len(words) * len(words[0]):
            return []

        result, n, m, k = [], len(s), len(words), len(words[0])
        maps = collections.Counter(words)

        for i in range(k):
            cur_map, count, start, cur = {}, 0, i, i
            while cur + k <= n:
                cur_str = s[cur:cur+k]
                if cur_str in maps:
                    cur_map[cur_str] = cur_map.get(cur_str, 0) + 1
                    if cur_map[cur_str] <= maps[cur_str]:
                        count += 1
                    while cur_map[cur_str] > maps[cur_str]:
                        begin_str = s[start:start+k]
                        cur_map[begin_str] -= 1
                        start += k
                        if cur_map[begin_str] < maps[begin_str]:
                            count -= 1
                    if count == m:
                        result.append(start)
                        begin_str = s[start:start+k]
                        cur_map[begin_str] -= 1
                        start += k
                        count -= 1
                else:
                    cur_map, count, start = {}, 0, cur + k
                cur += k

        return result

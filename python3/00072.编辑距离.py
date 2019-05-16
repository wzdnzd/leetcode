#
# @lc app=leetcode.cn id=72 lang=python3
#
# [72] 编辑距离
#
# https://leetcode-cn.com/problems/edit-distance/description/
#
# algorithms
# Hard (47.02%)
# Likes:    226
# Dislikes: 0
# Total Accepted:    6.4K
# Total Submissions: 12.7K
# Testcase Example:  '"horse"\n"ros"'
#
# 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
#
# 你可以对一个单词进行如下三种操作：
#
#
# 插入一个字符
# 删除一个字符
# 替换一个字符
#
#
# 示例 1:
#
# 输入: word1 = "horse", word2 = "ros"
# 输出: 3
# 解释:
# horse -> rorse (将 'h' 替换为 'r')
# rorse -> rose (删除 'r')
# rose -> ros (删除 'e')
#
#
# 示例 2:
#
# 输入: word1 = "intention", word2 = "execution"
# 输出: 5
# 解释:
# intention -> inention (删除 't')
# inention -> enention (将 'i' 替换为 'e')
# enention -> exention (将 'n' 替换为 'x')
# exention -> exection (将 'n' 替换为 'c')
# exection -> execution (插入 'u')
#
#
#


class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if not word1:
            return len(word2 or '')
        if not word2:
            return len(word1 or '')

        m, n = len(word1) + 1, len(word2) + 1
        record = [[0] * n for _ in range(m)]

        for i in range(n):
            record[0][i] = i
        for j in range(m):
            record[j][0] = j

        for i in range(1, m):
            for j in range(1, n):
                if word1[i-1] == word2[j-1]:
                    record[i][j] = record[i-1][j-1]
                else:
                    record[i][j] = 1 + \
                        min(record[i-1][j-1], record[i-1][j], record[i][j-1])

        return record[m-1][n-1]

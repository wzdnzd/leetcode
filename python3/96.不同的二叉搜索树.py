#
# @lc app=leetcode.cn id=96 lang=python3
#
# [96] 不同的二叉搜索树
#
# https://leetcode.cn/problems/unique-binary-search-trees/description/
#
# algorithms
# Medium (70.77%)
# Likes:    2037
# Dislikes: 0
# Total Accepted:    321.2K
# Total Submissions: 453.4K
# Testcase Example:  '3'
#
# 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
#
#
#
# 示例 1：
#
#
# 输入：n = 3
# 输出：5
#
#
# 示例 2：
#
#
# 输入：n = 1
# 输出：1
#
#
#
#
# 提示：
#
#
# 1
#
#
#

# @lc code=start


class Solution:
    memos = {}

    def numTrees(self, n: int) -> int:
        if n <= 1:
            return 1
        if n in self.memos:
            return self.memos.get(n)

        ans = 0
        for i in range(1, n + 1):
            ans += self.numTrees(i - 1) * self.numTrees(n - i)

        self.memos[n] = ans
        return ans


# @lc code=end

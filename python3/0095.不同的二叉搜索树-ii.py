#
# @lc app=leetcode.cn id=95 lang=python3
#
# [95] 不同的二叉搜索树 II
#
# https://leetcode.cn/problems/unique-binary-search-trees-ii/description/
#
# algorithms
# Medium (72.92%)
# Likes:    1376
# Dislikes: 0
# Total Accepted:    157.7K
# Total Submissions: 215.7K
# Testcase Example:  '3'
#
# 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
#
#
#
#
#
# 示例 1：
#
#
# 输入：n = 3
# 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
#
#
# 示例 2：
#
#
# 输入：n = 1
# 输出：[[1]]
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
#
#


# @lc code=start
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class Solution:
    def generateTrees(self, n: int) -> List[Optional[TreeNode]]:
        return [] if n <= 0 else self.buildTree(1, n)

    def buildTree(self, start: int, end: int) -> List[Optional[TreeNode]]:
        if start > end:
            return [None]

        ans = []
        for i in range(start, end + 1):
            left = self.buildTree(start, i - 1)
            right = self.buildTree(i + 1, end)
            for l in left:
                for r in right:
                    root = TreeNode(i, l, r)
                    ans.append(root)

        return ans


# @lc code=end

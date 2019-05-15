#
# @lc app=leetcode.cn id=257 lang=python3
#
# [257] 二叉树的所有路径
#
# https://leetcode-cn.com/problems/binary-tree-paths/description/
#
# algorithms
# Easy (56.74%)
# Total Accepted:    7.9K
# Total Submissions: 13.7K
# Testcase Example:  '[1,2,3,null,5]'
#
# 给定一个二叉树，返回所有从根节点到叶子节点的路径。
#
# 说明: 叶子节点是指没有子节点的节点。
#
# 示例:
#
# 输入:
#
# ⁠  1
# ⁠/   \
# 2     3
# ⁠\
# ⁠ 5
#
# 输出: ["1->2->5", "1->3"]
#
# 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
#
#
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def binaryTreePaths(self, root: TreeNode) -> List[str]:
        if not root:
            return []
        paths = []
        if not root.left and not root.right:
            return [str(root.val)]
        if root.left:
            for i in self.binaryTreePaths(root.left):
                paths.append(str(root.val) + '->' + i)
        if root.right:
            for i in self.binaryTreePaths(root.right):
                paths.append(str(root.val) + '->' + i)
        return paths

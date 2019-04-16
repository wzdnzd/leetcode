#
# @lc app=leetcode.cn id=110 lang=python3
#
# [110] 平衡二叉树
#
# https://leetcode-cn.com/problems/balanced-binary-tree/description/
#
# algorithms
# Easy (45.89%)
# Total Accepted:    11.4K
# Total Submissions: 24.6K
# Testcase Example:  '[3,9,20,null,null,15,7]'
#
# 给定一个二叉树，判断它是否是高度平衡的二叉树。
#
# 本题中，一棵高度平衡二叉树定义为：
#
#
# 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
#
#
# 示例 1:
#
# 给定二叉树 [3,9,20,null,null,15,7]
#
# ⁠   3
# ⁠  / \
# ⁠ 9  20
# ⁠   /  \
# ⁠  15   7
#
# 返回 true 。
#
# 示例 2:
#
# 给定二叉树 [1,2,2,3,3,null,null,4,4]
#
# ⁠      1
# ⁠     / \
# ⁠    2   2
# ⁠   / \
# ⁠  3   3
# ⁠ / \
# ⁠4   4
#
#
# 返回 false 。
#
#
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def isBalanced(self, root: TreeNode) -> bool:
        if not root:
            return True
        if abs(self.treeHight(root.left) - self.treeHight(root.right)) <= 1:
            return self.isBalanced(root.left) and self.isBalanced(root.right)
        else:
            return False

    def treeHight(self, root):
        if not root:
            return 0
        return max(self.treeHight(root.left), self.treeHight(root.right)) + 1

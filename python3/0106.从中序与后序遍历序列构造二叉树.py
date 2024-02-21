#
# @lc app=leetcode.cn id=106 lang=python3
#
# [106] 从中序与后序遍历序列构造二叉树
#
# https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
#
# algorithms
# Medium (71.80%)
# Likes:    1174
# Dislikes: 0
# Total Accepted:    341.4K
# Total Submissions: 475K
# Testcase Example:  '[9,3,15,20,7]\n[9,15,7,20,3]'
#
# 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder
# 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
#
#
#
# 示例 1:
#
#
# 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
# 输出：[3,9,20,null,null,15,7]
#
#
# 示例 2:
#
#
# 输入：inorder = [-1], postorder = [-1]
# 输出：[-1]
#
#
#
#
# 提示:
#
#
# 1 <= inorder.length <= 3000
# postorder.length == inorder.length
# -3000 <= inorder[i], postorder[i] <= 3000
# inorder 和 postorder 都由 不同 的值组成
# postorder 中每一个值都在 inorder 中
# inorder 保证是树的中序遍历
# postorder 保证是树的后序遍历
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
    def buildTree(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
        records = {v: i for i, v in enumerate(inorder)}
        return self.buildTreeHelper(postorder, 0, len(postorder) - 1, records, 0)

    def buildTreeHelper(
        self, postorder: List[int], start: int, end: int, records: dict, current: int
    ) -> Optional[TreeNode]:
        if not postorder or start > end:
            return None

        root = TreeNode(postorder[end])
        index = records[root.val]
        length = index - current

        root.left = self.buildTreeHelper(postorder, start, start + length - 1, records, current)
        root.right = self.buildTreeHelper(postorder, start + length, end - 1, records, index + 1)

        return root


# @lc code=end

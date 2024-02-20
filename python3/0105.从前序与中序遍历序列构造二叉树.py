#
# @lc app=leetcode.cn id=105 lang=python3
#
# [105] 从前序与中序遍历序列构造二叉树
#
# https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
#
# algorithms
# Medium (71.21%)
# Likes:    2210
# Dislikes: 0
# Total Accepted:    583.9K
# Total Submissions: 819.2K
# Testcase Example:  '[3,9,20,15,7]\n[9,3,15,20,7]'
#
# 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder
# 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
#
#
#
# 示例 1:
#
#
# 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
# 输出: [3,9,20,null,null,15,7]
#
#
# 示例 2:
#
#
# 输入: preorder = [-1], inorder = [-1]
# 输出: [-1]
#
#
#
#
# 提示:
#
#
# 1 <= preorder.length <= 3000
# inorder.length == preorder.length
# -3000 <= preorder[i], inorder[i] <= 3000
# preorder 和 inorder 均 无重复 元素
# inorder 均出现在 preorder
# preorder 保证 为二叉树的前序遍历序列
# inorder 保证 为二叉树的中序遍历序列
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
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        records = {v: i for i, v in enumerate(inorder)}
        return self.buildTreeHelper(preorder, 0, len(preorder) - 1, records, 0)

    def buildTreeHelper(
        self, preorder: List[int], start: int, end: int, record: dict, current: int
    ) -> Optional[TreeNode]:
        if not preorder or start > end:
            return None

        root = TreeNode(preorder[start])
        index = record[root.val]
        root.left = self.buildTreeHelper(preorder, start + 1, start + index - current, record, current)
        root.right = self.buildTreeHelper(preorder, start + index - current + 1, end, record, index + 1)

        return root


# @lc code=end

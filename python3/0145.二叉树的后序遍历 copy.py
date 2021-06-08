#
# @lc app=leetcode.cn id=145 lang=python3
#
# [145] 二叉树的后序遍历
#

# @lc code=start
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def postorderTraversal(self, root: TreeNode) -> List[int]:
        result = []
        self.helper(root, result)
        return result

    def helper(self, root: TreeNode, result: List[int]):
        if not root:
            return

        self.helper(root.left, result)
        self.helper(root.right, result)
        result.append(root.val)
# @lc code=end

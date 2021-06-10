#
# @lc app=leetcode.cn id=199 lang=python3
#
# [199] 二叉树的右视图
#

# @lc code=start
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def rightSideView(self, root: TreeNode) -> List[int]:
        if not root:
            return []

        result, queue = [], [root]
        while queue:
            result.append(queue[-1].val)
            temp = []
            for root in queue:
                if root.left:
                    temp.append(root.left)
                if root.right:
                    temp.append(root.right)

            queue = temp

        return result
# @lc code=end

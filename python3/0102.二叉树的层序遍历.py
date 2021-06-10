#
# @lc app=leetcode.cn id=102 lang=python3
#
# [102] 二叉树的层序遍历
#

# @lc code=start
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        result = []
        if not root:
            return result

        queue = [root]
        while queue:
            tempv, tempr = [], []
            for root in queue:
                tempv.append(root.val)
                if root.left:
                    tempr.append(root.left)
                if root.right:
                    tempr.append(root.right)

            result.append(tempv)
            queue = tempr

        return result
# @lc code=end

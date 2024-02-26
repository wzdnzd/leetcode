#
# @lc app=leetcode.cn id=938 lang=python3
#
# [938] 二叉搜索树的范围和
#
# https://leetcode.cn/problems/range-sum-of-bst/description/
#
# algorithms
# Easy (82.10%)
# Likes:    346
# Dislikes: 0
# Total Accepted:    130.8K
# Total Submissions: 159K
# Testcase Example:  '[10,5,15,3,7,null,18]\n7\n15'
#
# 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
#
#
#
# 示例 1：
#
#
# 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
# 输出：32
#
#
# 示例 2：
#
#
# 输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
# 输出：23
#
#
#
#
# 提示：
#
#
# 树中节点数目在范围 [1, 2 * 10^4] 内
# 1
# 1
# 所有 Node.val 互不相同
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
    def rangeSumBST(self, root: Optional[TreeNode], low: int, high: int) -> int:
        if not root:
            return 0
        if root.val < low:
            return self.rangeSumBST(root.right, low, high)
        if root.val > high:
            return self.rangeSumBST(root.left, low, high)
        return root.val + self.rangeSumBST(root.left, low, high) + self.rangeSumBST(root.right, low, high)


# @lc code=end

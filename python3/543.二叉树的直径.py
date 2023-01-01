#
# @lc app=leetcode.cn id=543 lang=python3
#
# [543] 二叉树的直径
#
# https://leetcode.cn/problems/diameter-of-binary-tree/description/
#
# algorithms
# Easy (57.73%)
# Likes:    1153
# Dislikes: 0
# Total Accepted:    261.4K
# Total Submissions: 452.8K
# Testcase Example:  '[1,2,3,4,5]'
#
# 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
#
#
#
# 示例 :
# 给定二叉树
#
# ⁠         1
# ⁠        / \
# ⁠       2   3
# ⁠      / \
# ⁠     4   5
#
#
# 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
#
#
#
# 注意：两结点之间的路径长度是以它们之间边的数目表示。
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
    # def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
    #     _, diameter = self.maxDepth(root, 0)
    #     return diameter

    # def maxDepth(self, root: Optional[TreeNode], diameter: int) -> int:
    #     if not root:
    #         return 0, diameter

    #     left, diameter = self.maxDepth(root.left, diameter)
    #     right, diameter = self.maxDepth(root.right, diameter)
    #     diameter = max(diameter, left + right)

    #     return max(left, right) + 1, diameter

    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        diameter = 0

        def maxDepth(root: Optional[TreeNode]) -> int:
            nonlocal diameter

            if not root:
                return 0

            left = maxDepth(root.left)
            right = maxDepth(root.right)
            diameter = max(diameter, left + right)
            return max(left, right) + 1

        maxDepth(root)
        return diameter


# @lc code=end

#
# @lc app=leetcode.cn id=1080 lang=python3
#
# [1080] 根到叶路径上的不足节点
#
# https://leetcode.cn/problems/insufficient-nodes-in-root-to-leaf-paths/description/
#
# algorithms
# Medium (51.80%)
# Likes:    138
# Dislikes: 0
# Total Accepted:    16.6K
# Total Submissions: 27.9K
# Testcase Example:  '[1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14]\n1'
#
# 给你二叉树的根节点 root 和一个整数 limit ，请你同时删除树中所有 不足节点 ，并返回最终二叉树的根节点。
#
# 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为 不足节点 ，需要被删除。
#
# 叶子节点，就是没有子节点的节点。
#
#
#
# 示例 1：
#
#
# 输入：root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
# 输出：[1,2,3,4,null,null,7,8,9,null,14]
#
#
# 示例 2：
#
#
# 输入：root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
# 输出：[5,4,8,11,null,17,4,7,null,null,null,5]
#
#
# 示例 3：
#
#
# 输入：root = [1,2,-3,-5,null,4,null], limit = -1
# 输出：[1,null,-3,4]
#
#
#
#
# 提示：
#
#
# 树中节点数目在范围 [1, 5000] 内
# -10^5 <= Node.val <= 10^5
# -10^9 <= limit <= 10^9
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
    def sufficientSubset(
        self, root: Optional[TreeNode], limit: int
    ) -> Optional[TreeNode]:
        flag = self.dfs(root, 0, limit)
        return None if flag else root

    def dfs(self, root: Optional[TreeNode], current: int, limit: int) -> bool:
        if not root.left and not root.right:
            return current + root.val < limit

        left, right, value = True, True, current + root.val
        if root.left:
            left = self.dfs(root.left, value, limit)
        if root.right:
            right = self.dfs(root.right, value, limit)

        if left:
            root.left = None
        if right:
            root.right = None

        return left and right


# @lc code=end

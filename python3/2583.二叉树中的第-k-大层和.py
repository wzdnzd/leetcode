#
# @lc app=leetcode.cn id=2583 lang=python3
#
# [2583] 二叉树中的第 K 大层和
#
# https://leetcode.cn/problems/kth-largest-sum-in-a-binary-tree/description/
#
# algorithms
# Medium (44.22%)
# Likes:    19
# Dislikes: 0
# Total Accepted:    11.7K
# Total Submissions: 24.6K
# Testcase Example:  '[5,8,9,2,1,3,7,4,6]\n2'
#
# 给你一棵二叉树的根节点 root 和一个正整数 k 。
#
# 树中的 层和 是指 同一层 上节点值的总和。
#
# 返回树中第 k 大的层和（不一定不同）。如果树少于 k 层，则返回 -1 。
#
# 注意，如果两个节点与根节点的距离相同，则认为它们在同一层。
#
#
#
# 示例 1：
#
#
#
#
# 输入：root = [5,8,9,2,1,3,7,4,6], k = 2
# 输出：13
# 解释：树中每一层的层和分别是：
# - Level 1: 5
# - Level 2: 8 + 9 = 17
# - Level 3: 2 + 1 + 3 + 7 = 13
# - Level 4: 4 + 6 = 10
# 第 2 大的层和等于 13 。
#
#
# 示例 2：
#
#
#
#
# 输入：root = [1,2,null,3], k = 1
# 输出：3
# 解释：最大的层和是 3 。
#
#
#
#
# 提示：
#
#
# 树中的节点数为 n
# 2 <= n <= 10^5
# 1 <= Node.val <= 10^6
# 1 <= k <= n
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
    def kthLargestLevelSum(self, root: Optional[TreeNode], k: int) -> int:
        if not root:
            return -1

        levels, queue = [], [root]
        while queue:
            levels.append(sum([node.val for node in queue]))
            queue = [child for node in queue for child in (node.left, node.right) if child]

        levels.sort(reverse=True)
        return levels[k - 1] if k <= len(levels) else -1


# @lc code=end

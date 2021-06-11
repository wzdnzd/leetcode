#
# @lc app=leetcode.cn id=222 lang=python3
#
# [222] 完全二叉树的节点个数
#
# https://leetcode-cn.com/problems/count-complete-tree-nodes/description/
#
# algorithms
# Medium (77.56%)
# Likes:    497
# Dislikes: 0
# Total Accepted:    97.5K
# Total Submissions: 125.6K
# Testcase Example:  '[1,2,3,4,5,6]'
#
# 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
#
# 完全二叉树
# 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h
# 层，则该层包含 1~ 2^h 个节点。
#
#
#
# 示例 1：
#
#
# 输入：root = [1,2,3,4,5,6]
# 输出：6
#
#
# 示例 2：
#
#
# 输入：root = []
# 输出：0
#
#
# 示例 3：
#
#
# 输入：root = [1]
# 输出：1
#
#
#
#
# 提示：
#
#
# 树中节点的数目范围是[0, 5 * 10^4]
# 0
# 题目数据保证输入的树是 完全二叉树
#
#
#
#
# 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
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
    def countNodes(self, root: TreeNode) -> int:
        # if not root:
        #     return 0

        # total, queue = 0, [root]
        # while queue:
        #     total += len(queue)
        #     temp = []
        #     for root in queue:
        #         if root.left:
        #             temp.append(root.left)
        #         if root.right:
        #             temp.append(root.right)

        #     queue = temp

        # return total

        if not root:
            return 0

        left, right = root.left, root.right
        left_depth, right_depth = 1, 1
        while left:
            left = left.left
            left_depth += 1
        while right:
            right = right.right
            right_depth += 1

        if left_depth == right_depth:
            return 2 ** left_depth - 1

        return self.countNodes(root.left) + self.countNodes(root.right) + 1

# @lc code=end

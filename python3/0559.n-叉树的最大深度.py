#
# @lc app=leetcode.cn id=559 lang=python3
#
# [559] N 叉树的最大深度
#
# https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/description/
#
# algorithms
# Easy (72.24%)
# Likes:    172
# Dislikes: 0
# Total Accepted:    52.7K
# Total Submissions: 72.9K
# Testcase Example:  '[1,null,3,2,4,null,5,6]'
#
# 给定一个 N 叉树，找到其最大深度。
#
# 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
#
# N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
#
#
#
# 示例 1：
#
#
#
#
# 输入：root = [1,null,3,2,4,null,5,6]
# 输出：3
#
#
# 示例 2：
#
#
#
#
# 输入：root =
# [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
# 输出：5
#
#
#
#
# 提示：
#
#
# 树的深度不会超过 1000 。
# 树的节点数目位于 [0, 10^4] 之间。
#
#
#

# @lc code=start
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""


class Solution:
    def maxDepth(self, root: 'Node') -> int:
        if not root:
            return 0

        depth, queue = 0, [root]
        while queue:
            queue = [x for y in queue for x in y.children]
            depth += 1

        return depth

# @lc code=end

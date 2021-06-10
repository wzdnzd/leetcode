#
# @lc app=leetcode.cn id=515 lang=python3
#
# [515] 在每个树行中找最大值
#
# https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/description/
#
# algorithms
# Medium (63.95%)
# Likes:    131
# Dislikes: 0
# Total Accepted:    30.7K
# Total Submissions: 48.1K
# Testcase Example:  '[1,3,2,5,3,null,9]'
#
# 您需要在二叉树的每一行中找到最大的值。
#
# 示例：
#
#
# 输入:
#
# ⁠         1
# ⁠        / \
# ⁠       3   2
# ⁠      / \   \
# ⁠     5   3   9
#
# 输出: [1, 3, 9]
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
    def largestValues(self, root: TreeNode) -> List[int]:
        if not root:
            return []

        result, queue = [], [root]
        while queue:
            temp = []
            maxv = queue[0].val
            for root in queue:
                maxv = max(maxv, root.val)
                if root.left:
                    temp.append(root.left)
                if root.right:
                    temp.append(root.right)

            result.append(maxv)
            queue = temp

        return result
# @lc code=end

#
# @lc app=leetcode.cn id=653 lang=python3
#
# [653] 两数之和 IV - 输入二叉搜索树
#
# https://leetcode.cn/problems/two-sum-iv-input-is-a-bst/description/
#
# algorithms
# Easy (63.52%)
# Likes:    480
# Dislikes: 0
# Total Accepted:    114.4K
# Total Submissions: 180.1K
# Testcase Example:  '[5,3,6,2,4,null,7]\n9'
#
# 给定一个二叉搜索树 root 和一个目标结果 k，如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 true。
#
#
#
# 示例 1：
#
#
# 输入: root = [5,3,6,2,4,null,7], k = 9
# 输出: true
#
#
# 示例 2：
#
#
# 输入: root = [5,3,6,2,4,null,7], k = 28
# 输出: false
#
#
#
#
# 提示:
#
#
# 二叉树的节点个数的范围是  [1, 10^4].
# -10^4 <= Node.val <= 10^4
# 题目数据保证，输入的 root 是一棵 有效 的二叉搜索树
# -10^5 <= k <= 10^5
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
    def findTarget(self, root: Optional[TreeNode], k: int) -> bool:
        nums, records = [], set()
        self.__inorder(root, nums)
        for num in nums:
            if k - num in records:
                return True

            records.add(num)

        return False

    def __inorder(self, root: Optional[TreeNode], nums: List[int]) -> None:
        if not root:
            return

        self.__inorder(root.left, nums)
        nums.append(root.val)
        self.__inorder(root.right, nums)


# @lc code=end

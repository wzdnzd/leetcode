#
# @lc app=leetcode.cn id=572 lang=python3
#
# [572] 另一棵树的子树
#
# https://leetcode.cn/problems/subtree-of-another-tree/description/
#
# algorithms
# Easy (47.49%)
# Likes:    946
# Dislikes: 0
# Total Accepted:    181.2K
# Total Submissions: 381.5K
# Testcase Example:  '[3,4,5,1,2]\n[4,1,2]'
#
# 
# 
# 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true
# ；否则，返回 false 。
# 
# 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
# 输出：true
# 
# 
# 示例 2：
# 
# 
# 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
# 输出：false
# 
# 
# 
# 
# 提示：
# 
# 
# root 树上的节点数量范围是 [1, 2000]
# subRoot 树上的节点数量范围是 [1, 1000]
# -10^4 
# -10^4 
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
    def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
        def isSameTree(r: Optional[TreeNode], s: Optional[TreeNode]) -> bool:
            if not r and not s:
                return True
            if not r or not s:
                return False
            if r.val != s.val:
                return False
            
            return isSameTree(r.left, s.left) and isSameTree(r.right, s.right)
        
        if not subRoot:
            return True
        if not root:
            return False
                
        return isSameTree(root, subRoot) or self.isSubtree(root.left, subRoot) or self.isSubtree(root.right, subRoot)
# @lc code=end


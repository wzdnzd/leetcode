#
# @lc app=leetcode.cn id=117 lang=python3
#
# [117] 填充每个节点的下一个右侧节点指针 II
#
# https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/description/
#
# algorithms
# Medium (60.01%)
# Likes:    412
# Dislikes: 0
# Total Accepted:    73.1K
# Total Submissions: 121.8K
# Testcase Example:  '[1,2,3,4,5,null,7]'
#
# 给定一个二叉树
#
#
# struct Node {
# ⁠ int val;
# ⁠ Node *left;
# ⁠ Node *right;
# ⁠ Node *next;
# }
#
# 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
#
# 初始状态下，所有 next 指针都被设置为 NULL。
#
#
#
# 进阶：
#
#
# 你只能使用常量级额外空间。
# 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
#
#
#
#
# 示例：
#
#
#
#
# 输入：root = [1,2,3,4,5,null,7]
# 输出：[1,#,2,3,#,4,5,7,#]
# 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next
# 指针连接），'#' 表示每层的末尾。
#
#
#
# 提示：
#
#
# 树中的节点数小于 6000
# -100 
#
#
#
#
#
#
#
#

# @lc code=start
"""
# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""


class Solution:
    # 与[116]题相比，此题的树不是完美二叉树，每层的头节点不一定出现在最左侧
    def connect(self, root: 'Node') -> 'Node':
        node = root

        while root:
            head = Node(-1)
            pre = head
            while root:
                if root.left:
                    pre.next = root.left
                    pre = pre.next
                if root.right:
                    pre.next = root.right
                    pre = pre.next
                root = root.next

            root = head.next

        return node

# @lc code=end

#
# @lc app=leetcode.cn id=116 lang=python3
#
# [116] 填充每个节点的下一个右侧节点指针
#
# https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/description/
#
# algorithms
# Medium (69.66%)
# Likes:    471
# Dislikes: 0
# Total Accepted:    122.6K
# Total Submissions: 176K
# Testcase Example:  '[1,2,3,4,5,6,7]'
#
# 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
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
# 输入：root = [1,2,3,4,5,6,7]
# 输出：[1,#,2,3,#,4,5,6,7,#]
# 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由
# next 指针连接，'#' 标志着每一层的结束。
#
#
#
#
# 提示：
#
#
# 树中节点的数量少于 4096
# -1000
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
    def connect(self, root: 'Node') -> 'Node':
        node = root

        while root and root.left:
            # 取出左孩子节点
            next = root.left
            while root:
                # 左孩子节点的next指向右孩子节点
                root.left.next = root.right
                # 右孩子节点的next指针指向父节点next指针节点的左孩子节点
                root.right.next = root.next and root.next.left
                root = root.next
            root = next

        return node


# @lc code=end

#
# @lc app=leetcode.cn id=143 lang=python3
#
# [143] 重排链表
#
# https://leetcode.cn/problems/reorder-list/description/
#
# algorithms
# Medium (64.92%)
# Likes:    1251
# Dislikes: 0
# Total Accepted:    261.2K
# Total Submissions: 401.4K
# Testcase Example:  '[1,2,3,4]'
#
# 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
#
#
# L0 → L1 → … → Ln - 1 → Ln
#
#
# 请将其重新排列后变为：
#
#
# L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
#
# 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
#
#
#
# 示例 1：
#
#
#
#
# 输入：head = [1,2,3,4]
# 输出：[1,4,2,3]
#
# 示例 2：
#
#
#
#
# 输入：head = [1,2,3,4,5]
# 输出：[1,5,2,4,3]
#
#
#
# 提示：
#
#
# 链表的长度范围为 [1, 5 * 10^4]
# 1 <= node.val <= 1000
#
#
#


# @lc code=start
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reorderList(self, head: Optional[ListNode]) -> None:
        """
        Do not return anything, modify head in-place instead.
        """
        if not head or not head.next:
            return

        middle = self.__get_middle(head)
        node = middle.next
        middle.next = None
        node = self.__reverse(node)
        return self.__merge(head, node)

    def __get_middle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        slow, fast = head, head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next

        return slow

    def __reverse(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode()
        while head:
            node = head.next
            head.next = dummy.next
            dummy.next = head
            head = node

        return dummy.next

    def __merge(
        self, head1: Optional[ListNode], head2: Optional[ListNode]
    ) -> Optional[ListNode]:
        if not head1:
            return head2
        if not head2:
            return head1

        l1, l2 = head1, head2
        while l1 and l2:
            n1, n2 = l1.next, l2.next
            l2.next = n1
            l1.next = l2
            l1, l2 = n1, n2

        return head1


# @lc code=end

#
# @lc app=leetcode.cn id=24 lang=python3
#
# [24] 两两交换链表中的节点
#
# https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/
#
# algorithms
# Medium (56.66%)
# Likes:    208
# Dislikes: 0
# Total Accepted:    22.2K
# Total Submissions: 37.1K
# Testcase Example:  '[1,2,3,4]'
#
# 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
#
# 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
#
#
#
# 示例:
#
# 给定 1->2->3->4, 你应该返回 2->1->4->3.
#
#
#
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:
        # if not head or not head.next:
        #     return head

        # p = head.next
        # head.next = self.swapPairs(head.next.next)
        # p.next = head
        # return p

        if head is None or head.next is None:
            return head

        p = q = ListNode(0)
        while head and head.next:
            node = head.next.next
            tail = head.next
            head.next = node
            tail.next = head

            q.next = tail
            q = head

            head = node

        return p.next

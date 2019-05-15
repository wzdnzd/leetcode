#
# @lc app=leetcode.cn id=203 lang=python3
#
# [203] 移除链表元素
#
# https://leetcode-cn.com/problems/remove-linked-list-elements/description/
#
# algorithms
# Easy (38.74%)
# Total Accepted:    21.5K
# Total Submissions: 53.4K
# Testcase Example:  '[1,2,6,3,4,5,6]\n6'
#
# 删除链表中等于给定值 val 的所有节点。
#
# 示例:
#
# 输入: 1->2->6->3->4->5->6, val = 6
# 输出: 1->2->3->4->5
#
#
#
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def removeElements(self, head: ListNode, val: int) -> ListNode:
        p1 = ListNode(0)
        p2 = p1

        while head:
            if head.val != val:
                node = head.next
                head.next = None
                p2.next = head
                head = node
                p2 = p2.next
            else:
                head = head.next

        return p1.next

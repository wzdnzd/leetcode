#
# @lc app=leetcode.cn id=2 lang=python3
#
# [2] 两数相加
#
# https://leetcode-cn.com/problems/add-two-numbers/description/
#
# algorithms
# Medium (32.20%)
# Likes:    2389
# Dislikes: 0
# Total Accepted:    127.5K
# Total Submissions: 374.1K
# Testcase Example:  '[2,4,3]\n[5,6,4]'
#
# 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
#
# 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
#
# 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
#
# 示例：
#
# 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
# 输出：7 -> 0 -> 8
# 原因：342 + 465 = 807
#
#
#
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        head = ListNode(0)
        pre = head
        carry = 0

        while(l1 or l2 or carry != 0):
            if l1:
                carry += l1.val
                l1 = l1.next
            if l2:
                carry += l2.val
                l2 = l2.next

            pre.next = (ListNode(carry % 10))
            pre = pre.next

            carry //= 10

        return head.next

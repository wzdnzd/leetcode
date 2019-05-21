#
# @lc app=leetcode.cn id=25 lang=python3
#
# [25] k个一组翻转链表
#
# https://leetcode-cn.com/problems/reverse-nodes-in-k-group/description/
#
# algorithms
# Hard (48.41%)
# Likes:    205
# Dislikes: 0
# Total Accepted:    11.3K
# Total Submissions: 21.8K
# Testcase Example:  '[1,2,3,4,5]\n2'
#
# 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
#
# k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
#
# 示例 :
#
# 给定这个链表：1->2->3->4->5
#
# 当 k = 2 时，应当返回: 2->1->4->3->5
#
# 当 k = 3 时，应当返回: 3->2->1->4->5
#
# 说明 :
#
#
# 你的算法只能使用常数的额外空间。
# 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
#
#
#
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def reverseKGroup(self, head: ListNode, k: int) -> ListNode:
        p, count = head, 0
        while p and count != k:
            p = p.next
            count += 1

        if count == k:
            tail = self.reverseKGroup(p, k)
            while count > 0:
                node = head.next
                head.next = tail
                tail = head
                head = node
                count -= 1
            return tail

        return head

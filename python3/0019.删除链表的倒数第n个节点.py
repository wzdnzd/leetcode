#
# @lc app=leetcode.cn id=19 lang=python3
#
# [19] 删除链表的倒数第N个节点
#
# https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/description/
#
# algorithms
# Medium (31.80%)
# Likes:    390
# Dislikes: 0
# Total Accepted:    44.6K
# Total Submissions: 132.9K
# Testcase Example:  '[1,2,3,4,5]\n2'
#
# 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
#
# 示例：
#
# 给定一个链表: 1->2->3->4->5, 和 n = 2.
#
# 当删除了倒数第二个节点后，链表变为 1->2->3->5.
#
#
# 说明：
#
# 给定的 n 保证是有效的。
#
# 进阶：
#
# 你能尝试使用一趟扫描实现吗？
#
#
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        # count, p = 0, head
        # while p:
        #     count += 1
        #     p = p.next

        # if n == count:
        #     return head.next

        # index, p = 1, head
        # while p and index < count - n:
        #     p = p.next
        #     index += 1

        # if p and p.next:
        #     p.next = p.next.next

        # return head

        # 一趟扫描，fast指针先走 n 步
        slow = fast = dummy = ListNode(-1)
        dummy.next = head
        count = 0

        while fast.next:
            if count < n:
                count += 1
                fast = fast.next
            else:
                fast = fast.next
                slow = slow.next
        slow.next = slow.next.next
        return dummy.next

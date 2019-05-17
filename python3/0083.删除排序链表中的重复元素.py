#
# @lc app=leetcode.cn id=83 lang=python3
#
# [83] 删除排序链表中的重复元素
#
# https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/description/
#
# algorithms
# Easy (43.38%)
# Total Accepted:    16.3K
# Total Submissions: 37.5K
# Testcase Example:  '[1,1,2]'
#
# 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
#
# 示例 1:
#
# 输入: 1->1->2
# 输出: 1->2
#
#
# 示例 2:
#
# 输入: 1->1->2->3->3
# 输出: 1->2->3
#
#
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def deleteDuplicates(self, head: ListNode) -> ListNode:
        p = head
        while p is not None:
            if p.next is None:
                break
            if p.val == p.next.val:
                p.next = p.next.next
            else:
                p = p.next

        return head

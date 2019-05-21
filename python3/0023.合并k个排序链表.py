#
# @lc app=leetcode.cn id=23 lang=python3
#
# [23] 合并K个排序链表
#
# https://leetcode-cn.com/problems/merge-k-sorted-lists/description/
#
# algorithms
# Hard (42.88%)
# Likes:    235
# Dislikes: 0
# Total Accepted:    23.8K
# Total Submissions: 51.9K
# Testcase Example:  '[[1,4,5],[1,3,4],[2,6]]'
#
# 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
#
# 示例:
#
# 输入:
# [
# 1->4->5,
# 1->3->4,
# 2->6
# ]
# 输出: 1->1->2->3->4->4->5->6
#
#
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        # if not lists:
        #     return

        # head = tail = ListNode(0)

        # while lists:
        #     index, minVal = 0, float('inf')

        #     for i in range(len(lists)):
        #         node = lists[i]
        #         if node is None:
        #             continue

        #         elif node.val < minVal:
        #             minVal = node.val
        #             index = i

        #     node = lists[index]
        #     if node:
        #         tail.next = lists[index]
        #         tail = tail.next

        #         lists[index] = lists[index].next

        #     lists = [x for x in lists if x is not None]

        # return head.next

        from heapq import heappush, heappop

        nodes, lookup = [], collections.defaultdict(list)

        for head in lists:
            if head:
                heappush(nodes, head.val)
                lookup[head.val].append(head)

        dummy = cur = ListNode(0)

        while nodes:
            minVal = heappop(nodes)
            minNode = lookup[minVal].pop(0)
            cur.next = minNode
            cur = cur.next
            if minNode.next:
                heappush(nodes, minNode.next.val)
                lookup[minNode.next.val].append(minNode.next)

        return dummy.next

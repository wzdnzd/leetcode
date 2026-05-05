/*
 * @lc app=leetcode.cn id=61 lang=golang
 *
 * [61] 旋转链表
 *
 * https://leetcode.cn/problems/rotate-list/description/
 *
 * algorithms
 * Medium (41.47%)
 * Likes:    1192
 * Dislikes: 0
 * Total Accepted:    509.9K
 * Total Submissions: 1.2M
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 *
 * 示例 2：
 *
 *
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
 *
 *
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
package main

func rotateRight(head *ListNode, k int) *ListNode {
	if head == nil || head.Next == nil || k == 0 {
		return head
	}

	count, tail := 1, head
	for tail.Next != nil {
		tail = tail.Next
		count++
	}

	index := k % count
	if index == 0 {
		return head
	}

	current := head
	for i := 0; i < count-index-1; i++ {
		current = current.Next
	}

	node := current.Next
	current.Next = nil
	tail.Next = head

	return node
}

// @lc code=end

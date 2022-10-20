/*
 * @lc app=leetcode.cn id=82 lang=java
 *
 * [82] 删除排序链表中的重复元素 II
 *
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/description/
 *
 * algorithms
 * Medium (53.57%)
 * Likes:    1005
 * Dislikes: 0
 * Total Accepted:    299.9K
 * Total Submissions: 560K
 * Testcase Example:  '[1,2,3,3,4,4,5]'
 *
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // ListNode dummy = new ListNode(0, head), tail = dummy;
        // while (tail.next != null && tail.next.next != null) {
        // if (tail.next.val == tail.next.next.val) {
        // int val = tail.next.val;
        // while (tail.next != null && tail.next.val == val)
        // tail.next = tail.next.next;
        // } else
        // tail = tail.next;
        // }

        // return dummy.next;

        ListNode dummy = new ListNode(0, head), pre = dummy, tail = head;
        while (tail != null && tail.next != null) {
            if (tail.val == tail.next.val) {
                int val = tail.val;
                while (tail != null && tail.val == val)
                    tail = tail.next;
                pre.next = tail;
            } else {
                pre = pre.next;
                tail = tail.next;
            }
        }

        return dummy.next;
    }
}
// @lc code=end

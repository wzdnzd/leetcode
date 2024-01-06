/*
 * @lc app=leetcode.cn id=2807 lang=java
 *
 * [2807] 在链表中插入最大公约数
 *
 * https://leetcode.cn/problems/insert-greatest-common-divisors-in-linked-list/description/
 *
 * algorithms
 * Medium (87.85%)
 * Likes:    39
 * Dislikes: 0
 * Total Accepted:    15.2K
 * Total Submissions: 16.7K
 * Testcase Example:  '[18,6,10,3]'
 *
 * 给你一个链表的头 head ，每个结点包含一个整数值。
 * 
 * 在相邻结点之间，请你插入一个新的结点，结点值为这两个相邻结点值的 最大公约数 。
 * 
 * 请你返回插入之后的链表。
 * 
 * 两个数的 最大公约数 是可以被两个数字整除的最大正整数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：head = [18,6,10,3]
 * 输出：[18,6,6,2,10,1,3]
 * 解释：第一幅图是一开始的链表，第二幅图是插入新结点后的图（蓝色结点为新插入结点）。
 * - 18 和 6 的最大公约数为 6 ，插入第一和第二个结点之间。
 * - 6 和 10 的最大公约数为 2 ，插入第二和第三个结点之间。
 * - 10 和 3 的最大公约数为 1 ，插入第三和第四个结点之间。
 * 所有相邻结点之间都插入完毕，返回链表。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 输入：head = [7]
 * 输出：[7]
 * 解释：第一幅图是一开始的链表，第二幅图是插入新结点后的图（蓝色结点为新插入结点）。
 * 没有相邻结点，所以返回初始链表。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 链表中结点数目在 [1, 5000] 之间。
 * 1 <= Node.val <= 1000
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
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy, current = dummy.next;

        while (current != null && current.next != null) {
            int gcd = gcd(current.val, current.next.val);

            ListNode node = new ListNode(gcd, current.next);
            current.next = node;
            prev = current;
            current = current.next.next;
        }

        return dummy.next;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
// @lc code=end

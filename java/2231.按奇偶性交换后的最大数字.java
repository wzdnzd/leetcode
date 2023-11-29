/*
 * @lc app=leetcode.cn id=2231 lang=java
 *
 * [2231] 按奇偶性交换后的最大数字
 *
 * https://leetcode.cn/problems/largest-number-after-digit-swaps-by-parity/description/
 *
 * algorithms
 * Easy (64.45%)
 * Likes:    29
 * Dislikes: 0
 * Total Accepted:    12.6K
 * Total Submissions: 19.6K
 * Testcase Example:  '1234'
 *
 * 给你一个正整数 num 。你可以交换 num 中 奇偶性 相同的任意两位数字（即，都是奇数或者偶数）。
 * 
 * 返回交换 任意 次之后 num 的 最大 可能值。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：num = 1234
 * 输出：3412
 * 解释：交换数字 3 和数字 1 ，结果得到 3214 。
 * 交换数字 2 和数字 4 ，结果得到 3412 。
 * 注意，可能存在其他交换序列，但是可以证明 3412 是最大可能值。
 * 注意，不能交换数字 4 和数字 1 ，因为它们奇偶性不同。
 * 
 * 
 * 示例 2：
 * 
 * 输入：num = 65875
 * 输出：87655
 * 解释：交换数字 8 和数字 6 ，结果得到 85675 。
 * 交换数字 5 和数字 7 ，结果得到 87655 。
 * 注意，可能存在其他交换序列，但是可以证明 87655 是最大可能值。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= num <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.PriorityQueue;

class Solution {
    public int largestInteger(int num) {
        PriorityQueue<Integer> odds = new PriorityQueue<>((a, b) -> (b - a));
        PriorityQueue<Integer> evens = new PriorityQueue<>((a, b) -> (b - a));

        char[] chars = String.valueOf(num).toCharArray();

        for (char c : chars) {
            int t = c - '0';
            if (t % 2 == 1)
                odds.offer(t);
            else
                evens.offer(t);
        }

        StringBuilder sb = new StringBuilder();

        for (char c : chars) {
            int t = c - '0';
            if (t % 2 == 1)
                sb.append(odds.poll());
            else
                sb.append(evens.poll());
        }

        return Integer.parseInt(sb.toString());
    }
}
// @lc code=end

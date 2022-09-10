import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/*
 * @lc app=leetcode.cn id=402 lang=java
 *
 * [402] 移掉 K 位数字
 *
 * https://leetcode.cn/problems/remove-k-digits/description/
 *
 * algorithms
 * Medium (32.09%)
 * Likes:    862
 * Dislikes: 0
 * Total Accepted:    119.2K
 * Total Submissions: 371.6K
 * Testcase Example:  '"1432219"\n3'
 *
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 * 
 * 
 * 示例 1 ：
 * 
 * 
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 * 
 * 
 * 示例 2 ：
 * 
 * 
 * 输入：num = "10200", k = 1
 * 输出："200"
 * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 
 * 
 * 示例 3 ：
 * 
 * 
 * 输入：num = "10", k = 2
 * 输出："0"
 * 解释：从原数字移除所有的数字，剩余为空就是 0 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * num 仅由若干位数字（0 - 9）组成
 * 除了 0 本身之外，num 不含任何前导零
 * 
 * 
 */

// @lc code=start
class Solution {
    public String removeKdigits(String num, int k) {
        if ("".equals(num))
            return "0";

        int capacity = num.length() - k;
        Deque<Character> queue = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (k > 0 && !queue.isEmpty() && queue.peekLast() > c) {
                queue.pollLast();
                k--;
            }

            queue.addLast(c);
        }

        while (!queue.isEmpty() && queue.peekFirst() == '0') {
            queue.pollFirst();
            capacity--;
        }

        if (capacity <= 0)
            return "0";

        char[] chars = new char[capacity];
        for (int i = 0; i < capacity; i++) {
            chars[i] = queue.pollFirst();
        }

        return new String(chars);
    }
}
// @lc code=end

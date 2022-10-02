/*
 * @lc app=leetcode.cn id=509 lang=java
 *
 * [509] 斐波那契数
 *
 * https://leetcode.cn/problems/fibonacci-number/description/
 *
 * algorithms
 * Easy (66.60%)
 * Likes:    529
 * Dislikes: 0
 * Total Accepted:    458.9K
 * Total Submissions: 689.6K
 * Testcase Example:  '2'
 *
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * 
 * 
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 
 * 
 * 给定 n ，请计算 F(n) 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：n = 4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= n <= 30
 * 
 * 
 */

// @lc code=start
class Solution {
    public int fib(int n) {
        // 解法一: 递归
        // return n < 2 ? n : fib(n - 1) + fib(n - 2);

        // 解法二: 借助数组
        // if (n < 2)
        // return n;

        // int[] array = new int[n + 1];
        // for (int i = 2; i <= n; i++) {
        // array[i] = array[i-2] + array[i-1];
        // }

        // return array[n];

        // 解法三
        if (n < 2)
            return n;
        int p = 0, q = 1, ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = p + q;
            p = q;
            q = ans;
        }

        return ans;
    }
}
// @lc code=end

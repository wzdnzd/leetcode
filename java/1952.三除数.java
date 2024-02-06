/*
 * @lc app=leetcode.cn id=1952 lang=java
 *
 * [1952] 三除数
 *
 * https://leetcode.cn/problems/three-divisors/description/
 *
 * algorithms
 * Easy (55.31%)
 * Likes:    29
 * Dislikes: 0
 * Total Accepted:    16.5K
 * Total Submissions: 29.9K
 * Testcase Example:  '2'
 *
 * 给你一个整数 n 。如果 n 恰好有三个正除数 ，返回 true ；否则，返回 false 。
 * 
 * 如果存在整数 k ，满足 n = k * m ，那么整数 m 就是 n 的一个 除数 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：n = 2
 * 输出：false
 * 解释：2 只有两个除数：1 和 2 。
 * 
 * 示例 2：
 * 
 * 输入：n = 4
 * 输出：true
 * 解释：4 有三个除数：1、2 和 4 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isThree(int n) {
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0)
                count++;
        }

        return count == 3;
    }
}
// @lc code=end

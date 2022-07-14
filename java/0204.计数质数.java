/*
 * @lc app=leetcode.cn id=204 lang=java
 *
 * [204] 计数质数
 *
 * https://leetcode.cn/problems/count-primes/description/
 *
 * algorithms
 * Medium (37.54%)
 * Likes:    918
 * Dislikes: 0
 * Total Accepted:    212.6K
 * Total Submissions: 566.2K
 * Testcase Example:  '10'
 *
 * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 0
 * 输出：0
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：n = 1
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= n <= 5 * 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i))
                ans++;
        }

        return ans;
    }

    private boolean isPrime(int x) {
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0)
                return false;
        }

        return true;
    }
}
// @lc code=end

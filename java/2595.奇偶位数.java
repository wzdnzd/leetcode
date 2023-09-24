/*
 * @lc app=leetcode.cn id=2595 lang=java
 *
 * [2595] 奇偶位数
 *
 * https://leetcode.cn/problems/number-of-even-and-odd-bits/description/
 *
 * algorithms
 * Easy (72.80%)
 * Likes:    7
 * Dislikes: 0
 * Total Accepted:    10.2K
 * Total Submissions: 14K
 * Testcase Example:  '17'
 *
 * 给你一个 正 整数 n 。
 * 
 * 用 even 表示在 n 的二进制形式（下标从 0 开始）中值为 1 的偶数下标的个数。
 * 
 * 用 odd 表示在 n 的二进制形式（下标从 0 开始）中值为 1 的奇数下标的个数。
 * 
 * 返回整数数组 answer ，其中 answer = [even, odd] 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：n = 17
 * 输出：[2,0]
 * 解释：17 的二进制形式是 10001 。 
 * 下标 0 和 下标 4 对应的值为 1 。 
 * 共有 2 个偶数下标，0 个奇数下标。
 * 
 * 
 * 示例 2：
 * 
 * 输入：n = 2
 * 输出：[0,1]
 * 解释：2 的二进制形式是 10 。 
 * 下标 1 对应的值为 1 。 
 * 共有 0 个偶数下标，1 个奇数下标。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] evenOddBit(int n) {
        int[] ans = new int[2];
        for (int i = 0; n > 0; i ^= 1, n >>= 1)
            ans[i] += n & 1;

        return ans;
    }
}
// @lc code=end

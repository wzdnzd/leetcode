/*
 * @lc app=leetcode.cn id=1304 lang=java
 *
 * [1304] 和为零的 N 个不同整数
 *
 * https://leetcode.cn/problems/find-n-unique-integers-sum-up-to-zero/description/
 *
 * algorithms
 * Easy (70.22%)
 * Likes:    79
 * Dislikes: 0
 * Total Accepted:    30K
 * Total Submissions: 42.8K
 * Testcase Example:  '5'
 *
 * 给你一个整数 n，请你返回 任意 一个由 n 个 各不相同 的整数组成的数组，并且这 n 个数相加和为 0 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：n = 5
 * 输出：[-7,-1,1,3,4]
 * 解释：这些数组也是正确的 [-5,-1,1,2,3]，[-3,-1,2,-2,4]。
 * 
 * 
 * 示例 2：
 * 
 * 输入：n = 3
 * 输出：[-1,0,1]
 * 
 * 
 * 示例 3：
 * 
 * 输入：n = 1
 * 输出：[0]
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
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        int index = 0;

        for (int i = 1; i <= n / 2; i++) {
            ans[index++] = -i;
            ans[index++] = i;
        }

        return ans;
    }
}
// @lc code=end

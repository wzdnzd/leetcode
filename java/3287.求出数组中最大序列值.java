/*
 * @lc app=leetcode.cn id=3287 lang=java
 *
 * [3287] 求出数组中最大序列值
 *
 * https://leetcode.cn/problems/find-the-maximum-sequence-value-of-array/description/
 *
 * algorithms
 * Hard (46.14%)
 * Likes:    17
 * Dislikes: 0
 * Total Accepted:    2.2K
 * Total Submissions: 4.1K
 * Testcase Example:  '[2,6,7]\n1'
 *
 * 给你一个整数数组 nums 和一个 正 整数 k 。
 * 
 * 定义长度为 2 * x 的序列 seq 的 值 为：
 * 
 * 
 * (seq[0] OR seq[1] OR ... OR seq[x - 1]) XOR (seq[x] OR seq[x + 1] OR ... OR
 * seq[2 * x - 1]).
 * 
 * 
 * 请你求出 nums 中所有长度为 2 * k 的 子序列 的 最大值 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [2,6,7], k = 1
 * 
 * 输出：5
 * 
 * 解释：
 * 
 * 子序列 [2, 7] 的值最大，为 2 XOR 7 = 5 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [4,2,5,6,7], k = 2
 * 
 * 输出：2
 * 
 * 解释：
 * 
 * 子序列 [4, 5, 6, 7] 的值最大，为 (4 OR 5) XOR (6 OR 7) = 2 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= nums.length <= 400
 * 1 <= nums[i] < 2^7
 * 1 <= k <= nums.length / 2
 * 
 * 
 */

// @lc code=start
class Solution {
    private static final int MAX_NUM = (1 << 7) - 1;

    public int maxValue(int[] nums, int k) {
        int n = nums.length;
        boolean[][][] dpLeft = new boolean[n][k + 1][MAX_NUM + 1];
        for (int i = 0; i < n; i++)
            dpLeft[i][0][0] = true;

        dpLeft[0][1][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int p = 0; p <= MAX_NUM; p++)
                    dpLeft[i][j][p] = dpLeft[i - 1][j][p];
            }

            for (int j = 1; j <= k; j++) {
                for (int p = 0; p <= MAX_NUM; p++) {
                    if (dpLeft[i - 1][j - 1][p])
                        dpLeft[i][j][p | nums[i]] = true;
                }
            }
        }

        boolean[][][] dpRight = new boolean[n][k + 1][MAX_NUM + 1];
        for (int i = 0; i < n; i++)
            dpRight[i][0][0] = true;

        dpRight[n - 1][1][nums[n - 1]] = true;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= k; j++) {
                for (int p = 0; p <= MAX_NUM; p++)
                    dpRight[i][j][p] = dpRight[i + 1][j][p];
            }

            for (int j = 1; j <= k; j++) {
                for (int p = 0; p <= MAX_NUM; p++) {
                    if (dpRight[i + 1][j - 1][p])
                        dpRight[i][j][p | nums[i]] = true;
                }
            }
        }

        int maxXOR = 0;
        int leftMin = k - 1, leftMax = n - k - 1;
        for (int i = leftMin; i <= leftMax; i++) {
            for (int p = 0; p <= MAX_NUM; p++) {
                for (int q = 0; q <= MAX_NUM; q++) {
                    if (dpLeft[i][k][p] && dpRight[i + 1][k][q])
                        maxXOR = Math.max(maxXOR, p ^ q);
                }
            }
        }

        return maxXOR;
    }
}
// @lc code=end

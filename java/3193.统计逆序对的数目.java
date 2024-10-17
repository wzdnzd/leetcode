/*
 * @lc app=leetcode.cn id=3193 lang=java
 *
 * [3193] 统计逆序对的数目
 *
 * https://leetcode.cn/problems/count-the-number-of-inversions/description/
 *
 * algorithms
 * Hard (44.98%)
 * Likes:    17
 * Dislikes: 0
 * Total Accepted:    2.8K
 * Total Submissions: 5.6K
 * Testcase Example:  '3\n[[2,2],[0,0]]'
 *
 * 给你一个整数 n 和一个二维数组 requirements ，其中 requirements[i] = [endi, cnti]
 * 表示这个要求中的末尾下标和 逆序对 的数目。
 * 
 * 整数数组 nums 中一个下标对 (i, j) 如果满足以下条件，那么它们被称为一个 逆序对 ：
 * 
 * 
 * i < j 且 nums[i] > nums[j]
 * 
 * 
 * 请你返回 [0, 1, 2, ..., n - 1] 的 排列 perm 的数目，满足对 所有 的 requirements[i] 都有
 * perm[0..endi] 恰好有 cnti 个逆序对。
 * 
 * 由于答案可能会很大，将它对 10^9 + 7 取余 后返回。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 3, requirements = [[2,2],[0,0]]
 * 
 * 输出：2
 * 
 * 解释：
 * 
 * 两个排列为：
 * 
 * 
 * [2, 0, 1]
 * 
 * 
 * 前缀 [2, 0, 1] 的逆序对为 (0, 1) 和 (0, 2) 。
 * 前缀 [2] 的逆序对数目为 0 个。
 * 
 * 
 * [1, 2, 0]
 * 
 * 前缀 [1, 2, 0] 的逆序对为 (0, 2) 和 (1, 2) 。
 * 前缀 [1] 的逆序对数目为 0 个。
 * 
 * 
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 3, requirements = [[2,2],[1,1],[0,0]]
 * 
 * 输出：1
 * 
 * 解释：
 * 
 * 唯一满足要求的排列是 [2, 0, 1] ：
 * 
 * 
 * 前缀 [2, 0, 1] 的逆序对为 (0, 1) 和 (0, 2) 。
 * 前缀 [2, 0] 的逆序对为 (0, 1) 。
 * 前缀 [2] 的逆序对数目为 0 。
 * 
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：n = 2, requirements = [[0,0],[1,0]]
 * 
 * 输出：1
 * 
 * 解释：
 * 
 * 唯一满足要求的排列为 [0, 1] ：
 * 
 * 
 * 前缀 [0] 的逆序对数目为 0 。
 * 前缀 [0, 1] 的逆序对为 (0, 1) 。
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= n <= 300
 * 1 <= requirements.length <= n
 * requirements[i] = [endi, cnti]
 * 0 <= endi <= n - 1
 * 0 <= cnti <= 400
 * 输入保证至少有一个 i 满足 endi == n - 1 。
 * 输入保证所有的 endi 互不相同。
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    static final int MODE = 1000000007;

    public int numberOfPermutations(int n, int[][] requirements) {
        int[] inversions = new int[n + 1];
        Arrays.fill(inversions, -1);

        for (int[] requirement : requirements) {
            if (requirement[1] > requirement[0] * (requirement[0] + 1) / 2)
                return 0;

            inversions[requirement[0] + 1] = requirement[1];
        }

        int k = inversions[n];
        long[] dp = new long[k + 1];
        dp[0] = 1;

        for (int i = 2; i <= n; i++) {
            long[] dpNew = new long[k + 1];

            for (int j = 0; j <= k; j++) {
                dpNew[j] = (dp[j] + (j >= 1 ? dpNew[j - 1] : 0) - (j >= i ? dp[j - i] : 0)) % MODE;
                if (dpNew[j] < 0)
                    dpNew[j] += MODE;

            }

            if (inversions[i] >= 0) {
                for (int j = 0; j <= k; j++) {
                    if (j != inversions[i])
                        dpNew[j] = 0;

                }
            }

            dp = dpNew;
        }

        return (int) dp[k];
    }
}
// @lc code=end

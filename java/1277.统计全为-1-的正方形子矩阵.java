/*
 * @lc app=leetcode.cn id=1277 lang=java
 *
 * [1277] 统计全为 1 的正方形子矩阵
 *
 * https://leetcode.cn/problems/count-square-submatrices-with-all-ones/description/
 *
 * algorithms
 * Medium (74.94%)
 * Likes:    356
 * Dislikes: 0
 * Total Accepted:    42.6K
 * Total Submissions: 56.5K
 * Testcase Example:  '[[0,1,1,1],[1,1,1,1],[0,1,1,1]]'
 *
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：matrix =
 * [
 * [0,1,1,1],
 * [1,1,1,1],
 * [0,1,1,1]
 * ]
 * 输出：15
 * 解释： 
 * 边长为 1 的正方形有 10 个。
 * 边长为 2 的正方形有 4 个。
 * 边长为 3 的正方形有 1 个。
 * 正方形的总数 = 10 + 4 + 1 = 15.
 * 
 * 
 * 示例 2：
 * 
 * 输入：matrix = 
 * [
 * ⁠ [1,0,1],
 * ⁠ [1,1,0],
 * ⁠ [1,1,0]
 * ]
 * 输出：7
 * 解释：
 * 边长为 1 的正方形有 6 个。 
 * 边长为 2 的正方形有 1 个。
 * 正方形的总数 = 6 + 1 = 7.
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countSquares(int[][] matrix) {
        return matrix.length >= matrix[0].length ? countSquaresHorizontal(matrix) : countSquaresVertical(matrix);
    }

    private int countSquaresHorizontal(int[][] matrix) {
        int count = 0, m = matrix.length, n = matrix[0].length;

        int[] dp = new int[n];
        for (int j = 0; j < n; j++) {
            dp[j] = matrix[0][j];
            count += dp[j];
        }

        for (int i = 1; i < m; i++) {
            int[] dpNew = new int[n];
            dpNew[0] = matrix[i][0];
            count += dpNew[0];

            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dpNew[j] = Math.min(Math.min(dp[j], dpNew[j - 1]), dp[j - 1]) + 1;
                    count += dpNew[j];
                }
            }

            dp = dpNew;
        }

        return count;
    }

    private int countSquaresVertical(int[][] matrix) {
        int count = 0, m = matrix.length, n = matrix[0].length;

        int[] dp = new int[m];
        for (int i = 0; i < m; i++) {
            dp[i] = matrix[i][0];
            count += dp[i];
        }

        for (int j = 1; j < n; j++) {
            int[] dpNew = new int[m];
            dpNew[0] = matrix[0][j];
            count += dpNew[0];

            for (int i = 1; i < m; i++) {
                if (matrix[i][j] == 1) {
                    dpNew[i] = Math.min(Math.min(dp[i], dpNew[i - 1]), dp[i - 1]) + 1;
                    count += dpNew[i];
                }
            }

            dp = dpNew;
        }

        return count;
    }
}
// @lc code=end

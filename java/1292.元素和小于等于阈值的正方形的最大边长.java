/*
 * @lc app=leetcode.cn id=1292 lang=java
 *
 * [1292] 元素和小于等于阈值的正方形的最大边长
 *
 * https://leetcode.cn/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/description/
 *
 * algorithms
 * Medium (53.20%)
 * Likes:    131
 * Dislikes: 0
 * Total Accepted:    16.6K
 * Total Submissions: 30.4K
 * Testcase Example:  '[[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]]\n4'
 *
 * 给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
 * 
 * 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * 输出：2
 * 解释：总和小于或等于 4 的正方形的最大边长为 2，如图所示。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]],
 * threshold = 1
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 300
 * 0 <= mat[i][j] <= 10^4
 * 0 <= threshold <= 10^5^ 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length, maxSide = 0;
        int[][] prefixSums = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixSums[i + 1][j + 1] = prefixSums[i][j + 1] + prefixSums[i + 1][j] - prefixSums[i][j] + mat[i][j];

                int side = maxSide + 1;
                while (i + 1 - side >= 0 && j + 1 - side >= 0 && squareSum(prefixSums, i, j, side) <= threshold) {
                    maxSide = side;
                    side++;
                }
            }
        }

        return maxSide;
    }

    private int squareSum(int[][] prefixSums, int row, int col, int side) {
        return prefixSums[row + 1][col + 1] - prefixSums[row + 1 - side][col + 1] - prefixSums[row + 1][col + 1 - side]
                + prefixSums[row + 1 - side][col + 1 - side];
    }
}
// @lc code=end

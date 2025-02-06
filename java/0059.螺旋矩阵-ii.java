/*
 * @lc app=leetcode.cn id=59 lang=java
 *
 * [59] 螺旋矩阵 II
 *
 * https://leetcode.cn/problems/spiral-matrix-ii/description/
 *
 * algorithms
 * Medium (70.61%)
 * Likes:    1373
 * Dislikes: 0
 * Total Accepted:    497K
 * Total Submissions: 704.5K
 * Testcase Example:  '3'
 *
 * 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 1
 * 输出：[[1]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    private static final int[][] DIRICTORIES = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int i = 0, j = 0, index = 0;

        for (int k = 1; k <= n * n; k++) {
            result[i][j] = k;
            int x = i + DIRICTORIES[index][0];
            int y = j + DIRICTORIES[index][1];

            if (x < 0 || x >= n || y < 0 || y >= n || result[x][y] != 0)
                index = (index + 1) % 4;

            i += DIRICTORIES[index][0];
            j += DIRICTORIES[index][1];
        }

        return result;
    }
}
// @lc code=end

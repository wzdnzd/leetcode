/*
 * @lc app=leetcode.cn id=764 lang=java
 *
 * [764] 最大加号标志
 *
 * https://leetcode.cn/problems/largest-plus-sign/description/
 *
 * algorithms
 * Medium (54.24%)
 * Likes:    217
 * Dislikes: 0
 * Total Accepted:    30K
 * Total Submissions: 55.3K
 * Testcase Example:  '5\n[[4,2]]'
 *
 * 在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。mines[i] = [xi, yi]表示
 * grid[xi][yi] == 0
 * 
 * 返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
 * 
 * 一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为
 * k-1，由 1 组成的臂。注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入: n = 5, mines = [[4, 2]]
 * 输出: 2
 * 解释: 在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入: n = 1, mines = [[0, 0]]
 * 输出: 0
 * 解释: 没有加号标志，返回 0 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 500
 * 1 <= mines.length <= 5000
 * 0 <= xi, yi < n
 * 每一对 (xi, yi) 都 不重复​​​​​​​
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] grid = new int[n][n];
        for (int[] row : grid)
            Arrays.fill(row, 1);

        for (int[] mine : mines)
            grid[mine[0]][mine[1]] = 0;

        int[][] left = new int[n][n];
        int[][] right = new int[n][n];
        int[][] up = new int[n][n];
        int[][] down = new int[n][n];

        for (int i = 0; i < n; i++) {
            left[i][0] = grid[i][0];
            for (int j = 1; j < n; j++)
                left[i][j] = grid[i][j] == 0 ? 0 : left[i][j - 1] + 1;

            right[i][n - 1] = grid[i][n - 1];
            for (int j = n - 2; j >= 0; j--)
                right[i][j] = grid[i][j] == 0 ? 0 : right[i][j + 1] + 1;

            up[0][i] = grid[0][i];
            for (int j = 1; j < n; j++)
                up[j][i] = grid[j][i] == 0 ? 0 : up[j - 1][i] + 1;

            down[n - 1][i] = grid[n - 1][i];
            for (int j = n - 2; j >= 0; j--)
                down[j][i] = grid[j][i] == 0 ? 0 : down[j + 1][i] + 1;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int[] leftRow = left[i];
            int[] rightRow = right[i];
            int[] upRow = up[i];
            int[] downRow = down[i];

            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, Math.min(Math.min(leftRow[j], rightRow[j]), Math.min(upRow[j], downRow[j])));
            }
        }

        return ans;
    }
}
// @lc code=end

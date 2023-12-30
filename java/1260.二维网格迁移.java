/*
 * @lc app=leetcode.cn id=1260 lang=java
 *
 * [1260] 二维网格迁移
 *
 * https://leetcode.cn/problems/shift-2d-grid/description/
 *
 * algorithms
 * Easy (65.37%)
 * Likes:    126
 * Dislikes: 0
 * Total Accepted:    44.8K
 * Total Submissions: 68.6K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]\n1'
 *
 * 给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
 * 
 * 每次「迁移」操作将会引发下述活动：
 * 
 * 
 * 位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
 * 位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
 * 位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
 * 
 * 
 * 请你返回 k 次迁移操作后最终得到的 二维网格。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * 输出：[[9,1,2],[3,4,5],[6,7,8]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * 输出：[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * 输出：[[1,2,3],[4,5,6],[7,8,9]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 
 * 1 
 * -1000 
 * 0 
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] matrix = new int[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = grid[i][j];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                grid[(i + (j + k) / n) % m][(j + k) % n] = matrix[i][j];

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++)
                row.add(grid[i][j]);

            result.add(row);
        }

        return result;
    }
}
// @lc code=end

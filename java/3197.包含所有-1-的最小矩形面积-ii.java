/*
 * @lc app=leetcode.cn id=3197 lang=java
 *
 * [3197] 包含所有 1 的最小矩形面积 II
 *
 * https://leetcode.cn/problems/find-the-minimum-area-to-cover-all-ones-ii/description/
 *
 * algorithms
 * Hard (34.28%)
 * Likes:    17
 * Dislikes: 0
 * Total Accepted:    3.1K
 * Total Submissions: 8.3K
 * Testcase Example:  '[[1,0,1],[1,1,1]]'
 *
 * 给你一个二维 二进制 数组 grid。你需要找到 3 个 不重叠、面积 非零 、边在水平方向和竖直方向上的矩形，并且满足 grid 中所有的 1
 * 都在这些矩形的内部。
 * 
 * 返回这些矩形面积之和的 最小 可能值。
 * 
 * 注意，这些矩形可以相接。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入： grid = [[1,0,1],[1,1,1]]
 * 
 * 输出： 5
 * 
 * 解释：
 * 
 * 
 * 
 * 
 * 位于 (0, 0) 和 (1, 0) 的 1 被一个面积为 2 的矩形覆盖。
 * 位于 (0, 2) 和 (1, 2) 的 1 被一个面积为 2 的矩形覆盖。
 * 位于 (1, 1) 的 1 被一个面积为 1 的矩形覆盖。
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入： grid = [[1,0,1,0],[0,1,0,1]]
 * 
 * 输出： 5
 * 
 * 解释：
 * 
 * 
 * 
 * 
 * 位于 (0, 0) 和 (0, 2) 的 1 被一个面积为 3 的矩形覆盖。
 * 位于 (1, 1) 的 1 被一个面积为 1 的矩形覆盖。
 * 位于 (1, 3) 的 1 被一个面积为 1 的矩形覆盖。
 * 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= grid.length, grid[i].length <= 30
 * grid[i][j] 是 0 或 1。
 * 输入保证 grid 中至少有三个 1 。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumSum(int[][] grid) {
        return Math.min(solve(grid), solve(rotate(grid)));
    }

    private int solve(int[][] a) {
        int m = a.length, n = a[0].length, result = Integer.MAX_VALUE;

        if (m >= 3) {
            for (int i = 1; i < m; i++) {
                for (int j = i + 1; j < m; j++) {
                    int area = minimumArea(a, 0, i, 0, n);
                    area += minimumArea(a, i, j, 0, n);
                    area += minimumArea(a, j, m, 0, n);
                    result = Math.min(result, area);
                }
            }
        }

        if (m >= 2 && n >= 2) {
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    int area = minimumArea(a, 0, i, 0, n);
                    area += minimumArea(a, i, m, 0, j);
                    area += minimumArea(a, i, m, j, n);
                    result = Math.min(result, area);

                    area = minimumArea(a, 0, i, 0, j);
                    area += minimumArea(a, 0, i, j, n);
                    area += minimumArea(a, i, m, 0, n);
                    result = Math.min(result, area);
                }
            }
        }

        return result;
    }

    private int minimumArea(int[][] matrix, int startRow, int endRow, int startCol, int endCol) {
        int left = endCol, right = 0, top = endRow, bottom = 0;

        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                if (matrix[i][j] == 1) {
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                    top = Math.min(top, i);
                    bottom = i;
                }
            }
        }

        return (right - left + 1) * (bottom - top + 1);
    }

    private int[][] rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                result[j][m - 1 - i] = matrix[i][j];
        }

        return result;
    }
}
// @lc code=end

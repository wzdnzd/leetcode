/*
 * @lc app=leetcode.cn id=3446 lang=java
 *
 * [3446] 按对角线进行矩阵排序
 *
 * https://leetcode.cn/problems/sort-matrix-by-diagonals/description/
 *
 * algorithms
 * Medium (79.38%)
 * Likes:    13
 * Dislikes: 0
 * Total Accepted:    5.2K
 * Total Submissions: 6.3K
 * Testcase Example:  '[[1,7,3],[9,8,2],[4,5,6]]'
 *
 * 给你一个大小为 n x n 的整数方阵 grid。返回一个经过如下调整的矩阵：
 * 
 * 
 * 左下角三角形（包括中间对角线）的对角线按 非递增顺序 排序。
 * 右上角三角形 的对角线按 非递减顺序 排序。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入： grid = [[1,7,3],[9,8,2],[4,5,6]]
 * 
 * 输出： [[8,2,3],[9,6,7],[4,5,1]]
 * 
 * 解释：
 * 
 * 
 * 
 * 标有黑色箭头的对角线（左下角三角形）应按非递增顺序排序：
 * 
 * 
 * [1, 8, 6] 变为 [8, 6, 1]。
 * [9, 5] 和 [4] 保持不变。
 * 
 * 
 * 标有蓝色箭头的对角线（右上角三角形）应按非递减顺序排序：
 * 
 * 
 * [7, 2] 变为 [2, 7]。
 * [3] 保持不变。
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入： grid = [[0,1],[1,2]]
 * 
 * 输出： [[2,1],[1,0]]
 * 
 * 解释：
 * 
 * 
 * 
 * 标有黑色箭头的对角线必须按非递增顺序排序，因此 [0, 2] 变为 [2, 0]。其他对角线已经符合要求。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入： grid = [[1]]
 * 
 * 输出： [[1]]
 * 
 * 解释：
 * 
 * 只有一个元素的对角线已经符合要求，因此无需修改。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * grid.length == grid[i].length == n
 * 1 <= n <= 10
 * -10^5 <= grid[i][j] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid[0].length;

        for (int i = 0; i < n; i++)
            sortDiagonal(grid, i, 0, true);

        for (int j = 1; j < n; j++)
            sortDiagonal(grid, 0, j, false);

        return grid;
    }

    private void sortDiagonal(int[][] grid, int row, int col, boolean reverse) {
        int n = grid[0].length;
        int length = Math.min(n - row, n - col);

        for (int i = 1; i < length; i++) {
            int num = grid[row + i][col + i];
            int insertIndex = i;

            for (int j = i - 1; j >= 0 && shouldChangeOrder(grid[row + j][col + j], num, reverse); j--) {
                grid[row + j + 1][col + j + 1] = grid[row + j][col + j];
                insertIndex = j;
            }

            if (insertIndex != i)
                grid[row + insertIndex][col + insertIndex] = num;
        }
    }

    private boolean shouldChangeOrder(int prev, int curr, boolean reverse) {
        return reverse ? prev < curr : prev > curr;
    }
}
// @lc code=end

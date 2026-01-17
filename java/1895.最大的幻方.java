/*
 * @lc app=leetcode.cn id=1895 lang=java
 *
 * [1895] 最大的幻方
 *
 * https://leetcode.cn/problems/largest-magic-square/description/
 *
 * algorithms
 * Medium (59.54%)
 * Likes:    31
 * Dislikes: 0
 * Total Accepted:    6K
 * Total Submissions: 9.6K
 * Testcase Example:  '[[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]'
 *
 * 一个 k x k 的 幻方 指的是一个 k x k 填满整数的方格阵，且每一行、每一列以及两条对角线的和 全部相等 。幻方中的整数 不需要互不相同
 * 。显然，每个 1 x 1 的方格都是一个幻方。
 * 
 * 给你一个 m x n 的整数矩阵 grid ，请你返回矩阵中 最大幻方 的 尺寸 （即边长 k）。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
 * 输出：3
 * 解释：最大幻方尺寸为 3 。
 * 每一行，每一列以及两条对角线的和都等于 12 。
 * - 每一行的和：5+1+6 = 5+4+3 = 2+7+3 = 12
 * - 每一列的和：5+5+2 = 1+4+7 = 6+3+3 = 12
 * - 对角线的和：5+4+3 = 6+4+2 = 12
 * 
 * 
 * 示例 2：
 * 
 * 输入：grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
 * 输出：2
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] rowPrefixSums = new int[m][n + 1];
        int[][] colPrefixSums = new int[m + 1][n];
        int[][][] diagonalPrefixSums = new int[m + 1][n + 1][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowPrefixSums[i][j + 1] = rowPrefixSums[i][j] + grid[i][j];
                colPrefixSums[i + 1][j] = colPrefixSums[i][j] + grid[i][j];
                diagonalPrefixSums[i + 1][j + 1][0] = diagonalPrefixSums[i][j][0] + grid[i][j];
                diagonalPrefixSums[i + 1][j][1] = diagonalPrefixSums[i][j + 1][1] + grid[i][j];
            }
        }

        for (int k = Math.min(m, n); k > 1; k--) {
            if (existMagicSquare(rowPrefixSums, colPrefixSums, diagonalPrefixSums, m, n, k))
                return k;
        }

        return 1;
    }

    private boolean existMagicSquare(int[][] rowPrefixSums, int[][] colPrefixSums, int[][][] diagonalPrefixSums, int m,
            int n, int k) {
        int maxRow = m - k, maxCol = n - k;
        for (int i = 0; i <= maxRow; i++) {
            for (int j = 0; j <= maxCol; j++) {
                if (isMagicSquare(rowPrefixSums, colPrefixSums, diagonalPrefixSums, i, j, k))
                    return true;
            }
        }

        return false;
    }

    private boolean isMagicSquare(int[][] rowPrefixSums, int[][] colPrefixSums, int[][][] diagonalPrefixSums,
            int startRow, int startCol, int k) {
        int expectedSum = rowPrefixSums[startRow][startCol + k] - rowPrefixSums[startRow][startCol];

        for (int i = 1; i < k; i++) {
            int rowSum = rowPrefixSums[startRow + i][startCol + k] - rowPrefixSums[startRow + i][startCol];
            if (rowSum != expectedSum)
                return false;
        }

        for (int j = 0; j < k; j++) {
            int colSum = colPrefixSums[startRow + k][startCol + j] - colPrefixSums[startRow][startCol + j];
            if (colSum != expectedSum)
                return false;
        }

        return diagonalPrefixSums[startRow + k][startCol + k][0]
                - diagonalPrefixSums[startRow][startCol][0] == expectedSum
                && diagonalPrefixSums[startRow + k][startCol][1]
                        - diagonalPrefixSums[startRow][startCol + k][1] == expectedSum;
    }
}
// @lc code=end

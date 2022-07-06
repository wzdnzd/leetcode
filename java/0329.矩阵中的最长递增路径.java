/*
 * @lc app=leetcode.cn id=329 lang=java
 *
 * [329] 矩阵中的最长递增路径
 *
 * https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/description/
 *
 * algorithms
 * Hard (50.45%)
 * Likes:    670
 * Dislikes: 0
 * Total Accepted:    77.8K
 * Total Submissions: 153.8K
 * Testcase Example:  '[[9,9,4],[6,6,8],[2,1,1]]'
 *
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * 
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * 输出：4 
 * 解释：最长递增路径为 [1, 2, 6, 9]。
 * 
 * 示例 2：
 * 
 * 
 * 输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * 输出：4 
 * 解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：matrix = [[1]]
 * 输出：1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == matrix.length
 * n == matrix[i].length
 * 1 
 * 0 
 * 
 * 
 */

// @lc code=start
class Solution {
    private static final int[][] DIRECTIONS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    private int rows, cols;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        rows = matrix.length;
        cols = matrix[0].length;
        int[][] visited = new int[rows][cols];
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ans = Math.max(ans, dfs(matrix, visited, i, j));
            }
        }

        return ans;
    }

    private int dfs(int[][] matrix, int[][] visited, int i, int j) {
        if (visited[i][j] != 0)
            return visited[i][j];
        visited[i][j] += 1;

        for (int[] direction : DIRECTIONS) {
            int dx = i + direction[0];
            int dy = j + direction[1];

            if (dx >= 0 && dx < rows && dy >= 0 && dy < cols && matrix[i][j] < matrix[dx][dy])
                visited[i][j] = Math.max(visited[i][j], dfs(matrix, visited, dx, dy) + 1);
        }

        return visited[i][j];
    }
}
// @lc code=end

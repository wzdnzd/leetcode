/*
 * @lc app=leetcode.cn id=1905 lang=java
 *
 * [1905] 统计子岛屿
 *
 * https://leetcode.cn/problems/count-sub-islands/description/
 *
 * algorithms
 * Medium (66.50%)
 * Likes:    78
 * Dislikes: 0
 * Total Accepted:    19.3K
 * Total Submissions: 28.9K
 * Testcase Example:  '[[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]]\n' +
  '[[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]'
 *
 * 给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。一个 岛屿 是由 四个方向
 * （水平或者竖直）上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。
 * 
 * 如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，也就是说 grid2 中该岛屿的每一个格子都被 grid1
 * 中同一个岛屿完全包含，那么我们称 grid2 中的这个岛屿为 子岛屿 。
 * 
 * 请你返回 grid2 中 子岛屿 的 数目 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]],
 * grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
 * 输出：3
 * 解释：如上图所示，左边为 grid1 ，右边为 grid2 。
 * grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
 * 
 * 
 * 示例 2：
 * 
 * 输入：grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]],
 * grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
 * 输出：2 
 * 解释：如上图所示，左边为 grid1 ，右边为 grid2 。
 * grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == grid1.length == grid2.length
 * n == grid1[i].length == grid2[i].length
 * 1 <= m, n <= 500
 * grid1[i][j] 和 grid2[i][j] 都要么是 0 要么是 1 。
 * 
 * 
 */

// @lc code=start
class Solution {
    private int[][] directions = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int ans = 0;
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[0].length; j++) {
                if (grid2[i][j] == 1 && dfs(grid1, grid2, i, j))
                    ans++;
            }
        }

        return ans;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, int i, int j) {
        int m = grid2.length, n = grid2[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid2[i][j] != 1)
            return true;

        grid2[i][j] = 2;
        boolean match = grid1[i][j] == 1;
        // 短路机制导致结果可能不正确
        // match = match && dfs(grid1, grid2, i - 1, j) && dfs(grid1, grid2, i + 1, j)
        // && dfs(grid1, grid2, i, j - 1) && dfs(grid1, grid2, i, j + 1);

        for (int[] direction : directions)
            match = dfs(grid1, grid2, i + direction[0], j + direction[1]) && match;

        return match;
    }
}
// @lc code=end

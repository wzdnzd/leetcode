/*
 * @lc app=leetcode.cn id=741 lang=java
 *
 * [741] 摘樱桃
 *
 * https://leetcode.cn/problems/cherry-pickup/description/
 *
 * algorithms
 * Hard (50.73%)
 * Likes:    402
 * Dislikes: 0
 * Total Accepted:    22.1K
 * Total Submissions: 43K
 * Testcase Example:  '[[0,1,-1],[1,0,-1],[1,1,1]]'
 *
 * 给你一个 n x n 的网格 grid ，代表一块樱桃地，每个格子由以下三种数字的一种来表示：
 * 
 * 
 * 0 表示这个格子是空的，所以你可以穿过它。
 * 1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。
 * -1 表示这个格子里有荆棘，挡着你的路。
 * 
 * 
 * 请你统计并返回：在遵守下列规则的情况下，能摘到的最多樱桃数：
 * 
 * 
 * 从位置 (0, 0) 出发，最后到达 (n - 1, n - 1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为 0 或者 1
 * 的格子）；
 * 当到达 (n - 1, n - 1) 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子；
 * 当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为 0 ）；
 * 如果在 (0, 0) 和 (n - 1, n - 1) 之间不存在一条可经过的路径，则无法摘到任何一个樱桃。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：grid = [[0,1,-1],[1,0,-1],[1,1,1]]
 * 输出：5
 * 解释：玩家从 (0, 0) 出发：向下、向下、向右、向右移动至 (2, 2) 。
 * 在这一次行程中捡到 4 个樱桃，矩阵变成 [[0,1,-1],[0,0,-1],[0,0,0]] 。
 * 然后，玩家向左、向上、向上、向左返回起点，再捡到 1 个樱桃。
 * 总共捡到 5 个樱桃，这是最大可能值。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：grid = [[1,1,-1],[1,-1,1],[-1,1,1]]
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * grid[i][j] 为 -1、0 或 1
 * grid[0][0] != -1
 * grid[n - 1][n - 1] != -1
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] memos = new int[2 * n - 1][n][n];

        // init memos
        for (int[][] matrix : memos) {
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }
        }

        int result = dfs(grid, memos, 2 * n - 2, n - 1, n - 1);
        return result < 0 ? 0 : result;
    }

    private int dfs(int[][] grid, int[][][] memos, int t, int j, int k) {
        if (j < 0 || k < 0 || t < j || t < k || grid[t - j][j] < 0 || grid[t - k][k] < 0)
            return Integer.MIN_VALUE;

        if (t == 0)
            return grid[0][0];

        if (memos[t][j][k] != -1)
            return memos[t][j][k];

        int result = Math
                .max(Math.max(dfs(grid, memos, t - 1, j, k), dfs(grid, memos, t - 1, j, k - 1)),
                        Math.max(dfs(grid, memos, t - 1, j - 1, k), dfs(grid, memos, t - 1, j - 1, k - 1)))
                + grid[t - j][j] + (k == j ? 0 : grid[t - k][k]);

        memos[t][j][k] = result;
        return result;
    }
}
// @lc code=end

/*
 * @lc app=leetcode.cn id=2257 lang=java
 *
 * [2257] 统计网格图中没有被保卫的格子数
 *
 * https://leetcode.cn/problems/count-unguarded-cells-in-the-grid/description/
 *
 * algorithms
 * Medium (53.00%)
 * Likes:    27
 * Dislikes: 0
 * Total Accepted:    6.7K
 * Total Submissions: 12.6K
 * Testcase Example:  '4\n6\n[[0,0],[1,1],[2,3]]\n[[0,1],[2,2],[1,4]]'
 *
 * 给你两个整数 m 和 n 表示一个下标从 0 开始的 m x n 网格图。同时给你两个二维整数数组 guards 和 walls ，其中
 * guards[i] = [rowi, coli] 且 walls[j] = [rowj, colj] ，分别表示第 i 个警卫和第 j
 * 座墙所在的位置。
 * 
 * 一个警卫能看到 4 个坐标轴方向（即东、南、西、北）的 所有 格子，除非他们被一座墙或者另外一个警卫 挡住 了视线。如果一个格子能被 至少
 * 一个警卫看到，那么我们说这个格子被 保卫 了。
 * 
 * 请你返回空格子中，有多少个格子是 没被保卫 的。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
 * 输出：7
 * 解释：上图中，被保卫和没有被保卫的格子分别用红色和绿色表示。
 * 总共有 7 个没有被保卫的格子，所以我们返回 7 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 输入：m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
 * 输出：4
 * 解释：上图中，没有被保卫的格子用绿色表示。
 * 总共有 4 个没有被保卫的格子，所以我们返回 4 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= m, n <= 10^5
 * 2 <= m * n <= 10^5
 * 1 <= guards.length, walls.length <= 5 * 10^4
 * 2 <= guards.length + walls.length <= m * n
 * guards[i].length == walls[j].length == 2
 * 0 <= rowi, rowj < m
 * 0 <= coli, colj < n
 * guards 和 walls 中所有位置 互不相同 。
 * 
 * 
 */

// @lc code=start
class Solution {
    private static final int OBSTRUCTION = -1, UNGUARDED = 0, GUARDED = 1;
    private static int[][] directories = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        for (int[] guard : guards)
            grid[guard[0]][guard[1]] = OBSTRUCTION;

        for (int[] wall : walls)
            grid[wall[0]][wall[1]] = OBSTRUCTION;

        int unguardedCells = m * n - guards.length - walls.length;
        for (int[] guard : guards) {
            for (int[] directory : directories) {
                int row = guard[0] + directory[0], col = guard[1] + directory[1];
                while (row >= 0 && row < m && col >= 0 && col < n && grid[row][col] != OBSTRUCTION) {
                    if (grid[row][col] == UNGUARDED) {
                        grid[row][col] = GUARDED;
                        unguardedCells--;
                    }

                    row += directory[0];
                    col += directory[1];
                }
            }
        }

        return unguardedCells;
    }
}
// @lc code=end

/*
 * @lc app=leetcode.cn id=994 lang=java
 *
 * [994] 腐烂的橘子
 *
 * https://leetcode.cn/problems/rotting-oranges/description/
 *
 * algorithms
 * Medium (51.28%)
 * Likes:    846
 * Dislikes: 0
 * Total Accepted:    168.6K
 * Total Submissions: 327.7K
 * Testcase Example:  '[[2,1,1],[1,1,0],[0,1,1]]'
 *
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * 
 * 
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 
 * 
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * 
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：grid = [[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] 仅为 0、1 或 2
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int[][] DIRECTORIES = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public int orangesRotting(int[][] grid) {
        int freshCount = 0;
        int m = grid.length, n = grid[0].length;
        List<int[]> putrid = new ArrayList<>();

        // 统计一开始就腐烂的橘子以及新鲜橘子数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    freshCount++;
                else if (grid[i][j] == 2)
                    putrid.add(new int[] { i, j });
            }
        }

        int result = -1;
        while (!putrid.isEmpty()) {
            result++;

            List<int[]> oldPutrid = putrid;
            putrid = new ArrayList<>();
            for (int[] position : oldPutrid) {
                for (int[] directory : DIRECTORIES) {
                    int x = position[0] + directory[0];
                    int y = position[1] + directory[1];

                    if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1)
                        continue;

                    grid[x][y] = 2;
                    freshCount--;
                    putrid.add(new int[] { x, y });
                }
            }
        }

        return freshCount > 0 ? -1 : Math.max(0, result);
    }
}
// @lc code=end

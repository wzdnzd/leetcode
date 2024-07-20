/*
 * @lc app=leetcode.cn id=2850 lang=java
 *
 * [2850] 将石头分散到网格图的最少移动次数
 *
 * https://leetcode.cn/problems/minimum-moves-to-spread-stones-over-grid/description/
 *
 * algorithms
 * Medium (43.70%)
 * Likes:    38
 * Dislikes: 0
 * Total Accepted:    6.4K
 * Total Submissions: 13.4K
 * Testcase Example:  '[[1,1,0],[1,1,1],[1,2,1]]'
 *
 * 给你一个大小为 3 * 3 ，下标从 0 开始的二维整数矩阵 grid ，分别表示每一个格子里石头的数目。网格图中总共恰好有 9
 * 个石头，一个格子里可能会有 多个 石头。
 * 
 * 每一次操作中，你可以将一个石头从它当前所在格子移动到一个至少有一条公共边的相邻格子。
 * 
 * 请你返回每个格子恰好有一个石头的 最少移动次数 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：grid = [[1,1,0],[1,1,1],[1,2,1]]
 * 输出：3
 * 解释：让每个格子都有一个石头的一个操作序列为：
 * 1 - 将一个石头从格子 (2,1) 移动到 (2,2) 。
 * 2 - 将一个石头从格子 (2,2) 移动到 (1,2) 。
 * 3 - 将一个石头从格子 (1,2) 移动到 (0,2) 。
 * 总共需要 3 次操作让每个格子都有一个石头。
 * 让每个格子都有一个石头的最少操作次数为 3 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：grid = [[1,3,0],[1,0,0],[1,0,3]]
 * 输出：4
 * 解释：让每个格子都有一个石头的一个操作序列为：
 * 1 - 将一个石头从格子 (0,1) 移动到 (0,2) 。
 * 2 - 将一个石头从格子 (0,1) 移动到 (1,1) 。
 * 3 - 将一个石头从格子 (2,2) 移动到 (1,2) 。
 * 4 - 将一个石头从格子 (2,2) 移动到 (2,1) 。
 * 总共需要 4 次操作让每个格子都有一个石头。
 * 让每个格子都有一个石头的最少操作次数为 4 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * grid.length == grid[i].length == 3
 * 0 <= grid[i][j] <= 9
 * grid 中元素之和为 9 。
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minimumMoves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<int[]> from = new ArrayList<>();
        List<int[]> to = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 1)
                    from.add(new int[] { i, j });
                else if (grid[i][j] == 0)
                    to.add(new int[] { i, j });
            }
        }

        return dfs(grid, from, to, 0, 0);
    }

    private int dfs(int[][] grid, List<int[]> fromList, List<int[]> toList, int index, int cost) {
        if (index == toList.size())
            return cost;

        int result = Integer.MAX_VALUE;
        int[] to = toList.get(index);

        for (int[] from : fromList) {
            int i = from[0], j = from[1];

            if (grid[i][j] > 1) {
                grid[i][j]--;

                int count = Math.abs(i - to[0]) + Math.abs(j - to[1]);
                result = Math.min(result, dfs(grid, fromList, toList, index + 1, cost + count));

                grid[i][j]++;
            }
        }

        return result;
    }
}
// @lc code=end

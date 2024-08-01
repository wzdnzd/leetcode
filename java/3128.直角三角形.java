/*
 * @lc app=leetcode.cn id=3128 lang=java
 *
 * [3128] 直角三角形
 *
 * https://leetcode.cn/problems/right-triangles/description/
 *
 * algorithms
 * Medium (55.77%)
 * Likes:    5
 * Dislikes: 0
 * Total Accepted:    4.4K
 * Total Submissions: 7K
 * Testcase Example:  '[[0,1,0],[0,1,1],[0,1,0]]'
 *
 * 给你一个二维 boolean 矩阵 grid 。
 * 
 * 请你返回使用 grid 中的 3 个元素可以构建的 直角三角形 数目，且满足 3 个元素值 都 为 1 。
 * 
 * 注意：
 * 
 * 
 * 如果 grid 中 3 个元素满足：一个元素与另一个元素在 同一行，同时与第三个元素在 同一列 ，那么这 3 个元素称为一个 直角三角形 。这 3
 * 个元素互相之间不需要相邻。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 
 * 0
 * 1
 * 0
 * 
 * 
 * 0
 * 1
 * 1
 * 
 * 
 * 0
 * 1
 * 0
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 0
 * 1
 * 0
 * 
 * 
 * 0
 * 1
 * 1
 * 
 * 
 * 0
 * 1
 * 0
 * 
 * 
 * 
 * 
 * 
 * 
 * 输入：grid = [[0,1,0],[0,1,1],[0,1,0]]
 * 
 * 输出：2
 * 
 * 解释：
 * 
 * 有 2 个直角三角形。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 
 * 1
 * 0
 * 0
 * 0
 * 
 * 
 * 0
 * 1
 * 0
 * 1
 * 
 * 
 * 1
 * 0
 * 0
 * 0
 * 
 * 
 * 
 * 
 * 
 * 
 * 输入：grid = [[1,0,0,0],[0,1,0,1],[1,0,0,0]]
 * 
 * 输出：0
 * 
 * 解释：
 * 
 * 没有直角三角形。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 
 * 
 * 
 * 1
 * 0
 * 1
 * 
 * 
 * 1
 * 0
 * 0
 * 
 * 
 * 1
 * 0
 * 0
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 1
 * 0
 * 1
 * 
 * 
 * 1
 * 0
 * 0
 * 
 * 
 * 1
 * 0
 * 0
 * 
 * 
 * 
 * 
 * 
 * 
 * 输入：grid = [[1,0,1],[1,0,0],[1,0,0]]
 * 
 * 输出：2
 * 
 * 解释：
 * 
 * 有两个直角三角形。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= grid.length <= 1000
 * 1 <= grid[i].length <= 1000
 * 0 <= grid[i][j] <= 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public long numberOfRightTriangles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rows = new int[m], cols = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rows[i] += grid[i][j];
                cols[j] += grid[i][j];
            }
        }

        long count = 0L;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    count += (rows[i] - 1) * (cols[j] - 1);
            }
        }

        return count;
    }
}
// @lc code=end

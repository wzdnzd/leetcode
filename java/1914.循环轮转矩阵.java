/*
 * @lc app=leetcode.cn id=1914 lang=java
 *
 * [1914] 循环轮转矩阵
 *
 * https://leetcode.cn/problems/cyclically-rotating-a-grid/description/
 *
 * algorithms
 * Medium (48.91%)
 * Likes:    32
 * Dislikes: 0
 * Total Accepted:    6.1K
 * Total Submissions: 11.9K
 * Testcase Example:  '[[40,10],[30,20]]\n1'
 *
 * 给你一个大小为 m x n 的整数矩阵 grid​​​ ，其中 m 和 n 都是 偶数 ；另给你一个整数 k 。
 * 
 * 矩阵由若干层组成，如下图所示，每种颜色代表一层：
 * 
 * 
 * 
 * 矩阵的循环轮转是通过分别循环轮转矩阵中的每一层完成的。在对某一层进行一次循环旋转操作时，层中的每一个元素将会取代其 逆时针
 * 方向的相邻元素。轮转示例如下：
 * 
 * 返回执行 k 次循环轮转操作后的矩阵。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：grid = [[40,10],[30,20]], k = 1
 * 输出：[[10,20],[40,30]]
 * 解释：上图展示了矩阵在执行循环轮转操作时每一步的状态。
 * 
 * 示例 2：
 * ⁠ 
 * 
 * 输入：grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
 * 输出：[[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
 * 解释：上图展示了矩阵在执行循环轮转操作时每一步的状态。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 50
 * m 和 n 都是 偶数
 * 1 <= grid[i][j] <=^ 5000
 * 1 <= k <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int layers = Math.min(m, n) / 2;

        for (int layerIndex = 0; layerIndex < layers; layerIndex++) {
            int total = (m + n - 4 * layerIndex - 2) * 2;
            int verticalCount = m - 2 * layerIndex - 1, horizontalCount = n - 2 * layerIndex - 1;
            int shift = k % total;

            List<Integer> order = new ArrayList<>();
            for (int i = 0; i < total; i++) {
                int[] position = getPosition(layerIndex, layerIndex, verticalCount, horizontalCount, i);
                int row = position[0], col = position[1];
                order.add(grid[row][col]);
            }

            for (int i = 0; i < total; i++) {
                int[] position = getPosition(layerIndex, layerIndex, verticalCount, horizontalCount,
                        (i + shift) % total);
                int row = position[0], col = position[1];
                grid[row][col] = order.get(i);
            }
        }

        return grid;
    }

    private int[] getPosition(int startRow, int startCol, int verticalCount, int horizontalCount, int index) {
        if (index < verticalCount)
            return new int[] { startRow + index, startCol };
        else if (index < verticalCount + horizontalCount)
            return new int[] { startRow + verticalCount, startCol + index - verticalCount };
        else if (index < verticalCount * 2 + horizontalCount)
            return new int[] { startRow + verticalCount * 2 + horizontalCount - index, startCol + horizontalCount };
        else
            return new int[] { startRow, startCol + verticalCount * 2 + horizontalCount * 2 - index };
    }
}
// @lc code=end

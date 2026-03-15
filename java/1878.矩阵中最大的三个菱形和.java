/*
 * @lc app=leetcode.cn id=1878 lang=java
 *
 * [1878] 矩阵中最大的三个菱形和
 *
 * https://leetcode.cn/problems/get-biggest-three-rhombus-sums-in-a-grid/description/
 *
 * algorithms
 * Medium (49.55%)
 * Likes:    33
 * Dislikes: 0
 * Total Accepted:    4.7K
 * Total Submissions: 9.2K
 * Testcase Example:  '[[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]'
 *
 * 给你一个 m x n 的整数矩阵 grid 。
 * 
 * 菱形和 指的是 grid 中一个正菱形 边界
 * 上的元素之和。本题中的菱形必须为正方形旋转45度，且四个角都在一个格子当中。下图是四个可行的菱形，每个菱形和应该包含的格子都用了相应颜色标注在图中。
 * 
 * 
 * 
 * 注意，菱形可以是一个面积为 0 的区域，如上图中右下角的紫色菱形所示。
 * 
 * 请你按照 降序 返回 grid 中三个最大的 互不相同的菱形和 。如果不同的和少于三个，则将它们全部返回。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：grid =
 * [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
 * 输出：[228,216,211]
 * 解释：最大的三个菱形和如上图所示。
 * - 蓝色：20 + 3 + 200 + 5 = 228
 * - 红色：200 + 2 + 10 + 4 = 216
 * - 绿色：5 + 200 + 4 + 2 = 211
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[20,9,8]
 * 解释：最大的三个菱形和如上图所示。
 * - 蓝色：4 + 2 + 6 + 8 = 20
 * - 红色：9 （右下角红色的面积为 0 的菱形）
 * - 绿色：8 （下方中央面积为 0 的菱形）
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：grid = [[7,7,7]]
 * 输出：[7]
 * 解释：所有三个可能的菱形和都相同，所以返回 [7] 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int first = 0, second = 0, third = 0;
        int m = grid.length, n = grid[0].length;

        int[][][] prefixSums = new int[m + 1][n + 1][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixSums[i + 1][j + 1][0] = prefixSums[i][j][0] + grid[i][j];
                prefixSums[i + 1][j][1] = prefixSums[i][j + 1][1] + grid[i][j];
            }
        }

        int maxSide = (Math.min(m, n) - 1) / 2;
        for (int side = 0; side <= maxSide; side++) {
            int minTopRow = 0, maxTopRow = m - side * 2 - 1;
            int minTopCol = side, maxTopCol = n - side - 1;

            for (int topRow = minTopRow; topRow <= maxTopRow; topRow++) {
                for (int topCol = minTopCol; topCol <= maxTopCol; topCol++) {
                    int rhombusSum = side == 0 ? grid[topRow][topCol] : getRhombusSum(prefixSums, side, topRow, topCol);
                    if (rhombusSum > first) {
                        third = second;
                        second = first;
                        first = rhombusSum;
                    } else if (rhombusSum < first && rhombusSum > second) {
                        third = second;
                        second = rhombusSum;
                    } else if (rhombusSum < second && rhombusSum > third)
                        third = rhombusSum;
                }
            }
        }

        if (third > 0)
            return new int[] { first, second, third };
        else if (second > 0)
            return new int[] { first, second };
        else
            return new int[] { first };
    }

    private int getRhombusSum(int[][][] prefixSums, int side, int topRow, int topCol) {
        int upperRight = getRhombusSideSum(prefixSums, side, 1, topRow, topCol);
        int lowerRight = getRhombusSideSum(prefixSums, side, -1, topRow + side, topCol + side);
        int lowerLeft = getRhombusSideSum(prefixSums, side, 1, topRow + side + 1, topCol - side + 1);
        int upperLeft = getRhombusSideSum(prefixSums, side, -1, topRow + 1, topCol - 1);
        return upperRight + lowerRight + lowerLeft + upperLeft;
    }

    private int getRhombusSideSum(int[][][] prefixSums, int side, int direction, int startRow, int startCol) {
        int index = (1 - direction) / 2;
        return prefixSums[startRow + side][startCol + side * direction + index][index]
                - prefixSums[startRow][startCol + index][index];
    }
}
// @lc code=end

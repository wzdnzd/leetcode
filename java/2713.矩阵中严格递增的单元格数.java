/*
 * @lc app=leetcode.cn id=2713 lang=java
 *
 * [2713] 矩阵中严格递增的单元格数
 *
 * https://leetcode.cn/problems/maximum-strictly-increasing-cells-in-a-matrix/description/
 *
 * algorithms
 * Hard (36.33%)
 * Likes:    47
 * Dislikes: 0
 * Total Accepted:    4K
 * Total Submissions: 9.8K
 * Testcase Example:  '[[3,1],[3,4]]'
 *
 * 给你一个下标从 1 开始、大小为 m x n 的整数矩阵 mat，你可以选择任一单元格作为 起始单元格 。
 * 
 * 从起始单元格出发，你可以移动到 同一行或同一列 中的任何其他单元格，但前提是目标单元格的值 严格大于 当前单元格的值。
 * 
 * 你可以多次重复这一过程，从一个单元格移动到另一个单元格，直到无法再进行任何移动。
 * 
 * 请你找出从某个单元开始访问矩阵所能访问的 单元格的最大数量 。
 * 
 * 返回一个表示可访问单元格最大数量的整数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：mat = [[3,1],[3,4]]
 * 输出：2
 * 解释：上图展示了从第 1 行、第 2 列的单元格开始，可以访问 2 个单元格。可以证明，无论从哪个单元格开始，最多只能访问 2 个单元格，因此答案是 2
 * 。 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 输入：mat = [[1,1],[1,1]]
 * 输出：1
 * 解释：由于目标单元格必须严格大于当前单元格，在本示例中只能访问 1 个单元格。 
 * 
 * 
 * 示例 3：
 * 
 * 
 * 
 * 输入：mat = [[3,1,6],[-9,5,7]]
 * 输出：4
 * 解释：上图展示了从第 2 行、第 1 列的单元格开始，可以访问 4 个单元格。可以证明，无论从哪个单元格开始，最多只能访问 4 个单元格，因此答案是 4
 * 。  
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == mat.length 
 * n == mat[i].length 
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * -10^5 <= mat[i][j] <= 10^5
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length, n = mat[0].length, maxIncreasing = 1;
        Map<Integer, List<int[]>> numPositions = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                numPositions.putIfAbsent(mat[i][j], new ArrayList<>());
                numPositions.get(mat[i][j]).add(new int[] { i, j });
            }
        }

        List<Integer> nums = new ArrayList<>(numPositions.keySet());
        Collections.sort(nums);

        int[] rowsMax = new int[m], colsMax = new int[n];
        for (int num : nums) {
            List<Integer> maxValues = new ArrayList<>();
            List<int[]> positions = numPositions.get(num);

            for (int[] position : positions) {
                int row = position[0], col = position[1];
                int maxValue = Math.max(rowsMax[row], colsMax[col]) + 1;

                maxValues.add(maxValue);
                maxIncreasing = Math.max(maxIncreasing, maxValue);
            }

            int size = positions.size();
            for (int i = 0; i < size; i++) {
                int[] position = positions.get(i);
                int row = position[0], col = position[1], maxValue = maxValues.get(i);

                rowsMax[row] = Math.max(rowsMax[row], maxValue);
                colsMax[col] = Math.max(colsMax[col], maxValue);
            }
        }

        return maxIncreasing;
    }
}
// @lc code=end

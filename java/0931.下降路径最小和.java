import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=931 lang=java
 *
 * [931] 下降路径最小和
 *
 * https://leetcode.cn/problems/minimum-falling-path-sum/description/
 *
 * algorithms
 * Medium (67.18%)
 * Likes:    195
 * Dislikes: 0
 * Total Accepted:    47K
 * Total Submissions: 70K
 * Testcase Example:  '[[2,1,3],[6,5,4],[7,8,9]]'
 *
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * 
 * 下降路径
 * 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置
 * (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1)
 * 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * 输出：13
 * 解释：如图所示，为和最小的两条下降路径
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：matrix = [[-19,57],[-40,-5]]
 * 输出：-59
 * 解释：如图所示，为和最小的下降路径
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int[] directions = { -1, 0, 1 };
        int length = matrix.length, ans = Integer.MAX_VALUE;
        int[] memo = Arrays.copyOf(matrix[0], length);
        int[] dp = new int[length];

        for (int i = 1; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int minVal = Integer.MAX_VALUE;
                for (int direction : directions) {
                    int col = j + direction;
                    if (col < 0 || col >= length)
                        continue;
                    minVal = Math.min(minVal, memo[col]);
                }

                dp[j] = minVal + matrix[i][j];
            }

            System.arraycopy(dp, 0, memo, 0, length);
        }

        for (int num : memo)
            ans = Math.min(ans, num);

        return ans;
    }
}
// @lc code=end

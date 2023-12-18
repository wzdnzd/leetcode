/*
 * @lc app=leetcode.cn id=1351 lang=java
 *
 * [1351] 统计有序矩阵中的负数
 *
 * https://leetcode.cn/problems/count-negative-numbers-in-a-sorted-matrix/description/
 *
 * algorithms
 * Easy (74.31%)
 * Likes:    163
 * Dislikes: 0
 * Total Accepted:    56.5K
 * Total Submissions: 76K
 * Testcase Example:  '[[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]'
 *
 * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。 请你统计并返回 grid 中 负数 的数目。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * 输出：8
 * 解释：矩阵中共有 8 个负数。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：grid = [[3,2],[1,0]]
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * -100 <= grid[i][j] <= 100
 * 
 * 
 * 
 * 
 * 进阶：你可以设计一个时间复杂度为 O(n + m) 的解决方案吗？
 * 
 */

// @lc code=start
class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int i = m - 1, j = 0, count = 0;

        while (i >= 0 && j < n) {
            if (grid[i][j] < 0) {
                count += n - j;
                i--;
            } else
                j++;
        }

        return count;
    }
}
// @lc code=end

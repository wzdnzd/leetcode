/*
 * @lc app=leetcode.cn id=3195 lang=java
 *
 * [3195] 包含所有 1 的最小矩形面积 I
 *
 * https://leetcode.cn/problems/find-the-minimum-area-to-cover-all-ones-i/description/
 *
 * algorithms
 * Medium (79.62%)
 * Likes:    4
 * Dislikes: 0
 * Total Accepted:    9.4K
 * Total Submissions: 11.5K
 * Testcase Example:  '[[0,1,0],[1,0,1]]'
 *
 * 给你一个二维 二进制 数组 grid。请你找出一个边在水平方向和竖直方向上、面积 最小 的矩形，并且满足 grid 中所有的 1 都在矩形的内部。
 * 
 * 返回这个矩形可能的 最小 面积。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入： grid = [[0,1,0],[1,0,1]]
 * 
 * 输出： 6
 * 
 * 解释：
 * 
 * 
 * 
 * 这个最小矩形的高度为 2，宽度为 3，因此面积为 2 * 3 = 6。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入： grid = [[0,0],[1,0]]
 * 
 * 输出： 1
 * 
 * 解释：
 * 
 * 
 * 
 * 这个最小矩形的高度和宽度都是 1，因此面积为 1 * 1 = 1。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= grid.length, grid[i].length <= 1000
 * grid[i][j] 是 0 或 1。
 * 输入保证 grid 中至少有一个 1 。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumArea(int[][] grid) {
        int start = Integer.MAX_VALUE, end = Integer.MIN_VALUE, left = Integer.MAX_VALUE,
                right = Integer.MIN_VALUE;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    start = Math.min(start, i);
                    end = Math.max(end, i);

                    left = Math.min(left, j);
                    right = Math.max(right, j);
                }
            }
        }

        return (end - start + 1) * (right - left + 1);
    }
}
// @lc code=end

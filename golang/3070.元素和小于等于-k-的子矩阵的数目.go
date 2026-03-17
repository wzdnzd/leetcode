/*
 * @lc app=leetcode.cn id=3070 lang=golang
 *
 * [3070] 元素和小于等于 k 的子矩阵的数目
 *
 * https://leetcode.cn/problems/count-submatrices-with-top-left-element-and-sum-less-than-k/description/
 *
 * algorithms
 * Medium (64.23%)
 * Likes:    11
 * Dislikes: 0
 * Total Accepted:    11K
 * Total Submissions: 16.3K
 * Testcase Example:  '[[7,6,3],[6,6,1]]\n18'
 *
 * 给你一个下标从 0 开始的整数矩阵 grid 和一个整数 k。
 *
 * 返回包含 grid 左上角元素、元素和小于或等于 k 的 子矩阵的数目。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[7,6,3],[6,6,1]], k = 18
 * 输出：4
 * 解释：如上图所示，只有 4 个子矩阵满足：包含 grid 的左上角元素，并且元素和小于或等于 18 。
 *
 * 示例 2：
 *
 *
 * 输入：grid = [[7,2,9],[1,5,0],[2,6,6]], k = 20
 * 输出：6
 * 解释：如上图所示，只有 6 个子矩阵满足：包含 grid 的左上角元素，并且元素和小于或等于 20 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= n, m <= 1000
 * 0 <= grid[i][j] <= 1000
 * 1 <= k <= 10^9
 *
 *
 */

// @lc code=start
package main

func countSubmatrices(grid [][]int, k int) int {
	records := make([]int, len(grid[0]))
	count := 0
	for _, row := range grid {
		sum := 0

		for idx, x := range row {
			records[idx] += x
			sum += records[idx]

			if sum > k {
				break
			}

			count++
		}
	}

	return count
}

// @lc code=end

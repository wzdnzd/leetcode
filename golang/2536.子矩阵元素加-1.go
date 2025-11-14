/*
 * @lc app=leetcode.cn id=2536 lang=golang
 *
 * [2536] 子矩阵元素加 1
 *
 * https://leetcode.cn/problems/increment-submatrices-by-one/description/
 *
 * algorithms
 * Medium (66.10%)
 * Likes:    56
 * Dislikes: 0
 * Total Accepted:    11.8K
 * Total Submissions: 17.4K
 * Testcase Example:  '3\n[[1,1,2,2],[0,0,1,1]]'
 *
 * 给你一个正整数 n ，表示最初有一个 n x n 、下标从 0 开始的整数矩阵 mat ，矩阵中填满了 0 。
 *
 * 另给你一个二维整数数组 query 。针对每个查询 query[i] = [row1i, col1i, row2i, col2i]
 * ，请你执行下述操作：
 *
 *
 * 找出 左上角 为 (row1i, col1i) 且 右下角 为 (row2i, col2i) 的子矩阵，将子矩阵中的 每个元素 加 1
 * 。也就是给所有满足 row1i <= x <= row2i 和 col1i <= y <= col2i 的 mat[x][y] 加 1 。
 *
 *
 * 返回执行完所有操作后得到的矩阵 mat 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入：n = 3, queries = [[1,1,2,2],[0,0,1,1]]
 * 输出：[[1,1,0],[1,2,1],[0,1,1]]
 * 解释：上图所展示的分别是：初始矩阵、执行完第一个操作后的矩阵、执行完第二个操作后的矩阵。
 * - 第一个操作：将左上角为 (1, 1) 且右下角为 (2, 2) 的子矩阵中的每个元素加 1 。
 * - 第二个操作：将左上角为 (0, 0) 且右下角为 (1, 1) 的子矩阵中的每个元素加 1 。
 *
 *
 * 示例 2：
 *
 *
 *
 *
 * 输入：n = 2, queries = [[0,0,1,1]]
 * 输出：[[1,1],[1,1]]
 * 解释：上图所展示的分别是：初始矩阵、执行完第一个操作后的矩阵。
 * - 第一个操作：将矩阵中的每个元素加 1 。
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n <= 500
 * 1 <= queries.length <= 10^4
 * 0 <= row1i <= row2i < n
 * 0 <= col1i <= col2i < n
 *
 *
 */

// @lc code=start
package main

func rangeAddQueries(n int, queries [][]int) [][]int {
	matrix := make([][]int, n)
	for i := 0; i < n; i++ {
		matrix[i] = make([]int, n)
	}

	for _, query := range queries {
		for i := query[0]; i <= query[2]; i++ {
			for j := query[1]; j <= query[3]; j++ {
				matrix[i][j]++
			}
		}
	}

	return matrix
}

// @lc code=end

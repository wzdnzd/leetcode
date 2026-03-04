/*
 * @lc app=leetcode.cn id=1582 lang=golang
 *
 * [1582] 二进制矩阵中的特殊位置
 *
 * https://leetcode.cn/problems/special-positions-in-a-binary-matrix/description/
 *
 * algorithms
 * Easy (69.26%)
 * Likes:    111
 * Dislikes: 0
 * Total Accepted:    47.4K
 * Total Submissions: 68.1K
 * Testcase Example:  '[[1,0,0],[0,0,1],[1,0,0]]'
 *
 * 给定一个 m x n 的二进制矩阵 mat，返回矩阵 mat 中特殊位置的数量。
 *
 * 如果位置 (i, j) 满足 mat[i][j] == 1 并且行 i 与列 j 中的所有其他元素都是 0（行和列的下标从 0 开始计数），那么它被称为
 * 特殊 位置。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：mat = [[1,0,0],[0,0,1],[1,0,0]]
 * 输出：1
 * 解释：位置 (1, 2) 是一个特殊位置，因为 mat[1][2] == 1 且第 1 行和第 2 列的其他所有元素都是 0。
 *
 *
 * 示例 2：
 *
 *
 * 输入：mat = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 * 解释：位置 (0, 0)，(1, 1) 和 (2, 2) 都是特殊位置。
 *
 *
 *
 *
 * 提示：
 *
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * mat[i][j] 是 0 或 1。
 *
 *
 */

// @lc code=start
package main

import "slices"

func numSpecial(mat [][]int) int {
	count := 0

	for _, row := range mat {
		rowSum := 0
		for _, x := range row {
			rowSum += x
		}

		if rowSum != 1 {
			continue
		}

		j := slices.Index(row, 1)
		colSum := 0
		for _, arr := range mat {
			colSum += arr[j]
		}

		if colSum == 1 {
			count++
		}
	}

	return count
}

// @lc code=end

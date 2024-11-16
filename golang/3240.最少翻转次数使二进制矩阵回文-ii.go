/*
 * @lc app=leetcode.cn id=3240 lang=golang
 *
 * [3240] 最少翻转次数使二进制矩阵回文 II
 *
 * https://leetcode.cn/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-ii/description/
 *
 * algorithms
 * Medium (30.86%)
 * Likes:    18
 * Dislikes: 0
 * Total Accepted:    3.2K
 * Total Submissions: 7.9K
 * Testcase Example:  '[[1,0,0],[0,1,0],[0,0,1]]'
 *
 * 给你一个 m x n 的二进制矩阵 grid 。
 *
 * 如果矩阵中一行或者一列从前往后与从后往前读是一样的，那么我们称这一行或者这一列是 回文 的。
 *
 * 你可以将 grid 中任意格子的值 翻转 ，也就是将格子里的值从 0 变成 1 ，或者从 1 变成 0 。
 *
 * 请你返回 最少 翻转次数，使得矩阵中 所有 行和列都是 回文的 ，且矩阵中 1 的数目可以被 4 整除 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[1,0,0],[0,1,0],[0,0,1]]
 *
 * 输出：3
 *
 * 解释：
 *
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入：grid = [[0,1],[0,1],[0,0]]
 *
 * 输出：2
 *
 * 解释：
 *
 *
 *
 *
 * 示例 3：
 *
 *
 * 输入：grid = [[1],[1]]
 *
 * 输出：2
 *
 * 解释：
 *
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m * n <= 2 * 10^5
 * 0 <= grid[i][j] <= 1
 *
 *
 */

// @lc code=start
package main

func minFlips(grid [][]int) int {
	m, n, result := len(grid), len(grid[0]), 0
	for i := 0; i < m/2; i++ {
		for j := 0; j < n/2; j++ {
			k := grid[i][j] + grid[i][n-1-j] +
				grid[m-1-i][j] + grid[m-1-i][n-1-j]

			result += min(k, 4-k)
		}
	}

	diff, count := 0, 0
	if m%2 == 1 {
		for j := 0; j < n/2; j++ {
			if grid[m/2][j]^grid[m/2][n-1-j] != 0 {
				diff++
			} else {
				count += grid[m/2][j] * 2
			}
		}
	}

	if n%2 == 1 {
		for i := 0; i < m/2; i++ {
			if grid[i][n/2]^grid[m-1-i][n/2] != 0 {
				diff++
			} else {
				count += grid[i][n/2] * 2
			}
		}
	}

	if m%2 == 1 && n%2 == 1 {
		result += grid[m/2][n/2]
	}

	if diff > 0 {
		result += diff
	} else {
		result += count % 4
	}

	return result
}

// @lc code=end

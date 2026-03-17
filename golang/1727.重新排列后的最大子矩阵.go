/*
 * @lc app=leetcode.cn id=1727 lang=golang
 *
 * [1727] 重新排列后的最大子矩阵
 *
 * https://leetcode.cn/problems/largest-submatrix-with-rearrangements/description/
 *
 * algorithms
 * Medium (62.10%)
 * Likes:    102
 * Dislikes: 0
 * Total Accepted:    7.6K
 * Total Submissions: 11.7K
 * Testcase Example:  '[[0,0,1],[1,1,1],[1,0,1]]'
 *
 * 给你一个二进制矩阵 matrix ，它的大小为 m x n ，你可以将 matrix 中的 列 按任意顺序重新排列。
 *
 * 请你返回最优方案下将 matrix 重新排列后，全是 1 的子矩阵面积。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入：matrix = [[0,0,1],[1,1,1],[1,0,1]]
 * 输出：4
 * 解释：你可以按照上图方式重新排列矩阵的每一列。
 * 最大的全 1 子矩阵是上图中加粗的部分，面积为 4 。
 *
 *
 * 示例 2：
 *
 *
 *
 *
 * 输入：matrix = [[1,0,1,0,1]]
 * 输出：3
 * 解释：你可以按照上图方式重新排列矩阵的每一列。
 * 最大的全 1 子矩阵是上图中加粗的部分，面积为 3 。
 *
 *
 * 示例 3：
 *
 *
 * 输入：matrix = [[1,1,0],[1,0,1]]
 * 输出：2
 * 解释：由于你只能整列整列重新排布，所以没有比面积为 2 更大的全 1 子矩形。
 *
 * 示例 4：
 *
 *
 * 输入：matrix = [[0,0],[0,0]]
 * 输出：0
 * 解释：由于矩阵中没有 1 ，没有任何全 1 的子矩阵，所以面积为 0 。
 *
 *
 *
 * 提示：
 *
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1
 * matrix[i][j] 要么是 0 ，要么是 1 。
 *
 *
 */

// @lc code=start
package main

import "sort"

func largestSubmatrix(matrix [][]int) int {
	m, n, maxArea := len(matrix), len(matrix[0]), 0

	for i := 1; i < m; i++ {
		for j := 0; j < n; j++ {
			if matrix[i][j] == 1 {
				matrix[i][j] += matrix[i-1][j]
			}
		}
	}

	for i := 0; i < m; i++ {
		sort.Slice(matrix[i], func(a, b int) bool {
			return matrix[i][a] > matrix[i][b]
		})

		for j := 0; j < n; j++ {
			maxArea = max(maxArea, (j+1)*matrix[i][j])
		}
	}

	return maxArea
}

// @lc code=end

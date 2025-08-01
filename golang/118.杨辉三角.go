/*
 * @lc app=leetcode.cn id=118 lang=golang
 *
 * [118] 杨辉三角
 *
 * https://leetcode.cn/problems/pascals-triangle/description/
 *
 * algorithms
 * Easy (77.62%)
 * Likes:    1275
 * Dislikes: 0
 * Total Accepted:    697.4K
 * Total Submissions: 896.3K
 * Testcase Example:  '5'
 *
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 *
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 *
 * 示例 2:
 *
 *
 * 输入: numRows = 1
 * 输出: [[1]]
 *
 *
 *
 *
 * 提示:
 *
 *
 * 1
 *
 *
 */

// @lc code=start
package main

func generate(numRows int) [][]int {
	results := make([][]int, numRows)

	for i := 0; i < numRows; i++ {
		results[i] = make([]int, i+1)
		results[i][0] = 1
		results[i][i] = 1

		for j := 1; j < i; j++ {
			results[i][j] = results[i-1][j-1] + results[i-1][j]
		}
	}

	return results
}

// @lc code=end

/*
 * @lc app=leetcode.cn id=85 lang=golang
 *
 * [85] 最大矩形
 *
 * https://leetcode.cn/problems/maximal-rectangle/description/
 *
 * algorithms
 * Hard (56.09%)
 * Likes:    1782
 * Dislikes: 0
 * Total Accepted:    232.2K
 * Total Submissions: 412.4K
 * Testcase Example:  '[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]'
 *
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix =
 * [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 *
 *
 * 示例 2：
 *
 *
 * 输入：matrix = [["0"]]
 * 输出：0
 *
 *
 * 示例 3：
 *
 *
 * 输入：matrix = [["1"]]
 * 输出：1
 *
 *
 *
 *
 * 提示：
 *
 *
 * rows == matrix.length
 * cols == matrix[0].length
 * 1 <= rows, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 *
 *
 */

// @lc code=start
package main

func largestRectangleArea(heights []int) int {
	area := 0
	array := []int{-1}

	for right, height := range heights {
		for len(array) > 1 && heights[array[len(array)-1]] >= height {
			i := array[len(array)-1]
			array = array[:len(array)-1]
			left := array[len(array)-1]
			area = max(area, heights[i]*(right-left-1))
		}

		array = append(array, right)
	}

	return area
}

func maximalRectangle(matrix [][]byte) int {
	area := 0
	heights := make([]int, len(matrix[0])+1)
	for _, row := range matrix {
		for j, c := range row {
			if c == '0' {
				heights[j] = 0
			} else {
				heights[j]++
			}
		}

		area = max(area, largestRectangleArea(heights))
	}

	return area
}

// @lc code=end

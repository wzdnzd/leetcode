/*
 * @lc app=leetcode.cn id=3200 lang=golang
 *
 * [3200] 三角形的最大高度
 *
 * https://leetcode.cn/problems/maximum-height-of-a-triangle/description/
 *
 * algorithms
 * Easy (52.47%)
 * Likes:    9
 * Dislikes: 0
 * Total Accepted:    7.5K
 * Total Submissions: 13.5K
 * Testcase Example:  '2\n4'
 *
 * 给你两个整数 red 和 blue，分别表示红色球和蓝色球的数量。你需要使用这些球来组成一个三角形，满足第 1 行有 1 个球，第 2 行有 2
 * 个球，第 3 行有 3 个球，依此类推。
 *
 * 每一行的球必须是 相同 颜色，且相邻行的颜色必须 不同。
 *
 * 返回可以实现的三角形的 最大 高度。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： red = 2, blue = 4
 *
 * 输出： 3
 *
 * 解释：
 *
 *
 *
 * 上图显示了唯一可能的排列方式。
 *
 *
 * 示例 2：
 *
 *
 * 输入： red = 2, blue = 1
 *
 * 输出： 2
 *
 * 解释：
 *
 *
 * 上图显示了唯一可能的排列方式。
 *
 *
 * 示例 3：
 *
 *
 * 输入： red = 1, blue = 1
 *
 * 输出： 1
 *
 *
 * 示例 4：
 *
 *
 * 输入： red = 10, blue = 1
 *
 * 输出： 2
 *
 * 解释：
 *
 *
 * 上图显示了唯一可能的排列方式。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= red, blue <= 100
 *
 *
 */

// @lc code=start
package main

import "math"

func maxHeightOfTriangle(red int, blue int) int {
	return max(maxHeight(red, blue), maxHeight(blue, red))
}

func maxHeight(x, y int) int {
	odd := 2*int(math.Sqrt(float64(x))) - 1
	even := 2 * int((-1+math.Sqrt(1+4*float64(y)))/2)

	return min(odd, even) + 1
}

// @lc code=end

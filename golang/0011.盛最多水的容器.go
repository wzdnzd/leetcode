/*
 * @lc app=leetcode.cn id=11 lang=golang
 *
 * [11] 盛最多水的容器
 *
 * https://leetcode-cn.com/problems/container-with-most-water/description/
 *
 * algorithms
 * Medium (61.74%)
 * Likes:    3374
 * Dislikes: 0
 * Total Accepted:    687.7K
 * Total Submissions: 1.1M
 * Testcase Example:  '[1,8,6,2,5,4,8,3,7]'
 *
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例 2：
 *
 *
 * 输入：height = [1,1]
 * 输出：1
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 *
 *
 */

// @lc code=start
package main

func maxArea(height []int) int {
	if len(height) == 0 {
		panic("illega argument, height cannot be empty")
	}

	maxValue := 0
	for i, j := 0, len(height)-1; i < j; {
		minHeight := height[i]
		if minHeight > height[j] {
			minHeight = height[j]
			j--
		} else {
			i++
		}

		area := minHeight * (j - i + 1)
		maxValue = max(maxValue, area)
	}

	return maxValue
}

func max(n int, m int) int {
	if n > m {
		return n
	}

	return m
}

// @lc code=end

/*
 * @lc app=leetcode.cn id=628 lang=golang
 *
 * [628] 三个数的最大乘积
 *
 * https://leetcode.cn/problems/maximum-product-of-three-numbers/description/
 *
 * algorithms
 * Easy (51.84%)
 * Likes:    511
 * Dislikes: 0
 * Total Accepted:    155K
 * Total Submissions: 298.2K
 * Testcase Example:  '[1,2,3]'
 *
 * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,2,3]
 * 输出：6
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [1,2,3,4]
 * 输出：24
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [-1,-2,-3]
 * 输出：-6
 *
 *
 *
 *
 * 提示：
 *
 *
 * 3
 * -1000
 *
 *
 */

// @lc code=start
package main

import "math"

func maximumProduct(nums []int) int {
	m, n := math.MaxInt64, math.MaxInt64
	x, y, z := math.MinInt64, math.MinInt64, math.MinInt64

	for _, num := range nums {
		if num < m {
			n = m
			m = num
		} else if num < n {
			n = num
		}

		if num > x {
			z = y
			y = x
			x = num
		} else if num > y {
			z = y
			y = num
		} else if num > z {
			z = num
		}
	}

	return max(m*n*x, x*y*z)
}

func max(x, y int) int {
	if x > y {
		return x
	}

	return y
}

// @lc code=end

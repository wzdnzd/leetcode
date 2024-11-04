/*
 * @lc app=leetcode.cn id=633 lang=golang
 *
 * [633] 平方数之和
 *
 * https://leetcode.cn/problems/sum-of-square-numbers/description/
 *
 * algorithms
 * Medium (37.98%)
 * Likes:    470
 * Dislikes: 0
 * Total Accepted:    146.6K
 * Total Submissions: 384.6K
 * Testcase Example:  '5'
 *
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 *
 *
 * 示例 2：
 *
 *
 * 输入：c = 3
 * 输出：false
 *
 *
 *
 *
 * 提示：
 *
 *
 * 0 <= c <= 2^31 - 1
 *
 *
 */

// @lc code=start
package main

import "math"

func judgeSquareSum(c int) bool {
	a, b := 0, int(math.Sqrt(float64(c)))
	for a <= b {
		x := a*a + b*b
		if x == c {
			return true
		} else if x < c {
			a++
		} else {
			b--
		}
	}

	return false
}

// @lc code=end

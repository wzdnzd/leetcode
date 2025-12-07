/*
 * @lc app=leetcode.cn id=1925 lang=golang
 *
 * [1925] 统计平方和三元组的数目
 *
 * https://leetcode.cn/problems/count-square-sum-triples/description/
 *
 * algorithms
 * Easy (69.05%)
 * Likes:    27
 * Dislikes: 0
 * Total Accepted:    19.7K
 * Total Submissions: 28.5K
 * Testcase Example:  '5'
 *
 * 一个 平方和三元组 (a,b,c) 指的是满足 a^2 + b^2 = c^2 的 整数 三元组 a，b 和 c 。
 *
 * 给你一个整数 n ，请你返回满足 1 <= a, b, c <= n 的 平方和三元组 的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 5
 * 输出：2
 * 解释：平方和三元组为 (3,4,5) 和 (4,3,5) 。
 *
 *
 * 示例 2：
 *
 * 输入：n = 10
 * 输出：4
 * 解释：平方和三元组为 (3,4,5)，(4,3,5)，(6,8,10) 和 (8,6,10) 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n <= 250
 *
 *
 */

// @lc code=start
package main

import "math"

func countTriples(n int) int {
	count := 0
	for a := 1; a < n; a++ {
		for b := 1; b < a && a*a+b*b <= n*n; b++ {
			num := a*a + b*b
			c := int(math.Sqrt(float64(num)))
			if c*c == num {
				count++
			}
		}
	}

	return count * 2
}

// @lc code=end

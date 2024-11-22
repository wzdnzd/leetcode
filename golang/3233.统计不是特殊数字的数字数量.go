/*
 * @lc app=leetcode.cn id=3233 lang=golang
 *
 * [3233] 统计不是特殊数字的数字数量
 *
 * https://leetcode.cn/problems/find-the-count-of-numbers-which-are-not-special/description/
 *
 * algorithms
 * Medium (29.47%)
 * Likes:    8
 * Dislikes: 0
 * Total Accepted:    6.6K
 * Total Submissions: 19.9K
 * Testcase Example:  '5\n7'
 *
 * 给你两个 正整数 l 和 r。对于任何数字 x，x 的所有正因数（除了 x 本身）被称为 x 的 真因数。
 *
 * 如果一个数字恰好仅有两个 真因数，则称该数字为 特殊数字。例如：
 *
 *
 * 数字 4 是 特殊数字，因为它的真因数为 1 和 2。
 * 数字 6 不是 特殊数字，因为它的真因数为 1、2 和 3。
 *
 *
 * 返回区间 [l, r] 内 不是 特殊数字 的数字数量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： l = 5, r = 7
 *
 * 输出： 3
 *
 * 解释：
 *
 * 区间 [5, 7] 内不存在特殊数字。
 *
 *
 * 示例 2：
 *
 *
 * 输入： l = 4, r = 16
 *
 * 输出： 11
 *
 * 解释：
 *
 * 区间 [4, 16] 内的特殊数字为 4 和 9。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= l <= r <= 10^9
 *
 *
 */

// @lc code=start
package main

import "math"

func nonSpecialCount(l int, r int) int {
	n := int(math.Sqrt(float64(r)))
	visited := make([]bool, n+1)
	count := r - l + 1

	for i := 2; i <= n; i++ {
		if !visited[i] {
			if i*i >= l && i*i <= r {
				count--
			}

			for j := i * 2; j <= n; j += i {
				visited[j] = true
			}
		}
	}

	return count
}

// @lc code=end

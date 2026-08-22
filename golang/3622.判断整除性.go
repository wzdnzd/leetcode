/*
 * @lc app=leetcode.cn id=3622 lang=golang
 *
 * [3622] 判断整除性
 *
 * https://leetcode.cn/problems/check-divisibility-by-digit-sum-and-product/description/
 *
 * algorithms
 * Easy (65.72%)
 * Likes:    1
 * Dislikes: 0
 * Total Accepted:    5.7K
 * Total Submissions: 8.2K
 * Testcase Example:  '99'
 *
 * 给你一个正整数 n。请判断 n 是否可以被以下两值之和 整除：
 *
 *
 *
 * n 的 数字和（即其各个位数之和）。
 *
 *
 * n 的 数字积（即其各个位数之积）。
 *
 *
 *
 * 如果 n 能被该和整除，返回 true；否则，返回 false。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： n = 99
 *
 * 输出： true
 *
 * 解释：
 *
 * 因为 99 可以被其数字和 (9 + 9 = 18) 与数字积 (9 * 9 = 81) 之和 (18 + 81 = 99) 整除，因此输出为
 * true。
 *
 *
 * 示例 2：
 *
 *
 * 输入： n = 23
 *
 * 输出： false
 *
 * 解释：
 *
 * 因为 23 无法被其数字和 (2 + 3 = 5) 与数字积 (2 * 3 = 6) 之和 (5 + 6 = 11) 整除，因此输出为
 * false。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n <= 10^6
 *
 *
 */

// @lc code=start
package main

func checkDivisibility(n int) bool {
	num, sum, product := n, 0, 1
	for ; num != 0; num /= 10 {
		digit := num % 10
		sum += digit
		product *= digit
	}

	return n%(sum+product) == 0
}

// @lc code=end

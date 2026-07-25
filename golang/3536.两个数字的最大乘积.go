/*
 * @lc app=leetcode.cn id=3536 lang=golang
 *
 * [3536] 两个数字的最大乘积
 *
 * https://leetcode.cn/problems/maximum-product-of-two-digits/description/
 *
 * algorithms
 * Easy (78.99%)
 * Likes:    5
 * Dislikes: 0
 * Total Accepted:    6.7K
 * Total Submissions: 8.2K
 * Testcase Example:  '31'
 *
 * 给定一个正整数 n。
 *
 * 返回 任意两位数字 相乘所得的 最大 乘积。
 *
 * 注意：如果某个数字在 n 中出现多次，你可以多次使用该数字。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： n = 31
 *
 * 输出： 3
 *
 * 解释：
 *
 *
 * n 的数字是 [3, 1]。
 * 任意两位数字相乘的结果为：3 * 1 = 3。
 * 最大乘积为 3。
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入： n = 22
 *
 * 输出： 4
 *
 * 解释：
 *
 *
 * n 的数字是 [2, 2]。
 * 任意两位数字相乘的结果为：2 * 2 = 4。
 * 最大乘积为 4。
 *
 *
 *
 * 示例 3：
 *
 *
 * 输入： n = 124
 *
 * 输出： 8
 *
 * 解释：
 *
 *
 * n 的数字是 [1, 2, 4]。
 * 任意两位数字相乘的结果为：1 * 2 = 2, 1 * 4 = 4, 2 * 4 = 8。
 * 最大乘积为 8。
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 10 <= n <= 10^9
 *
 *
 */

// @lc code=start
package main

func maxProduct(n int) int {
	x, y := 0, 0

	for ; n > 0; n /= 10 {
		digit := n % 10

		if digit > x {
			y = x
			x = digit
		} else if digit > y {
			y = digit
		}
	}

	return x * y
}

// @lc code=end

/*
 * @lc app=leetcode.cn id=166 lang=golang
 *
 * [166] 分数到小数
 *
 * https://leetcode.cn/problems/fraction-to-recurring-decimal/description/
 *
 * algorithms
 * Medium (33.70%)
 * Likes:    520
 * Dislikes: 0
 * Total Accepted:    77.4K
 * Total Submissions: 228.5K
 * Testcase Example:  '1\n2'
 *
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 *
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 *
 * 如果存在多个答案，只需返回 任意一个 。
 *
 * 对于所有给定的输入，保证 答案字符串的长度小于 10^4 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：numerator = 1, denominator = 2
 * 输出："0.5"
 *
 *
 * 示例 2：
 *
 *
 * 输入：numerator = 2, denominator = 1
 * 输出："2"
 *
 *
 * 示例 3：
 *
 *
 * 输入：numerator = 4, denominator = 333
 * 输出："0.(012)"
 *
 *
 *
 *
 * 提示：
 *
 *
 * -2^31 <= numerator, denominator <= 2^31 - 1
 * denominator != 0
 *
 *
 */

// @lc code=start
package main

import "strconv"

func fractionToDecimal(numerator int, denominator int) string {
	if numerator == 0 {
		return "0"
	}

	decimal := []byte{}
	if (numerator < 0) != (denominator < 0) {
		decimal = append(decimal, '-')
	}

	numerator = abs(numerator)
	denominator = abs(denominator)
	decimal = append(decimal, strconv.Itoa(numerator/denominator)...)

	numerator %= denominator
	if numerator == 0 {
		return string(decimal)
	}

	decimal = append(decimal, '.')
	indices := map[int]int{}
	index := len(decimal)

	for numerator != 0 && indices[numerator] == 0 {
		indices[numerator] = index
		numerator *= 10

		decimal = append(decimal, strconv.Itoa(numerator/denominator)...)
		numerator %= denominator
		index++
	}

	if numerator != 0 {
		prev := indices[numerator]
		decimal = append(decimal[:prev], append([]byte{'('}, decimal[prev:]...)...)
		decimal = append(decimal, ')')
	}

	return string(decimal)
}

func abs(num int) int {
	if num >= 0 {
		return num
	}

	return -num
}

// @lc code=end

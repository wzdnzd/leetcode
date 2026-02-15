/*
 * @lc app=leetcode.cn id=190 lang=golang
 *
 * [190] 颠倒二进制位
 *
 * https://leetcode.cn/problems/reverse-bits/description/
 *
 * algorithms
 * Easy (74.29%)
 * Likes:    760
 * Dislikes: 0
 * Total Accepted:    301.6K
 * Total Submissions: 405K
 * Testcase Example:  '43261596'
 *
 * 颠倒给定的 32 位有符号整数的二进制位。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 43261596
 *
 * 输出：964176192
 *
 * 解释：
 *
 *
 *
 *
 * 整数
 * 二进制
 *
 *
 * 43261596
 * 00000010100101000001111010011100
 *
 *
 * 964176192
 * 00111001011110000010100101000000
 *
 *
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入：n = 2147483644
 *
 * 输出：1073741822
 *
 * 解释：
 *
 *
 *
 *
 * 整数
 * 二进制
 *
 *
 * 2147483644
 * 01111111111111111111111111111100
 *
 *
 * 1073741822
 * 00111111111111111111111111111110
 *
 *
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 0 <= n <= 2^31 - 2
 * n 为偶数
 *
 *
 *
 *
 * 进阶: 如果多次调用这个函数，你将如何优化你的算法？
 *
 */

// @lc code=start
package main

func reverseBits(n int) int {
	result := 0

	for i := 0; i < 32 && n > 0; i++ {
		result |= n & 1 << (31 - i)
		n >>= 1
	}

	return result
}

// @lc code=end

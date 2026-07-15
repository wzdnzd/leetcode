/*
 * @lc app=leetcode.cn id=3658 lang=golang
 *
 * [3658] 奇数和与偶数和的最大公约数
 *
 * https://leetcode.cn/problems/gcd-of-odd-and-even-sums/description/
 *
 * algorithms
 * Easy (84.21%)
 * Likes:    5
 * Dislikes: 0
 * Total Accepted:    6.4K
 * Total Submissions: 7.3K
 * Testcase Example:  '4'
 *
 * 给你一个整数 n。请你计算以下两个值的 最大公约数（GCD）：
 *
 *
 *
 * sumOdd：最小的 n 个正奇数的总和。
 *
 *
 * sumEven：最小的 n 个正偶数的总和。
 *
 *
 *
 * 返回 sumOdd 和 sumEven 的 GCD。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： n = 4
 *
 * 输出： 4
 *
 * 解释：
 *
 *
 * 前 4 个奇数的总和 sumOdd = 1 + 3 + 5 + 7 = 16
 * 前 4 个偶数的总和 sumEven = 2 + 4 + 6 + 8 = 20
 *
 *
 * 因此，GCD(sumOdd, sumEven) = GCD(16, 20) = 4。
 *
 *
 * 示例 2：
 *
 *
 * 输入： n = 5
 *
 * 输出： 5
 *
 * 解释：
 *
 *
 * 前 5 个奇数的总和 sumOdd = 1 + 3 + 5 + 7 + 9 = 25
 * 前 5 个偶数的总和 sumEven = 2 + 4 + 6 + 8 + 10 = 30
 *
 *
 * 因此，GCD(sumOdd, sumEven) = GCD(25, 30) = 5。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n <= 1000
 *
 *
 */

// @lc code=start
package main

func gcdOfOddEvenSums(n int) int {
	// 等差数列求前 n 项和：
	// sumOdd = n * 1 + n * (n-1) * 2 / 2 = n * n
	// sumEven = n * 2 + n * (n-1) * 2 / 2 = n * n = n * (n + 1)
	// gcd(n*n, n*(n+1)) = n
	return n
}

// @lc code=end

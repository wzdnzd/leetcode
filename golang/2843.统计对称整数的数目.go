/*
 * @lc app=leetcode.cn id=2843 lang=golang
 *
 * [2843] 统计对称整数的数目
 *
 * https://leetcode.cn/problems/count-symmetric-integers/description/
 *
 * algorithms
 * Easy (71.67%)
 * Likes:    22
 * Dislikes: 0
 * Total Accepted:    11.9K
 * Total Submissions: 16.2K
 * Testcase Example:  '1\n100'
 *
 * 给你两个正整数 low 和 high 。
 *
 * 对于一个由 2 * n 位数字组成的整数 x ，如果其前 n 位数字之和与后 n 位数字之和相等，则认为这个数字是一个对称整数。
 *
 * 返回在 [low, high] 范围内的 对称整数的数目 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：low = 1, high = 100
 * 输出：9
 * 解释：在 1 到 100 范围内共有 9 个对称整数：11、22、33、44、55、66、77、88 和 99 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：low = 1200, high = 1230
 * 输出：4
 * 解释：在 1200 到 1230 范围内共有 4 个对称整数：1203、1212、1221 和 1230 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= low <= high <= 10^4
 *
 *
 */

// @lc code=start
package main

func countSymmetricIntegers(low int, high int) int {
	count := 0
	for i := low; i <= high; i++ {
		if isSymmetric(i) {
			count++
		}
	}

	return count
}

func isSymmetric(x int) bool {
	digits := []int{}
	for x > 0 {
		digits = append(digits, x%10)
		x /= 10
	}

	n := len(digits)
	if n%2 == 1 {
		return false
	}

	leftSum, rightSum := 0, 0
	for i := 0; i < n/2; i++ {
		leftSum += digits[i]
		rightSum += digits[n-1-i]
	}

	return leftSum == rightSum
}

// @lc code=end

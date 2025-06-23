/*
 * @lc app=leetcode.cn id=2081 lang=golang
 *
 * [2081] k 镜像数字的和
 *
 * https://leetcode.cn/problems/sum-of-k-mirror-numbers/description/
 *
 * algorithms
 * Hard (44.93%)
 * Likes:    39
 * Dislikes: 0
 * Total Accepted:    6.1K
 * Total Submissions: 11.8K
 * Testcase Example:  '2\n5'
 *
 * 一个 k 镜像数字 指的是一个在十进制和 k 进制下从前往后读和从后往前读都一样的 没有前导 0 的 正 整数。
 *
 *
 * 比方说，9 是一个 2 镜像数字。9 在十进制下为 9 ，二进制下为 1001 ，两者从前往后读和从后往前读都一样。
 * 相反地，4 不是一个 2 镜像数字。4 在二进制下为 100 ，从前往后和从后往前读不相同。
 *
 *
 * 给你进制 k 和一个数字 n ，请你返回 k 镜像数字中 最小 的 n 个数 之和 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：k = 2, n = 5
 * 输出：25
 * 解释：
 * 最小的 5 个 2 镜像数字和它们的二进制表示如下：
 * ⁠ 十进制       二进制
 * ⁠   1          1
 * ⁠   3          11
 * ⁠   5          101
 * ⁠   7          111
 * ⁠   9          1001
 * 它们的和为 1 + 3 + 5 + 7 + 9 = 25 。
 *
 *
 * 示例 2：
 *
 * 输入：k = 3, n = 7
 * 输出：499
 * 解释：
 * 7 个最小的 3 镜像数字和它们的三进制表示如下：
 * ⁠ 十进制       三进制
 * ⁠   1          1
 * ⁠   2          2
 * ⁠   4          11
 * ⁠   8          22
 * ⁠   121        11111
 * ⁠   151        12121
 * ⁠   212        21212
 * 它们的和为 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499 。
 *
 *
 * 示例 3：
 *
 * 输入：k = 7, n = 17
 * 输出：20379000
 * 解释：17 个最小的 7 镜像数字分别为：
 * 1, 2, 3, 4, 5, 6, 8, 121, 171, 242, 292, 16561, 65656, 2137312, 4602064,
 * 6597956, 6958596
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= k <= 9
 * 1 <= n <= 30
 *
 *
 */

// @lc code=start
package main

func kMirror(k int, n int) int64 {
	digit := make([]int, 100)
	left, count, result := 1, 0, int64(0)

	for count < n {
		right := left * 10

		for i := 0; i < 2; i++ {
			for j := left; j < right && count < n; j++ {
				combined := int64(j)
				x := j

				if i == 0 {
					x = j / 10
				}

				for x > 0 {
					combined = combined*10 + int64(x%10)
					x /= 10
				}

				if isPalindrome(combined, k, digit) {
					count++
					result += combined
				}
			}
		}

		left = right
	}

	return result
}

func isPalindrome(x int64, k int, digit []int) bool {
	length := -1
	for x > 0 {
		length++
		digit[length] = int(x % int64(k))
		x /= int64(k)
	}

	for i, j := 0, length; i < j; i, j = i+1, j-1 {
		if digit[i] != digit[j] {
			return false
		}
	}

	return true
}

// @lc code=end

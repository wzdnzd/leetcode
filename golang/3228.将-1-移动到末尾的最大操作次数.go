/*
 * @lc app=leetcode.cn id=3228 lang=golang
 *
 * [3228] 将 1 移动到末尾的最大操作次数
 *
 * https://leetcode.cn/problems/maximum-number-of-operations-to-move-ones-to-the-end/description/
 *
 * algorithms
 * Medium (48.02%)
 * Likes:    9
 * Dislikes: 0
 * Total Accepted:    7.8K
 * Total Submissions: 15K
 * Testcase Example:  '"1001101"'
 *
 * 给你一个 二进制字符串 s。
 *
 * 你可以对这个字符串执行 任意次 下述操作：
 *
 *
 * 选择字符串中的任一下标 i（ i + 1 < s.length ），该下标满足 s[i] == '1' 且 s[i + 1] == '0'。
 * 将字符 s[i] 向 右移 直到它到达字符串的末端或另一个 '1'。例如，对于 s = "010010"，如果我们选择 i = 1，结果字符串将会是 s
 * = "000110"。
 *
 *
 * 返回你能执行的 最大 操作次数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： s = "1001101"
 *
 * 输出： 4
 *
 * 解释：
 *
 * 可以执行以下操作：
 *
 *
 * 选择下标 i = 0。结果字符串为 s = "0011101"。
 * 选择下标 i = 4。结果字符串为 s = "0011011"。
 * 选择下标 i = 3。结果字符串为 s = "0010111"。
 * 选择下标 i = 2。结果字符串为 s = "0001111"。
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入： s = "00111"
 *
 * 输出： 0
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 10^5
 * s[i] 为 '0' 或 '1'。
 *
 *
 */

// @lc code=start
package main

func maxOperations(s string) int {
	count, ones, n := 0, 0, len(s)

	for index := 0; index < n; index++ {
		if s[index] == '0' {
			for index+1 < n && s[index+1] == '0' {
				index++
			}

			count += ones
		} else {
			ones++
		}
	}

	return count
}

// @lc code=end

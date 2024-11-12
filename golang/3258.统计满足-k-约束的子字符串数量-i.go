/*
 * @lc app=leetcode.cn id=3258 lang=golang
 *
 * [3258] 统计满足 K 约束的子字符串数量 I
 *
 * https://leetcode.cn/problems/count-substrings-that-satisfy-k-constraint-i/description/
 *
 * algorithms
 * Easy (79.11%)
 * Likes:    8
 * Dislikes: 0
 * Total Accepted:    7.4K
 * Total Submissions: 9K
 * Testcase Example:  '"10101"\n1'
 *
 * 给你一个 二进制 字符串 s 和一个整数 k。
 *
 * 如果一个 二进制字符串 满足以下任一条件，则认为该字符串满足 k 约束：
 *
 *
 * 字符串中 0 的数量最多为 k。
 * 字符串中 1 的数量最多为 k。
 *
 *
 * 返回一个整数，表示 s 的所有满足 k 约束 的子字符串的数量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "10101", k = 1
 *
 * 输出：12
 *
 * 解释：
 *
 * s 的所有子字符串中，除了 "1010"、"10101" 和 "0101" 外，其余子字符串都满足 k 约束。
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "1010101", k = 2
 *
 * 输出：25
 *
 * 解释：
 *
 * s 的所有子字符串中，除了长度大于 5 的子字符串外，其余子字符串都满足 k 约束。
 *
 *
 * 示例 3：
 *
 *
 * 输入：s = "11111", k = 1
 *
 * 输出：15
 *
 * 解释：
 *
 * s 的所有子字符串都满足 k 约束。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 50
 * 1 <= k <= s.length
 * s[i] 是 '0' 或 '1'。
 *
 *
 */

// @lc code=start
package main

func countKConstraintSubstrings(s string, k int) int {
	left, result := 0, 0
	record := make([]int, 2)

	for right, char := range s {
		record[char&1]++

		for record[0] > k && record[1] > k {
			record[s[left]&1]--
			left++
		}

		result += right - left + 1
	}

	return result
}

// @lc code=end

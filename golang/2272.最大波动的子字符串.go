/*
 * @lc app=leetcode.cn id=2272 lang=golang
 *
 * [2272] 最大波动的子字符串
 *
 * https://leetcode.cn/problems/substring-with-largest-variance/description/
 *
 * algorithms
 * Hard (40.26%)
 * Likes:    81
 * Dislikes: 0
 * Total Accepted:    5.5K
 * Total Submissions: 13K
 * Testcase Example:  '"aababbb"'
 *
 * 字符串的 波动 定义为子字符串中出现次数 最多 的字符次数与出现次数 最少 的字符次数之差。
 *
 * 给你一个字符串 s ，它只包含小写英文字母。请你返回 s 里所有 子字符串的 最大波动 值。
 *
 * 子字符串 是一个字符串的一段连续字符序列。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "aababbb"
 * 输出：3
 * 解释：
 * 所有可能的波动值和它们对应的子字符串如以下所示：
 * - 波动值为 0 的子字符串："a" ，"aa" ，"ab" ，"abab" ，"aababb" ，"ba" ，"b" ，"bb" 和 "bbb" 。
 * - 波动值为 1 的子字符串："aab" ，"aba" ，"abb" ，"aabab" ，"ababb" ，"aababbb" 和 "bab" 。
 * - 波动值为 2 的子字符串："aaba" ，"ababbb" ，"abbb" 和 "babb" 。
 * - 波动值为 3 的子字符串 "babbb" 。
 * 所以，最大可能波动值为 3 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "abcde"
 * 输出：0
 * 解释：
 * s 中没有字母出现超过 1 次，所以 s 中每个子字符串的波动值都是 0 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 10^4
 * s  只包含小写英文字母。
 *
 *
 */

// @lc code=start
package main

import "math"

func largestVariance(s string) int {
	const k = 26
	var dp, record [k][k]int
	for i := range record {
		for j := range record[i] {
			record[i][j] = math.MinInt
		}
	}

	result := 0
	for _, c := range s {
		c -= 'a'
		for i := 0; i < k; i++ {
			if i == int(c) {
				continue
			}

			dp[c][i] = max(dp[c][i], 0) + 1
			record[c][i]++

			dp[i][c] = max(dp[i][c], 0) - 1
			record[i][c] = dp[i][c]
			result = max(result, max(record[c][i], record[i][c]))
		}
	}

	return result
}

// @lc code=end

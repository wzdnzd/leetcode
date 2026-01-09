/*
 * @lc app=leetcode.cn id=712 lang=golang
 *
 * [712] 两个字符串的最小ASCII删除和
 *
 * https://leetcode.cn/problems/minimum-ascii-delete-sum-for-two-strings/description/
 *
 * algorithms
 * Medium (73.00%)
 * Likes:    446
 * Dislikes: 0
 * Total Accepted:    68.2K
 * Total Submissions: 93.2K
 * Testcase Example:  '"sea"\n"eat"'
 *
 * 给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入: s1 = "sea", s2 = "eat"
 * 输出: 231
 * 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
 * 在 "eat" 中删除 "t" 并将 116 加入总和。
 * 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
 *
 *
 * 示例 2:
 *
 *
 * 输入: s1 = "delete", s2 = "leet"
 * 输出: 403
 * 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
 * 将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
 * 结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
 * 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
 *
 *
 *
 *
 * 提示:
 *
 *
 * 0 <= s1.length, s2.length <= 1000
 * s1 和 s2 由小写英文字母组成
 *
 *
 */

// @lc code=start
package main

func minimumDeleteSum(s1, s2 string) int {
	n := len(s2)

	total := 0
	for _, c := range s1 {
		total += int(c)
	}
	for _, c := range s2 {
		total += int(c)
	}

	dp := make([]int, n+1)
	for _, x := range s1 {
		prev := 0

		for j, y := range s2 {
			tmp := dp[j+1]
			if x == y {
				dp[j+1] = prev + int(x)
			} else {
				dp[j+1] = max(dp[j+1], dp[j])
			}

			prev = tmp
		}
	}

	return total - dp[n]*2
}

// @lc code=end

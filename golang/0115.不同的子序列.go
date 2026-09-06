/*
 * @lc app=leetcode.cn id=115 lang=golang
 *
 * [115] 不同的子序列
 *
 * https://leetcode.cn/problems/distinct-subsequences/description/
 *
 * algorithms
 * Hard (53.54%)
 * Likes:    1441
 * Dislikes: 0
 * Total Accepted:    263.3K
 * Total Submissions: 491.3K
 * Testcase Example:  '"rabbbit"\n"rabbit"'
 *
 * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
 *
 * 测试用例保证结果在 32 位有符号整数范围内。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * rabbbit
 * rabbbit
 *
 * 示例 2：
 *
 *
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 *
 *
 */

// @lc code=start
package main

func numDistinct(s, t string) int {
	n, m := len(s), len(t)
	if n < m {
		return 0
	}

	dp := make([][]int, n+1)
	for i := range dp {
		dp[i] = make([]int, m+1)
		dp[i][0] = 1
	}

	for i, c := range s {
		for j := max(m-n+i, 0); j < min(i+1, m); j++ {
			dp[i+1][j+1] = dp[i][j+1]
			if byte(c) == t[j] {
				dp[i+1][j+1] += dp[i][j]
			}
		}
	}

	return dp[n][m]
}

// @lc code=end

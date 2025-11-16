/*
 * @lc app=leetcode.cn id=1513 lang=golang
 *
 * [1513] 仅含 1 的子串数
 *
 * https://leetcode.cn/problems/number-of-substrings-with-only-1s/description/
 *
 * algorithms
 * Medium (41.53%)
 * Likes:    35
 * Dislikes: 0
 * Total Accepted:    19.5K
 * Total Submissions: 45.4K
 * Testcase Example:  '"0110111"'
 *
 * 给你一个二进制字符串 s（仅由 '0' 和 '1' 组成的字符串）。
 *
 * 返回所有字符都为 1 的子字符串的数目。
 *
 * 由于答案可能很大，请你将它对 10^9 + 7 取模后返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "0110111"
 * 输出：9
 * 解释：共有 9 个子字符串仅由 '1' 组成
 * "1" -> 5 次
 * "11" -> 3 次
 * "111" -> 1 次
 *
 * 示例 2：
 *
 * 输入：s = "101"
 * 输出：2
 * 解释：子字符串 "1" 在 s 中共出现 2 次
 *
 *
 * 示例 3：
 *
 * 输入：s = "111111"
 * 输出：21
 * 解释：每个子字符串都仅由 '1' 组成
 *
 *
 * 示例 4：
 *
 * 输入：s = "000"
 * 输出：0
 *
 *
 *
 *
 * 提示：
 *
 *
 * s[i] == '0' 或 s[i] == '1'
 * 1 <= s.length <= 10^5
 *
 *
 */

// @lc code=start
package main

const MOD = 1000000007

func numSub(s string) int {
	count, result := 0, 0
	accumulate := func(n int) {
		result += n * (n + 1) / 2
		result %= MOD
	}

	for _, c := range s {
		if c == '1' {
			count++
		} else {
			accumulate(count)
			count = 0
		}
	}

	accumulate(count)
	return result
}

// @lc code=end

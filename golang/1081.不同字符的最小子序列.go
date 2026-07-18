/*
 * @lc app=leetcode.cn id=1081 lang=golang
 *
 * [1081] 不同字符的最小子序列
 *
 * https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters/description/
 *
 * algorithms
 * Medium (60.41%)
 * Likes:    235
 * Dislikes: 0
 * Total Accepted:    37.7K
 * Total Submissions: 62.2K
 * Testcase Example:  '"bcabc"'
 *
 * 返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "bcabc"
 * 输出："abc"
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 *
 *
 *
 *
 * 注意：该题与 316 https://leetcode.cn/problems/remove-duplicate-letters/ 相同
 *
 */

// @lc code=start
package main

func smallestSubsequence(s string) string {
	records := ['z' + 1]int{}

	for _, c := range s {
		records[c]++
	}

	letters := []rune{}
	visited := ['z' + 1]bool{}

	for _, c := range s {
		records[c]--
		if visited[c] {
			continue
		}

		for len(letters) > 0 && c < letters[len(letters)-1] && records[letters[len(letters)-1]] > 0 {
			x := letters[len(letters)-1]
			letters = letters[:len(letters)-1]
			visited[x] = false
		}

		letters = append(letters, c)
		visited[c] = true
	}

	return string(letters)
}

// @lc code=end

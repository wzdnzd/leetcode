/*
 * @lc app=leetcode.cn id=131 lang=golang
 *
 * [131] 分割回文串
 *
 * https://leetcode.cn/problems/palindrome-partitioning/description/
 *
 * algorithms
 * Medium (74.57%)
 * Likes:    1966
 * Dislikes: 0
 * Total Accepted:    519.5K
 * Total Submissions: 695.7K
 * Testcase Example:  '"aab"'
 *
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 *
 *
 */

// @lc code=start
package main

func isPalindrome(s string, left, right int) bool {
	for left < right {
		if s[left] != s[right] {
			return false
		}

		left++
		right--
	}

	return true
}

func partition(s string) [][]string {
	n := len(s)
	path := []string{}
	result := [][]string{}

	var dfs func(int, int)
	dfs = func(current, start int) {
		if current == n {
			result = append(result, append([]string(nil), path...))
			return
		}

		if current < n-1 {
			dfs(current+1, start)
		}

		if isPalindrome(s, start, current) {
			path = append(path, s[start:current+1])
			dfs(current+1, current+1)
			path = path[:len(path)-1]
		}
	}

	dfs(0, 0)
	return result
}

// @lc code=end
